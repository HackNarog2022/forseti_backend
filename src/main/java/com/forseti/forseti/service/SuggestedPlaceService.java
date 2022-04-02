package com.forseti.forseti.service;

import com.forseti.forseti.model.SuggestedPlace;
import com.forseti.forseti.repository.SuggestedPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SuggestedPlaceService {
    private SuggestedPlaceRepository suggestedPlaceRepository;

    public Collection<SuggestedPlace> getSuggestedPlacesForLocation(String location) {
        return suggestedPlaceRepository.findAll()
                .stream()
                .filter(place -> place.getLocation().equals(location))
                .collect(Collectors.toList());
    }
}
