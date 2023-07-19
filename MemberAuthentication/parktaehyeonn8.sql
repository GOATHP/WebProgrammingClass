-- ȸ�� ���̺� ����
DROP TABLE MEMBERR;

CREATE TABLE MEMBERR  (
  id VARCHAR2(255) NOT NULL,
  name VARCHAR2(255) NOT NULL,
  password VARCHAR2(255) NOT NULL,
  contact VARCHAR2(255) UNIQUE NOT NULL,
  email VARCHAR2(255) UNIQUE,
  status VARCHAR2(20) CHECK (status IN ('���� ��', '����', '�Ͻ�����')) NOT NULL,
  role VARCHAR2(20) CHECK (role IN ('�Ϲݻ����', '������')) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE MEMBERR ADD withdraw CHAR(1) DEFAULT 'F' CHECK (withdraw IN ('T', 'F'));

COMMIT;

SELECT * FROM MEMBERR;

INSERT INTO MEMBERR (id,  name, password, contact, email, status, role)
VALUES
  ('TEST','ȸ��1', '1234', '010-9999-9999', 'user1@example.com', '���� ��', '�Ϲݻ����');

INSERT INTO MEMBERR (id, name, password, contact, email, status, role) VALUES
  ('admin', '������1', 'admin1234', '010-XXXX-XXXX', 'admin1@example.com', '����', '������');
-- ȸ�� ���̺� ��ȸ ����: ��� ȸ�� ��ȸ
SELECT * FROM MEMBERR;
commit;

select * from memberr where id = 'goat';
select * from memberr where id = 'admin';

-- ȸ�� ���̺� ��ȸ ����: �Ϲݻ������ ����ó ��ȸ
SELECT contact FROM USER WHERE role = '�Ϲݻ����';

-- ȸ�� ���̺� ������Ʈ ����: ȸ�� ���� ����
UPDATE USER SET status = '�Ͻ�����' WHERE username = 'user1';

-- ȸ�� ���̺� ���� ����: ȸ�� ����
DELETE FROM USER WHERE id = 2;
