package model;

import java.util.List;

import model.domain.News;
import model.domain.PortFolio;
import model.domain.User;
import java.util.List;


public class UserDAO {

	private User currentPlayer;
    
    private static UserDAO model = new UserDAO();
    
    public static UserDAO getModel() {
        return model;
    }
    
    private UserDAO() {}
    
    // News 관련 메서드
    public News[] getNewses() {
//        return db.getNews();
        return null;
    }
        
    // 새 플레이어 생성 (초기 자산 100만원)
    public User createNewPlayer(String playerName) {
        this.currentPlayer = new User(playerName);
        return currentPlayer;
    }
    
    public User getCurrentPlayer() {
    	return currentPlayer;
    }
    
    public void setCurrentPlayer(User user) {
        this.currentPlayer = user;
    }
    
    private int currentDay = 1;


    public void incrementDay() {
        this.currentDay++;
    }

 
    public int getCurrentDay() {
        return currentDay;
    }

  
    public void setCurrentDay(int day) {
        this.currentDay = day;
    }
    
    public static List<PortFolio> getPortFolios(){
//        return db.getPortFolios();
    	return null;
     }
    
}