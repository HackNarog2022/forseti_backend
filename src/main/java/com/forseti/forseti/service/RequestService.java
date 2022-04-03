package com.forseti.forseti.service;

import com.forseti.forseti.model.Request;
import com.forseti.forseti.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {
    private RequestRepository repository;
    private MatchingExecutor matchingExecutor;
    private TextAnalyticsService textAnalyticsService;

    public Request addRequest(Request request) {
        request.setNegative(textAnalyticsService.isNegative(request.getFreeText()));
        Request createdRequest = repository.insert(request);
        matchingExecutor.scheduleMatching(createdRequest);
        return createdRequest;
    }

    public List<Request> getAll() {
        return repository.findAll();
    }

    public Request getById(String id) {
        return repository.findById(id).orElseThrow();
    }

}
