package kr.teammanagers.member.dto.response;

import kr.teammanagers.team.dto.TeamDto;

import java.util.List;

public record GetMemberTeam(
        String name,
        List<TeamDto> teamList
) {
}
