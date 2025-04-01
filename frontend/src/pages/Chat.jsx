// ✅ Chat.jsx - 다채롭게 개선된 연애 상담 UI
import React, { useState } from 'react';
import { askLoveAdvice } from '../api/flask';

export default function Chat() {
  const [message, setMessage] = useState('');
  const [history, setHistory] = useState([]);

  const handleSend = async () => {
    if (!message) return;
    const res = await askLoveAdvice(message);
    const newEntry = {
      question: message,
      answer: res.response,
      time: new Date().toLocaleTimeString()
    };
    setHistory([...history, newEntry]);
    setMessage('');
  };

  return (
    <div style={{ padding: '1rem' }}>
      <h2>💌 연애 상담</h2>
      <input
        value={message}
        onChange={e => setMessage(e.target.value)}
        placeholder="고민을 입력하세요"
        style={{ marginRight: '0.5rem' }}
      />
      <button onClick={handleSend}>상담 요청</button>

      <ul style={{ marginTop: '1rem' }}>
        {history.map((entry, i) => (
          <li key={i} style={{ marginBottom: '1rem' }}>
            <strong>😟 당신: </strong> {entry.question}<br />
            <strong>🤖 AI: </strong> {entry.answer}<br />
            <small style={{ color: 'gray' }}>🕒 {entry.time}</small>
          </li>
        ))}
      </ul>
    </div>
  );
}
