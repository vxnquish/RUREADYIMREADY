import React, { useState } from 'react';
import { askLoveAdvice } from '../api/flask';

export default function Chat() {
  const [message, setMessage] = useState('');
  const [response, setResponse] = useState('');

  const handleSend = async () => {
    if (!message) return;
    const res = await askLoveAdvice(message);
    setResponse(res.response);
  };

  return (
    <div>
      <h2>💌 연애 상담</h2>
      <input value={message} onChange={e => setMessage(e.target.value)} placeholder="고민을 입력하세요" />
      <button onClick={handleSend}>상담 요청</button>
      {response && <p><strong>AI의 조언:</strong> {response}</p>}
    </div>
  );
}
