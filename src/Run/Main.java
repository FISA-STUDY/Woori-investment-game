package Run;

import model.domain.User;
import view.ConsoleUI;
import model.Model;

public class Main {
    public static void main(String[] args) {
        try {
            Model model = Model.getModel();
            
            // 게임 시작 - 플레이어 생성 
            User player = ConsoleUI.createPlayer();
            
            // 게임 1일차 시작
            int currentDay = 1;
            
            System.out.println("\n=== 게임 시작 ===");
            
            // 게임 상태 헤더 출력
            ConsoleUI.printGameStatus(currentDay, model.getCurrentPlayer());
            
            // 플레이어 정보 출력
            ConsoleUI.displayUserInfo(model.getCurrentPlayer());
            
            //메인 메뉴 출력
            ConsoleUI.printMainMenu();
               
            
        } catch (Exception e) {
            ConsoleUI.printError("게임 실행 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}