package kr.teammanagers.notice.dto.request;

import kr.teammanagers.notice.domain.Notice;

public record CreateNotice(
        String content
) {
    public Notice toNotice() {
        return Notice.builder()
                .content(this.content)
                .build();
    }
}
