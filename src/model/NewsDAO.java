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
			
			
<<<<<<< HEAD
			news = new News(rs.getInt("id"),
			rs.getBoolean("n_isGood"),
			rs.getString("n_message"));
=======
			news = new News(); // 10개의 메모리 증가
			new News(rs.getInt("empno"),
			rs.getString("ename"),
			rs.getInt("deptno"));
			}
>>>>>>> 9c54e19 (feat: 로그인/회원가입 기능 구현)
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DBUtil.close(conn,stmt,rs);
			
		}
		return news;
	}
    
	
}
