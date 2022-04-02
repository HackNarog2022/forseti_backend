package com.forseti.forseti.repository;

import com.forseti.forseti.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepository extends MongoRepository<Category, String> {
}
