package com.project.instagramclone.web.home.dto;

import com.project.instagramclone.domain.user.vo.HomePostVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "인스타그램 홈페이지 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeResponseDto {

    @ApiModelProperty(position = 1, value = "홈 게시물 정보")
    List<HomePostVo> posts;

    @Builder
    private HomeResponseDto(List<HomePostVo> posts) {
        this.posts = posts;
    }
}
