package kr.teammanagers.notice.dto;

import kr.teammanagers.notice.domain.Notice;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NoticeDto(
        Long noticeId,
        String content,
        LocalDateTime createdAt
) {
    public static NoticeDto from(Notice notice) {
        return NoticeDto.builder()
                .noticeId(notice.getId())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .build();
    }
}
