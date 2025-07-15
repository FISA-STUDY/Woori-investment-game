## 💰 JAVA로 모의투JA 게임

![Java](https://img.shields.io/badge/Java-ED8B00?style=plastic&logo=openjdk&logoColor=white)
![Console Game](https://img.shields.io/badge/Console_Game-000000?style=plastic&logo=windows-terminal&logoColor=white)
![Investment Simulator](https://img.shields.io/badge/Investment_Simulator-00D26A?style=plastic&logo=stockx&logoColor=white)
![MVC Pattern](https://img.shields.io/badge/MVC_Pattern-FF6B6B?style=plastic&logo=java&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=plastic&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=plastic&logo=mysql&logoColor=white)

순수 자바로 구현한 콘솔 기반 주식 투자 시뮬레이션 게임입니다. (Docker & MySQL 지원)

---

## 🛠️ 기술 스택

- **언어**: Java (JDK 17+)
- **아키텍처**: MVC 패턴
- **DB**: MySQL (Docker로 실행)
- **외부 라이브러리**: Lombok
- **배포/실행**: Docker, docker-compose

---

ERD

<img width="1070" height="562" alt="모의투자게임 (4)" src="https://github.com/user-attachments/assets/7153c4ae-ce07-48d3-b84b-a564e194619d" />

## 📁 실제 프로젝트 구조

```
Woori-investment-game/
 ┣ src/
 ┃ ┣ controller/
 ┃ ┃ ┣ MarketManager.java
 ┃ ┃ ┣ NewsGenerator.java
 ┃ ┃ ┣ StockManager.java
 ┃ ┃ ┗ UserController.java
 ┃ ┣ model/
 ┃ ┃ ┣ domain/
 ┃ ┃ ┃ ┣ News.java
 ┃ ┃ ┃ ┣ PortFolio.java
 ┃ ┃ ┃ ┣ Stock.java
 ┃ ┃ ┃ ┗ User.java
 ┃ ┃ ┣ dto/
 ┃ ┃ ┃ ┗ NewsStockPair.java
 ┃ ┃ ┣ NewsDAO.java
 ┃ ┃ ┣ PortfolioDAO.java
 ┃ ┃ ┣ StockDAO.java
 ┃ ┃ ┗ UserDAO.java
 ┃ ┣ Run/
 ┃ ┃ ┗ Main.java
 ┃ ┣ util/
 ┃ ┃ ┗ DBUtil.java
 ┃ ┗ view/
 ┃   ┗ ConsoleUI.java
 ┣ Dockerfile
 ┣ docker-compose.yml
 ┣ pom.xml
 ┗ README.md
```

---

## ⚙️ 설치 및 실행

### 1. Docker & MySQL 기반 실행 (권장)

#### 1) Docker 환경 준비

- Docker, docker-compose 설치 필요

#### 2) 실행

```bash
git clone https://github.com/FISA-STUDY/Woori-investment-game.git
cd Woori-investment-game
docker-compose up --build
```

- 최초 실행 시 MySQL 컨테이너가 자동으로 DB를 초기화합니다.
- Java 애플리케이션이 빌드되어 DB와 연동됩니다.

#### 3) 환경 변수 (docker-compose.yml에서 자동 설정)

- MYSQL_ROOT_PASSWORD: root
- MYSQL_DATABASE: woori_invest
- MYSQL_USER: woori
- MYSQL_PASSWORD: woori1234

#### 4) 수동 실행 (로컬 JDK 환경)

```bash
javac -d target src/**/*.java
java -cp target src.Run.Main
```

- 이 경우, MySQL 서버가 별도로 실행 중이어야 하며, DB 접속 정보는 `src/util/DBUtil.java`에서 수정할 수 있습니다.

---

## 🗄️ DB 연동 안내

- MySQL 컨테이너가 자동으로 `woori_invest` 데이터베이스를 생성합니다.
- DB 접속 정보는 `src/util/DBUtil.java`에서 관리합니다.
- DB 스키마 및 초기 데이터는 `/docker-entrypoint-initdb.d/`에 SQL 파일로 추가 가능합니다.
- JDBC 드라이버는 pom.xml에 명시되어 있습니다.

---

## 🎮 게임 소개

실제 주식 시장을 모방한 투자 시뮬레이션 게임으로, 가상의 자금으로 주식을 매수/매도하며 수익을 극대화하는 것이 목표입니다.

### 주요 기능

- 5개 주요 종목 실시간 거래 (삼성전자, SK하이닉스, NAVER, 카카오, LG에너지솔루션)
- 랜덤 가격 변동, 뉴스에 따른 주가 영향
- 주식 매수/매도, 포트폴리오 관리, 실시간 손익 계산
- 매일 랜덤 뉴스 생성, 뉴스 히스토리 관리

---

## 🗂️ 주요 클래스 구조

- **controller**: 시장/뉴스/주식/유저 관리
- **model/domain**: 핵심 도메인 객체(Stock, User 등)
- **model/dto**: DTO 객체
- **model/DAO**: DB 접근 객체
- **util**: DBUtil 등 유틸리티
- **view**: ConsoleUI 등 뷰
- **Run**: Main (게임 진입점)

---

## 📝 코드/구조 개선사항 (2024)

- 클래스/파일명, 변수명 camelCase로 통일
- 생성자, 필드, 메서드 순서 일관화
- 불필요한 import, 가변 필드 제거
- 예외처리 및 입력 검증 강화
- DB 연동 구조 개선 (싱글톤 DAO, DBUtil 분리)
- 실제 디렉터리 구조와 README 일치화

---

## 🐳 Docker & MySQL 연동 예시

- docker-compose.yml에서 MySQL 컨테이너와 Java 컨테이너를 함께 실행
- Java 애플리케이션은 DBUtil을 통해 MySQL에 JDBC로 접속
- DB 초기화 SQL은 `/docker-entrypoint-initdb.d/`에 배치 가능

---

## 🛠️ 기술 구현

- Java MVC 패턴 기반 구조
- DAO 패턴을 통한 MySQL 연동 (JDBC)
- 싱글톤 DAO, DBUtil 분리
- 예외처리 및 입력 검증 강화
- 도메인/DTO/컨트롤러/뷰 계층 분리
- Docker 기반 멀티 컨테이너 환경
- (기타 상세 기술 내용...)

## 🏗️ 시스템 아키텍처

<img width="700" height="459" alt="스크린샷 2025-07-16 오전 8 35 32" src="https://github.com/user-attachments/assets/f584a406-dc38-417a-a26e-321f53dda149" />

---

## 👥 팀원

|                    최홍석                    |                     최소영                     |                       홍윤기                       |                    홍혜원                    |
| :------------------------------------------: | :--------------------------------------------: | :------------------------------------------------: | :------------------------------------------: |
| [@ChatHongPT](https://github.com/ChatHongPT) | [@ottffss1005](https://github.com/ottffss1005) | [@yunkihong-dev](https://github.com/yunkihong-dev) | [@hyewon8245](https://github.com/hyewon8245) |

---

## 🔗 참고 및 문의

- [프로젝트 GitHub](https://github.com/FISA-STUDY/Woori-investment-game)
- Issue 또는 PR로 문의/기여 바랍니다.
