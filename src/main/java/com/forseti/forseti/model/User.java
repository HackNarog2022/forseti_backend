package com.forseti.forseti.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class User {
    @Id
    String id;
}
