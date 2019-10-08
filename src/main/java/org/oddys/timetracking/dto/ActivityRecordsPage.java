package org.oddys.timetracking.dto;

import java.util.List;

public class ActivityRecordsPage extends PageDto<ActivityRecordDto> {
    private Long userActivityId;
    private Boolean assigned;

    public ActivityRecordsPage() {}

    public ActivityRecordsPage(List<ActivityRecordDto> elements, int currentPage,
            int rowsPerPage, int numPages, Long userActivityId, Boolean assigned) {
        super(elements, currentPage, rowsPerPage, numPages);
        this.userActivityId = userActivityId;
        this.assigned = assigned;
    }

    public Long getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(Long userActivityId) {
        this.userActivityId = userActivityId;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }
}
