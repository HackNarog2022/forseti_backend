package com.forseti.forseti.service;

import com.forseti.forseti.model.Request;
import com.forseti.forseti.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Request> getAllUserRequests(String userId) {
        return repository.findAll()
                .stream()
                .filter(request -> request.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Request getById(String id) {
        return repository.findById(id).orElseThrow();
    }

}
