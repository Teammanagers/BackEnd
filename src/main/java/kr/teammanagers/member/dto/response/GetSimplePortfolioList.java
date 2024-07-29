package kr.teammanagers.member.dto.response;

import kr.teammanagers.member.dto.SimplePortfolioDto;
import kr.teammanagers.team.domain.Team;
import lombok.Builder;

import java.util.List;

@Builder
public record GetSimplePortfolioList(
        List<SimplePortfolioDto> portfolioList
) {

    public static GetSimplePortfolioList from(final List<Team> teamList) {
        return GetSimplePortfolioList.builder()
                .portfolioList(teamList.stream()
                        .map(SimplePortfolioDto::from)
                        .toList())
                .build();
    }
}
