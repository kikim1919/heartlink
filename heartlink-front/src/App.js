import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  // 1. 상태 관리 (변수 선언)
  const [messages, setMessages] = useState([]);
  const [nickname, setNickname] = useState('');
  const [content, setContent] = useState('');
  const [password, setPassword] = useState('');

  // 2. 서버에서 쪽지 가져오기
  useEffect(() => {
    fetchMessages();
  }, []);

  const fetchMessages = async () => {
    try {
      const response = await axios.get('http://localhost:8080/messages');
      setMessages(response.data);
    } catch (error) {
      console.error("쪽지 가져오기 실패", error);
    }
  };

  // 3. 쪽지 남기기
  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!nickname || !content) {
      alert("이름이랑 내용을 써주세요!");
      return;
    }

    try {
      await axios.post('http://localhost:8080/messages', {
        nickname: nickname,
        content: content,
        password: password
      });
      setNickname('');
      setContent('');
      setPassword('');
      fetchMessages(); 
    } catch (error) {
      console.error("저장 실패", error);
    }
  };

  // 삭제 기능 
  const handleDelete = async (id) => {
    const passwordInput = prompt("삭제하려면 비밀번호를 입력하세요!");
    
    if (!passwordInput) return; 

    try {
      const response = await axios.delete(`http://localhost:8080/messages/${id}`, {
        params: { password: passwordInput }
      });

      if (response.data === "삭제 성공!") {
        alert("깔끔하게 지웠어요요!");
        fetchMessages(); 
      } else {
        alert("비밀번호가 틀린 것 같은데요? (또는 이미 지워짐)");
      }
    } catch (error) {
      console.error("삭제 중 오류 발생", error);
    }
  };

  // 5. 화면 그리기
  return (
    <div className="App">
      <h1>💌 마음을 전해줘 (Heart-Link)</h1>
      
      <div className="input-box">
        <input 
          placeholder="닉네임" 
          value={nickname} 
          onChange={(e) => setNickname(e.target.value)} 
        />
        <input 
          type="password"
          placeholder="비밀번호 (삭제용)" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
        />
        <textarea 
          placeholder="하고 싶은 말을 적어봐!" 
          value={content} 
          onChange={(e) => setContent(e.target.value)} 
        />
        <button onClick={handleSubmit}>마음 남기기</button>
      </div>

      <div className="message-list">
        {messages.map((msg) => (
          <div key={msg.id} className="message-card">
            {/* 삭제 버튼이 여기 있어! */}
            <button 
              className="delete-btn" 
              onClick={() => handleDelete(msg.id)}
            >
              X
            </button>
            
            <h3>From. {msg.nickname}</h3>
            <p>{msg.content}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
