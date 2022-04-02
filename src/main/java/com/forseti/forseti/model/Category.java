package com.forseti.forseti.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
public class Category {

    @Id
    private String name;
    private Set<Inspiration> inspirations;
}
