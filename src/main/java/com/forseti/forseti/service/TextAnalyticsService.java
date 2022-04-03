package com.forseti.forseti.service;


import com.forseti.forseti.model.AzureAIDocument;
import com.forseti.forseti.model.AzureAnalysedDocuments;
import com.forseti.forseti.model.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextAnalyticsService {
    @Value(value = "${sentiment.key}")
    private String KEY;
    @Value(value = "${sentiment.endpoint}")
    private String ENDPOINT;

    public boolean isNegative(String freeText) {
        if (freeText == null) {
            return false;
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Ocp-Apim-Subscription-Key", KEY);

        AzureAIDocument azureAIDocument = new AzureAIDocument(List.of(new Document("1", freeText)));
        HttpEntity<AzureAIDocument> request = new HttpEntity<>(azureAIDocument, headers);
        ResponseEntity<AzureAnalysedDocuments> response = restTemplate.postForEntity(ENDPOINT + "/text/analytics/v3.2-preview.1/sentiment?opinionMining=true",
                request, AzureAnalysedDocuments.class);

        return Objects.requireNonNull(response.getBody()).getDocuments().stream().findFirst().get().getSentiment().equals("negative");
    }


}
