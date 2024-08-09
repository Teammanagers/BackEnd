package kr.teammanagers.global.util;

import com.amazonaws.services.s3.AmazonS3;
import kr.teammanagers.global.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AmazonS3Helper {

    private final AmazonConfig amazonConfig;

    // 팀별 bucket이 존재하는지 확인
    public void createBucketIfNotExists(final AmazonS3 amazonS3, final String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
    }

    // File Url Form 설정
    public String extractFileNameFromUrl(final String url, final String bucketName) {
        String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", bucketName, amazonConfig.getRegion());
        return url.substring(bucketUrl.length());
    }
    public String extractKeyFromUrl(final String url) {
        return extractFileNameFromUrl(url, amazonConfig.getBucket());
    }

    public String extractImageNameFromUrl(final String url) {
        String bucket = amazonConfig.getBucket();
        String prefix = "https://" + bucket + ".s3." + amazonConfig.getRegion() + ".amazonaws.com/";
        return url.substring(prefix.length());
    }

    // Key 이름 생성
    public String generateKeyName(final String filePath) {
        return filePath + '/' + UUID.randomUUID();
    }
}
