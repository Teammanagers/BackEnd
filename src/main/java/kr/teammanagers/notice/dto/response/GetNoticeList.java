package kr.teammanagers.notice.dto.response;

import kr.teammanagers.notice.dto.NoticeDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetNoticeList(
        List<NoticeDto> noticeList
) {
    public static GetNoticeList from(List<NoticeDto> noticeList) {
        return GetNoticeList.builder()
                .noticeList(noticeList)
                .build();
    }
}
