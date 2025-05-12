import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import Chat from './pages/Chat';
import Recommend from './pages/Recommend';
import Forum from './pages/Forum'; // ✅ 게시판 컴포넌트

const App = () => (
  <Router>
    <nav style={{ padding: '1rem', borderBottom: '1px solid #ddd' }}>
      <Link to="/" style={{ marginRight: '1rem' }}>🏠 홈</Link>
      <Link to="/chat" style={{ marginRight: '1rem' }}>💌 연애 상담</Link>
      <Link to="/recommend" style={{ marginRight: '1rem' }}>🗺️ 데이트 추천</Link>
      <Link to="/forum">📢 게시판</Link> {/* ✅ 게시판 링크 추가 */}
    </nav>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/chat" element={<Chat />} />
      <Route path="/recommend" element={<Recommend />} />
      <Route path="/forum" element={<Forum />} /> {/* ✅ 게시판 라우트 추가 */}
    </Routes>
  </Router>
);

export default App;
