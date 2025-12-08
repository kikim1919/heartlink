package com.helena.heartlink;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // "나 여기 매니저요!" 하고 명찰 다는 것.
@RequiredArgsConstructor // "필요한 도구(Repository)는 자동으로 챙겨줘."
@CrossOrigin(origins = "*") // "어디서 접속하든 문 열어줘!" (나중에 보안을 위해 수정할 거지만 지금은 프리하게!)
public class MessageController {

    private final MessageRepository repository; // 아까 만든 냉장고

    // 1. 메시지 조회하기 (GET 요청)
    // 손님이 "롤링페이퍼 보여줘!" 하면 냉장고 문 열어서 다 꺼내줌.
    @GetMapping("/messages")
    public List<Message> getMessages() {
        return repository.findAll();
    }

    // 2. 메시지 작성하기 (POST 요청)
    // 손님이 "내 글 남길래!" 하고 쪽지를 주면 냉장고에 넣음.
    @PostMapping("/messages")
    public Message writeMessage(@RequestBody Message message) {
        return repository.save(message);
    }

    // "이 글 지워줘!" 하면 냉장고에서 갖다 버림.
    @DeleteMapping("/messages/{id}")
    public String deleteMessage(@PathVariable Long id, @RequestParam String password) {
        // 1. 저장된 메시지를 찾는다.
        Message message = repository.findById(id).orElse(null);

        // 2. 메시지가 없거나, 비밀번호가 틀리면?
        if (message == null || !message.getPassword().equals(password)) {
            return "비밀번호가 틀렸거나 없는 메시지야!"; // 실패 메시지
        }

        // 3. 맞으면 삭제!
        repository.deleteById(id);
        return "삭제 성공!";
    }
}