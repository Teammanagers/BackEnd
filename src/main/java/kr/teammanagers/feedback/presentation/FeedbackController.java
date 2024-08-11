package kr.teammanagers.feedback.presentation;

import kr.teammanagers.auth.dto.PrincipalDetails;
import kr.teammanagers.feedback.application.FeedbackCommandService;
import kr.teammanagers.feedback.application.FeedbackQueryService;
import kr.teammanagers.feedback.dto.FeedbackDto;
import kr.teammanagers.feedback.dto.request.CreateFeedbackRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/storage/{storageId}/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackCommandService feedbackCommandService;
    private final FeedbackQueryService feedbackQueryService;

    @PostMapping
    public ResponseEntity<Void> createFeedback(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long storageId,
            @RequestBody CreateFeedbackRequest request) {


        request.setStorageId(storageId);

        Long parentId = request.getParentId() != null ? request.getParentId() : 0L;
        request.setParentId(parentId);

        feedbackCommandService.createFeedback(request, principalDetails.member());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getFeedbacks(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long storageId) {
        List<FeedbackDto> responses = feedbackQueryService.getFeedbacksByTeamData(storageId, principalDetails.member());
        return ResponseEntity.ok(responses);
    }
}
