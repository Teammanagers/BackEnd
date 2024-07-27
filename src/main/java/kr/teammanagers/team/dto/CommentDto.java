package kr.teammanagers.team.dto;

import kr.teammanagers.member.domain.Comment;
import lombok.Builder;

@Builder
public record CommentDto(
        Long commentId,
        String content,
        Boolean isHidden
) {

    public static CommentDto of(final Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .isHidden(comment.getIsHidden())
                .build();
    }
}
