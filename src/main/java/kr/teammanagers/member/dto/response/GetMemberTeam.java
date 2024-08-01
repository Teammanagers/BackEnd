package kr.teammanagers.member.dto.response;

import kr.teammanagers.member.domain.Member;
import kr.teammanagers.team.dto.TeamDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetMemberTeam(
        String name,
        List<TeamDto> teamList
) {

    public static GetMemberTeam of(final Member member, final List<TeamDto> teamList) {
        return GetMemberTeam.builder()
                .name(member.getName())
                .teamList(teamList)
                .build();
    }
}
