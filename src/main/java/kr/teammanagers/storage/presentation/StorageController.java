package kr.teammanagers.storage.presentation;

import com.amazonaws.services.ec2.model.Storage;
import kr.teammanagers.storage.application.StorageCommandService;
import kr.teammanagers.storage.application.StorageQueryService;
import kr.teammanagers.storage.dto.StorageDto;
import kr.teammanagers.storage.dto.request.CreateStorageRequest;
import kr.teammanagers.storage.dto.response.StorageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/team/{teamId}/storage")
@RequiredArgsConstructor
@Slf4j
public class StorageController {

    private final StorageCommandService storageCommandService;
    private final StorageQueryService storageQueryService;

    //파일 업로드
    @PostMapping
    public ResponseEntity<StorageResponse> uploadFile(
            final @AuthenticationPrincipal User user,
            @PathVariable Long teamId,
            @RequestParam("file") MultipartFile file) {

        CreateStorageRequest request = new CreateStorageRequest();
        request.setTeamId(teamId);
        request.setFile(file);


        StorageResponse response = storageCommandService.uploadFile(request, user);
        return ResponseEntity.ok(response);
    }

    //팀 파일 목록 가져오기
    @GetMapping
    public ResponseEntity<List<StorageDto>> getFiles(@PathVariable Long teamId, @AuthenticationPrincipal User user) {
        List<StorageDto> files = storageQueryService.getFiles(teamId, user);
        log.info(files.toString());
        return ResponseEntity.ok(files);
    }


    //파일 다운로드
    @GetMapping("/{storageId}")
    public ResponseEntity<InputStreamResource> downloadFile(
            @PathVariable Long teamId,
            @PathVariable Long storageId,
            @AuthenticationPrincipal User user) {

        StorageDto storageDto = storageQueryService.downloadFile(teamId, storageId, user);
        InputStreamResource resource = new InputStreamResource(storageDto.inputStream());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + storageDto.title() + "\"")
                .contentType(MediaType.parseMediaType(storageDto.contentType()))
                .body(resource);
    }


    //파일 삭제
    @DeleteMapping("/{storageId}")
    public String deleteFile(@PathVariable Long teamId, @PathVariable Long storageId, @AuthenticationPrincipal User user) {
        storageCommandService.deleteFile(teamId, storageId, user);
        return "파일이 삭제 되었습니다.";
    }
}
