package com.forseti.forseti.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@Builder
public class Meeting {

    @Id
    private String id;
    private Category category;
    private Set<Request> requests;
    private boolean active;
    private Set<String> inspirations;
    private Date date;
    private Collection<SuggestedPlace> suggestedPlaces;
    /**
     * Mao holds user ratings
     * key -> userId
     * value -> rating that this user got from the another user
     */
    private Map<String, Integer> ratings;
}
