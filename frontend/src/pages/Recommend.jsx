import React, { useState } from 'react';
import { fetchRecommendation } from '../api/flask';

export default function Recommend() {
  const [location, setLocation] = useState('');
  const [results, setResults] = useState([]);

  const handleSearch = async () => {
    if (!location) return;
    const res = await fetchRecommendation(location);
    const enriched = res.result.map((item, i) => ({
      name: item,
      description: getDescription(item),
      rank: i + 1
    }));
    setResults(enriched);
  };

  const getDescription = (place) => {
    if (place.includes("한강")) return "서울 데이트의 클래식! 강바람 맞으며 산책해요 🌊";
    if (place.includes("피크닉")) return "따뜻한 날엔 도시락 싸서 여유롭게 🧺";
    if (place.includes("야경")) return "낭만적인 밤의 마무리 💫";
    return "추천된 인기 장소입니다!";
  };

  return (
    <div>
      <h2>🗺️ 데이트 코스 추천</h2>
      <input value={location} onChange={e => setLocation(e.target.value)} placeholder="예: 서울, 부산" />
      <button onClick={handleSearch}>추천 받기</button>

      <ol style={{ marginTop: '1rem' }}>
        {results.map((place, idx) => (
          <li key={idx} style={{ marginBottom: '1rem' }}>
            <strong>{place.name}</strong>  
            <div style={{ fontStyle: 'italic', color: 'gray' }}>{place.description}</div>
          </li>
        ))}
      </ol>
    </div>
  );
}
