package kr.teammanagers.tag.request;

import lombok.Builder;

@Builder
public record UpdateTeamTag(
        String name
) {
}
