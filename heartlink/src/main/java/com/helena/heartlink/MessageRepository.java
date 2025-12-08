package com.helena.heartlink;

import org.springframework.data.jpa.repository.JpaRepository;

// <Message, Long> 뜻: "Message라는 요리를 저장할 거고, 번호표는 Long(숫자) 타입이야."
public interface MessageRepository extends JpaRepository<Message, Long> {
    // 놀랍게도... 아무것도 안 적어도 저장, 조회, 삭제 기능이 자동으로 생겨!
    // 이게 바로 스프링부트의 마법이지!
}