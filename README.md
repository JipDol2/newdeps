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
    - 해결 : 요청을 보낼 데이터들 마다 구분자를 입력해주어 해결 [링크](https://boomrabbit.tistory.com/255), Http11InputBuffer 를 이용하여 http header packet 을 확인
- private subnet 에 위치한 Amazon RDS 서버에 로컬에서 직접적으로 접근할 수 없었던 문제
    - 해결 : ssh tunneling 으로 was 서버를 통한 우회접근 [참고링크](https://letsmakemyselfprogrammer.tistory.com/123)
- image entity 를 save 된 후에 update 쿼리가 발생했던 문제
    - 원인 : post : image = 1 : N 관계를 맺고 있는 상태에서 post 를 save 한 뒤에 image 를 save 한 것이 아닌 image 먼저 save 한 뒤에 post entity 를 save 하려고 했던 것이 원인  
            => dirty checking 발생
    - 해결
          1) post 를 먼저 save 한 뒤에 image save 실행  
          2) casecadeType.All 옵션을 통해 영속성 전이 실행  
          => 2번 선택(이유 : post 와 image 관계는 post 가 필수적으로 존재해야지만 image 를 선택적으로 추가/삭제 할 수 있는 관계이니 casecadeTypa.All 옵션을 줘도 무방하다고 생각)

## 아쉬웠던 점
- docker volumne 을 활용한 이미지 파일 관리하기  
    - docker 설정사항에 변경이 요구되어질때 서버를 다운시키고 재기동 하는 일이 빈번  
    - docker 내부에 등록되어있던 이미지 파일들이 전부 삭제되었던 문제  
    - 해당 문제를 volumne 을 활용하여 해결할 수 있었지만 기한내 적용하지 못했던 아쉬움
      
- Back-End 역할을 혼자 했었던 것에 대한 아쉬움
    - 두 파트로 팀을 나누었지만 이탈로 인해 하나의 팀으로 재구성
    - Back-End 역할이 혼자밖에 없어서 서로 상의하고 의견을 나눌 팀원이 존재하지 않았음
 
## 어플리케이션 시연
메인화면  
![KakaoTalk_20231029_000553758_04](https://github.com/JipDol2/newdeps/assets/55746374/c782ab95-4f19-4802-a1d7-246ab542ab70)

추천(장소,맛집,숙소)  
![제목 없음](https://github.com/JipDol2/newdeps/assets/55746374/95828e70-cade-443c-9e71-788b97d6aeb7)
![image](https://github.com/JipDol2/newdeps/assets/55746374/e66e12c8-c3f1-40c5-afb6-1f45e9a31eb7)

컬렉션  
![image](https://github.com/JipDol2/newdeps/assets/55746374/a12177ee-90b4-43e5-9242-5273d41cece5)

노선  
![image](https://github.com/JipDol2/newdeps/assets/55746374/ea9dcb25-6ee2-4151-91a8-5355093c21ab)


