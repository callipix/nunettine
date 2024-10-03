<div style="background-color: white;">
<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&height=120&text=nunettine&animation=fadeIn&fontColor=000000&fontSize=40" />
</div>

<div style="margin: 0px 20px 0px 20px;">

<div> 
  <h2 style="color: #282d33;"> ✈️ 프로젝트 개요 </h2>
  <br>
  <div style="font-weight: 700; font-size: 15px; color: #282d33;" align="center">
   생활 서비스 검색 및 제공 시스템으로 기존에 존재하는 ‘숨고’ 라는 플랫폼을 모티브로 하여<br>
    내게 필요한 전문가가 어디에서나 쉽게 눈에 띄었으면 하는 바람을 가지고 ‘누네띠네’ 라고 이름지었습니다.
<br><br>
  </div>
  <br>
  <h4>
    1. 참여인원 : 7명<br><br>
    2. 제작기간 : 2024.02.19 - 2024.04.17
  <br><br><br>
  </h4>
   <h2 style="color: #282d33;"> 🏷️ 목차 </h2>
  1. ERD<br>
  2. 사용기술 및 개발환경<br>
  3. 서비스 구성 및 개요<br>
  4. 프로젝트 내에서 내가 맡은 부분
</div>
<br><br><br>
<h2 style="color: #282d33;"> 🗂️ ERD </h2>
<div align="center">
<img style="width: 100%" src="https://github.com/user-attachments/assets/dd34b958-822b-4021-a674-7c577ae55fb0">
</div>
<br><br><br>
  <h2 style="color: #282d33;"> 🛠️ 사용기술 및 개발환경</h2>
</div>
<div align="">
<div>
<div>
  <h4>BackEnd</h4>
