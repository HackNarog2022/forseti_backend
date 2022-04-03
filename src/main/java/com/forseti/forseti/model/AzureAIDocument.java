package com.forseti.forseti.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor

public class AzureAIDocument {
    Collection<Document> documents;
}
