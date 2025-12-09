package com.helena.heartlink;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 데이터 베이스에 저장
@Getter // 내용 열람
@Setter // 내용 수정
@NoArgsConstructor // 빈 내용 허가
public class Message {

    @Id // "이건 주민등록번호 같은 고유 번호야."
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 순서 지정
    private Long id;

    private String nickname; // 작성자 닉네임
    private String content;  // 하고 싶은 말
    private String password; // 삭제할 때 쓸 비밀번호

    // 생성자: 처음에 글을 쓸 때 필요한 것들을 묶어주는 역할
    public Message(String nickname, String content, String password) {
        this.nickname = nickname;
        this.content = content;
        this.password = password;
    }
}
