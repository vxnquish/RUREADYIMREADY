import React, { useState } from 'react';
import './Forum.css';

export default function Forum() {
  const [posts, setPosts] = useState([]);
  const [search, setSearch] = useState('');
  const [newPost, setNewPost] = useState('');

  const handlePost = () => {
    if (newPost.trim() === '') return;
    const newEntry = {
      id: Date.now(),
      content: newPost,
      time: new Date().toLocaleTimeString()
    };
    setPosts([newEntry, ...posts]);
    setNewPost('');
  };

  const filteredPosts = posts.filter(p => p.content.includes(search));

  return (
    <div className="forum-container">
      <h2>📢 익명 게시판</h2>
      <div className="forum-top">
        <input
          type="text"
          value={newPost}
          onChange={e => setNewPost(e.target.value)}
          placeholder="사연을 남겨보세요"
        />
        <button onClick={handlePost}>작성</button>
      </div>

      <div className="forum-sub">
        <input
          type="text"
          value={search}
          onChange={e => setSearch(e.target.value)}
          placeholder="검색어 입력"
        />
        <span className="visitor-count">👀 접속자 수: 약 {Math.floor(Math.random() * 100) + 1}명</span>
      </div>

      <ul className="forum-posts">
        {filteredPosts.length === 0 ? (
          <li className="empty">게시물이 없습니다.</li>
        ) : (
          filteredPosts.map(post => (
            <li key={post.id}>
              <p>{post.content}</p>
              <small>🕒 {post.time}</small>
            </li>
          ))
        )}
      </ul>
    </div>
  );
}
