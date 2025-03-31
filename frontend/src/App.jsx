import React from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Chat from './pages/Chat'
import Recommend from './pages/Recommend'

const App = () => (
  <Router>
    <div>
      {/* ✅ 상단 메뉴 바 */}
      <nav style={{ padding: '1rem', borderBottom: '1px solid #ccc' }}>
        <Link to="/" style={{ marginRight: '1rem' }}>🏠 Home</Link>
        <Link to="/chat" style={{ marginRight: '1rem' }}>💌 연애 상담</Link>
        <Link to="/recommend">🗺️ 데이트 추천</Link>
      </nav>

      {/* ✅ 페이지 내용 */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/chat" element={<Chat />} />
        <Route path="/recommend" element={<Recommend />} />
      </Routes>
    </div>
  </Router>
)

export default App
