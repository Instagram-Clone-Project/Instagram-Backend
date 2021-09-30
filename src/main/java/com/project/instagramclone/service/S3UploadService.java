package com.project.instagramclone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.instagramclone.PhotoTypeConst;
import com.project.instagramclone.domain.post.entity.Photo;
import com.project.instagramclone.domain.photo.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3Client amazonS3Client;

    @Value("${uploadFile.path}")
    public String localPhotoSavePath; //사진이 임시 저장될 경로

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;      //bucket이름

    public void uploadSave(MultipartFile file, String dirName, Post post) throws IOException {
        File uploadFile = convert(file)
                .orElseThrow(()-> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        String encFileName = UUID.randomUUID().toString();
        String S3FileName = dirName + "/" + encFileName + uploadFile.getName(); //s3에 저장될 파일 이름
        savePhoto(uploadFile, S3FileName, uploadFile.getName(), encFileName, post);
        putS3(uploadFile, S3FileName);
        removeNewFile(uploadFile);
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete Fail");
    }

    private void putS3(File file, String s3FileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, s3FileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private void savePhoto(File file, String s3FileName, String fileName, String encFileName, Post post) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setEncFileName(encFileName);
        photo.setRoute("https://ssc-instagram.s3.ap-northeast-2.amazonaws.com/" + s3FileName);
        photo.setFileSize(file.length());
        photo.setPhotoType(PhotoTypeConst.POST_PHOTO);
        photo.setPost(post);

    }


    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(localPhotoSavePath + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}
