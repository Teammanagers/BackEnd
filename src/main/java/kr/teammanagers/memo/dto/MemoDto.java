package kr.teammanagers.memo.dto;

import kr.teammanagers.memo.domain.Memo;
import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.dto.TagDto;
import lombok.Builder;

import java.util.List;

@Builder
public record MemoDto(
        Long memoId,
        String title,
        List<TagDto> tagList,
        String content
) {
    public static MemoDto of(Memo memo, List<Tag> tagList) {
        return MemoDto.builder()
                .memoId(memo.getId())
                .title(memo.getTitle())
                .tagList(tagList.stream()
                        .map(TagDto::from)
                        .toList())
                .content(memo.getContent())
                .build();
    }
}
