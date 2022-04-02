package com.forseti.forseti.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class Meeting {

    @Id
    private String id;
    private Category category;
    private Set<Request> requests;
    private MeetingStatus status;
    private Set<String> inspirations;
    private Date date;
    private Collection<SuggestedPlace> suggestedPlaces;
}
