package kr.teammanagers.member.dto.response;

import kr.teammanagers.member.dto.SimplePortfolioDto;

import java.util.List;

public record GetSimplePortfolioList(
        List<SimplePortfolioDto> portfolioList
) {
}
