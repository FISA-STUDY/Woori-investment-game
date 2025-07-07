package controller;

import model.StockModel;
import model.domain.User;
import model.domain.News;
import model.domain.Stock;
import model.Model;
import model.StockDatabase;

public class StockManager {
    
    private static StockModel stmodel = StockModel.getModel();
    private static MarketManager marketManager = new MarketManager();
    private static Model model = Model.getModel();
    
    public static void priceChange(News n) {
        for(Stock stock : stmodel.getStock()) {
            double rate;
            
            if(n.getS_name().equals(stock.getS_name())) {
                // 뉴스에 언급된 주식
                if(n.getN_isGood()) {
                    rate = Math.random() * 0.2; 
                    stock.setS_price((int)(stock.getS_price() * (1 + rate)));
                    System.out.println("📈 " + stock.getS_name() + " 주가 상승: +" + String.format("%.1f", rate * 100) + "%");
                } else {
                    rate = Math.random() * 0.2; 
                    stock.setS_price((int)(stock.getS_price() * (1 - rate)));
                    System.out.println("📉 " + stock.getS_name() + " 주가 하락: -" + String.format("%.1f", rate * 100) + "%");
                }
                stock.setS_graph(rate);
            } else {
                // 다른 주식들의 소폭 랜덤 변동
                rate = (Math.random() - 0.5) * 0.1; 
                stock.setS_price((int)(stock.getS_price() * (1 + rate)));
                stock.setS_graph(Math.abs(rate));
            }
        }
    }
    
    public static boolean stockBuy(String stockName, int num) {
        if (num <= 0) {
            return false;
        }
        
        Stock targetStock = findStockByName(stockName);
        if (targetStock == null) {
            return false;
        }
        
        int totalCost = targetStock.getS_price() * num;
        User currentPlayer = model.getCurrentPlayer();
        
        if (currentPlayer.getU_wallet() >= totalCost) {
            currentPlayer.setU_wallet(currentPlayer.getU_wallet() - totalCost);
            targetStock.setS_amount(targetStock.getS_amount()-num);
            marketManager.buyStock(targetStock,num);
            return true;
        }
        
        return false;
    }
    
    public static boolean stockSell(String stockName, int num) {
        if (num <= 0) {
            return false;
        }
        
        Stock targetStock = findStockByName(stockName);
        if (targetStock == null) {
            return false;
        }
        
        User currentPlayer = model.getCurrentPlayer();
        
        int totalValue = targetStock.getS_price() * num;
        currentPlayer.setU_wallet(currentPlayer.getU_wallet() + totalValue);
        
        return true;
    }
    
    // 주식 이름으로 찾기 헬퍼 메서드
    private static Stock findStockByName(String stockName) {
        for (Stock stock : stmodel.getStock()) {
            if (stockName.equals(stock.getS_name())) {
                return stock;
            }
        }
        return null;
    }
    
    public static Stock[] showStocks() {
        return stmodel.getStock();
    }
    
    // 추가 유틸리티 메서드들
    public static Stock getStockByName(String stockName) {
        return findStockByName(stockName);
    }
    
    public static boolean isValidStock(String stockName) {
        return findStockByName(stockName) != null;
    }
}