<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=data:image/svg%2bxml;base64,PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4KDTwhLS0gVXBsb2FkZWQgdG86IFNWRyBSZXBvLCB3d3cuc3ZncmVwby5jb20sIFRyYW5zZm9ybWVkIGJ5OiBTVkcgUmVwbyBNaXhlciBUb29scyAtLT4KPHN2ZyB3aWR0aD0iMTUwcHgiIGhlaWdodD0iMTUwcHgiIHZpZXdCb3g9IjAgMCAzMi4wMCAzMi4wMCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBmaWxsPSIjZmZmZmZmIiBzdHJva2U9IiNmZmZmZmYiIHN0cm9rZS13aWR0aD0iMC4yNTYiPgoNPGcgaWQ9IlNWR1JlcG9fYmdDYXJyaWVyIiBzdHJva2Utd2lkdGg9IjAiLz4KDTxnIGlkPSJTVkdSZXBvX3RyYWNlckNhcnJpZXIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIvPgoNPGcgaWQ9IlNWR1JlcG9faWNvbkNhcnJpZXIiPiA8cGF0aCBmaWxsPSIjZmZmZmZmIiBkPSJNMTIuNTU3IDIzLjIyYzAgMC0wLjk4MiAwLjU3MSAwLjY5OSAwLjc2NSAyLjAzNyAwLjIzMiAzLjA3OSAwLjE5OSA1LjMyNC0wLjIyNiAwIDAgMC41OSAwLjM3IDEuNDE1IDAuNjkxLTUuMDMzIDIuMTU3LTExLjM5LTAuMTI1LTcuNDM3LTEuMjN6TTExLjk0MiAyMC40MDVjMCAwLTEuMTAyIDAuODE2IDAuNTgxIDAuOTkgMi4xNzYgMC4yMjQgMy44OTUgMC4yNDMgNi44NjktMC4zMyAwIDAgMC40MTEgMC40MTcgMS4wNTggMC42NDUtNi4wODUgMS43NzktMTIuODYzIDAuMTQtOC41MDgtMS4zMDV6TTE3LjEyNyAxNS42M2MxLjI0IDEuNDI4LTAuMzI2IDIuNzEzLTAuMzI2IDIuNzEzczMuMTQ5LTEuNjI1IDEuNzAzLTMuNjYxYy0xLjM1MS0xLjg5OC0yLjM4Ni0yLjg0MSAzLjIyMS02LjA5MyAwIDAtOC44MDEgMi4xOTgtNC41OTggNy4wNDJ6TTIzLjc4MyAyNS4zMDJjMCAwIDAuNzI3IDAuNTk5LTAuODAxIDEuMDYyLTIuOTA1IDAuODgtMTIuMDkxIDEuMTQ2LTE0LjY0MyAwLjAzNS0wLjkxNy0wLjM5OSAwLjgwMy0wLjk1MyAxLjM0NC0xLjA2OSAwLjU2NC0wLjEyMiAwLjg4Ny0wLjEgMC44ODctMC4xLTEuMDIwLTAuNzE5LTYuNTk0IDEuNDExLTIuODMxIDIuMDIxIDEwLjI2MiAxLjY2NCAxOC43MDYtMC43NDkgMTYuMDQ0LTEuOTV6TTEzLjAyOSAxNy40ODljMCAwLTQuNjczIDEuMTEtMS42NTUgMS41MTMgMS4yNzQgMC4xNzEgMy44MTQgMC4xMzIgNi4xODEtMC4wNjYgMS45MzQtMC4xNjMgMy44NzYtMC41MSAzLjg3Ni0wLjUxcy0wLjY4MiAwLjI5Mi0xLjE3NSAwLjYyOWMtNC43NDUgMS4yNDgtMTMuOTExIDAuNjY3LTExLjI3Mi0wLjYwOSAyLjIzMi0xLjA3OSA0LjA0Ni0wLjk1NiA0LjA0Ni0wLjk1NnpNMjEuNDEyIDIyLjE3NGM0LjgyNC0yLjUwNiAyLjU5My00LjkxNSAxLjAzNy00LjU5MS0wLjM4MiAwLjA3OS0wLjU1MiAwLjE0OC0wLjU1MiAwLjE0OHMwLjE0Mi0wLjIyMiAwLjQxMi0wLjMxOGMzLjA3OS0xLjA4MyA1LjQ0OCAzLjE5My0wLjk5NCA0Ljg4Ny0wIDAgMC4wNzUtMC4wNjcgMC4wOTctMC4xMjZ6TTE4LjUwMyAzLjMzN2MwIDAgMi42NzEgMi42NzItMi41MzQgNi43ODEtNC4xNzQgMy4yOTYtMC45NTIgNS4xNzYtMC4wMDIgNy4zMjMtMi40MzYtMi4xOTgtNC4yMjQtNC4xMzMtMy4wMjUtNS45MzQgMS43NjEtMi42NDQgNi42MzgtMy45MjUgNS41Ni04LjE3ek0xMy41MDMgMjguOTY2YzQuNjMgMC4yOTYgMTEuNzQtMC4xNjQgMTEuOTA4LTIuMzU1IDAgMC0wLjMyNCAwLjgzMS0zLjgyNiAxLjQ5LTMuOTUyIDAuNzQ0LTguODI2IDAuNjU3LTExLjcxNiAwLjE4IDAgMCAwLjU5MiAwLjQ5IDMuNjM1IDAuNjg1eiIvPiA8L2c+Cg08L3N2Zz4=">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=Plastic&logo=Spring&logoColor=white">
</div>
<div>
  <h4>Database</h4>
  <img src="https://img.shields.io/badge/Oracle-F80000?style=Plastic&logo=Oracle&logoColor=white" />
