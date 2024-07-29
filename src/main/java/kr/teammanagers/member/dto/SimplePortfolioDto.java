package kr.teammanagers.member.dto;

import kr.teammanagers.team.domain.Team;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SimplePortfolioDto(
        Long teamId,
        String name,
        LocalDateTime start,
        LocalDateTime end
) {

    public static SimplePortfolioDto from(final Team team) {
        return SimplePortfolioDto.builder()
                .teamId(team.getId())
                .name(team.getTitle())
                .start(team.getCreatedAt())
                .end(team.getUpdatedAt())
                .build();
    }
}
