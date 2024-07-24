package kr.teammanagers.member.dto.request;

import jakarta.validation.constraints.Size;
import kr.teammanagers.tag.dto.TagDto;
import kr.teammanagers.team.dto.CommentDto;

import java.util.List;

public record UpdateProfile(
        @Size(max = 20)
        String belong,
        List<TagDto> confidentRole,
        List<CommentDto> commentList
) {
}
