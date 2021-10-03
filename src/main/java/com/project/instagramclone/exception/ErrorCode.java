package com.project.instagramclone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //409 CONFLICT : Response 의 현재 상태와 충돌, 보통 중복된 데이터 존재
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 EMAIL 이 존재합니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 USERNAME 입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
