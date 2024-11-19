package kr.teammanagers.global.provider;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kr.teammanagers.global.config.AmazonConfig;
import kr.teammanagers.global.constant.AmazonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class AmazonS3ProviderV3 {
    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;

    public AmazonS3ProviderV3(AmazonS3 amazonS3, AmazonConfig amazonConfig) {
        this.amazonS3 = amazonS3;
        this.amazonConfig = amazonConfig;
    }

    public String uploadImage(String filePath, long id, MultipartFile file) {
        //UUID + 파일명으로 중복 파일명 허용
        String fileName = filePath + "/" + id + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), fileName, file.getInputStream(), metadata));
        } catch (IOException e) {
            log.error(AmazonConstant.FILE_UPLOAD_ERROR + ": {}", (Object) e.getStackTrace());
        }
        return amazonS3.getUrl(amazonConfig.getBucket(), fileName).toString();

    }






}
