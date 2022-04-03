package com.forseti.forseti.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class Request {

    @Id
    private String requestId;
    private User user;
    private Category category;
    private String freeText;
    private boolean isNegative;
    private String place;
    private ExpertiseEnum expectedExpertise;
    private ExpertiseEnum declaredExpertise;
    // these dates define the validity of the request
    private Date startDate;
    private Date endDate;
}
