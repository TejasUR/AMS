package com.tezprojects.AMS.Repositories;

import com.tezprojects.AMS.Models.MarkAttendenceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkAttendenceRepository extends MongoRepository<MarkAttendenceModel,String> {
}
