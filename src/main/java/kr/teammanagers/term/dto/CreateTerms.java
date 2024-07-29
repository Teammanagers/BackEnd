package kr.teammanagers.term.dto;

import kr.teammanagers.term.domain.CheckedTerms;

public record CreateTerms(
        Boolean termsOfUse,
        Boolean privacyPolicy
) {
    public static CheckedTerms from(final CreateTerms createTerms) {
        return CheckedTerms.builder()
                .privacyPolicy(createTerms.privacyPolicy())
                .termsOfUse(createTerms.termsOfUse())
                .build();
    }
}
