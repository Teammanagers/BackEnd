package kr.teammanagers.storage.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageResponse {
    private String message;
    private boolean success;
}
