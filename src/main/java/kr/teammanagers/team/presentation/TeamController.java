package kr.teammanagers.team.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.team.application.TeamService;
import kr.teammanagers.team.dto.request.CreateTeam;
import kr.teammanagers.team.dto.request.CreateTeamComment;
import kr.teammanagers.team.dto.request.CreateTeamPassword;
import kr.teammanagers.team.dto.response.CreateTeamResult;
import kr.teammanagers.team.dto.response.GetTeam;
import kr.teammanagers.team.dto.response.GetTeamMember;
import kr.teammanagers.team.dto.response.UpdateTeamEndResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public ApiPayload<CreateTeamResult> create(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @RequestPart(name = "createTeam") final CreateTeam createTeam,
            @RequestPart(name = "imageFile", required = false) final MultipartFile imageFile
    ) {
        CreateTeamResult result = teamService.createTeam(1L, createTeam, imageFile);       // TODO : 인증 객체 구현시 auth.id로 변경 필요
        return ApiPayload.onSuccess(result);
    }

    @PatchMapping("/team/{teamId}/password")
    public ApiPayload<Void> createPassword(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId,
            @RequestBody final CreateTeamPassword createTeamPassword
    ) {
        teamService.createTeamPassword(teamId, createTeamPassword);
        return ApiPayload.onSuccess();
    }

    @GetMapping("/team/{teamId}")
    public ApiPayload<GetTeam> getById(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId
    ) {
        GetTeam result = teamService.getTeamById(teamId);
        return ApiPayload.onSuccess(result);
    }

    @GetMapping("/team")
    public ApiPayload<GetTeam> getByTeamCode(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @RequestParam("teamCode") final String teamCode
    ) {
        GetTeam result = teamService.getTeamByTeamCode(teamCode);
        return ApiPayload.onSuccess(result);
    }

    @GetMapping("/team/{teamId}/member")
    public ApiPayload<GetTeamMember> getTeamMember(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId
    ) {
        GetTeamMember result = teamService.getTeamMember(teamId);
        return ApiPayload.onSuccess(result);
    }

    @PatchMapping("/team/{teamId}/state")
    public ApiPayload<UpdateTeamEndResult> updateTeamState(
//            @AuthenticationPrincipal final AuthDto auth,          // TODO : 인증 객체 구현 필요
            @PathVariable("teamId") final Long teamId
    ) {
        UpdateTeamEndResult result = teamService.updateTeamState(1L, teamId);
        return ApiPayload.onSuccess(result);
    }

    @PostMapping("/team/comment")
    public ApiPayload<Void> createComment(
//            @AuthenticationPrincipal final AuthDto auth         // TODO : 인증 객체 구현 필요
            @RequestBody final CreateTeamComment createTeamComment
    ) {
        teamService.createComment(createTeamComment);
        return ApiPayload.onSuccess();
    }
}
