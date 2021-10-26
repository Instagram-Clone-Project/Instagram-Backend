package com.project.instagramclone.web;

import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public void userValidationCheck(Long userA, Long userB){
        if(userA!=userB) throw new CustomException(ErrorCode.NOT_CERTIFIED_USER);
    }


}
