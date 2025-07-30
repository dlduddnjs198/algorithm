# 💣 **하나의 트랜잭션 안에서 DDL과 DML을 함께 써도 될까?**

> ✅ 결론 : PostgreSQL만 괜찮고, 나머지는 자동 커밋 지옥에 빠진다
>

---

## 1️⃣ [🚨 결론 먼저: PostgreSQL을 제외하면 **DDL + DML 함께 쓰면 안 된다**]

- MySQL, MariaDB, Oracle 등은 **트랜잭션 블록 내에서 DDL을 실행하는 순간 자동 커밋 발생**
- 이 때문에 그 이전의 DML(INSERT 등)까지 **강제 커밋되어 ROLLBACK이 무의미**
- PostgreSQL은 예외. 대부분의 DDL이 트랜잭션 내에서도 **정상적으로 롤백 가능**

🧠 **한 줄 요약**

> "DDL도 트랜잭션 안에 넣어도 되나?" → PostgreSQL만 OK, 나머지는 위험
>

---

## 2️⃣ [🔄 예시로 보는 동작 차이]

```sql
START TRANSACTION;

-- DML 실행
INSERT INTO test (id) VALUES (1);

-- DDL 실행 (암묵적 커밋 발생!)
CREATE TABLE test2 (id INT);

ROLLBACK; -- 의미 없음. INSERT는 이미 커밋됨
```

- 위 코드는 PostgreSQL에서는 롤백 가능
- 하지만 MySQL, MariaDB, Oracle에선 **CREATE TABLE 실행 순간 INSERT도 커밋됨**

---

## 3️⃣ [🧩 각 DBMS별 DDL 트랜잭션 처리 방식 비교]

| DBMS | DDL 실행 시점에 커밋 발생? | 트랜잭션 내 DDL-Rollback 가능? | 특이사항 |
| --- | --- | --- | --- |
| **MySQL** | ✅ 암묵적 커밋 | ❌ 불가 | `CREATE TEMPORARY TABLE`은 예외 |
| **MariaDB** | ✅ 암묵적 커밋 | ❌ 불가 | MySQL과 동일 동작 |
| **Oracle** | ✅ 암묵적 커밋 | ❌ 불가 | `CREATE`, `ALTER`, `DROP` 전부 자동 커밋 |
| **PostgreSQL** | ❌ 자동 커밋 없음 | ✅ 가능 | 단, `CREATE DATABASE`, `TABLESPACE` 등은 예외 |

---

## 4️⃣ [⚙️ 내부 구조 차이: 왜 PostgreSQL만 가능한가?]

### 🔸 MySQL(InnoDB), Oracle 등

- MVCC는 **사용자 데이터에만 적용**,
- 메타데이터(테이블 정의 등)는 별도 구조로 관리됨 → **롤백 불가**

### 🔸 PostgreSQL

- **시스템 카탈로그조차 일반 테이블처럼 관리**
- 테이블 정의 정보도 MVCC 대상 → **DDL도 트랜잭션으로 안전하게 감쌀 수 있음**

🧠 PostgreSQL에서 가능한 이유는 **아키텍처 자체의 차이** 때문

---

## 5️⃣ [📌 실무 팁 요약]

| 상황 | 권장 여부 | 설명 |
| --- | --- | --- |
| MySQL/MariaDB에서 트랜잭션 중 DDL | ❌ 지양 | DML까지 강제 커밋됨 |
| Oracle에서 트랜잭션 중 DDL | ❌ 지양 | 즉시 커밋 발생 |
| PostgreSQL에서 트랜잭션 중 DDL | ✅ 사용 가능 | 대부분 롤백 가능 |
| 임시 테이블 생성 (MySQL) | ⚠️ 가능 | `CREATE TEMPORARY TABLE`은 예외로 커밋 발생 안 함 |

---

## ✨ 마무리: 왜 이걸 꼭 알아야 할까?

> 💬 "내가 INSERT 해놓고 ROLLBACK 했는데 왜 반영됐지?"
>
>
> → 대부분 **중간에 DDL 넣었다가 강제 커밋된 것**
>
- 특히 **마이그레이션 스크립트나 테이블 생성 로직에 주의**
- **PostgreSQL은 신뢰 가능**, 나머지는 **DDL은 트랜잭션 밖에서 따로 처리**하는 게 안전

---

## 🔗 참고자료

- [MySQL: Implicit Commit Statements](https://dev.mysql.com/doc/refman/8.4/en/implicit-commit.html)
- [MariaDB: Statements That Cause an Implicit Commit](https://mariadb.com/kb/en/sql-statements-that-cause-an-implicit-commit/)
- [Oracle SQL Statement Types](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/Types-of-SQL-Statements.html)
- [PostgreSQL System Catalogs](https://www.postgresql.org/docs/current/catalogs.html)

---

출처 : https://dkswnkk.tistory.com/767