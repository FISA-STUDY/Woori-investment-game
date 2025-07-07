package controller;

import java.util.List;

import model.Model;
import model.domain.PortFolio;
import model.domain.Stock;
import model.domain.User;

public class MarketManager {
   //포트폴리오 가져와서 전체 수량, 가격 정리
//   private List<Portfolio> portfolios = new ArrayList<>();
   private static List<PortFolio>  portfolios = Model.getPortFolios();
   private static Model model = Model.getModel();
   
   
   void buyStock(Stock stock, int num){
	   boolean found = false;
	   
	      for(PortFolio portfolio : portfolios){
	    	if(portfolio.getP_name().equals(stock.getS_name())){
	            int now_price = (portfolio.getP_price()*portfolio.getP_amount() + stock.getS_price()*num)/(portfolio.getP_amount() +num);
	            portfolio.setP_price(now_price);
	            portfolio.setP_amount(portfolio.getP_amount()+num);
	            PortFolio pf = new PortFolio(stock.getS_name(), num, stock.getS_price(), model.getCurrentPlayer().getU_name());
	            found = true;
	            portfolios.add(pf);

	            
	      }
	}
	      if(!found) {
	    	  PortFolio pf = new PortFolio(stock.getS_name(), num, stock.getS_price(), model.getCurrentPlayer().getU_name());
	    	  portfolios.add(pf);
	      }
   }
   boolean sellStock(Stock stock, int num) {
	    for(PortFolio portfolio : portfolios) {
	        if(portfolio.getP_name().equals(stock.getS_name())) {
	            if(portfolio.getP_amount() < num) {
	                System.out.println("판매할 개수가 보유량보다 클 수 없습니다.");
	                return false;
	            }

	            // 수량 감소
	            portfolio.setP_amount(portfolio.getP_amount() - num);

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
           Stock currentStock = StockManager.getStockByName(portfolio.getP_name());
           if (currentStock != null) {
               totalValue += currentStock.getS_price() * portfolio.getP_amount();
           }
       }
       
       return totalValue;
   }
   public static int calculateTotalAsset() {
       User currentPlayer = model.getCurrentPlayer();
       return currentPlayer.getU_wallet() + calculateTotalPortfolioValue();
   }
   
   public static List<PortFolio> showPortfolio(){
	   return portfolios;
   }
   
}