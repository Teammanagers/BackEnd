package kr.teammanagers.team.dto;

import kr.teammanagers.team.domain.TeamManage;
import lombok.Builder;

@Builder
public record SimpleTeamMemberDto(
        Long teamManageId,
        String name
) {
    public static SimpleTeamMemberDto from(final TeamManage teamManage) {
        return SimpleTeamMemberDto.builder()
                .teamManageId(teamManage.getId())
                .name(teamManage.getMember().getName())
                .build();
    }
}