<img src="https://img.shields.io/badge/SQL Developer-gray?style=flat&logo=data:image/png%2bxml;base64,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">
  <img src="https://img.shields.io/badge/MyBatis-gray?style=flat&logo=data:image/png%2bxml;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAACXBIWXMAAAsTAAALEwEAmpwYAAADoklEQVR4nO2ZaYhOURjHf2MdY89kj0KyZBmSLJHsQpQvaBRKkeQDktDIlpSdwQd7lg92yZbQNCWKDyaR/YMIMwbTzBgzr079b51u8473Lu/Mq+6/TnPfe+855/mf8zz/57lnIEKECLWFKUAeMJr/HJuBGPAJmAcMA1rzH6IhcEVknFYB5AINSCGkASOAATW8Ux9YAlwE8oFSETL3UgaLZNQHYKzcpicwEVig5/OBLBEymKQ+j0khNAJuuVwnXnsDjAcy9bvINVYnYBVwUzFVDhQDL4EzWpCMZJJpAmzQhIUy+A5wBNgBHNU9Jz5m6LpK/ZsD+2T4vxajCFgml04q0uJMYnZuu4z5KRLmehDwIsEdjVnNLE69ZBN5BZzXyg20YsPgpMugMv39BlR6JLOOJCPPNeF34BowTWJQ7Hp+DkiXiw2Xmh0C7kpEquIQ+Q10q40kaLcfQHc9323d35uAi2QB7xRv2cBy4J76Hwwj0cVLaH2AA1IzE+R/ZADakbcy4kKCfr7TIv4eWKPSx6kYAsVKhvz6gSbKFgE7HuxAd9BG+WUP0DTBuUZJ7ewdLrdcrj8BcbMaFzLB+0S6vxaYq8zfOcDKPbPGNwn2kmvOmUGJzPeoMmYVnwK7PK5iqTXGEC2MuXdD97YEzSsmEX71kQNiktpcK1MPBsbEmed4Nf1viIBdNawHuvols84nEaddlWiY2HoOPAIWAi2tOdIVh0XaVSMSbV1ESiXjk/3uToa0PgiZpRprcTWGzQbax5n7sPX+WaALATEnIBGnCu5dwzsFckWTQ6YCvYDr1nPj4idUkAaCW0m8tF8ao5XP/svCrLtMfnjt05DPGsMp7722LEJGb5XxfgIeqZbXvuVSz9DRV2WEF2OM0hhs80EkP0zj2wEbgX763VHf54kYYgLYiY/vPoisDINAdxWHTuY15fkE6/k4iUBZnLhwgjRN8umVREUN0vxPtFBpcj/Od8IfkbNl0CS3kaq7nNrLqZwbA/t9BvllP0Fs9Pt2gt/VMWXh7aqNqoPJ1rNcBaHXEmeIVyK7fE7mtI8q981n7ildFwUc8xg+kKYTkViKtEIJCn7J5KQAiUp9HQZGto5z6orIakJED+BhHZDYRBJQT/8m+FILBKqAFSQZmTpIKEkSCXPiYhJrraGtzrO+hUSgTOdfzagjmKOf6TpFKfEprVuBDqQQGgFDVVOdVqXqnNjHFF8FqhhyVMaYg78IESJEiBAhAiHiL7qqAQdttdy6AAAAAElFTkSuQmCC">
</div>
<div>
  <h4>FrontEnd</h4>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=Plastic&logo=HTML5&logoColor=white" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=Plastic&logo=CSS3&logoColor=white" />
  <img src="https://img.shields.io/badge/Javascript-F7DF1E?style=Plastic&logo=Javascript&logoColor=white" />
  <img src="https://img.shields.io/badge/jQuery-0769AD?style=Plastic&logo=jQuery&logoColor=white" />
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=Plastic&logo=Bootstrap&logoColor=white" />
  <br>
</div>

<h4>Tools & ETC </h4>

<img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=Plastic&logo=Eclipse&logoColor=white" />

