package controller;

import model.StockModel;
import model.domain.User;
import model.domain.News;
import model.domain.Stock;
import model.Model;
import model.StockDatabase;


public class StockManager {
	
	private static Model stmodel = Model.getModel();

   public static int priceChange(Stock stock) {
      double rate = (4*(Math.random()-2)/10); //-0.2 ~ 0.2 
      int change = (int)(stock.getS_price()*(1+rate));
      stock.setS_price(change);
      return change;
   }

   public static void StockBuy(Stock stock,int num) {
	   //set(get u_wallet - stock.getS_price*num);
   }
   
   public static void StockSell(Stock stock,int num) {
	   //set(get u_wallet + stock.getS_price*num)
      
   }

}
