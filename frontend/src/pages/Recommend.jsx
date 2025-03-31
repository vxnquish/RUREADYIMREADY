import React, { useState } from 'react';
import { fetchRecommendation } from '../api/flask';

export default function Recommend() {
  const [location, setLocation] = useState('');
  const [result, setResult] = useState([]);

  const handleSearch = async () => {
    const res = await fetchRecommendation(location);
    setResult(res.result);
  };

  return (
    <div>
      <h1>데이트 코스 추천 🗺️</h1>
      <input value={location} onChange={e => setLocation(e.target.value)} placeholder="지역을 입력하세요 (예: 서울)" />
      <button onClick={handleSearch}>추천 받기</button>
      <ul>
        {result.map((item, idx) => <li key={idx}>{item}</li>)}
      </ul>
    </div>
  );
}
