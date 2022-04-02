package com.forseti.forseti.service;

import com.forseti.forseti.repository.SuggestedPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuggestedPlaceService {
    private SuggestedPlaceRepository suggestedPlaceRepository;
}
