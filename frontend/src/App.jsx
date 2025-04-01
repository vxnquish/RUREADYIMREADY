import React from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Chat from './pages/Chat'
import Recommend from './pages/Recommend'

const App = () => (
  <Router>
    <nav style={{ padding: '1rem', borderBottom: '1px solid #ddd' }}>
      <Link to="/" style={{ marginRight: '1rem' }}>🏠 홈</Link>
      <Link to="/chat" style={{ marginRight: '1rem' }}>💌 연애 상담</Link>
      <Link to="/recommend">🗺️ 데이트 추천</Link>
    </nav>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/chat" element={<Chat />} />
      <Route path="/recommend" element={<Recommend />} />
    </Routes>
  </Router>
)

export default App
