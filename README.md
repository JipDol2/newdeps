# Travelmaker

## 기술스택
- Mobile : Android
- Front-End : React
- Back-End : java 17, spring boot 3.1.2, mysql, H2 database, Amazon linux, Amazon RDS
- devops : jenkins, docker

## 프로젝트 아키텍처
![image](https://github.com/JipDol2/newdeps/assets/55746374/37316ac5-8d00-4ec8-bd48-f3408f4d0d3c)

## 기획의도
여행을 다니면서 방문했던 장소들에 대해 추억을 남길 수 있는 서비스를 만들어보자 라는 목적으로 이루어졌습니다.

## 팀원과 역할
Mobile : 윤민창, 장예은, 정현화  
Front-End : 김지원  
Back-End : 이성주  

## 개발하면서 마주했던 이슈들
- 인텔리제이 .http 를 이용하여 api test 를 진행했을때 multipart/form-data 형식의 요청이 정상적으로 이루어지지 않았던 문제
    - 원인 : Content-Type : multipart/form-data 인 경우 각 데이터마다 Content-Deposition 과 boundary 를 정의해주지 않았던 것이 원인
    - 해결 : 요청을 보낼 데이터들 마다 구분자를 입력해주어 해결[링크](https://boomrabbit.tistory.com/255), Http11InputBuffer 를 이용하여 http header packet 을 확인
- private subnet 에 위치한 Amazon RDS 서버에 로컬에서 직접적으로 접근할 수 없었던 문제
    - 해결 : ssh tunneling 으로 was 서버를 통한 우회접근(참고링크)[https://letsmakemyselfprogrammer.tistory.com/123]
- image entity 를 save 된 후에 update 쿼리가 발생했던 문제
    - 원인 : post : image = 1 : N 관계를 맺고 있는 상태에서 post 를 save 한 뒤에 image 를 save 한 것이 아닌 image 먼저 save 한 뒤에 post entity 를 save 하려고 했던 것이 원인  
            => dirty checking 발생
    - 해결
          1) post 를 먼저 save 한 뒤에 image save 실행  
          2) casecadeType.All 옵션을 통해 영속성 전이 실행  
          => 2번 선택(이유 : post 와 image 관계는 post 가 필수적으로 존재해야지만 image 를 선택적으로 추가/삭제 할 수 있는 관계이니 casecadeTypa.All 옵션을 줘도 무방하다고 생각)


## 아쉬웠던 점
- 
