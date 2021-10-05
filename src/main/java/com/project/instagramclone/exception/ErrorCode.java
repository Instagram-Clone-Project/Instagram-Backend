package com.project.instagramclone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST : 잘못된 요청
    MISMATCH_AUTH_CODE(HttpStatus.BAD_REQUEST, "인증코드가 일치하지 않습니다."),
    MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    MISMATCH_OLD_PASSWORD(HttpStatus.BAD_REQUEST, "이전 비밀번호가 잘못 입력되었습니다. 다시 입력해주세요."),
    MISMATCH_CONFIRM_PASSWORD(HttpStatus.BAD_REQUEST, "두 비밀번호가 일치하는지 확인하세요."),

    //404 NOT_FOUND : Resource 를 찾을 수 없음
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다."),

    //409 CONFLICT : Resource 의 현재 상태와 충돌, 보통 중복된 데이터 존재
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 EMAIL 이 존재합니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 USERNAME 입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
