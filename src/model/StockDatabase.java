package model;

import model.domain.Stock;

public class StockDatabase {
   	
   private static Stock[] s;
   
   static {
      s = new Stock[] {
         new Stock("삼성전자", 70000, 0.0, 100),
         new Stock("SK하이닉스", 85000, 0.0, 100),
         new Stock("NAVER", 180000, 0.0, 100),
         new Stock("LG에너지솔루션",520000,0.0, 100),
         new Stock("카카오",270000, 0.0, 100)
      };
   }
   
   public static Stock[] getStocks() {
      return s;
   }

}