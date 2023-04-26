# 1. 프로젝트 소개
+ **프로젝트 명** : Bus4You (버스 티켓 예매 프로그램)

+ **프로젝트 소개**

  사용자는 원하는 날짜와 시간, 그리고 목적지를 선택하고 버스 티켓을 손쉽게 예매할 수 있다.
  실시간으로 남은 좌석 현황을 확인할 수 있다. 예약 후에는 예매 현황을 확인할 수 있고 필요하다면 취소할 수 있다.

+ **프로젝트 목적**
  + JAVA, JDBC, ORACLE, SWING을 이용하여 사용자가 편리하게 예약할 수 있는 버스 티켓 예매 프로그램을 개발한다. 실시간 좌석 확인과 예약 변경/취소 기능을 제공하여 고객 만족도를 높인다.
  + 동시에 자바 GUI를 활용하여 UI를 만들고 이를 DB와 연동하는 과정을 학습한다

+ **프로젝트 기간**
   
   2023-04-19 ~ 2023-04-24
# 2. 팀 소개
| 이름 | 이메일 |역할 |
| ------ | -- | ----------- |
| 서재화 | woghk6761@pusan.ac.kr | 관리자 CRUD </br> DB설계</br> Swing|
| 김수지 | sub9399@naver.com | 사용자 CRUD </br> 버스 CRUD</br>Swing|
| 정준엽 | innateshy@gmail.com | 예매 CRUD</br> 경로 CRUD </br>Swing|
| 오석진 | osj0203@naver.com | 회원가입, 로그인</br>Swing 디자인</br>테스트케이스작성|

# 3. DB 구성도
<img width="585" alt="BUS4YOU_ERM" src="https://user-images.githubusercontent.com/88009952/234216589-f99450c3-2bcf-4763-859d-534806a5c11c.png">


+ **티켓 예약 시스템 구성**
  + 실시간 좌석 정보 제공
  + 예약 내역 조회

+ **관리자 시스템 구성**
  + 노선 등록 및 삭제
  + 유저 조회
  + 버스 등록 및 삭제

+ **유저 시스템 구성**
  + 로그인, 회원가입
  + 포인트 충전
  + 티켓 구매 및 환불
  
# 4. 시연 영상
링크

# 5. 사용법

1. git에서 파일 내려 받기
```
git clone
```

2. Conn 폴더에 DBconn data 수정
```
oracle ID=
oracle Password=
```

3. DB Schema 생성
```
DataFirstCreate.java 실행
```

4. GUI 폴더 이동
```
Login.java 실행
```


