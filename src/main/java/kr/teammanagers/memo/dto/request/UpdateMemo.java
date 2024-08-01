package kr.teammanagers.memo.dto.request;

import java.util.List;

public record UpdateMemo(
        String title,
        List<String> tagList,
        String content
) {
}
