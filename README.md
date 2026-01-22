# Servlet Product Exam

Spring Boot 4 기반의 간단한 서블릿/머스테치(Mustache) 예제 프로젝트입니다. `DispatcherServlet`이 `*.do` 요청을 받고, `ViewResolver`가 `templates` 아래의 Mustache 템플릿을 렌더링합니다.

## 주요 기능
- `*.do` 요청을 서블릿에서 직접 처리
- `cmd` 파라미터로 간단한 라우팅 구현 (`cmd=list`)
- Mustache 템플릿 렌더링 (`templates/list.mustache`)
- MySQL 연결 헬퍼 제공 (`DBConnection`)

## 실행 환경
- Java 21
- Gradle Wrapper 포함
- Spring Boot 4.0.1
- MySQL Connector/J

## 프로젝트 구조
- `src/main/java/com/example/prodwebapp/ProdwebappApplication.java`
- `src/main/java/com/example/prodwebapp/DispatcherServlet.java`
- `src/main/java/com/example/prodwebapp/DBConnection.java`
- `src/main/java/com/example/prodwebapp/lib/ViewResolver.java`
- `src/main/java/com/example/prodwebapp/lib/View.java`
- `src/main/resources/templates/list.mustache`

## 로컬 실행
Windows:
```bash
.\gradlew.bat bootRun
```

macOS/Linux:
```bash
./gradlew bootRun
```

## 요청 예시
`DispatcherServlet`은 `*.do` 패턴을 처리합니다. `cmd=list`일 때 `list.mustache`를 렌더링합니다.

예시:
```
http://localhost:8080/product.do?cmd=list
```

## 데이터베이스 설정
`DBConnection`에 MySQL 접속 정보가 하드코딩되어 있습니다.

```java
String url = "jdbc:mysql://localhost:3306/productdb";
String username = "root";
String password = "bitc5600!";
```

필요 시 본인 환경에 맞게 수정하세요.

## 템플릿
`list.mustache`는 샘플 HTML 테이블을 포함합니다. 실제 데이터를 출력하려면 `View.forward`로 전달하는 `model` 속성에 데이터를 담아 Mustache 템플릿에서 사용하면 됩니다.

## 테스트
```bash
./gradlew test
```

## 참고
- 서블릿 기반으로 동작하므로 컨트롤러(@Controller) 없이도 간단한 요청/응답 흐름을 확인할 수 있습니다.
- 템플릿을 찾지 못하면 `ViewResolver`가 `ServletException`을 던집니다.
