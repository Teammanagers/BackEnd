package kr.teammanagers.storage.dto;

import kr.teammanagers.storage.domain.TeamData;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record StorageDto(
        Long storageId,
        String title,
        String size,
        LocalDateTime uploadAt,
        String fileUrl,
        String uploader
) {

    public static StorageDto from(final TeamData teamData) {
        return StorageDto.builder()
                .storageId(teamData.getId())
                .title(teamData.getTitle())
                .size(teamData.getSize())
                .uploadAt(teamData.getCreatedAt())
                .fileUrl(teamData.getFileUrl())
                .uploader(teamData.getTeamManage().getMember().getName())
                .build();
    }
}
