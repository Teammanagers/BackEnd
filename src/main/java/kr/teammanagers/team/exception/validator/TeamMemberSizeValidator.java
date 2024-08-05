package kr.teammanagers.team.exception.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.teammanagers.team.application.TeamService;
import kr.teammanagers.team.dto.RegisterCommentDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TeamMemberSizeValidator implements ConstraintValidator<TeamMemberSize, List<RegisterCommentDto>> {

    private final TeamService teamService;

    @Override
    public void initialize(TeamMemberSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<RegisterCommentDto> value, ConstraintValidatorContext constraintValidatorContext) {
        return teamService.countTeamMembersByTeamManageId(value.getFirst().teamManageId()) - 1 == value.size();
    }
}
