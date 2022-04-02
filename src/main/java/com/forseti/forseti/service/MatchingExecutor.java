package com.forseti.forseti.service;

import com.forseti.forseti.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MatchingExecutor {
    private final ExecutorService executor;
    private final MatchingService matchingService;

    public MatchingExecutor(MatchingService matchingService) {
        this.matchingService = matchingService;
        executor = Executors.newFixedThreadPool(8);
    }

    public void scheduleMatching(Request request) {
        executor.submit(() -> {
            matchingService.matchRequest(request);
        });
    }

}
