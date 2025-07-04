package model;

import model.domain.Stock;

public class StockDatabase {
   	
   private static Stock[] s;
   
   static {
      s = new Stock[] {
         new Stock("     삼성전자      ", 70000, 0.1),
         new Stock("     SK하이닉스    ", 85000, 0.15),
         new Stock("     NAVER          ", 180000, 0.2),
         new Stock("     LG에너지솔루션",520000,0.11)
      };
   }
   
   public static Stock[] getStocks() {
      return s;
   }

}