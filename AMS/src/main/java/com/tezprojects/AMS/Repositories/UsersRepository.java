package com.tezprojects.AMS.Repositories;

import com.tezprojects.AMS.Models.UsersModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<UsersModel,Integer> {
}
