package org.oddys.timetracking.dto;

import java.time.LocalDate;

public class ActivityRecordDto {
    Long id;
    String activityName;
    LocalDate activityDate;
    Long duration;
    Long userId;
    Long activityId;
}
