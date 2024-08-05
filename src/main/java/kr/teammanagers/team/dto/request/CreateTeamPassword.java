package kr.teammanagers.team.dto.request;

import jakarta.validation.constraints.Size;

public record CreateTeamPassword(
        @Size(max = 6)
        String password
) {
}
