package com.project.instagramclone.web.activity.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ActivityDto {

    private ArrayList<ActivityListDto> activityListDtos = new ArrayList<>();
}
