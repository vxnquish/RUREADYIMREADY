package com.example.ruready.controller;

import com.example.ruready.service.LoveTutorService;
import com.example.ruready.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdviceController {

    private final LoveTutorService loveTutorService;
    private final RecommendationService recommendationService;

    public AdviceController(LoveTutorService loveTutorService, RecommendationService recommendationService) {
        this.loveTutorService = loveTutorService;
        this.recommendationService = recommendationService;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        String response = loveTutorService.getAdvice(message);
        return Map.of("response", response);
    }

    @GetMapping("/recommend")
    public Map<String, List<String>> recommend(@RequestParam String location) {
        return Map.of("result", recommendationService.getRecommendations(location));
    }
}