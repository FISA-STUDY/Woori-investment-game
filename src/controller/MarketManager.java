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
	        	 System.out.println("ㅎㅇㅎㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅇㅎ"+stock.getS_name());
	            int now_price = (portfolio.getP_price()*portfolio.getP_amount() + stock.getS_price()*num)/(portfolio.getP_amount() +num);
	            portfolio.setP_price(now_price);
	            portfolio.setP_amount(portfolio.getP_amount()+num);
	            PortFolio pf = new PortFolio(stock.getS_name(), num, stock.getS_price(), model.getCurrentPlayer().getU_name());
	            portfolios.add(pf);
	            found = true;
	             
	      }
		if(!found) {
			System.out.println("들어와라");
			PortFolio pf = new PortFolio(stock.getS_name(), num, stock.getS_price(), model.getCurrentPlayer().getU_name());
	        portfolios.add(pf);
		}
	}
   }
   //포트폴리오 가져와서 수량정리 + userwallet정리
   void sellStock(Stock stock, int num) {
      for(PortFolio portfolio : portfolios){
         if(portfolio.getP_name().equals(stock.getS_name())){
            //int now_price = (portfolio.getP_price()*portfolio.getP_amount + stock.getS_price()*num)/(portflio.getP_amount +num);
            //portfolio.setP_price(now_price);
            portfolio.setP_amount(portfolio.getP_amount()-num);
            model.getCurrentPlayer().setU_wallet( model.getCurrentPlayer().getU_wallet()+(stock.getS_price()*num));
         }
      }
   }
   
}
