package kr.teammanagers.notice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.teammanagers.notice.domain.Notice;

public record CreateNotice(
        @NotNull
        @Size(max = 100)
        String content
) {
    public Notice toNotice() {
        return Notice.builder()
                .content(this.content)
                .build();
    }
}
