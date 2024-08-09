package kr.teammanagers.storage.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateStorageRequest {
    private Long teamId;
    private String title;
    private MultipartFile file;
}
