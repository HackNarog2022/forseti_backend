package com.forseti.forseti.service;

import com.forseti.forseti.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestService {
    private RequestRepository repository;
}
