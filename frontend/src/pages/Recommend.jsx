import React, { useState } from 'react';
import { fetchRecommendation } from '../api/flask';

export default function Recommend() {
  const [location, setLocation] = useState('');
  const [results, setResults] = useState([]);

  const handleSearch = async () => {
    if (!location) return;
    const res = await fetchRecommendation(location);
    setResults(res.result);
  };

  return (
    <div>
      <h2>🗺️ 데이트 코스 추천</h2>
      <input value={location} onChange={e => setLocation(e.target.value)} placeholder="예: 서울, 부산" />
      <button onClick={handleSearch}>추천 받기</button>
      <ul>
        {results.map((r, i) => <li key={i}>{r}</li>)}
      </ul>
    </div>
  );
}
