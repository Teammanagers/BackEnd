package kr.teammanagers.term.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.term.dto.CreateTerms;
import kr.teammanagers.term.application.TermCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/terms")
@RequiredArgsConstructor
public class TermRestController {

    private final TermCommandService termCommandService;

    @PostMapping
    public ApiPayload<Void> create(CreateTerms request) {

        termCommandService.createTerms(request);

        return ApiPayload.onSuccess(null);
    }



}
