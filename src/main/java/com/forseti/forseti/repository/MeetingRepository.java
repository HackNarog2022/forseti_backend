package com.forseti.forseti.repository;

import com.forseti.forseti.model.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Component;

@Component
public interface MeetingRepository extends MongoRepository<Meeting, String> {
}
