# Servlet Product Exam

서블릿 기반 요청/응답 흐름과 간단한 MVC 구조를 학습하기 위한 프로젝트다. Spring Boot 위에서 동작하지만, 컨트롤러는 어노테이션 방식이 아니라 `DispatcherServlet`이 직접 라우팅하는 구조를 사용한다. 뷰는 Mustache 템플릿을 직접 로딩해 렌더링한다.

## 목표
- 서블릿의 요청/응답 흐름을 코드로 직접 확인
- Front Controller 패턴을 단순 구현
- JDBC 기반 CRUD 흐름을 이해
- 템플릿 렌더링 과정을 직접 제어

## 기술 스택
- Java 21
- Spring Boot 4.0.1
- Mustache
- MySQL Connector/J
- Gradle

## 아키텍처 개요
```
브라우저
  -> DispatcherServlet (*.do)
    -> ProductController
      -> ProductService
        -> ProductRepository (JDBC)
          -> MySQL
    -> ViewResolver -> View (Mustache 렌더링)
```

## 요청 흐름
1. `/` 접근 시 `static/index.html`이 `/product.do?cmd=list`로 리다이렉트한다.
2. `DispatcherServlet`이 `*.do` 요청을 수신한다.
3. `cmd` 파라미터로 요청을 분기한다.
4. `ProductController`가 서비스 계층을 호출하고 뷰 이름을 반환한다.
5. `ViewResolver`가 `templates/{view}.mustache`를 로딩한다.
6. `View.forward()`가 모델을 템플릿에 바인딩해 HTML을 응답한다.

## 주요 기능
- 목록 조회: `cmd=list` (GET)
- 상세 조회: `cmd=detail&id=...` (GET)
- 등록 폼: `cmd=insert-form` (GET)
- 등록 처리: `cmd=insert` (POST)
- 삭제 처리: `cmd=delete&id=...` (POST)

## 핵심 클래스 설명

### DispatcherServlet
- `@WebServlet("*.do")`로 모든 `.do` 요청을 처리한다.
- `doGet`에서 `list`, `insert-form`, `detail`을 분기한다.
- `doPost`에서 `insert`, `delete`를 분기한다.
- 등록 요청은 302 상태코드와 `Location` 헤더를 직접 설정해 리다이렉트를 구현한다.

### ProductController
- 요청 파라미터를 읽고 서비스 계층 호출 후 뷰 이름을 반환한다.
- `list`는 목록 데이터를 `req.setAttribute("models", ...)`로 저장한다.
- `detail`은 단일 데이터를 `req.setAttribute("model", ...)`로 저장한다.

### ProductService
- 리포지토리 호출 결과를 검증한다.
- 기대한 결과가 아니면 `RuntimeException`을 발생시킨다.

### ProductRepository
- JDBC로 SQL을 직접 실행한다.
- `insert`, `deleteById`, `findById`, `findAll` 구현.

### ViewResolver / View
- `ViewResolver`가 Mustache 템플릿을 로딩한다.
- `View.forward()`가 request attribute를 모델로 변환한다.
- `List`와 `Map`은 그대로 모델에 합산한다.
- `request` 객체를 모델에 포함해 템플릿에서 접근 가능하게 한다.

## 템플릿
- `list.mustache`: 목록과 삭제 버튼 렌더링
- `detail.mustache`: 상세 정보 렌더링
- `insert-form.mustache`: 등록 폼 렌더링

## DB 연결
`DBConnection`에 JDBC URL과 계정이 하드코딩되어 있다. 환경에 맞게 수정해야 한다.
- URL: `jdbc:mysql://localhost:3306/productdb`
- User: `root`
- Password: `bitc5600!`

## 주의 사항
- 예외 처리 전용 서블릿이나 글로벌 예외 핸들러는 없다.
- 소스/템플릿/README 일부 한글이 깨져 보일 수 있다(인코딩 설정 필요).
- Spring MVC 어노테이션 컨트롤러는 사용하지 않는다.

## 참고 파일 위치
- `src/main/java/com/example/prodwebapp/DispatcherServlet.java`
- `src/main/java/com/example/prodwebapp/DBConnection.java`
- `src/main/java/com/example/prodwebapp/lib/ViewResolver.java`
- `src/main/java/com/example/prodwebapp/lib/View.java`
- `src/main/java/com/example/prodwebapp/product/ProductController.java`
- `src/main/java/com/example/prodwebapp/product/ProductService.java`
- `src/main/java/com/example/prodwebapp/product/ProductRepository.java`
- `src/main/resources/templates/list.mustache`
- `src/main/resources/templates/detail.mustache`
- `src/main/resources/templates/insert-form.mustache`
