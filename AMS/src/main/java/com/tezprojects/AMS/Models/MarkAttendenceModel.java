package com.tezprojects.AMS.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "MarkedAttendence")
public class MarkAttendenceModel {
    @Id
    private String id;
    private String studentList;
}
