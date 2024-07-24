package kr.teammanagers.member.dto.response;

import kr.teammanagers.tag.dto.TagDto;
import kr.teammanagers.team.dto.CommentDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetProfile(
        String imageUrl,
        String name,
        String belong,
        List<TagDto> confidentRole,
        List<CommentDto> commentList
) {
}
