package model;

import model.domain.News;
import java.util.Random;

public class Database {
    private static News[] news; // 실체화 되는 시점 : 객체 생성시점
    private static Random random = new Random();

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

    /**
     * 모든 뉴스 반환
     */
    public static News[] getNews() {
        return news;
    }
    
    /**
     * 랜덤한 뉴스 하나 반환
     */
    public static News getRandomNews() {
        int randomIndex = random.nextInt(news.length);
        return news[randomIndex];
    }
    
    /**
     * 특정 회사의 뉴스만 반환
     */
    public static News[] getNewsByCompany(String companyName) {
        // 해당 회사 뉴스 개수 계산
        int count = 0;
        for (News n : news) {
            if (n.getS_name().equals(companyName)) {
                count++;
            }
        }
        
        // 해당 회사 뉴스 배열 생성
        News[] companyNews = new News[count];
        int index = 0;
        for (News n : news) {
            if (n.getS_name().equals(companyName)) {
                companyNews[index++] = n;
            }
        }
        
        return companyNews;
    }
    
    /**
     * 특정 회사의 랜덤 뉴스 반환
     */
    public static News getRandomNewsByCompany(String companyName) {
        News[] companyNews = getNewsByCompany(companyName);
        if (companyNews.length > 0) {
            int randomIndex = random.nextInt(companyNews.length);
            return companyNews[randomIndex];
        }
        return null;
    }
    
    /**
     * 뉴스 추가 (동적으로 뉴스를 추가하고 싶을 때)
     */
    public static void addNews(News newNews) {
        News[] newArray = new News[news.length + 1];
        System.arraycopy(news, 0, newArray, 0, news.length);
        newArray[news.length] = newNews;
        news = newArray;
    }
    
    /**
     * 사용 가능한 모든 회사명 반환
     */
    public static String[] getAvailableCompanies() {
        // 중복 제거를 위해 임시로 배열 사용 (실제로는 Set을 사용하는 것이 좋음)
        String[] companies = new String[news.length];
        int count = 0;
        
        for (News n : news) {
            String company = n.getS_name();
            boolean exists = false;
            
            // 이미 존재하는 회사인지 확인
            for (int i = 0; i < count; i++) {
                if (companies[i].equals(company)) {
                    exists = true;
                    break;
                }
            }
            
            // 새로운 회사라면 추가
            if (!exists) {
                companies[count++] = company;
            }
        }
        
        // 실제 크기만큼 배열 생성
        String[] result = new String[count];
        System.arraycopy(companies, 0, result, 0, count);
        return result;
    }
}