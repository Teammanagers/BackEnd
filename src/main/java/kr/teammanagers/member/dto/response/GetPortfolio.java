package kr.teammanagers.member.dto.response;

import kr.teammanagers.storage.dto.StorageDto;

import java.time.LocalDateTime;
import java.util.List;

public record GetPortfolio(
        Long teamId,
        String name,
        LocalDateTime start,
        LocalDateTime end,
        List<String> teamTagList,
        List<String> teamMemberList,
        List<String> teamMyRole,
        List<StorageDto> storageList
) {
}