<img src="https://img.shields.io/badge/Visual Studio Code-0288d1?style=flat&logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciICB2aWV3Qm94PSIwIDAgNDggNDgiIHdpZHRoPSI0OHB4IiBoZWlnaHQ9IjQ4cHgiPjxwYXRoIGZpbGw9IiMyOWI2ZjYiIGQ9Ik00NCwxMS4xMXYyNS43OGMwLDEuMjctMC43OSwyLjQtMS45OCwyLjgybC04LjgyLDQuMTRMMzQsMzNWMTVMMzMuMiw0LjE1bDguODIsNC4xNCBDNDMuMjEsOC43MSw0NCw5Ljg0LDQ0LDExLjExeiIvPjxwYXRoIGZpbGw9IiMwMjc3YmQiIGQ9Ik05LDMzLjg5NkwzNCwxNVY1LjM1M2MwLTEuMTk4LTEuNDgyLTEuNzU4LTIuMjc1LTAuODZMNC42NTgsMjkuMjM5IGMtMC45LDAuODMtMC44NDksMi4yNjcsMC4xMDcsMy4wMzJjMCwwLDEuMzI0LDEuMjMyLDEuODAzLDEuNTc0QzcuMzA0LDM0LjM3LDguMjcxLDM0LjQzLDksMzMuODk2eiIvPjxwYXRoIGZpbGw9IiMwMjg4ZDEiIGQ9Ik05LDE0LjEwNEwzNCwzM3Y5LjY0N2MwLDEuMTk4LTEuNDgyLDEuNzU4LTIuMjc1LDAuODZMNC42NTgsMTguNzYxIGMtMC45LTAuODMtMC44NDktMi4yNjcsMC4xMDctMy4wMzJjMCwwLDEuMzI0LTEuMjMyLDEuODAzLTEuNTc0QzcuMzA0LDEzLjYzLDguMjcxLDEzLjU3LDksMTQuMTA0eiIvPjwvc3ZnPg==">
<img src="https://img.shields.io/badge/Redmine-B32024?style=Plastic&logo=Redmine&logoColor=white" />
</div>
</div>
<br><br><br>
<div>
  <h2 style="color: #282d33;"> 🧾 서비스 구성 및 개요 </h2>
</div>
<br>
<div align="center">
    <img style="width:30%; border-radius: 25px" src="https://github.com/user-attachments/assets/4e709d25-70be-428e-8fda-36dbdd6ef5b4">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img style="width:25%; border-radius: 25px" src="https://github.com/user-attachments/assets/49221a49-46b4-4195-9d8e-c2e450065ad5">
&nbsp;&nbsp;&nbsp;
    <img style="width:25%; border-radius: 25px" src="https://github.com/user-attachments/assets/413df461-c903-495a-9fd1-498f0cc140aa">
    <br>
    <img style="width:30%; border-radius: 25px" src="https://github.com/user-attachments/assets/555daf3d-3c78-4aa6-a046-eb6fb4507ff9">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img style="width:25%; border-radius: 25px" src="https://github.com/user-attachments/assets/75af85c2-14c6-4b86-a1d7-f409003e478a">
&nbsp;&nbsp;&nbsp;
    <img style="width:25%; border-radius: 25px" src="https://github.com/user-attachments/assets/1a7ab495-18a9-4973-9ec7-e20c13b8c53a">
</div>
<br><br>
<img style="width: 100%;" src="https://github.com/user-attachments/assets/60ea5b62-3dc1-4b72-b6cd-0165ff8dfec0">
<br><br>
<h2 style="color: #282d33;"> 🔎 프로젝트에서 내가 기여한 부분 </h2>
<h5>이하 펼쳐보기 부분 전부 플로우차트 및 시퀀스 다이어그램, 클래스 다이어그램으로 수정 예정</h5>
<h3>1. SMS 인증번호를 통한 가입 인증진행</h3>
  <details>
            <summary>펼쳐보기</summary>
  <div align="center">
         <h6>CoolSMS API를 사용하여 가입시 문자인증 후 가입진행 하도록 구현<br><br>
     문자 인증 동작 과정
     </h6>
  <img style="width:100%" src="https://github.com/user-attachments/assets/f0d63c5b-2e82-42ce-9c81-f93bd22755a2">
    </div>
  </details>
<br>
<h3>2. 사용자 경험 개선을 위한 로딩바 추가</h3>
  <details>
            <summary>펼쳐보기</summary>
      <div align="center">
     <h6>
    이미지가 많아지거나 이미지크기(용량)이 커질 것을 고려해 메인 이미지 등록 시 썸네일 처리를 하였으나<br>
    그럼에도 로딩 시간이 지연될 경우가 생길 수 있으므로 이미지 자체를 처리하기보다는 사용자 경험 개선을 통해<br>
    해결해 보고자 이미지 로딩 상황을 알 수 있는 로딩 프로그래스바를 추가
    </h6>
      </div>
    <br>
  <div align="center">
  <img style="width:100%" src="https://github.com/user-attachments/assets/9d3ee15b-0ace-424d-88f7-d5b3719a4214">
    </div>
  </details>
  <br>
  <h3>3. 무분별한 조회수 증가를 막기 위한 쿠키사용</h3>
  <details>
            <summary>펼쳐보기</summary>
      <h4>클릭할 때마다 계속해서 증가하는 조회수를 방지하기 위해 고려한 방법은 크게 2가지</h4>
