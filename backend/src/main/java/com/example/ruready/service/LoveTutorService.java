// ✅ LoveTutorService.java - 더 다양한 연애 조언 추가
package com.example.ruready.service;

import org.springframework.stereotype.Service;

@Service
public class LoveTutorService {

    public String getAdvice(String message) {
        String lower = message.toLowerCase();

        if (lower.contains("짝사랑")) {
            return "짝사랑은 설레지만 아픈 감정이에요. 진심을 전할 용기를 내보는 건 어때요? 💌";
        } else if (lower.contains("고백")) {
            return "고백은 타이밍과 분위기가 중요해요. 상대방이 편안한 순간을 노려보세요 😊";
        } else if (lower.contains("이별")) {
            return "이별은 누구에게나 힘든 일이에요. 너무 자책하지 말고 자신을 돌보는 시간을 가져보세요 💔";
        } else if (lower.contains("썸")) {
            return "썸은 서로의 관심이 피어나는 시간이에요. 가볍게 커피 한 잔 제안해보는 건 어떨까요? ☕";
        } else if (lower.contains("데이트")) {
            return "데이트는 함께하는 시간이 가장 중요해요. 작은 이벤트나 산책도 좋은 추억이 될 수 있어요 💑";
        } else if (lower.contains("연애")) {
            return "연애는 서로의 다름을 이해하고 존중하는 과정이에요. 대화로 마음을 자주 나눠보세요 ❤️";
        } else if (lower.contains("장거리")) {
            return "장거리 연애는 신뢰와 소통이 핵심이에요. 규칙적인 연락과 서프라이즈가 큰 힘이 돼요 📱";
        } else if (lower.contains("연락")) {
            return "상대방이 자주 연락을 하지 않는다면, 이유를 물어보는 대화가 필요할 수도 있어요 📞";
        } else if (lower.contains("권태기")) {
            return "권태기는 대부분의 연인들이 겪어요. 새로운 추억을 만들거나, 잠시 거리두기도 도움이 될 수 있어요 🔄";
        } else {
            return "그 고민에 대해 조금 더 자세히 이야기해줄래요? 함께 고민해볼게요 😌";
        }
    }
}
