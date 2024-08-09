package kr.teammanagers.storage.application;

import kr.teammanagers.storage.dto.StorageDto;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface StorageQueryService {
    List<StorageDto> getFiles(Long teamId, User user);
    StorageDto downloadFile(Long teamId, Long storageId, User user);
}
