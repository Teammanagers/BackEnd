package kr.teammanagers.tag.dto;

import lombok.Builder;

@Builder
public record TagDto(
        Long tagId,
        String name
) {
}
