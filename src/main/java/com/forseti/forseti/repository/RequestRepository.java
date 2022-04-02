package com.forseti.forseti.repository;
import com.forseti.forseti.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Component;

@Component
public interface RequestRepository extends MongoRepository<Request, String> {

}
