package kr.teammanagers.team.dto;

import kr.teammanagers.tag.dto.TagDto;

import java.util.List;

public record TeamDto(
        Long teamId,
        String title,
        String teamCode,
        String imageUrl,
        List<TagDto> teamTagList
) {
}
