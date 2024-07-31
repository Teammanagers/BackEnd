package kr.teammanagers.memo.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.memo.application.MemoService;
import kr.teammanagers.memo.dto.request.CreateMemo;
import kr.teammanagers.memo.dto.request.UpdateMemo;
import kr.teammanagers.memo.dto.response.GetMemoList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/team/{teamId}/memo")
    public ApiPayload<Void> create(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId,
            @RequestBody final CreateMemo createMemo
    ) {
        memoService.createMemo(teamId, createMemo);
        return ApiPayload.onSuccess();
    }

    @GetMapping("/team/{teamId}/memo")
    public ApiPayload<GetMemoList> get(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId
    ) {
        GetMemoList result = memoService.getMemoList(teamId);
        return ApiPayload.onSuccess(result);
    }

    @PatchMapping("/memo/{memoId}")
    public ApiPayload<Void> update(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("memoId") final Long memoId,
            @RequestBody final UpdateMemo updateMemo
    ) {
        memoService.updateMemo(memoId, updateMemo);
        return ApiPayload.onSuccess();
    }

    @DeleteMapping("/memo/{memoId}")
    public ApiPayload<Void> delete(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("memoId") final Long memoId
    ) {
        memoService.deleteMemo(memoId);
        return ApiPayload.onSuccess();
    }
}
