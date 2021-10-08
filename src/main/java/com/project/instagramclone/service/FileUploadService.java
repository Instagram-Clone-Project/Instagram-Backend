package com.project.instagramclone.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.project.instagramclone.domain.photo.entity.Photo;
import com.project.instagramclone.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private static final String baseUrl = "https://ssc-instagram.s3.ap-northeast-2.amazonaws.com/";

    private final AWSS3UploadService s3UploadService;
    //Multipart를 통해 전송된 파일을 업로드 하는 메소드
    public String uploadImage(MultipartFile file) {

        String fileName = createdFileName(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            s3UploadService.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발행하였습니다 (%s)",file.getOriginalFilename()));
        }

        return s3UploadService.getFileUrl(fileName);
    }
    //기존 확장자명을 유지한 채, 유니크한 파일의 이름을 생성하는 로직
    private String createdFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }
    //파일의 확장자명을 가져오는 로직
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다", fileName));
        }
    }

    public void uploadImageToPost(MultipartFile file, Post post) {
        String fileName = createdFileName(file.getOriginalFilename());
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//
//        objectMetadata.setContentLength(file.getSize());
//        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            InputStream resizedPhoto = resizePhoto(inputStream, 600, 600, getFileExtension(file.getOriginalFilename()));

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(resizedPhoto.available());
            objectMetadata.setContentType(getFileExtension(getFileExtension(file.getOriginalFilename())));

            s3UploadService.uploadFile(resizedPhoto, objectMetadata, fileName);
            savePhotoToPost(file, fileName, post);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발행하였습니다 (%s)",file.getOriginalFilename()));
        }

//        return s3UploadService.getFileUrl(fileName);
    }

    private void savePhotoToPost(MultipartFile file, String route, Post post) {
        Photo photo = new Photo();
        photo.setFileName(file.getOriginalFilename());
        photo.setRoute(baseUrl + route);
        photo.setFileSize(file.getSize());
        photo.setPost(post);
    }

    public InputStream resizePhoto(InputStream inputStream, int width, int height, String extension) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputStream);
        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImage,0,0, width, height, null);
        graphics2D.dispose();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, extension, baos);

        return new ByteArrayInputStream(baos.toByteArray());
    }


}
