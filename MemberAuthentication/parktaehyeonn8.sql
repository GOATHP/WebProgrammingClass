-- 회원 테이블 생성
DROP TABLE MEMBERR;

CREATE TABLE MEMBERR  (
  id VARCHAR2(255) NOT NULL,
  name VARCHAR2(255) NOT NULL,
  password VARCHAR2(255) NOT NULL,
  contact VARCHAR2(255) UNIQUE NOT NULL,
  email VARCHAR2(255) UNIQUE,
  status VARCHAR2(20) CHECK (status IN ('승인 전', '정상', '일시정지')) NOT NULL,
  role VARCHAR2(20) CHECK (role IN ('일반사용자', '관리자')) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE MEMBERR ADD withdraw CHAR(1) DEFAULT 'F' CHECK (withdraw IN ('T', 'F'));

COMMIT;

SELECT * FROM MEMBERR;

INSERT INTO MEMBERR (id,  name, password, contact, email, status, role)
VALUES
  ('TEST','회원1', '1234', '010-9999-9999', 'user1@example.com', '승인 전', '일반사용자');

INSERT INTO MEMBERR (id, name, password, contact, email, status, role) VALUES
  ('admin', '관리자1', 'admin1234', '010-XXXX-XXXX', 'admin1@example.com', '정상', '관리자');
-- 회원 테이블 조회 예시: 모든 회원 조회
SELECT * FROM MEMBERR;
commit;

select * from memberr where id = 'goat';
select * from memberr where id = 'admin';

-- 회원 테이블 조회 예시: 일반사용자의 연락처 조회
SELECT contact FROM USER WHERE role = '일반사용자';

-- 회원 테이블 업데이트 예시: 회원 상태 변경
UPDATE USER SET status = '일시정지' WHERE username = 'user1';

-- 회원 테이블 삭제 예시: 회원 삭제
DELETE FROM USER WHERE id = 2;
