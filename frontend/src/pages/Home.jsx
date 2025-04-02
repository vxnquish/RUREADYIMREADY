import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

export default function Home() {
  return (
    <div className="home-container">
      <h1>💘 RUREADYIMREADY</h1>
      <p>연애에 어려움을 겪는 사람들을 위한 종합 연애 플랫폼</p>

      <div className="home-buttons">
        <Link to="/chat">
          <button className="home-button">💬 연애 상담</button>
        </Link>
        <Link to="/recommend">
          <button className="home-button">📍 데이트 코스 추천</button>
        </Link>
      </div>

      <footer className="home-footer">
        © 2025 RUREADYIMREADY | Made with 💖
      </footer>
    </div>
  );
}