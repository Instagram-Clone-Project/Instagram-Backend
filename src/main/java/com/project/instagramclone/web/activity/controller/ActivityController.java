package com.project.instagramclone.web.activity.controller;

import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.web.activity.dto.ActivityDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @ApiOperation(value = "알람 목록")
    @GetMapping("/api/activity")
    public ResponseEntity<ActivityDto> getAlarmList(@AuthenticationPrincipal PrincipalDetails userDetails){
        return null;
    }
}
