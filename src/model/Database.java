package model;

import model.domain.News;

public class Database {
	private static News[] news; // 실체화 되는 시점 : 객체 생성시점
	
	// byte code가 메모리에 로딩될 때 읽혀지는 시점에 실행
	static {
		news = new News[] {
				new News(1, true, "삼성전자, 차세대 AI 반도체 양산 돌입… 글로벌 점유율 확대 기대", "삼성전자"),
				new News(2, false, "삼성전자, 스마트폰 판매 둔화로 2분기 실적 전망 하향", "삼성전자"),
				new News(3, true, "SK하이닉스, HBM4 개발 가속… 엔비디아 공급 확대 기대감", "SK하이닉스"),
				new News(4, false, "SK하이닉스, 메모리 가격 반등 지연에 수익성 악화 우려", "SK하이닉스"),
				new News(5, true, "네이버, 일본 시장 웹툰 매출 신기록… 해외 성장세 지속", "NAVER"),
				new News(6, false, "네이버, AI 투자 부담에 영업이익 감소 우려", "NAVER"),
				new News(7, true, "카카오, 카카오T 해외 진출 확대… 모빌리티 사업 성장 기대", "카카오"),
				new News(8, false, "카카오, 콘텐츠 매출 성장 둔화에 투자자 불안", "카카오"),
				new News(9, true, "LG에너지솔루션, 테슬라 공급 확대… 배터리 수주 잇따라", "LG에너지솔루션"),
				new News(10, false, "LG에너지솔루션, 원재료 가격 상승 부담에 수익성 하락 우려", "LG에너지솔루션")
		};
		
	}
	
	// 검색 로직이라 가정하세요
	 News[] getNews() {
		 return news;
	 }
}
