package kr.teammanagers.global.provider;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kr.teammanagers.global.config.AmazonConfig;
import kr.teammanagers.global.constant.AmazonConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Provider {

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    // 일반 파일 업로드
    public String uploadFile(final String keyName, final MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            log.error(AmazonConstant.FILE_UPLODAD_ERROR + ": {}", (Object) e.getStackTrace());
        }
        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    // 일반 이미지 업로드 (Url을 통해 바로 조회할 수 있도록 변경)
    public String uploadImage(final String keyName, final MultipartFile file) {
        String originName = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originName).substring(originName.lastIndexOf(".")); // 확장자

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType()); // 다운로드가 아닌 브라우저로 직접 조회를 하기 위함
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName + ext, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.error(AmazonConstant.FILE_UPLODAD_ERROR + ": {}", (Object) e.getStackTrace());
        }
        return amazonS3.getUrl(amazonConfig.getBucket(), keyName + ext).toString();
    }

    public String extractImageNameFromUrl(final String url) {
        String bucket = amazonConfig.getBucket();
        String prefix = "https://" + bucket + ".s3." + amazonConfig.getRegion() + ".amazonaws.com/";
        return url.substring(prefix.length());
    }

    public void deleteFile(final String fileName) {
        amazonS3.deleteObject(amazonConfig.getBucket(), fileName);
    }

    public String generateKeyName(final String filePath) {
        return filePath + '/' + UUID.randomUUID();
    }
}
