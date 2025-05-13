import React, { useState } from 'react';
import { fetchRecommendation } from '../api/flask';
import './Recommend.css';

const locationOptions = ["서울", "부산", "대전", "제주", "인천"];

export default function Recommend() {
  const [location, setLocation] = useState('');
  const [results, setResults] = useState([]);

  const handleSearch = async () => {
    if (!location) return;
    const res = await fetchRecommendation(location);
    setResults(res.result.map(name => ({
      name,
      description: getDescription(name)
    })));
  };

  const getDescription = (place) => {
    if (place.includes("한강")) return "서울 데이트의 정석! 시원한 강바람과 함께 💨";
    if (place.includes("북촌")) return "전통 감성과 고요한 산책이 가능한 한옥마을 🏡";
    if (place.includes("광안리")) return "바다와 야경이 아름다운 낭만 명소 🌊";
    if (place.includes("제주")) return "청정 자연과 이국적인 분위기의 완벽한 휴식 🌴";
    if (place.includes("해운대")) return "바다와 모래사장이 어우러진 부산의 대표 핫플 💕";
    if (place.includes("경복궁")) return "역사와 낭만이 흐르는 도심 속 궁궐 데이트 👑";
    if (place.includes("카페") || place.includes("거리")) return "감성 카페 거리에서 여유로운 데이트 ☕️";
    return "연인과 함께 하기 좋은 장소입니다.";
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') handleSearch();
  };

  return (
    <div className="recommend-container">
      <h2>🗺️ 데이트 코스 추천</h2>
      <p className="recommend-subtitle">도시를 선택하거나 입력해보세요. 연인과 즐기기 좋은 장소를 추천해드립니다!</p>

      <div className="locations">
        {locationOptions.map((loc, i) => (
          <button key={i} onClick={() => setLocation(loc)}>{loc}</button>
        ))}
      </div>

      <div className="input-group">
        <input
          value={location}
          onChange={e => setLocation(e.target.value)}
          onKeyDown={handleKeyDown}
          placeholder="예: 서울, 부산"
        />
        <button onClick={handleSearch}>추천 받기</button>
      </div>

      {results.length > 0 ? (
        <ol className="recommend-list">
          {results.map((place, idx) => (
            <li key={idx}>
              <strong>{place.name}</strong><br />
              <span>{place.description}</span>
            </li>
          ))}
        </ol>
      ) : (
        location && (
          <div className="no-result">
            <p><strong>추천할 장소가 없어요 😢</strong></p>
            <p>연인과 함께 하기 좋은 장소입니다.</p>
          </div>
        )
      )}
    </div>
  );
}
