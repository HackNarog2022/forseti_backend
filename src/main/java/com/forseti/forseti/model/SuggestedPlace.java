package com.forseti.forseti.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class SuggestedPlace {
    @Id
    private String id;
    private String location;
    private String name;
    private String description;
}
