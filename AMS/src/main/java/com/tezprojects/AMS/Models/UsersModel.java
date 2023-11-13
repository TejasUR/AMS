package com.tezprojects.AMS.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@Document(collection = "IDs")
public class UsersModel {
    @Id
    private Integer id;
    @Field
    private String name;
    private String profession;
}
