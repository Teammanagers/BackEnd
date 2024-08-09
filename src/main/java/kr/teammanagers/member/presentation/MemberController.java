package kr.teammanagers.member.presentation;

import kr.teammanagers.auth.dto.PrincipalDetails;
import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.member.application.MemberService;
import kr.teammanagers.member.dto.request.UpdateProfile;
import kr.teammanagers.member.dto.response.GetMemberTeam;
import kr.teammanagers.member.dto.response.GetPortfolio;
import kr.teammanagers.member.dto.response.GetProfile;
import kr.teammanagers.member.dto.response.GetSimplePortfolioList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public ApiPayload<GetProfile> getProfile(
            @AuthenticationPrincipal final PrincipalDetails auth
    ) {
        GetProfile result = memberService.getProfile(auth.member().getId());
        return ApiPayload.onSuccess(result);
    }

    @PatchMapping(value = "/member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiPayload<Void> updateProfile(
            @AuthenticationPrincipal final PrincipalDetails auth,
            @RequestPart(name = "updateProfile") final UpdateProfile updateProfile,
            @RequestPart(name = "imageFile", required = false) final MultipartFile imageFile
    ) {
        memberService.updateProfile(auth.member().getId(), updateProfile, imageFile);
        return ApiPayload.onSuccess();
    }

    @PatchMapping("/comment/{commentId}/state")
    public ApiPayload<Void> updateCommentState(
            @AuthenticationPrincipal final PrincipalDetails auth,
            @PathVariable(name = "commentId") final Long commentId
    ) {
        memberService.updateCommentState(commentId);
        return ApiPayload.onSuccess();
    }

    @GetMapping("/member/portfolio")
    public ApiPayload<GetSimplePortfolioList> getSimplePortfolioList(
            @AuthenticationPrincipal final PrincipalDetails auth
    ) {
        GetSimplePortfolioList result = memberService.getSimplePortfolioList(auth.member().getId());
        return ApiPayload.onSuccess(result);
    }

    @GetMapping("/member/portfolio/{teamId}")
    public ApiPayload<GetPortfolio> getPortfolio(
            @AuthenticationPrincipal final PrincipalDetails auth,
            @PathVariable(name = "teamId") final Long teamId
    ) {
        GetPortfolio result = memberService.getPortfolio(auth.member().getId(), teamId);
        return ApiPayload.onSuccess(result);
    }

    @GetMapping("/member/team")
    public ApiPayload<GetMemberTeam> getTeam(
            @AuthenticationPrincipal final PrincipalDetails auth
    ) {
        GetMemberTeam result = memberService.getMemberTeam(auth.member().getId());
        return ApiPayload.onSuccess(result);
    }
}
