package com.example.ruready.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    public List<String> getRecommendations(String location) {
        Map<String, List<String>> data = new HashMap<>();
        data.put("서울", List.of("한강 피크닉", "북촌 한옥마을 산책"));
        data.put("부산", List.of("광안리 야경", "영도 흰여울길"));

        return data.getOrDefault(location, List.of("추천할 장소가 없어요 😢"));
    }
}