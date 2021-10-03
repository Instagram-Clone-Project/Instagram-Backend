package com.project.instagramclone.service;

import com.project.instagramclone.domain.photo.entity.Photo;
import com.project.instagramclone.domain.photo.repository.PhotoRepository;
import com.project.instagramclone.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.File;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Transactional
    public Long photoSave(Photo photo) {
        photoRepository.save(photo);
        return photo.getPhotoId();
    }


    @Transactional
    public void photoRemove(Photo photo) {
        photoRepository.delete(photo);
    }

    public List<Photo> findAllPhotoByPostId(Long postId) {
        return photoRepository.findPhotoListByPostId(postId);
    }


}
