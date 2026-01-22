# Servlet Product Exam

서블릿으로 HTTP 요청/응답 흐름을 이해하기 위한 학습 프로젝트입니다. `*.do` 패턴을 처리하는 서블릿과 Mustache 템플릿 렌더링을 통해 기본적인 요청 흐름을 실습합니다.

## 현재 구현
- `DispatcherServlet`이 `*.do` 요청을 처리
- `cmd` 파라미터 기반 분기(현재 `list`만 처리)
- `ViewResolver`가 Mustache 템플릿을 로드해 `View`로 렌더링
- MySQL 연결 헬퍼(`DBConnection`) 제공
- 샘플 템플릿 3종(list/detail/save-form) 존재

## 실행 환경
- Java 21
- Spring Boot 4.0.1
- Mustache
- MySQL Connector/J

## 프로젝트 구조
- `src/main/java/com/example/prodwebapp/DispatcherServlet.java`
- `src/main/java/com/example/prodwebapp/DBConnection.java`
- `src/main/java/com/example/prodwebapp/lib/ViewResolver.java`
- `src/main/java/com/example/prodwebapp/lib/View.java`
- `src/main/resources/templates/list.mustache`
- `src/main/resources/templates/detail.mustache`
- `src/main/resources/templates/save-form.mustache`

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
`DispatcherServlet`은 `*.do` 패턴을 처리합니다. 현재 `cmd=list` 요청이 설정되어 있습니다.

예시:
```
http://localhost:8080/product.do?cmd=list
```

## 참고
- 템플릿 렌더링은 `ViewResolver.render(viewName)`을 통해 수행됩니다.
- 요청 데이터 바인딩과 비즈니스 로직은 앞으로 추가될 예정입니다.
