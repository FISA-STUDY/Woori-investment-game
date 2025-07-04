package view;

import model.Model;
import model.domain.Stock;
import model.domain.User;


import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import controller.NewsGenerator;
import controller.StockManager;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private static final NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.KOREA);
    private static Model model = Model.getModel(); // Model 인스턴스
    private static StockManager stockManager = new StockManager();
    private static Stock stock;
    
    // 게임 시작 - 플레이어 생성
    public static User createPlayer() {
        printTitle();
        System.out.println();
        System.out.println("🎮 미니투자게임에 오신 것을 환영합니다!");
        System.out.println();
        
        String playerName = "";
        while(playerName.trim().isEmpty()) {
            printPrompt("플레이어 이름을 입력하세요");
            playerName = scanner.nextLine();
            
            if(playerName.trim().isEmpty()) {
                printError("이름을 입력하세요.");
            }
        }
        System.out.println("────────────────────────────────────────");
        System.out.println();

        // Model을 통해 User 객체 생성 (currentPlayer로 자동 설정됨)
        User newUser = model.createNewPlayer(playerName);
        
        printSuccess("환영합니다, " + playerName + "님!");
        System.out.println("💰 초기 자산: " + formatCurrency(newUser.getU_wallet()));
        System.out.println();
        
        return newUser;
    }
    

	public static void printStocks() {
	    System.out.println();
	    System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
	    System.out.println("                            📊 주식 시장 현황 📊                           ");
	    System.out.println("╠══════════════════════════════════════════════════════════════════════╣");
	    System.out.printf("%-20s %-20s %-25s%n", "  📈 종목명", "💰 현재가", "📊 가격 동향");
	    System.out.println("╠══════════════════════════════════════════════════════════════════════╣");
	    
	    for(Stock s : stockManager.showstocks()) {
	        // 주식 이름을 20자로 맞춤 (한글 고려)
	        String stockName = String.format("  %-18s", s.getS_name());
	        
	        // 가격을 20자로 맞춤
	        String price = String.format("%-18s", formatCurrency(s.getS_price()));
	        
	        // 그래프는 그대로
	        double graph = s.getS_graph();
	        
	        System.out.printf("%-20s %-20s %s%n", stockName, price, graph);
	    }
	    
	    System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
	    System.out.println();
	}
    
    public static void sellMenu() {
        // stockManager.stockBuy(stock, 0); // 세미콜론 추가 및 주석 처리
        printInfo("매도 기능을 구현 중입니다.");
    }
    
    //메인 메뉴 상태 출력
    public static void printMainMenu() {
        System.out.println();
        System.out.println("📋 메인 메뉴");
        System.out.println("1. 📊 주식 시장 보기");
        System.out.println("2. 💳 주식 매매");
        System.out.println("3. 📈 포트폴리오 보기");
        System.out.println("4. 📅 다음날로 넘어가기");
        System.out.println("0. 🚪 게임 종료");
        System.out.println();
    }
   
    public static int printMenuChoice() {
        while(true) {
            try {
                printPrompt("메뉴를 선택하세요 (0-4)");
                int choice = Integer.parseInt(scanner.nextLine());
                if(choice >= 0 && choice <= 4) {
                    return choice;
                } else {
                    printError("0-4 사이의 숫자를 입력하세요.");
                }
            } catch(NumberFormatException e) {
                printError("숫자를 입력하세요.");
            }
        }
    }
    
    // 사용자 정보 출력
    public static void displayUserInfo(User user) {
        System.out.println();
        System.out.println("👤 플레이어 정보");
        System.out.println("────────────────────────────────────────");
        System.out.println("이름: " + user.getU_name());
        System.out.println("보유 자산: " + formatCurrency(user.getU_wallet()));
        System.out.println();
    }
    
    // 게임 상태 헤더 출력
    public static void printGameStatus(int day, User user) {
        System.out.println("============================================================");
        System.out.printf("📅 %d일차 | 👤 %s | 💰 %s%n", 
                         day, user.getU_name(), formatCurrency(user.getU_wallet()));
        System.out.println("============================================================");
    }
    
    // 게임 타이틀
    public static void printTitle() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("              💰 미니투자게임 💰               ");
        System.out.println("╚════════════════════════════════════════╝");
        NewsGenerator news = new NewsGenerator();
        System.out.println();
    }
    
    public static void nextDay() {
        System.out.println();
        System.out.println("⏰ 다음날로 넘어갑니다...");
        System.out.println();
        
        // 1. 게임 날짜 증가
        model.incrementDay();
                
        displayDailyNews();
        
        User currentPlayer = model.getCurrentPlayer();
        
        System.out.println();
        printSuccess("새로운 하루가 시작되었습니다!");
        System.out.println();
        
        // 아무 키나 누르면 계속
        printPrompt("계속하려면 Enter를 누르세요");
        scanner.nextLine();
    }

    /**
     * 일일 뉴스 표시
     */
    private static void displayDailyNews() {
        NewsGenerator newsGenerator = new NewsGenerator();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("             📰 오늘의 뉴스 📰              ");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println(newsGenerator.generateNews().getN_message());
        System.out.println();
    }

    // 성공 메시지 출력
    public static void printSuccess(String message) {
        System.out.println("✅ " + message);
    }
    
    // 에러 메시지 출력
    public static void printError(String message) {
        System.out.println("❌ " + message);
    }
    
    // 정보 메시지 출력
    public static void printInfo(String message) {
        System.out.println("ℹ️  " + message);
    }
    
    // 경고 메시지 출력
    public static void printWarning(String message) {
        System.out.println("⚠️  " + message);
    }
    
    // 입력 프롬프트 출력
    public static void printPrompt(String message) {
        System.out.print("➤ " + message + ": ");
    }
    
    // 돈 형식으로 포맷팅
    public static String formatCurrency(int amount) {
        return currencyFormat.format(amount) + "원";
    }
}