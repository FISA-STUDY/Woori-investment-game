package controller;

import model.Database;
import model.domain.News;

public class NewsGenerator {
    
    /**
     * 랜덤한 뉴스 생성
     */
    public News generateNews() {
        return Database.getRandomNews();
    }
    
    /**
     * 특정 회사의 랜덤 뉴스 생성
     */
    public News generateNewsForCompany(String companyName) {
        return Database.getRandomNewsByCompany(companyName);
    }
    
    /**
     * 뉴스 생성 후 주식 가격에 바로 반영
     * 이 메서드가 핵심입니다!
     */
    public News generateNewsAndApplyPriceChange() {
        // 1. 랜덤 뉴스 생성
        News todayNews = generateNews();
        
        // 2. 뉴스에 따른 주식 가격 변동 적용
        StockManager.priceChange(todayNews);
        
        return todayNews;
    }
    
    /**
     * 여러 개의 뉴스 생성 (하루에 여러 뉴스가 나올 때)
     */
    public News[] generateMultipleNews(int count) {
        News[] dailyNews = new News[count];
        
        for (int i = 0; i < count; i++) {
            dailyNews[i] = generateNews();
            // 각 뉴스마다 주식 가격 변동 적용
            StockManager.priceChange(dailyNews[i]);
        }
        
        return dailyNews;
    }
}