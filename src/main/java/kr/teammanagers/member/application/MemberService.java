package kr.teammanagers.member.application;

import kr.teammanagers.global.config.AmazonConfig;
import kr.teammanagers.global.provider.AmazonS3Provider;
import kr.teammanagers.member.domain.Comment;
import kr.teammanagers.member.domain.Member;
import kr.teammanagers.member.dto.request.UpdateProfile;
import kr.teammanagers.member.dto.response.GetMemberTeam;
import kr.teammanagers.member.dto.response.GetPortfolio;
import kr.teammanagers.member.dto.response.GetProfile;
import kr.teammanagers.member.dto.response.GetSimplePortfolioList;
import kr.teammanagers.member.repository.CommentRepository;
import kr.teammanagers.member.repository.MemberRepository;
import kr.teammanagers.storage.domain.TeamData;
import kr.teammanagers.storage.repository.TeamDataRepository;
import kr.teammanagers.tag.application.TagModuleService;
import kr.teammanagers.tag.domain.ConfidentRole;
import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.domain.TagTeam;
import kr.teammanagers.tag.repository.ConfidentRoleRepository;
import kr.teammanagers.tag.repository.TagTeamRepository;
import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.dto.TeamDto;
import kr.teammanagers.team.repository.TeamManageRepository;
import kr.teammanagers.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final TeamDataRepository teamDataRepository;
    private final TeamManageRepository teamManageRepository;
    private final ConfidentRoleRepository confidentRoleRepository;
    private final TeamRepository teamRepository;

    private final TagModuleService tagModuleService;
    private final AmazonConfig amazonConfig;
    private final AmazonS3Provider amazonS3Provider;
    private final TagTeamRepository tagTeamRepository;

    public GetProfile getProfile(final Long authId) {
        Member member = memberRepository.findById(authId).orElseThrow(RuntimeException::new);       // TODO : 예외 처리 필요
        List<Tag> tagList = tagModuleService.getAllConfidentRole(authId);
        List<Comment> commentList = commentRepository.findAllByMemberId(authId);
        String imageUrl = amazonS3Provider.generateUrl(amazonConfig.getMemberProfilePath(), member.getId());
        return GetProfile.of(member, tagList, commentList, imageUrl);
    }

    @Transactional
    public void updateProfile(final Long authId, final UpdateProfile request, final MultipartFile imageFile) {
        Member member = memberRepository.findById(authId).orElseThrow(RuntimeException::new);       // TODO : 예외 처리 필요

        updateMemberBelong(request.belong(), member);
        updateConfidentRoles(request.confidentRole(), member);
        updateProfileImageIfPresent(imageFile, member);
    }


    @Transactional
    public void updateCommentState(final Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);     // TODO : 예외 처리 필요
        comment.updateIsHidden();
    }

    public GetSimplePortfolioList getSimplePortfolioList(final Long authId) {
        List<Team> teamList = teamManageRepository.findAllByMemberId(authId).stream()
                .map(TeamManage::getTeam)
                .toList();
        return GetSimplePortfolioList.from(teamList);
    }

    public GetPortfolio getPortfolio(final Long authId, final Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);     // TODO : 예외 처리 필요
        List<TeamManage> teamManageList = teamManageRepository.findAllByTeamId(teamId);

        List<Member> memberList = teamManageRepository.findAllByTeamId(teamId).stream()
                .map(TeamManage::getMember)
                .toList();

        List<Tag> teamRoleList = tagModuleService.getAllTeamRoleTag(
                teamManageList.stream()
                        .filter(teamManage -> teamManage.getId().equals(authId))
                        .findFirst()
                        .orElseThrow(RuntimeException::new)
                        .getId());

        List<TeamData> teamDataList = teamManageList.stream()
                .flatMap(teamManage -> teamDataRepository.findAllByTeamManageId(teamManage.getId()).stream())
                .toList();

        return GetPortfolio.of(team, tagModuleService.getAllTeamTag(teamId), memberList, teamRoleList, teamDataList);
    }


    public GetMemberTeam getMemberTeam(final Long authId) {
        Member member = memberRepository.findById(authId).orElseThrow(RuntimeException::new);       // TODO : 예외 처리 필요
        List<TeamDto> teamList = teamManageRepository.findAllByMemberId(authId).stream()
                .map(TeamManage::getTeam)
                .map(team -> {
                    List<Tag> tagList = tagTeamRepository.findAllByTeamId(team.getId()).stream()
                            .map(TagTeam::getTag).toList();
                    return TeamDto.from(team, tagList,
                            amazonS3Provider.generateUrl(amazonConfig.getTeamProfilePath(), team.getId()));
                })
                .toList();
        return GetMemberTeam.of(member, teamList);
    }

    private void updateProfileImage(final MultipartFile imageFile, final Member member) {
        String memberProfilePath = amazonConfig.getMemberProfilePath();
        if (amazonS3Provider.isFileExist(memberProfilePath, member.getId())) {
            amazonS3Provider.deleteFile(memberProfilePath, member.getId());
        }
        amazonS3Provider.uploadImage(memberProfilePath, member.getId(), imageFile);
    }

    private void updateMemberBelong(final String belong, final Member member) {
        if (belong != null) {
            member.updateBelong(belong);
        }
    }

    private void updateConfidentRoles(final List<String> requestedRoles, final Member member) {
        if (requestedRoles == null) {
            return;
        }

        List<ConfidentRole> currentRoles = confidentRoleRepository.findAllByMemberId(member.getId());
        List<String> currentRoleNames = currentRoles.stream()
                .map(role -> role.getTag().getName())
                .toList();

        tagModuleService.addNewConfidentRoles(requestedRoles, currentRoleNames, member);
        tagModuleService.removeOldConfidentRoles(requestedRoles, currentRoles);
    }

    private void updateProfileImageIfPresent(final MultipartFile imageFile, final Member member) {
        if (imageFile != null && !imageFile.isEmpty()) {
            updateProfileImage(imageFile, member);
        }
    }
}
