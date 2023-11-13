package com.tezprojects.AMS.Models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarkAttendenceRequestModel {
    private String id;
    private Integer studentId;
}
