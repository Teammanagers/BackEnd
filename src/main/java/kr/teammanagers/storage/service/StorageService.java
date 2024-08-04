package kr.teammanagers.storage.service;

import kr.teammanagers.global.provider.AmazonS3Provider;
import kr.teammanagers.storage.domain.TeamData;
import kr.teammanagers.storage.dto.StorageDto;
import kr.teammanagers.storage.repository.TeamDataRepository;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageService {

    private final TeamDataRepository teamDataRepository;
    private final TeamManageRepository teamManageRepository;
    private final AmazonS3Provider amazonS3Provider;

    @Transactional
    public StorageDto uploadFile(MultipartFile file, Long teamManageId, String title) {
        TeamManage teamManage = teamManageRepository.findById(teamManageId)
                .orElseThrow(() -> new RuntimeException("TeamManage not found"));

        String fileUrl = amazonS3Provider.uploadFile(amazonS3Provider.generateKeyName("team-data"), file);
        TeamData teamData = TeamData.builder()
                .title(title)
                .size(String.valueOf(file.getSize()))
                .fileUrl(fileUrl)
                .build();
        teamData.setTeamManage(teamManage);
        teamDataRepository.save(teamData);

        return StorageDto.from(teamData);
    }

    public List<StorageDto> getFilesByTeamId(Long teamId) {
        List<TeamData> teamDataList = teamDataRepository.findAllByTeamManageId(teamId);
        return teamDataList.stream()
                .map(StorageDto::from)
                .collect(Collectors.toList());
    }

    public InputStream downloadFile(String fileUrl) {
        return amazonS3Provider.downloadFile(fileUrl);
    }
}
