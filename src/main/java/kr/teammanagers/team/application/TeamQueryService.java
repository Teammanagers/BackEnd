package kr.teammanagers.team.application;

import kr.teammanagers.team.dto.response.GetTeam;
import kr.teammanagers.team.dto.response.GetTeamMember;

public interface TeamQueryService {
    GetTeam getTeamById(Long teamId);

    GetTeam getTeamByTeamCode(String teamCode);

    GetTeamMember getTeamMember(Long teamId);
}
