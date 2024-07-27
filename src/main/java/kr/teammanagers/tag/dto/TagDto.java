package kr.teammanagers.tag.dto;

import kr.teammanagers.tag.domain.Tag;
import lombok.Builder;

@Builder
public record TagDto(
        Long tagId,
        String name
) {

    public static TagDto of(final Tag tag) {
        return TagDto.builder()
                .tagId(tag.getId())
                .name(tag.getName())
                .build();
    }
}
