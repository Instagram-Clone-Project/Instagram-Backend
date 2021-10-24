package com.project.instagramclone.web.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryRequestDto {

    private String email;
    private String newPassword;
    private String confirmPassword;
}
