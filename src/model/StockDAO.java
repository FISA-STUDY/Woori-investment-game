package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import model.domain.Stock;
import util.DBUtil;

public class StockDAO {
   
   private static StockDAO stockDAO = new StockDAO();
   
   public StockDAO() {} 
   
   public static StockDAO getStockDAO() {
      return stockDAO;
   }
   
   public ArrayList<Stock> getStock() throws Exception{
	   Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Stock> all = null;
		try {
			conn = DBUtil.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from Stock");

			
			all = new ArrayList<>();
			while(rs.next()) {	
				all.add(new Stock(rs.getInt("s_id"),
									rs.getString("s_name"),
									rs.getInt("s_price"),
									rs.getDouble("s_graph")));
			}
		} finally {
			DBUtil.close(conn, stmt,rs);
		}
		return all;
   }
   
   public Stock getOneRandomStock() throws Exception{
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   Stock stock = null;
	   try {
		   conn = DBUtil.getConnection();
		   stmt=conn.createStatement();
		   rs=stmt.executeQuery("select * from Stock ORDER BY RAND() limit 1");
		   
		   
		   stock = new Stock(rs.getInt("s_id"),
					   rs.getString("s_name"),
					   rs.getInt("s_price"),
					   rs.getDouble("s_graph"));
		   
	   } finally {
		   DBUtil.close(conn, stmt,rs);
	   }
	   return stock;
   }

}