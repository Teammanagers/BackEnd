package kr.teammanagers.team.application;

import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.domain.TagTeam;
import kr.teammanagers.tag.repository.TagTeamRepository;
import kr.teammanagers.team.dto.SimpleTeamMemberDto;
import kr.teammanagers.team.dto.response.GetTeam;
import kr.teammanagers.team.dto.response.GetTeamMember;
import kr.teammanagers.team.repository.TeamManageRepository;
import kr.teammanagers.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamQueryServiceImpl implements TeamQueryService {

    private final TeamRepository teamRepository;
    private final TagTeamRepository tagTeamRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public GetTeam getTeamById(final Long teamId) {
        return teamRepository.findById(teamId)
                .map(team -> {
                    List<Tag> tagList = tagTeamRepository.findAllByTeamId(team.getId()).stream()
                            .map(TagTeam::getTag).toList();
                    return GetTeam.from(team, tagList);
                })
                .orElseThrow(RuntimeException::new);        // TODO : 예외 처리 필요

    }

    @Override
    public GetTeam getTeamByTeamCode(final String teamCode) {
        return teamRepository.findByTeamCode(teamCode)
                .map(team -> {
                    List<Tag> tagList = tagTeamRepository.findAllByTeamId(team.getId()).stream()
                            .map(TagTeam::getTag).toList();
                    return GetTeam.from(team, tagList);
                })
                .orElse(null);
    }

    @Override
    public GetTeamMember getTeamMember(final Long teamId) {
        List<SimpleTeamMemberDto> memberList = teamManageRepository.findAllByTeamId(teamId).stream()
                .map(SimpleTeamMemberDto::from)
                .toList();

        return GetTeamMember.from(memberList);
    }
}
