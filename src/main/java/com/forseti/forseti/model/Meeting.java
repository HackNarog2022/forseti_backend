package com.forseti.forseti.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Set;

@Data
public class Meeting {

    @Id
    private String id;
    private Category category;
    private Set<Request> requests;
    private MeetingStatus status;
    private Set<Inspiration> inspirations;
    private Date date;
}
