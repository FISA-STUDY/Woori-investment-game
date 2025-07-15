package model;

import java.sql.*;
import model.domain.User;
import util.DBUtil;

public class UserDAO {

    private static UserDAO model = new UserDAO();
    public static UserDAO getModel() {
        return model;
    }

    private UserDAO() {}

    private User currentPlayer;

    // 현재 로그인한 유저 정보 반환
    public User getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isUserExists(String uName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT u_name FROM User WHERE u_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uName);
            rs = pstmt.executeQuery();
            return rs.next(); // 존재하면 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }

    // 회원가입
    public boolean register(String uName, String uPassword) {
        if (isUserExists(uName)) {
            return false; // 이미 존재하는 사용자
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO User (u_name, u_password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uName);
            pstmt.setString(2, uPassword);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 내용을 확인하고 로그에 남길 수 있음
            return false;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

 // 로그인
    public boolean login(String uName, String uPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM User WHERE u_name = ? AND u_password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uName);
            stmt.setString(2, uPassword);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int wallet = rs.getInt("u_wallet");
                currentPlayer = new User(uName, uPassword, wallet); 
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }

        return false;
    }
    
    public User createNewPlayer(String playerName) {
        String defaultPassword = "default"; 
        boolean inserted = register(playerName, defaultPassword); 

        if (inserted) {
            this.currentPlayer = new User(playerName, defaultPassword, 1000000);
            return currentPlayer;
        } else {
            return null;
        }
    }


    
    private int currentDay = 1;

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int day) {
        this.currentDay = day;
    }

    public void incrementDay() {
        this.currentDay++;
    }

	public Object getPortFolios() {
		// TODO Auto-generated method stub
		return null;
	}


}
