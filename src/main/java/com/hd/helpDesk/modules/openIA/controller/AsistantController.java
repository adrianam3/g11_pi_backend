package com.hd.helpDesk.modules.openIA.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hd.helpDesk.modules.openIA.service.AsisstantService;

import reactor.core.publisher.Mono;

@RestController
public class AsistantController {
    
    private final AsisstantService openAIService;

    public AsistantController(AsisstantService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/interact/{assistantId}")
    public Mono<String> interactWithAssistant(@PathVariable String assistantId) {
        return openAIService.interactWithAssistant(assistantId);
    }
}
