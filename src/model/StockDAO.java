package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import model.domain.Stock;
import util.DBUtil;

public class StockDAO {
   
   
   private static StockDAO stmodel = new StockDAO();
   public StockDAO() {} 
   public static StockDAO getModel() {
      return stmodel;
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

			
			all = new ArrayList<>(); // 10개의 메모리 증가
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

}