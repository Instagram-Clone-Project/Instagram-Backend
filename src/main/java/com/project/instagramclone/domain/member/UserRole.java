package com.project.instagramclone.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER("ROLE_USER", "사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;
}
