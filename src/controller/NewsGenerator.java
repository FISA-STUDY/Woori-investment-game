package controller;

import model.Database;
import model.domain.News;

public class NewsGenerator {
    
    public News generateNews() {
        return Database.getRandomNews();
    }
    
 
    public News generateNewsAndApplyPriceChange() {
        // 1. 랜덤 뉴스 생성
        News todayNews = generateNews();
        
        // 2. 뉴스에 따른 주식 가격 변동 적용
        StockManager.priceChange(todayNews);
        
        return todayNews;
    }
    
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