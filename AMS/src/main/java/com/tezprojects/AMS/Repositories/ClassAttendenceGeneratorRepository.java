package com.tezprojects.AMS.Repositories;

import com.tezprojects.AMS.Models.ClassAttendenceGeneratorModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassAttendenceGeneratorRepository extends MongoRepository<ClassAttendenceGeneratorModel,String>{
}
