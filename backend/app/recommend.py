def get_date_recommendation(location):
    recommendations = {
        "서울": {
            "카페": ["익선동 감성 카페", "연남동 루프탑"],
            "자연": ["남산 둘레길", "한강 피크닉"]
        },
        "부산": {
            "바다": ["해운대 야경", "광안리 밤산책"],
            "데이트": ["영도 흰여울길", "오륙도 스카이워크"]
        }
    }

    if location in recommendations:
        return [place for sublist in recommendations[location].values() for place in sublist]
    else:
        return ["해당 지역에 대한 정보가 부족해요 😢"]
