package com.hd.helpDesk.modules.openIA.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@Service
public class AsisstantService {
    private final WebClient webClient;
    @Value("${openai.api.key}")
    private String openaiApiKey;


    public AsisstantService(@Value("${openai.api.key}") String apiKey, @Value("${openai.endpoint}") String apiEndpoint) {
        this.webClient = WebClient.builder()
                .baseUrl(apiEndpoint)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<String> interactWithAssistant(String assistantId) {
        return this.webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/assistants/{assistantId}/sessions").build(assistantId))
                .retrieve()
                .bodyToMono(String.class);
    }
}