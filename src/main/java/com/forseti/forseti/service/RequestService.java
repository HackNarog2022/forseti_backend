package com.forseti.forseti.service;

import com.forseti.forseti.model.Category;
import com.forseti.forseti.model.ExpertiseEnum;
import com.forseti.forseti.model.Request;
import com.forseti.forseti.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestService {
    private RequestRepository repository;
    private MatchingExecutor matchingExecutor;

    public Request addRequest(Request request) {
        Request createdRequest = repository.insert(request);
        matchingExecutor.scheduleMatching(createdRequest);
        return createdRequest;
    }


}
