package com.forseti.forseti.service;

import com.forseti.forseti.model.SuggestedPlace;
import com.forseti.forseti.repository.SuggestedPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SuggestedPlaceService {
    private SuggestedPlaceRepository suggestedPlaceRepository;

    public Collection<SuggestedPlace> getSuggestedPlacesForLocation(String location) {
        if ("Kraków".equals(location)) {
            return List.of(
                    SuggestedPlace.builder()
                            .name("Forum Przestrzenie")
                            .location("Marii Konopnickiej 28, 30-302 Kraków")
                            .description("Spotkajcie się w tym urokliwym i zacisznym miejscu, by odkrywać tajemnice nowej rzeczywistości")
                            .build(),
                    SuggestedPlace.builder()
                            .name("Miasteczko Studenckie AGH")
                            .location("Józefa Rostafińskiego 7a, 30-072 Kraków")
                            .description("Miejsce to sprzyja merytorycznej wymianie zdań i można tu również spotkać innych ludzi chętnych na poznawanie świata.")
                            .build()
            );
        } else if ("Wadowice".equals(location)) {
            return List.of(
                    SuggestedPlace.builder()
                            .name("Kremówki caffee")
                            .location("plac Jana Pawła II 15, 34-100 Wadowice")
                            .description("Zjedzenie kremówki w tej cukierni napewno umili czas!")
                            .build()
            );
        } else if ("Warszawa".equals(location)) {
            return List.of(
                    SuggestedPlace.builder()
                            .name("Dworzec Główny w Warszawie")
                            .location("Towarowa 3, 00-811 Warszawa")
                            .description("Można tu znaleźć wiele ciekawych kierunków, w które można się udać i w miłych okolicach poszerzać swoje horyzonty")
                            .build()
            );
        } else {
            return suggestedPlaceRepository.findAll()
                    .stream()
                    .filter(place -> place.getLocation().equals(location))
                    .collect(Collectors.toList());
        }
    }
}
