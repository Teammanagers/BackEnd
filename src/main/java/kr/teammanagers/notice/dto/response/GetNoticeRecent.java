package kr.teammanagers.notice.dto.response;

import kr.teammanagers.notice.dto.NoticeDto;
import lombok.Builder;

@Builder
public record GetNoticeRecent(
        NoticeDto recentNotice
) {
    public static GetNoticeRecent from(NoticeDto noticeDto) {
        return GetNoticeRecent.builder()
                .recentNotice(noticeDto)
                .build();
    }
}
