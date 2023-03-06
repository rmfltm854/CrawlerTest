package com.example.securitytest.Repository;

import com.example.securitytest.EntityClass.TestCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<TestCollection,String> {

    TestCollection findByName(String name);

}
