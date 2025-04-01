package com.example.ruready.service;

import org.springframework.stereotype.Service;

@Service
public class LoveTutorService {

    public String getAdvice(String message) {
        if (message.contains("짝사랑")) {
            return "짝사랑은 어렵지만, 솔직한 표현이 가장 중요해요!";
        } else if (message.contains("이별")) {
            return "힘든 시간이겠지만, 스스로를 먼저 돌보는 게 우선이에요.";
        } else {
            return "'" + message + "'에 대해선 더 많은 정보를 알려주세요 💡";
        }
    }
}