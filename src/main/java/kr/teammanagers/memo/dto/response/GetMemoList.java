package kr.teammanagers.memo.dto.response;

import kr.teammanagers.memo.dto.MemoDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetMemoList(
        List<MemoDto> memoList
) {
    public static GetMemoList from(List<MemoDto> memoList) {
        return GetMemoList.builder()
                .memoList(memoList)
                .build();
    }
}
