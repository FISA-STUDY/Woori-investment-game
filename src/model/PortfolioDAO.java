package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.domain.PortFolio;
import model.domain.User;
import util.DBUtil;

public class PortfolioDAO {

    private static final PortfolioDAO portfolioDAO = new PortfolioDAO();
	
	public static PortfolioDAO getPortfolioDAO() {
		return portfolioDAO;
	}
	
    private PortfolioDAO() {}
	
    private String getCurrentUserName() {
    	User current = UserDAO.getModel().getCurrentPlayer();
    	return (current != null) ? current.getUName() : null;
    }
    
    public List<PortFolio> getPortFolios() {
        String userName = getCurrentUserName();
        List<PortFolio> portfolioList = new ArrayList<>();

        if (userName == null) return portfolioList;
		
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Portfolio");

            while (rs.next()) {
                PortFolio pf = new PortFolio();
                pf.setPId(rs.getLong("p_id"));
                pf.setPPrice(rs.getInt("p_price"));
                pf.setPAmount(rs.getInt("p_amount"));
                pf.setUName(rs.getString("u_name"));
                pf.setSName(rs.getString("s_name"));
                pf.setSId(rs.getLong("s_id"));

                portfolioList.add(pf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }

        return portfolioList;
    }
	
	 
	 public void insertPortfolio(PortFolio pf) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = DBUtil.getConnection();
	            String sql = "INSERT INTO Portfolio (p_price, p_amount, u_name, s_name, s_id) VALUES (?, ?, ?, ?, ?)";
	            pstmt.setInt(1, pf.getPPrice());
	            pstmt.setInt(2, pf.getPAmount());
	            pstmt.setString(3, pf.getUName());
	            pstmt.setString(4, pf.getSName());
	            pstmt.setLong(5, pf.getSId());



	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.close(conn, pstmt, null);
	        }
	    }
	 public void updatePortfolio(PortFolio pf) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = DBUtil.getConnection();
	            String sql = "UPDATE Portfolio SET amount = ?, price = ? WHERE user_name = ? AND stock_name = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, pf.getPAmount());
	            pstmt.setInt(2, pf.getPPrice());
	            pstmt.setString(3, pf.getUName());
	            pstmt.setString(4, pf.getSName());

	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.close(conn, pstmt, null);
	        }
	    }
	 
	 public void deletePortfolio(String userName, String stockName) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = DBUtil.getConnection();
	            String sql = "DELETE FROM Portfolio WHERE user_name = ? AND stock_name = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, userName);
	            pstmt.setString(2, stockName);

	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.close(conn, pstmt, null);
	        }
	    }

	}