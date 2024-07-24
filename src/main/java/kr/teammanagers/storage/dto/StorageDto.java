package kr.teammanagers.storage.dto;

import java.time.LocalDateTime;

public record StorageDto(
        Long storageId,
        String title,
        String size,
        LocalDateTime uploadAt,
        String fileUrl,
        String uploader
) {
}
