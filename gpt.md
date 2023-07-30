## 설계
- gpt-boot store sample 설계 데이터
- https://start.spring.io/ 에서 spring boot 프로젝트 생성
Dependencies : Spring Web, H2 Database, Spring Data JPA, Spring Security,lombok

## 데이터로 질문 시작
- I am trying to create a book management service with Java and Spring Boot. Design a domain based on the information below.
위 설계 데이터를 csv로 변형하여 붙여 넣기(shift enter 키 로 줄바꿈)  
entity 코드 작성 ( gpt-boot store sample 설계 데이터.csv)
- Q: I use Spring Data JPA with lombok. Please write the Book Entity code. 생성된 entity에 book category many to one 관계로 추가되어 있지 않으면 추가해 달라고 추가 요청(Q: add book category to book entity)
- Q: I use Spring Data JPA. Please write the BookCategory Entity code.
## Repository Service Controller 코드 작성
- Q: Please write the BookRepository code.
- Q: Please write the BookCategoryRepository code.
- Q: Please write the BookService code.
- Q: Please write the BookCategoryService code.
- Q: Please write the BookController code.
- Q: Please write the BookCategoryController code.
## 실제 프로젝트 실행
## Security
- Q : Please write spring security configuration code.
## 테스트 코드
- Q : Please write the test code with mokito
## readme
- Q : Please make the Readme.md file