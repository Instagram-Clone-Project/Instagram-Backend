package com.project.instagramclone.web.post.dto;

import com.project.instagramclone.domain.post.entity.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Getter
@Setter
public class PostSaveDto {

    @ApiModelProperty(required = true, value="글 내용")
    private String content;

    @ApiModelProperty(required = true, value="사진")
    private List<MultipartFile> files;

    public Post toEntity(){
        return Post.builder()
                .content(this.content)
                .build();
    }
}
