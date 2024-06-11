import React, { useState } from "react";
import { Box, TextField, Button, Typography } from "@mui/material";

const ChatApp = () => {
  const [messages, setMessages] = useState([]); // 채팅 메시지를 저장하는 상태
  const [inputText, setInputText] = useState(""); // 입력창에 입력된 텍스트를 저장하는 상태

  // 채팅 입력창에서 텍스트를 변경할 때 호출되는 함수
  const handleInputChange = (event) => {
    setInputText(event.target.value); // 입력창에 입력된 텍스트를 상태에 반영
  };

  // 채팅 전송 버튼을 클릭할 때 호출되는 함수
  const handleSendMessage = () => {
    if (inputText.trim() !== "") { // 입력된 텍스트가 공백이 아닌 경우에만 처리
      setMessages([...messages, inputText]); // 이전 메시지에 새로운 메시지를 추가하여 상태를 업데이트
      setInputText(""); // 입력창 초기화
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        height: "100vh", // 화면 전체 높이
      }}
    >
      {/* 채팅 메시지를 표시하는 영역 */}
      <Box
        sx={{
          backgroundColor: "#f0f0f0", // 채팅 영역의 배경색
          padding: "20px",
          borderRadius: "10px",
          marginBottom: "20px",
          width: "300px",
        }}
      >
        {messages.map((message, index) => (
          <Typography key={index} variant="body1">
            {message}
          </Typography>
        ))}
      </Box>
      {/* 채팅 입력창과 전송 버튼 */}
      <Box>
        <TextField
          type="text"
          value={inputText}
          onChange={handleInputChange}
          placeholder="메시지를 입력하세요..."
          variant="outlined"
        />
        <Button variant="contained" onClick={handleSendMessage}>
          전송
        </Button>
      </Box>
    </Box>
  );
};

export default ChatApp;
