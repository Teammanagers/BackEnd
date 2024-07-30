package kr.teammanagers.notice.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.notice.application.NoticeService;
import kr.teammanagers.notice.dto.request.CreateNotice;
import kr.teammanagers.notice.dto.response.GetNoticeList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/team/{teamId}/notice")
    public ApiPayload<Void> create(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId,
            @RequestBody final CreateNotice createNotice
    ) {
        noticeService.createNotice(teamId, createNotice);
        return ApiPayload.onSuccess();
    }

    @GetMapping("/team/{teamId}/notice")
    public ApiPayload<GetNoticeList> get(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId
    ) {
        GetNoticeList result = noticeService.getNoticeList(teamId);
        return ApiPayload.onSuccess(result);
    }
}
