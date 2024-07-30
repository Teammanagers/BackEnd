package kr.teammanagers.memo.dto.request;

import kr.teammanagers.memo.domain.Memo;

import java.util.List;

public record CreateMemo(
        String title,
        List<String> tagList,
        String content
) {

    public Memo toMemo() {
        return Memo.builder()
                .title(title)
                .content(content)
                .build();
    }
}
