use scheduel;

CREATE TABLE scheduel(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    todo VARCHAR(50) NULL COMMENT '할일',
    name VARCHAR(15) NOT NULL COMMENT '이름',
    password VARCHAR(30) NOT NULL COMMENT '비밀번호',
    date DATETIME NOT NULL COMMENT '작성일'
);