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
            
            boolean isGaming = true;
            while(isGaming)
            {
                int choice = ConsoleUI.printMenuChoice();
            	switch(choice)
            	{
            		case 1:
            			ConsoleUI.printStocks();
            			break;
            		case 2:
            		case 3:
            		case 4:
            		case 0:
            			ConsoleUI.printInfo("게임을 종료했습니다 !");
            			isGaming = false;
            			break;
            		default:
            			ConsoleUI.printError("잘못된 입력입니다.");
            	}
            }
            
        } catch (Exception e) {
            ConsoleUI.printError("게임 실행 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}