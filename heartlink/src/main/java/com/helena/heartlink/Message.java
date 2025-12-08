package com.helena.heartlink;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // "이건 데이터베이스에 저장할 녀석이야!" 라고 도장 쾅!
@Getter // "이 안에 든 내용을 꺼내 볼 수 있게 해줘!"
@Setter // "이 안에 내용을 수정할 수 있게 해줘!"
@NoArgsConstructor // "빈 종이도 일단 만들어줘!"
public class Message {

    @Id // "이건 주민등록번호 같은 고유 번호야."
    @GeneratedValue(strategy = GenerationType.IDENTITY) // "번호는 1, 2, 3... 자동으로 매겨줘."
    private Long id;

    private String nickname; // 작성자 닉네임 (예: 헬레나)
    private String content;  // 하고 싶은 말 (예: 너 천재야!)
    private String password; // 삭제할 때 쓸 비밀번호

    // 생성자: 처음에 글을 쓸 때 필요한 것들을 묶어주는 역할
    public Message(String nickname, String content, String password) {
        this.nickname = nickname;
        this.content = content;
        this.password = password;
    }
}
