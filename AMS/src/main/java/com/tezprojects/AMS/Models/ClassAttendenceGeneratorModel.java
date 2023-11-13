package com.tezprojects.AMS.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "ClassAttendenceGenerator")
@Builder
@Data
public class ClassAttendenceGeneratorModel {
    @Id
    private String id;
    @Field
    private Date startTime;
    private Date endTime;
    private Integer teacherId;
}
