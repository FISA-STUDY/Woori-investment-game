package controller;

import java.util.List;

import model.UserDAO;
import model.PortfolioDAO;
import model.domain.PortFolio;
import model.domain.Stock;
import model.domain.User;

public class MarketManager {
   //포트폴리오 가져와서 전체 수량, 가격 정리
//   private List<Portfolio> portfolios = new ArrayList<>();
   private static List<PortFolio>  portfolios = PortfolioDAO.getPortFolios();
   private static UserDAO model = UserDAO.getModel();
   
   
   void buyStock(Stock stock, int num){
	   String userName = model.getCurrentPlayer().getUName();
	   
	      for(PortFolio portfolio : portfolios){
	    	if(portfolio.getPName().equals(stock.getSName())){
	            int now_price = (portfolio.getPPrice()*portfolio.getPAmount() + stock.getSPrice()*num)/(portfolio.getPAmount() +num);
	            portfolio.setPPrice(now_price);
	            portfolio.setPAmount(portfolio.getPAmount()+num);
	            PortFolio pf = new PortFolio(stock.getSName(), num, stock.getSPrice(), model.getCurrentPlayer().getUName());
	            found = true;
	            portfolios.add(pf);

	            
	      }
	}
	      if(!found) {
	    	  PortFolio pf = new PortFolio(stock.getSName(), num, stock.getSPrice(), model.getCurrentPlayer().getUName());
	    	  portfolios.add(pf);
	      }
   }
   boolean sellStock(Stock stock, int num) {
	    for(PortFolio portfolio : portfolios) {
	        if(portfolio.getPName().equals(stock.getSName())) {
	            if(portfolio.getPAmount() < num) {
	                System.out.println("판매할 개수가 보유량보다 클 수 없습니다.");
	                return false;
	            }

	            // 수량 감소
	            portfolio.setPAmount(portfolio.getPAmount() - num);
	            if(portfolio.getPAmount() == 0) {
	            	portfolios.remove(portfolio);
	            }
	            // 평균 단가는 매도 시 갱신하지 않음

	            return true;
	        }
	    }
	    return false; // 보유한 종목이 없음
	}

   public static int calculateTotalPortfolioValue() {
       List<PortFolio> playerPortfolios = portfolios;
       int totalValue = 0;
       
       for (PortFolio portfolio : playerPortfolios) {
           // 현재 주가로 계산
//           Stock currentStock = StockManager.getStockByName(portfolio.getPName());
//           if (currentStock != null) {
//               totalValue += currentStock.getSPrice() * portfolio.getPAmount();
//           }
       }
       
       return totalValue;
   }
   public static int calculateTotalAsset() {
       User currentPlayer = model.getCurrentPlayer();
       return currentPlayer.getUWallet() + calculateTotalPortfolioValue();
   }
   
   public static List<PortFolio> showPortfolio(){
	   return portfolios;
   }
   
}