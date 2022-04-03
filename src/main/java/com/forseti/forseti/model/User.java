package com.forseti.forseti.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @Id
    String id;
    String email;
}
