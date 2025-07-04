# 💰 JAVA로 미니투자게임

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Console Game](https://img.shields.io/badge/Console_Game-000000?style=for-the-badge&logo=windows-terminal&logoColor=white)
![Investment Simulator](https://img.shields.io/badge/Investment_Simulator-00D26A?style=for-the-badge&logo=stockx&logoColor=white)
![Pure Java](https://img.shields.io/badge/Pure_Java-FF6B6B?style=for-the-badge&logo=java&logoColor=white)

순수 자바로 구현한 콘솔 기반 주식 투자 시뮬레이션 게임입니다.

## 🎮 게임 소개

실제 주식 시장을 모방한 투자 시뮬레이션 게임으로, 가상의 자금으로 주식을 매수/매도하며 수익을 극대화하는 것이 목표입니다.

## 📊 ERD
<img width="892" alt="스크린샷 2025-07-04 오후 12 37 48" src="https://github.com/user-attachments/assets/08f12ec9-755c-4fa3-8d70-152b7b4a8baf" />


## 🚀 주요 기능

### 📈 주식 시장 시뮬레이션
- **6개 주요 종목** 실시간 거래
  - 삼성전자, SK하이닉스, NAVER, 카카오, LG에너지솔루션, 현대차
- **랜덤 가격 변동** 시스템
- **상승/하락/보합** 상태 표시

### 💰 투자 시스템
- **주식 매수/매도** 기능
- **평균 매수가** 자동 계산
- **실시간 손익** 계산 및 표시
- **수익률** 분석

### 📱 포트폴리오 관리
- **보유 종목** 현황 관리
- **투자 성과** 분석
- **총 자산** 추적
- **개별 종목별** 손익 현황

### 📰 뉴스 시스템
- **랜덤 뉴스** 생성 (30% 확률)
- **호재/악재**에 따른 주가 영향
- **뉴스 히스토리** 관리
- **실시간 시장 반응** 시뮬레이션

### 📊 게임 통계
- **게임 진행** 현황
- **투자 성과** 종합 분석
- **총 자산** 변화 추적

## 🛠️ 기술 스택

![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Console](https://img.shields.io/badge/Interface-Console-000000?style=flat-square&logo=windows-terminal&logoColor=white)
![OOP](https://img.shields.io/badge/Paradigm-OOP-4CAF50?style=flat-square&logo=object&logoColor=white)

- **언어**: Pure Java (JDK 8+)
- **인터페이스**: Console 기반
- **패러다임**: 객체지향 프로그래밍
- **외부 라이브러리**: 없음 (순수 자바)

## 📁 프로젝트 구조

```
📦 MiniInvestmentGame
 ┣ 📜 MiniInvestmentGame.java
 ┣ 📜 README.md
 └ 📂 classes/
   ┣ 📜 Stock.class
   ┣ 📜 User.class
   ┣ 📜 Portfolio.class
   ┗ 📜 News.class
```

## ⚙️ 설치 및 실행

### 📋 사전 요구사항
![JDK](https://img.shields.io/badge/JDK-8+-007396?style=flat-square&logo=java&logoColor=white)

- Java JDK 8 이상

### 🔧 실행 방법

1. **저장소 클론**
   ```bash
   git clone https://github.com/your-username/mini-investment-game.git
   cd mini-investment-game
   ```

2. **컴파일**
   ```bash
   javac MiniInvestmentGame.java
   ```

3. **실행**
   ```bash
   java MiniInvestmentGame
   ```

## 🎯 게임 플레이 가이드

### 🏁 게임 시작
1. **사용자 이름** 입력
2. **초기 자금** 설정 (기본: 10,000,000원)
3. 게임 시작!

### 📋 메인 메뉴
- `1` 📊 **주식 시장 보기** - 현재 주가 및 변동 현황
- `2` 💳 **주식 매수** - 원하는 종목 매수
- `3` 💸 **주식 매도** - 보유 종목 매도
- `4` 📈 **포트폴리오 보기** - 투자 현황 및 수익률
- `5` 📰 **뉴스 보기** - 시장 뉴스 확인
- `6` ⏭️ **다음 날** - 시간 진행
- `7` 📊 **게임 통계** - 전체 게임 현황
- `0` 🚪 **게임 종료**

### 💡 게임 팁
- 📰 **뉴스를 주의깊게** 확인하세요
- 📈 **분산 투자**로 리스크를 줄이세요
- 📊 **포트폴리오**를 정기적으로 점검하세요
- ⏰ **타이밍**이 중요합니다

## 🗂️ 클래스 구조

### 📊 Stock
```java
- String name      // 주식명
- int price        // 현재가
- int graph        // 변동폭
```

### 👤 User
```java
- String name      // 사용자명
- long wallet      // 보유 현금
```

### 📈 Portfolio
```java
- String portfolioName  // 포트폴리오명
- String stockName     // 주식명
- int purchasePrice    // 매수가
- int amount          // 보유량
- String userName     // 사용자명
```

### 📰 News
```java
- int id              // 뉴스 ID
- String stockName    // 관련 주식명
- boolean isGood      // 호재/악재
- String message      // 뉴스 내용
```

## 🎨 게임 스크린샷

```
╔════════════════════════════════════════╗
║          💰 미니투자게임 💰            ║
║        Pure Java Implementation        ║
╚════════════════════════════════════════╝

============================================================
📅 1일차 | 👤 투자왕 | 💰 10,000,000원
============================================================

📋 메인 메뉴
1. 📊 주식 시장 보기
2. 💳 주식 매수
3. 💸 주식 매도
4. 📈 포트폴리오 보기
5. 📰 뉴스 보기
6. ⏭️  다음 날
7. 📊 게임 통계
0. 🚪 게임 종료
```

## 🔮 향후 계획

![Roadmap](https://img.shields.io/badge/Roadmap-2024-FF6B6B?style=flat-square&logo=roadmap&logoColor=white)

- [ ] 🎨 **GUI 버전** 구현 (JavaFX)
- [ ] 💾 **게임 저장/로드** 기능
- [ ] 📊 **차트 시각화** 추가
- [ ] 🏆 **순위 시스템** 구현
- [ ] 🌐 **멀티플레이어** 지원
- [ ] 📈 **더 많은 종목** 추가
- [ ] 🎯 **퀘스트 시스템** 도입

## 🤝 기여하기

1. **Fork** 프로젝트
2. **Feature branch** 생성 (`git checkout -b feature/AmazingFeature`)
3. **Commit** 변경사항 (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to branch (`git push origin feature/AmazingFeature`)
5. **Pull Request** 생성

## 📄 라이센스

![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

이 프로젝트는 MIT 라이센스 하에 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하세요.

## 👨‍💻 개발자

![Developer](https://img.shields.io/badge/Developer-Your_Name-blue?style=flat-square&logo=github&logoColor=white)

**Your Name** - [@your-username](https://github.com/your-username)

프로젝트 링크: [https://github.com/your-username/mini-investment-game](https://github.com/your-username/mini-investment-game)

## 🙏 감사의 말

![Thanks](https://img.shields.io/badge/Thanks-Java_Community-red?style=flat-square&logo=java&logoColor=white)

- Java 커뮤니티의 지원에 감사드립니다
- 순수 자바의 힘을 보여주는 프로젝트입니다

---

⭐ 이 프로젝트가 도움이 되었다면 **Star**를 눌러주세요!
