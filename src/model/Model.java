package model;

import model.domain.News;
import model.domain.User;

public class Model {
    private Database db = new Database();

	private User currentPlayer;
    
    private static Model model = new Model();
    
    // 게임 상수
    private static final int INITIAL_WALLET = 1000000; // 초기 자산 100만원
    
    public static Model getModel() {
        return model;
    }
    
    private Model() {}
    
    // News 관련 메서드
    public News[] getNewses() {
        return db.getNews();
    }
        
    // 새 플레이어 생성 (초기 자산 100만원)
    public User createNewPlayer(String playerName) {
        this.currentPlayer = new User(playerName, INITIAL_WALLET);
        return currentPlayer;
    }
    
    public User getCurrentPlayer() {
    	return currentPlayer;
    }
    
    public void setCurrentPlayer(User user) {
        this.currentPlayer = user;
    }
    
    
}