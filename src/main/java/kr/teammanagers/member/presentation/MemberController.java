package kr.teammanagers.member.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.member.application.MemberService;
import kr.teammanagers.member.dto.request.UpdateProfile;
import kr.teammanagers.member.dto.response.GetMemberTeam;
import kr.teammanagers.member.dto.response.GetPortfolio;
import kr.teammanagers.member.dto.response.GetProfile;
import kr.teammanagers.member.dto.response.GetSimplePortfolioList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public ApiPayload<GetProfile> getProfile(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
    ) {
        GetProfile result = memberService.getProfile(1L);           // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess(result);
    }

    @PatchMapping(value = "/member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiPayload<?> updateProfile(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @RequestPart(name = "updateProfile") final UpdateProfile updateProfile,
            @RequestPart(name = "imageFile", required = false) final MultipartFile imageFile
    ) {
        memberService.updateProfile(1L, updateProfile, imageFile);             // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess();
    }

    @PatchMapping( "/comment/{commentId}/state")
    public ApiPayload<?> updateCommentState(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable(name = "commentId") final Long commentId
    ) {
        memberService.updateCommentState(commentId);            // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess();
    }

    @GetMapping("/member/portfolio")
    public ApiPayload<GetSimplePortfolioList> getSimplePortfolioList(
//            @AuthenticationPrincipal AuthDto auth,                                // TODO : 인증 객체 구현 필요
    ) {
        GetSimplePortfolioList result = memberService.getSimplePortfolioList(1L);   // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess(result);
    }

    @GetMapping("/member/portfolio/{teamId}")
    public ApiPayload<GetPortfolio> getPortfolio(
//            @AuthenticationPrincipal AuthDto auth,                                // TODO : 인증 객체 구현 필요
            @PathVariable(name = "teamId") final Long teamId
    ) {
        GetPortfolio result = memberService.getPortfolio(1L, teamId);               // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess(result);
    }

    @GetMapping("/member/team")
    public ApiPayload<GetMemberTeam> getMemberTeam(
//            @AuthenticationPrincipal AuthDto auth,                                // TODO : 인증 객체 구현 필요
    ) {
        GetMemberTeam result = memberService.getMemberTeam(1L);                     // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess(result);
    }
}
