package kr.teammanagers.storage.controller;

import kr.teammanagers.storage.dto.StorageDto;
import kr.teammanagers.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<StorageDto> uploadFile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("teamManageId") Long teamManageId) {
        StorageDto storageDto = storageService.uploadFile(file, teamManageId, title);
        return ResponseEntity.ok(storageDto);
    }

    @GetMapping("/files/{teamId}")
    public ResponseEntity<List<StorageDto>> getFiles(@PathVariable Long teamId) {
        List<StorageDto> files = storageService.getFilesByTeamId(teamId);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileUrl") String fileUrl) throws IOException {
        InputStream inputStream = storageService.downloadFile(fileUrl);
        byte[] content = inputStream.readAllBytes();

        String fileName = URLEncoder.encode(fileUrl.substring(fileUrl.lastIndexOf('/') + 1), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(content);
    }
}
