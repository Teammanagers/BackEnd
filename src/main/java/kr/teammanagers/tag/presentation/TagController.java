package kr.teammanagers.tag.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.tag.application.TagService;
import kr.teammanagers.tag.request.CreateRoleTag;
import kr.teammanagers.tag.request.UpdateRoleTag;
import kr.teammanagers.tag.request.UpdateTeamTag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PatchMapping("/team/{teamId}/tag/{tagId}")
    public ApiPayload<?> updateTeamTag(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId,
            @PathVariable("tagId") final Long tagId,
            @RequestBody UpdateTeamTag updateTeamTag
    ) {
        tagService.updateTeamTag(teamId, tagId, updateTeamTag);
        return ApiPayload.onSuccess();
    }

    @DeleteMapping("/team/{teamId}/tag/{tagId}")
    public ApiPayload<?> deleteTeamTag(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId,
            @PathVariable("tagId") final Long tagId
    ) {
        tagService.deleteTeamTag(teamId, tagId);
        return ApiPayload.onSuccess();
    }

    @PostMapping("/management/{teamManageId}/role")
    public ApiPayload<?> createRoleTag(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamManageId") final Long teamManageId,
            @RequestBody final CreateRoleTag createRoleTag
    ) {
        tagService.createRoleTag(teamManageId, createRoleTag);
        return ApiPayload.onSuccess();
    }

    @PatchMapping("/management/{teamManageId}/role/{tagId}")
    public ApiPayload<?> updateRoleTag(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamManageId") final Long teamManageId,
            @PathVariable("tagId") final Long tagId,
            @RequestBody final UpdateRoleTag updateRoleTag
    ) {
        tagService.updateRoleTag(teamManageId, tagId, updateRoleTag);
        return ApiPayload.onSuccess();
    }

    @DeleteMapping("/management/{teamManageId}/role/{tagId}")
    public ApiPayload<?> deleteRoleTag(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamManageId") final Long teamManageId,
            @PathVariable("tagId") final Long tagId
    ) {
        tagService.deleteRoleTag(teamManageId, tagId);
        return ApiPayload.onSuccess();
    }
}
