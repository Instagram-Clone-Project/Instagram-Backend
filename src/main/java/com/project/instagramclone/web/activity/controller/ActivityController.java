package com.project.instagramclone.web.activity.controller;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.ActivityService;
import com.project.instagramclone.web.activity.dto.ActivityDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;


    @ApiOperation(value = "알람 목록")
    @GetMapping("/api/activity")
    public ResponseEntity<ActivityDto> getAlarmList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        return new ResponseEntity<>(activityService.getAlarmList(user), HttpStatus.OK);
    }

}
