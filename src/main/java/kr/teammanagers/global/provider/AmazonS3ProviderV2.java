package kr.teammanagers.global.provider;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import kr.teammanagers.global.config.AmazonConfig;
import kr.teammanagers.global.constant.AmazonConstant;
import kr.teammanagers.global.util.AmazonS3Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3ProviderV2 {

    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;
    private final AmazonS3Helper amazonS3Helper;

/*    public String uploadFile(final String teamId, final MultipartFile file) {
        String bucketName = amazonConfig.getBucket();
        String folderPath = "team-data/" + teamId;
        String keyName = amazonS3Helper.generateKeyName(folderPath);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.Private));
        } catch (IOException e) {
            log.error(AmazonConstant.FILE_UPLOAD_ERROR + ": {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(bucketName, keyName).toString();
    }*/

    public String uploadFile(final String teamId, final MultipartFile file) {
        String bucketName = amazonConfig.getBucket();
        String folderPath = "team-data/" + teamId;
        String encodedFileName = URLEncoder.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
        String keyName = folderPath + "/" + encodedFileName;

        log.info("Uploading file to S3 with key: {}", keyName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(determineContentType(file.getOriginalFilename()));

        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.Private));
        } catch (IOException e) {
            log.error(AmazonConstant.FILE_UPLOAD_ERROR + ": {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(bucketName, keyName).toString();
    }


    // 팀별 파일 전체 조회
    public List<String> listFiles(String teamId) {
        String bucketName = amazonConfig.getBucket();
        String prefix = "team-data/" + teamId + "/";
        List<S3ObjectSummary> s3ObjectSummaries = amazonS3.listObjects(bucketName, prefix).getObjectSummaries();
        return s3ObjectSummaries.stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    // 파일 다운로드
    public InputStream downloadFile(final String key) {
/*        String bucketName = amazonConfig.getBucket();
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName, key));
        return s3Object.getObjectContent();*/
        String bucketName = amazonConfig.getBucket();
        String decodedKey = URLDecoder.decode(key, StandardCharsets.UTF_8);
        log.info("Attempting to download file from bucket: {}, with key: {}", bucketName, decodedKey);

        try {
            // Check if the object exists
            if (!amazonS3.doesObjectExist(bucketName, decodedKey)) {
                log.error("The specified key does not exist in S3: {}", decodedKey);
                throw new AmazonS3Exception("The specified key does not exist.");
            }

            // If the object exists, get the object
            S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName, decodedKey));
            log.info("Successfully downloaded file from S3 with key: {}", decodedKey);
            return s3Object.getObjectContent();
        } catch (AmazonS3Exception e) {
            log.error("Amazon S3 Exception: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteFile(final String fileName) {
        amazonS3.deleteObject(amazonConfig.getBucket(), fileName);
    }

    public String generateKeyName(final String filePath) {
        return filePath + '/' + UUID.randomUUID();
    }

    public String extractFileNameFromUrl(final String url, final String bucketName) {
        String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", bucketName, amazonConfig.getRegion());
        return url.substring(bucketUrl.length());
    }

    public String decodeFileName(final String encodedFileName) {
        return new String(Base64.getDecoder().decode(encodedFileName), StandardCharsets.UTF_8);
    }

    public String determineContentType(String fileName) {
        Tika tika = new Tika();
        return tika.detect(fileName);
    }
}
