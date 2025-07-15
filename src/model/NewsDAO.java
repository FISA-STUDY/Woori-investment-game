package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.domain.News;
import util.DBUtil;

public class NewsDAO {

   private static NewsDAO newsDAO = new NewsDAO();

    public static NewsDAO getNewsDAO() {
        return newsDAO;
    }
    
    private NewsDAO() {}
    
    public News getNews() throws Exception{
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      News news = null;
      try {
         conn = DBUtil.getConnection();
         stmt=conn.createStatement();
         rs=stmt.executeQuery("select * from News ORDER BY RAND() limit 1");
         
         
         news = new News(rs.getInt("id"),
         rs.getBoolean("n_isGood"),
         rs.getString("n_message"));
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         
         DBUtil.close(conn,stmt,rs);
         
      }
      return news;
   }
    
   
}
