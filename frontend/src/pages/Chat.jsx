import React, { useState } from 'react';
import { askLoveAdvice } from '../api/flask';

export default function Chat() {
  const [message, setMessage] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async () => {
    const res = await askLoveAdvice(message);
    setResponse(res.response);
  };

  return (
    <div>
      <h1>연애 상담소 💌</h1>
      <input value={message} onChange={e => setMessage(e.target.value)} placeholder="고민을 적어보세요" />
      <button onClick={handleSubmit}>상담 요청</button>
      <p>{response}</p>
    </div>
  );
}
