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
            while(isGaming) {
                ConsoleUI.printMainMenu();

                int choice = ConsoleUI.printMenuChoice();
                switch(choice) {
                    case 1:
                        // 주식 시장 보기
                        System.out.println("\n📊 주식 시장 현황");
                        ConsoleUI.printStocks();
                        break;

                    case 2:
                        boolean inTradeMenu = true;
                        while(inTradeMenu) {
                            ConsoleUI.printTradeMenu();
                            int tradeChoice = ConsoleUI.printTradeMenuChoice();
                            
                            switch(tradeChoice) {
                                case 1:
                                    ConsoleUI.buyStockMenu();
                                    break;
                                case 2:
                                    ConsoleUI.sellStockMenu();
                                    break;
                                case 0:
                                    inTradeMenu = false;
                                    break;
                                default:
                                    ConsoleUI.printError("잘못된 입력입니다.");
                            }
                        }
                        break;

                    case 3:
                        ConsoleUI.showPortfolio();
                        break;

                    case 4:
                        ConsoleUI.nextDay();
                        currentDay = model.getCurrentDay(); 

                        ConsoleUI.printGameStatus(currentDay, model.getCurrentPlayer());

                        ConsoleUI.displayUserInfo(model.getCurrentPlayer());
                        break;

                    case 0:
                        // 게임 종료
                        System.out.println();
                        ConsoleUI.printInfo("게임을 종료합니다. 수고하셨습니다!");
                        System.out.println();
                        ConsoleUI.printSuccess("최종 게임 결과:");
                        System.out.println("────────────────────────────────────────");
                        System.out.println("📅 플레이 일수: " + currentDay + "일");
                        System.out.println("👤 플레이어: " + model.getCurrentPlayer().getU_name());
                        System.out.println("💰 최종 보유 자산: " + ConsoleUI.formatCurrency(model.getCurrentPlayer().getU_wallet()));
                        System.out.println();
                        
                        isGaming = false;
                        break;

                    default:
                        ConsoleUI.printError("잘못된 입력입니다.");
                }

                // 게임이 계속되는 경우 구분선 출력
                if(isGaming && choice != 0) {
                    System.out.println("\n" + "=".repeat(60));
                }
                
                if(currentDay == 20)
                	{
	                	 System.out.println();
	                     ConsoleUI.printInfo("게임을 종료합니다. 수고하셨습니다!");
	                     System.out.println();
	                     ConsoleUI.printSuccess("최종 게임 결과:");
	                     System.out.println("────────────────────────────────────────");
	                     System.out.println("📅 플레이 일수: " + currentDay + "일");
	                     System.out.println("👤 플레이어: " + model.getCurrentPlayer().getU_name());
	                     System.out.println("💰 최종 보유 자산: " + ConsoleUI.formatCurrency(model.getCurrentPlayer().getU_wallet()));
	                     System.out.println();
	                     isGaming = false;
	                     break;
                	}
            }

        } catch (Exception e) {
            ConsoleUI.printError("게임 실행 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}