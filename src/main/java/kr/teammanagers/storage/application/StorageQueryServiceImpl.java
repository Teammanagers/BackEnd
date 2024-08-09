package kr.teammanagers.storage.application;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teammanagers.global.config.AmazonConfig;
import kr.teammanagers.global.provider.AmazonS3ProviderV2;
import kr.teammanagers.global.util.AmazonS3Helper;
import kr.teammanagers.member.domain.Member;
import kr.teammanagers.member.repository.MemberRepository;
import kr.teammanagers.storage.domain.QTeamData;
import kr.teammanagers.storage.domain.TeamData;
import kr.teammanagers.storage.dto.StorageDto;
import kr.teammanagers.storage.repository.TeamDataRepository;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class StorageQueryServiceImpl implements StorageQueryService {

    private final AmazonS3ProviderV2 s3Provider;
    private final AmazonS3Helper s3Helper;
    private final AmazonConfig s3config;
    private final TeamDataRepository teamDataRepository;
    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;
    private final Tika tika = new Tika();
    private final TeamManageRepository teamManageRepository;

    //파일 목록 get
    @Override
    public List<StorageDto> getFiles(Long teamId, User user) {
        QTeamData qTeamData = QTeamData.teamData;

        Member member = memberRepository.findByProviderId(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 일치하지 않습니다."));

        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(member.getId(), teamId)
                .orElseThrow(() -> new IllegalArgumentException("팀에 유저가 속해있지 않습니다."));


        List<TeamData> teamDataList = queryFactory.selectFrom(qTeamData)
                .where(qTeamData.teamManage.team.id.eq(teamId))
                .fetch();

        if (teamDataList.isEmpty()) {
            log.info("No files found for team ID: " + teamId);
        }

        return teamDataList.stream()
                .map(StorageDto::from)
                .collect(Collectors.toList());
    }

    //파일 다운로드
    @Override
    public StorageDto downloadFile(Long teamId, Long storageId, User user) {

        log.info("service code in");

        TeamData teamData = teamDataRepository.findById(storageId)
                .orElseThrow(() -> new IllegalArgumentException("파일이 존재하지 않습니다."));

        log.info("teamData check in");

        Member member = memberRepository.findByProviderId(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 일치하지 않습니다."));

        log.info("memberRepo check in");

        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(member.getId(), teamId)
                .orElseThrow(() -> new IllegalArgumentException("팀에 유저가 속해있지 않습니다."));

        log.info("TeamManage check in");

        if (!teamData.getTeamManage().getTeam().getId().equals(teamId)) {
            throw new IllegalArgumentException("File does not belong to the specified team");
        }

        log.info("User check in");

        String fileUrl = teamData.getFileUrl();

        log.info("fireurl check in" + fileUrl);

        String key = s3Helper.extractKeyFromUrl(fileUrl);

        log.info("Key check in " + key);

        InputStream inputStream = s3Provider.downloadFile(key);

        log.info("inputStream check in");


        // 전체 파일 이름 (이름 + 확장자)
        String decodedFileName = URLDecoder.decode(teamData.getTitle(), StandardCharsets.UTF_8);
        String contentType = s3Provider.determineContentType(decodedFileName);

        return StorageDto.builder()
                .inputStream(inputStream)
                .title(decodedFileName)
                .contentType(contentType)
                .build();
    }
}
