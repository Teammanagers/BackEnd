package kr.teammanagers.term.application;

import kr.teammanagers.common.payload.code.GeneralException;
import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.member.repository.MemberRepository;
import kr.teammanagers.term.domain.CheckedTerms;
import kr.teammanagers.term.dto.CreateTerms;
import kr.teammanagers.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermCommandServiceImpl implements TermCommandService {

    private final TermRepository termRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createTerms(CreateTerms request) {
        CheckedTerms newCheckedTerms = CheckedTerms.builder()
                .termsOfUse(request.getTermsOfUse())
                .privacyPolicy(request.getPrivacyPolicy())
                .build();

        Long memberId = 1L;     //이후 수정
        newCheckedTerms.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND)));

        termRepository.save(newCheckedTerms);
    }
}
