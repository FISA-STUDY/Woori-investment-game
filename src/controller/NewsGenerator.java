package controller;

import model.NewsDAO;
import model.StockDAO;
import model.domain.News;
import model.domain.Stock;
import model.dto.NewsStockPair;

public class NewsGenerator {

    private static StockDAO stockDAO = StockDAO.getStockDAO();
<<<<<<< HEAD
	private static NewsDAO newsDAO = NewsDAO.getNewsDAO();
 
    public NewsStockPair generateNews() throws Exception {
    	News todayNews =  newsDAO.getNews();
		Stock randStock = stockDAO.getOneRandomStock();
        return NewsStockPair.builder()
        		.news(todayNews)
        		.stock(randStock)
        		.build();
=======
   private static NewsDAO newsDAO = NewsDAO.getNewsDAO();
 
    public NewsStockPair generateNews() throws Exception {
       News todayNews =  newsDAO.getNews();
      Stock randStock = stockDAO.getOneRandomStock();
        return NewsStockPair.builder()
              .news(todayNews)
              .stock(randStock)
              .build();
>>>>>>> 9c54e19 (feat: 로그인/회원가입 기능 구현)
    }
    
}