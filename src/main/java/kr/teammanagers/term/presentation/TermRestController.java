package kr.teammanagers.term.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.term.dto.CreateTerms;
import kr.teammanagers.term.application.TermCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TermRestController {

    private final TermCommandService termCommandService;

    @PostMapping("/terms")
    public ApiPayload<Void> create(@RequestBody final CreateTerms request) {

        termCommandService.createTerms(request);    //Todo: memberId 파라미터 추가
        return ApiPayload.onSuccess();
    }
}
