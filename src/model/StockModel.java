package model;

import model.domain.Stock;

public class StockModel {
	
	private StockDatabase db = new StockDatabase();
	
	private static StockModel model = new StockModel();
	private StockModel() {} 
	public static StockModel getModel() {
		return model;
	}
	
	public Stock[] getStock() {
		StockDatabase db = new StockDatabase();
		return db.getStocks();
	}

}