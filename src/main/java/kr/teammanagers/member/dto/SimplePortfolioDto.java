package kr.teammanagers.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SimplePortfolioDto(
        Long teamId,
        String name,
        LocalDateTime start,
        LocalDateTime end
) {
}
