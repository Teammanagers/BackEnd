package kr.teammanagers.feedback.application;

import kr.teammanagers.feedback.domain.Feedback;
import kr.teammanagers.feedback.dto.request.CreateFeedbackRequest;
import kr.teammanagers.feedback.repository.FeedbackRepository;
import kr.teammanagers.member.domain.Member;
import kr.teammanagers.storage.domain.TeamData;
import kr.teammanagers.storage.repository.TeamDataRepository;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import kr.teammanagers.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackCommandServiceImpl implements FeedbackCommandService {

    private final FeedbackRepository feedbackRepository;
    private final TeamManageRepository teamManageRepository;
    private final TeamDataRepository teamDataRepository;
    private final TeamRepository teamRepository;

    @Override
    public void createFeedback(CreateFeedbackRequest request, Member member) {


        TeamData teamData = teamDataRepository.findById(request.getStorageId())
                .orElseThrow(() -> new IllegalArgumentException("Team Data not found"));

        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(member.getId(), teamData.getTeamManage().getTeam().getId())
                .orElseThrow(() -> new IllegalArgumentException("Team or User not found"));


        Feedback parentFeedback = null;
        if (request.getParentId() != 0) {
            parentFeedback = feedbackRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 피드백을 찾을 수 없습니다."));
        }

        Feedback feedback = Feedback.builder()
                .content(request.getContent())
                .build();
        feedback.setTeamManage(teamManage);
        feedback.setTeamData(teamData);
        feedback.setParent(parentFeedback);

        feedbackRepository.save(feedback);
    }
}