import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './ForumWrite.css';

export default function ForumWrite() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async () => {
    if (!title.trim()) return alert('제목을 입력해주세요.');
    if (!content.trim()) return alert('내용을 입력해주세요.');

    try {
      const response = await fetch('http://localhost:8080/forum/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, content, tags: [] }),
      });

      if (!response.ok) throw new Error('서버 응답 오류');

      // 작성 완료 후 목록으로 이동 (새로 mount 유도)
      navigate('/forum', { replace: true });
    } catch (err) {
      console.error('작성 실패:', err);
      alert('글 작성 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="forum-write">
      <h2>✍️ 게시글 작성</h2>
      <input
        type="text"
        placeholder="제목을 입력하세요"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        placeholder="내용을 입력하세요"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <button onClick={handleSubmit}>작성</button>
    </div>
  );
}
