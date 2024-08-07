package kr.teammanagers.team.application;

import kr.teammanagers.team.dto.request.CreateTeam;
import kr.teammanagers.team.dto.request.CreateTeamComment;
import kr.teammanagers.team.dto.request.CreateTeamPassword;
import kr.teammanagers.team.dto.response.CreateTeamResult;
import kr.teammanagers.team.dto.response.UpdateTeamEndResult;
import org.springframework.web.multipart.MultipartFile;

public interface TeamCommandService {
    CreateTeamResult createTeam(Long authId, CreateTeam request, MultipartFile imageFile);

    void createTeamPassword(Long teamId, CreateTeamPassword request);

    UpdateTeamEndResult updateTeamState(Long authId, Long teamId);

    void createComment(CreateTeamComment request);
}
