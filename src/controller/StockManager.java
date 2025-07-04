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

   public static void priceChange() {
	   
      for(Stock stock:stmodel.getStock()) {
          double rate = (4*(Math.random()-2)/10); //-0.2 ~ 0.2 
          int change = (int)(stock.getS_price()*(1+rate));
          stock.setS_price(change);
      }
   }

   public static boolean StockBuy(Stock stock,int num) {
	   if(model.getCurrentPlayer().getU_wallet() >= stock.getS_price()*num) {
		   model.getCurrentPlayer().setU_wallet((model.getCurrentPlayer().getU_wallet() - stock.getS_price()*num));
		   return true;
	   } else {
		   return false;
	   }
   }
   
   public static void StockSell(Stock stock,int num) {
	   model.getCurrentPlayer().setU_wallet((model.getCurrentPlayer().getU_wallet() + stock.getS_price()*num));
   }

}
