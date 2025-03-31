def get_date_recommendation(location):
    sample = {
        "서울": ["한강 피크닉", "북촌 한옥마을 산책"],
        "부산": ["해운대 바다 산책", "광안리 야경"]
    }
    return sample.get(location, ["추천할 장소가 없습니다. 다른 지역을 입력해보세요!"])
