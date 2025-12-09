package com.helena.heartlink;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 명찰
@RequiredArgsConstructor // 필요한 도구 불러오기
@CrossOrigin(origins = "*") // 접속 유동성
public class MessageController {

    private final MessageRepository repository;

    // 1. 메시지 조회하기 (GET 요청)
    @GetMapping("/messages")
    public List<Message> getMessages() {
        return repository.findAll();
    }

    // 2. 메시지 작성하기 (POST 요청)
    @PostMapping("/messages")
    public Message writeMessage(@RequestBody Message message) {
        return repository.save(message);
    }

    @DeleteMapping("/messages/{id}")
    public String deleteMessage(@PathVariable Long id, @RequestParam String password) {
        // 1. 저장된 메시지를 찾는다.
        Message message = repository.findById(id).orElse(null);

        // 2. 메시지가 없거나, 비밀번호가 틀리면?
        if (message == null || !message.getPassword().equals(password)) {
            return "비밀번호가 틀렸거나 없는 메시지야!"; // 실패 메시지
        }

        // 3. 맞으면 삭제
        repository.deleteById(id);
        return "삭제 성공!";
    }
}
