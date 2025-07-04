package controller;

import model.StockModel;
import model.domain.User;
import model.domain.News;
import model.domain.Stock;
import model.Model;
import model.StockDatabase;


public class StockManager {
	
	private static StockModel stmodel = StockModel.getModel();
	private static Model model = Model.getModel();

   public static void priceChange(News n) {
	   
      for(Stock stock:stmodel.getStock()) {
    	  
    	  
    	  if(n.getS_name().equals(stock.getS_name())) {
    		  if(n.getN_isGood()) {
    			  double rate = ((Math.random())*2/10);// 현 주식가격*(1+0~0.2)
    			  stock.setS_price((int)(stock.getS_price()*(1+rate)));
    		  } else {
    			  double rate = ((Math.random())*2/10);// 현 주식가격*(1+0~0.2)
    			  stock.setS_price((int)(stock.getS_price()*(1-rate)));
    		  	}
    		  } else {
    			  double rate = ((Math.random())*2/10); //-0.2 ~ 0.2 
    			  int change = (int)(stock.getS_price()*(1+rate));
    			  stock.setS_price(change);
    	  }
      }
   }

//   public static boolean stockBuy(Stock stock,int num) {
//	   if(model.getCurrentPlayer().getU_wallet() >= stock.getS_price()*num) {
//		   model.getCurrentPlayer().setU_wallet((model.getCurrentPlayer().getU_wallet() - stock.getS_price()*num));
//		   return true;
//	   } else {
//		   return false;
//	   }
//   }
   
   public static boolean stockBuy(String stockName,int num) {
	   
	   for(Stock stock:stmodel.getStock() ) {
		   if(stockName.equals(stock.getS_name())){
			   if(model.getCurrentPlayer().getU_wallet() >= stock.getS_price()*num) {
				   model.getCurrentPlayer().setU_wallet((model.getCurrentPlayer().getU_wallet() - stock.getS_price()*num));
				   return true;
			   } else {
				   return false;
			   }
		   } else {
			   return false;
		   }
	   }
	   return false;
   }
   
   public static void stockSell(String stockName,int num) {
	   
	   for(Stock stock:stmodel.getStock() ) {
		   if(stockName.equals(stock.getS_name())) {
			   model.getCurrentPlayer().setU_wallet((model.getCurrentPlayer().getU_wallet() + stock.getS_price()*num));
		   }
	   }
   }
   
   public static Stock[] showstocks() {
	   return stmodel.getStock();
   }

}
