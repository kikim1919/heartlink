package com.helena.heartlink;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // 아무것도 안 적어도 저장, 조회, 삭제 기능이 자동으로 생김
}
