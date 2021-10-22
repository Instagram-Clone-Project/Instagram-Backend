package com.project.instagramclone.domain.user.vo;

import com.project.instagramclone.domain.photo.entity.Photo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImageVo {

    @ApiModelProperty(value = "게시물 사진 URL")
    private String postImageUrl;

    @Builder
    private PostImageVo(Photo image) {
        this.postImageUrl = image.getRoute();
    }
}
