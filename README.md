# 일정 관리 프로젝트 (Schedule Management System)

Spring Boot와 JPA를 활용하여 일정을 CRUD(등록, 조회, 수정, 삭제)하고, 관련 댓글을 관리할 수 있는 REST API 기반의 프로젝트입니다.
객체 지향적인 설계를 위해 DTO와 Entity를 철저히 분리하였으며, JPA Auditing을 통한 자동 시간 관리 및 트랜잭션 기반의 비즈니스 로직을 구현했습니다.

[![API Docs](https://img.shields.io/badge/API_Docs-View_Redoc-orange?style=for-the-badge&logo=swagger)](https://cheatiskey.github.io/schedule/)

> 위 뱃지를 클릭하면 <b>상세 API 명세서(HTTP Request/Response 예시 포함)<b>를 웹사이트에서 바로 확인할 수 있습니다.

---

## 구조 (UML 클래스 다이어그램 & ERD)

### ERD (Entity Relationship Diagram)
![일정 프로젝트 ERD](/img/일정%20프로젝트.png)

### Class Diagram
![클래스 다이어그램](/img/일정%20프로젝트%20클래스%20다이어그램.png)

---

## 기능

- **일정 관리 (Schedule)**
    - 일정 생성, 전체 조회, 단건 조회, 수정, 삭제
    - 작성자명(name)을 통한 필터링 조회 지원 (Query Parameter)
    - 비밀번호 검증을 통한 수정 및 삭제 권한 제어
- **댓글 관리 (Comment)**
    - 특정 일정에 댓글 추가
    - 일정 당 최대 10개의 댓글 제한 로직 적용
    - 일정 상세 조회 시 연관된 댓글 목록 함께 반환
- **기술적 특징**
    - JPA Auditing을 활용한 생성일/수정일 자동화
    - Dirty Checking(변경 감지)을 이용한 Update 로직
    - Java Record를 활용한 불변 DTO 및 생성자 레벨 검증

---

## API 명세

상세한 요청/응답 스키마와 예시 값은 상단의 [API Docs 링크](https://cheatiskey.github.io/schedule/)에서 확인 가능합니다.

| Method | URI               | 기능       | 설명                                 |
|:-------|:------------------|:---------|:-----------------------------------|
| POST   | /api              | 일정 생성    | 제목, 내용, 작성자, 비밀번호를 입력받아 일정을 저장합니다. |
| GET    | /api              | 전체 일정 조회 | 수정일 기준 내림차순으로 정렬합니다.               |
| GET    | /api/{id}         | 특정 일정 조회 | 해당 일정과 연관된 댓글 목록을 함께 반환합니다.        |
| PUT    | /api/{id}         | 일정 수정    | 비밀번호가 일치할 경우 제목과 작성자명을 수정합니다.      |
| DELETE | /api/{id}         | 일정 삭제    | 비밀번호가 일치할 경우 일정을 삭제합니다.            |
| POST   | /api/{scheduleId} | 댓글 생성    | 특정 일정에 댓글을 등록합니다. (최대 10개)         |

---

## 클래스 역할

| 클래스             | 역할                                                                                                |
|:----------------|:--------------------------------------------------------------------------------------------------|
| Controller      | `ScheduleController`, `CommentController` <br> 클라이언트의 요청을 받아 Service에 전달하고, 처리 결과를 DTO 형태로 반환합니다. |
| Service         | `ScheduleService`, `CommentService` <br> 비즈니스 로직을 수행합니다. (비밀번호 검증, 댓글 개수 제한, 트랜잭션 관리)             |
| Repository      | `ScheduleRepository`, `CommentRepository` <br> JPA를 상속받아 DB에 직접적인 CRUD 작업을 수행합니다.                 |
| Domain (Entity) | `Schedule`, `Comment` <br> 데이터베이스 테이블과 매핑되는 핵심 도메인 객체입니다. 생성자에서 `Validator`를 통해 무결성을 검증합니다.       |
| DTO             | `Record` 타입을 활용하여 불변성을 보장하고, 생성자에서 데이터 검증을 수행합니다.                                                 |
| Util            | `Validator` <br> `static` 메서드로 구현된 검증 유틸리티입니다. Null 체크 및 길이 제한 로직을 관리하여 코드 중복을 제거합니다.             |

---

### 1) JPA Auditing (BaseEntity)

- `@MappingSuperclass`와 `@EntityListeners(AuditingEntityListener.class)`를 사용하여 생성일(`createdAt`)과 수정일(`modifiedAt`)을 모든 엔티티에서 자동으로 관리하도록 추상화했습니다.
- 이를 통해 중복 코드를 제거하고 날짜 데이터의 일관성을 유지합니다.

---

### 2) Dirty Checking (변경 감지)

- `updateSchedule` 메서드에서는 별도의 `repository.save()` 호출 없이 구현되었습니다.
- `@Transactional` 환경에서 조회한 엔티티의 필드 값을 변경(`changeScheduleName`, `changeScheduleTitle`)하면, 트랜잭션 커밋 시점에 JPA가 변경 사항을 감지하여 자동으로 Update 쿼리를 실행합니다.

---

### 3) 데이터 검증 전략 (이중 검증 & 유틸리티)

- **1차 검증 (DTO)**: `Record`의 **Compact Constructor**를 사용하여 요청 데이터가 들어오는 즉시 형식(Null, 길이 등)을 검증합니다.
- **2차 검증 (Entity)**: `Validator` 유틸리티 클래스(`static` 메서드 활용)를 통해 엔티티 생성 시점에 데이터 무결성을 최종적으로 보장합니다.
- 이를 통해 잘못된 데이터가 비즈니스 로직으로 침투하는 것을 차단합니다.

---

### 4) 도메인 규칙 및 제약조건

- **댓글 개수 제한**: `CommentService`에서 `countByScheduleId`를 호출하여 한 일정당 댓글이 10개를 초과할 경우 `CommentLimitExceededException`을 발생시켜 도메인 규칙을 준수합니다.
- **연관관계 조회**: 일정 상세 조회 시 `Schedule` 뿐만 아니라 연관된 `List<Comment>`를 별도로 조회하여 하나의 DTO(`ScheduleAndCommentReadResponseDto`)로 병합해 반환합니다.

---

### 예외 처리

| 상황               | 예외 클래스                           | 처리 방식                                               |
|:-----------------|:---------------------------------|:----------------------------------------------------|
| 존재하지 않는 일정 ID 조회 | `NoSuchScheduleException`        | 404 Not Found 반환 (메시지: "해당 일정이 존재하지 않습니다.")         |
| 비밀번호 불일치         | `PasswordMismatchException`      | 401 Unauthorized 반환 (메시지: "비밀번호가 일치하지 않습니다.")       |
| 댓글 허용 개수 초과      | `CommentLimitExceededException`  | 400 Bad Request 반환 (메시지: "댓글은 최대 10개까지만 작성 가능합니다.") |
| 필수값 누락 / 형식 오류   | `IllegalArgumentException`       | 400 Bad Request 반환 (DTO 생성자 검증 실패)                  |
| Request Body 누락  | `HttpMessageNotReadableException` | 400 Bad Request 반환 (DELETE 요청 시에도 JSON Body 필수로 요구  |
| 서버 내부 오류         | `Exception`                      | 500 Internal Server Error 반환                        |

---

## 실행 흐름 (요청 처리 과정)

1. **요청 진입**: 클라이언트가 API URI로 HTTP 요청을 보냅니다.
2. **DTO 매핑 및 검증**: Request Body의 JSON 데이터가 DTO로 변환됩니다. 이 과정에서 1차 검증(생성자)이 실행되어 유효하지 않은 데이터는 즉시 예외 처리됩니다.
3. **비즈니스 로직 수행 (Service)**:
   - `Service` 계층은 `Repository`를 통해 필요한 Entity를 조회합니다.
   - 비밀번호 검증, 댓글 개수 확인 등 비즈니스 규칙을 수행합니다.
   - 데이터 변경/생성이 필요한 경우 Entity 내부에서 2차 검증(Validator)을 수행합니다.
4. **DB 반영 및 응답**: 트랜잭션이 커밋되면서 변경 사항이 DB에 반영되고, 결과 데이터는 Response DTO로 변환되어 클라이언트에게 반환됩니다.
