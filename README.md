# TEST-SERVER

### 사용가능한 API -> 테스트 완료
- 회원가입, 로그인
- 회원가입 /register
- 로그인 /login

### 사용방법
1. 올려둔 서버 클론
2. mysql에 test 라는 database 생성
3. resources/application.properties 에 "mysql local password insert" 지우고 새로 입력  
ex) spring.datasource.password=asdkl12345
4. 연결됐다면 테이블은 자동 생성될거임
5. 서버가 열릴경우 Tomcat의 포트번호는 localhost:8080
6. 테스트용 앱에서 회원가입 및 테스트 처리해보기

### 테스트 할 내용
1. 회원가입 성공시 Server에서 JSON 타입으로 name 과 email을 return, 토스트메시지로 "name, email, 성공하였습니다." 라는 메시지 출력
2. 로그인 성공시 JSON 타입으로 status : 200, statusMessage : "로그인 성공" return, status가 200일때 statusMessage를 그대로 출력해주는 액티비티로 넘어가게 구현
3. 로그인 실패 JSON 타입으로 
   - "timestamp": "2022-08-02T15:11:43.145+00:00",
   - "status": 500,
   - "error": "Internal Server Error",
   - "path": "/login"

    이렇게 넘어 올건데 status만 활용해 로그인이 실패하였다는 다이알 로그창 생성 확인