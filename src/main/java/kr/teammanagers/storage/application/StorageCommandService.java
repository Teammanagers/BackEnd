package kr.teammanagers.storage.application;

import kr.teammanagers.storage.dto.request.CreateStorageRequest;
import kr.teammanagers.storage.dto.response.StorageResponse;
import org.springframework.security.core.userdetails.User;

public interface StorageCommandService {
    StorageResponse uploadFile(CreateStorageRequest request, User user);
    void deleteFile(Long teamId, Long storageId, User user);
}