<h5>
  1.&nbsp;&nbsp;DB에 게시글 읽음여부 컬럼추가 → 해당 값에 따른 조회수 증가 or 유지<br>
  2.&nbsp;&nbsp;쿠키를 사용하여 방문 여부 체크 → 해당 값에 따른 조회수 증가 or 유지
</h5>
    <h6>
    이 외에도 여러 방법이 있었으나 학습한 지식 내에서 활용할 수 있으며 서버의 부담을 줄일 수 있는 쿠키를 사용
    </h6>
    <br>
    <div align="center">
    <h6>쿠키를 사용한 조회수 증가 방지 순서도</h6>
      <img style="width: 100%;" src="https://github.com/user-attachments/assets/9b6de6f2-afc1-4637-a76b-5067956dd548">
      </div>
  </details>
  <br>
  <h3>4. 웹소켓을 사용한 실시간 통신(채팅)</h3>
  <details>
            <summary>펼쳐보기</summary>
      <h4>
    모임의 채팅기능 구현 시 고려 했던 부분
  </h4>
  <h6>1. 실시간 통신 처리 방법 → WebSocket 사용<br>
    2. 대화 내용을 I/O 하기 위한 DB : 실제로는 RDBMS보다는 NoSQL을 사용<br>
  &nbsp;&nbsp;&nbsp;→ NoSQL의 경우 새로운 학습, 일부 설정 충돌가능성 등을 고려해 기존의 RDBMS를 사용하기로 결정<br>
  3. 채팅에서의 메시지 처리를 위한 STOMP 프로토콜 사용</h6>
  <h4>STOMP 특징</h4>
  <h6>1. 서버 ↔ 클라이언트의 양방향 통신, 웹소켓과 함께 사용<br>
    2. 간단한 프로토콜로 구조 파악이 쉽다.<br>
    3. 스프링과 쉬운 연동으로 다양한 기능을 함께 사용<br>
    4. 메시지 브로커를 통한 구독/발행 패턴으로 메시지 전송 및 관리를 간편히 사용<br>
    5. 표준화된 프로토콜로 다양한 시스템에서의 운용 및 확장성 편리
  </h6>
    <br>
    <div align="center">
          <h6>채팅 과정 구조도</h6>
    <img style="width: 90%;" src="https://github.com/user-attachments/assets/dc675021-82a3-47f9-bbd2-ff15c2267ad2">
    <br>
      <h6>채팅 뷰 & DB 저장 내역</h6>
    <img style="width: 60%;" src="https://github.com/user-attachments/assets/8e5d573e-6645-4cc1-9bae-fb2d9b84464e">
    </div>
  </details>
  <br>
  <h3>5. 서버 부하를 줄이기 위한 정렬 기능<br></h3>
 <details>
            <summary>펼쳐보기</summary>
   <div align="center">
   <h6>
     정렬 버튼을 클릭할 때마다 서버에서 비즈니스 로직 → 데이터베이스 쿼리를 실행하여 정렬을 수행하고자 했으나<br>
     이러한 방법은 정렬할 때마다 서버에서 DB까지 요청하므로 서버에 부담이 커질 수 있다는 문제점이 존재<br><br>
     따라서 정렬을 반복 수행하더라도 서버 부하를 줄이며 문제를 해결할 방법 찾아보던 와중 클라이언트에서 정렬을 수행하는 방법을 선택
   </h6>
        </div>
   <br>
   <div align="center">
          <h6>정렬과정 살펴보기</h6>
   <img src="https://github.com/user-attachments/assets/4a1ac87b-2ee2-4bd5-bd27-2f934c16941a">
   </div>
   </details>
<h4>
</h4>
<br><br>
<div>
<h2 style="color: #282d33;"> 🧑‍💻 Contact Me..</h2>
</div>
  <div align="center">
    <a href="mailto:avooov@gmail.com">
    <img src="https://img.shields.io/badge/Gmail-30B980?style=flat&logo=Gmail&logoColor=white" />
    </a>
</div>
</div>
</div>
