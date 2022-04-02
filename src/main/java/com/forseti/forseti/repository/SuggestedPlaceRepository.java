package com.forseti.forseti.repository;

import com.forseti.forseti.model.SuggestedPlace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface SuggestedPlaceRepository extends MongoRepository<SuggestedPlace, String> {
}
