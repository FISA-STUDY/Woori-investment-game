package model;

import model.domain.Stock;

public class StockModel {
   
   private StockDatabase db = new StockDatabase();
   
   private static StockModel stmodel = new StockModel();
   public StockModel() {} 
   public static StockModel getModel() {
      return stmodel;
   }
   
   public Stock[] getStock() {
      StockDatabase db = new StockDatabase();
      return db.getStocks();
   }

}