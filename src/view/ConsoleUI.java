package view;

import model.Model;
import model.StockDatabase;
import model.domain.Stock;
import model.domain.User;
import model.domain.News;  // News 클래스 import 추가

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import controller.NewsGenerator;
import controller.StockManager;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private static final NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.KOREA);
    private static Model model = Model.getModel(); // Model 인스턴스
    
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
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("                            📊 주식 시장 현황 📊                           ");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("  %-18s  %-12s  %-10s  %-8s%n", "📈 종목명", "💰 현재가", "📊 가격 동향", "📦 수량");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");

        for (Stock s : StockManager.showStocks()) {
            String stockName = s.getS_name();
            String price = formatCurrency(s.getS_price());
            double graph = s.getS_graph();
            int amount = s.getS_amount();

            System.out.printf("  %-18s  %-12s  %-10.2f  %-8d%n", stockName, price, graph, amount);
        }

        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }

    
    public static void sellMenu() {
        printInfo("매도 기능을 구현 중입니다.");
        // TODO: 매도 기능 구현
    }
    
    // 메인 메뉴 상태 출력
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
    
    // 주식 매매 메뉴
    public static void printTradeMenu() {
        System.out.println();
        System.out.println("📋 주식 매매 메뉴");
        System.out.println("1. 💳 주식 매수");
        System.out.println("2. 📈 주식 매도");
        System.out.println("0. 🚪 뒤로 가기");
        System.out.println();
    }
    
    public static int printTradeMenuChoice() {
        while(true) {
            try {
                printPrompt("메뉴를 선택하세요 (0-2)");
                int choice = Integer.parseInt(scanner.nextLine());
                if(choice >= 0 && choice <= 2) {
                    return choice;
                } else {
                    printError("0-2 사이의 숫자를 입력하세요.");
                }
            } catch(NumberFormatException e) {
                printError("숫자를 입력하세요.");
            }
        }
    }
    
    // 주식 매수 메뉴
    public static void buyStockMenu() {
        System.out.println();
        System.out.println("💳 주식 매수");
        System.out.println("────────────────────────────────────────");
        
        // 현재 보유 자산 표시
        User currentPlayer = model.getCurrentPlayer();
        System.out.println("💰 보유 자산: " + formatCurrency(currentPlayer.getU_wallet()));
        System.out.println();
        
        // 주식 목록 표시
        printStocks();
        
        while(true) {
            try {
                System.out.println("구입하실 종목과 개수를 입력하세요 (예: 삼성전자 10)");
                printPrompt("종목명과 개수를 입력하세요 (취소하려면 '0' 입력)");
                String input = scanner.nextLine().trim();
                
                if(input.equals("0")) {
                    return; // 메뉴로 돌아가기
                }
                
                String[] parts = input.split("\\s+");
                if(parts.length != 2) {
                    printError("올바른 형식으로 입력해주세요. (예: 삼성전자 10)");
                    continue;
                }
                
                String stockName = parts[0];
                int quantity;
                
                try {
                    quantity = Integer.parseInt(parts[1]);
                    if(quantity <= 0) {
                        printError("수량은 1개 이상이어야 합니다.");
                        continue;
                    }
                } catch(NumberFormatException e) {
                    printError("수량은 숫자로 입력해주세요.");
                    continue;
                }
                
                // 주식 존재 여부 확인
                if(!StockManager.isValidStock(stockName)) {
                    printError("존재하지 않는 주식입니다.");
                    continue;
                }
                
                // 주식 가격 계산
                Stock targetStock = StockManager.getStockByName(stockName);
                int totalCost = targetStock.getS_price() * quantity;
                
                System.out.println();
                System.out.println("📊 주문 정보");
                System.out.println("종목: " + stockName);
                System.out.println("수량: " + quantity + "주");
                System.out.println("단가: " + formatCurrency(targetStock.getS_price()));
                System.out.println("총 금액: " + formatCurrency(totalCost));
                System.out.println();
                
                // 잔고 확인
                if(currentPlayer.getU_wallet() < totalCost) {
                    printError("보유 자산이 부족합니다.");
                    System.out.println("필요 금액: " + formatCurrency(totalCost));
                    System.out.println("보유 자산: " + formatCurrency(currentPlayer.getU_wallet()));
                    continue;
                }
                
                // 구매 확인
                printPrompt("구매하시겠습니까? (y/n)");
                String confirm = scanner.nextLine().trim().toLowerCase();
                
                if(confirm.equals("y") || confirm.equals("yes")) {
                    if(StockManager.stockBuy(stockName, quantity)) {
                        printSuccess("주식 구매가 완료되었습니다!");
                        System.out.println("잔여 자산: " + formatCurrency(model.getCurrentPlayer().getU_wallet()));
                        return;
                    } else {
                        printError("주식 구매에 실패했습니다.");
                    }
                } else {
                    printInfo("구매를 취소했습니다.");
                }
                
            } catch(Exception e) {
                printError("입력 처리 중 오류가 발생했습니다.");
            }
        }
    }
    
    // 주식 매도 메뉴
    public static void sellStockMenu() {
        System.out.println();
        System.out.println("📈 주식 매도");
        System.out.println("────────────────────────────────────────");
        
        printPrompt("계속하려면 Enter를 누르세요");
        scanner.nextLine();
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
        System.out.println();
    }
    
    public static void nextDay() {
        System.out.println();
        System.out.println("⏰ 다음날로 넘어갑니다...");
        System.out.println();
        
        // 1. 게임 날짜 증가
        model.incrementDay();
        
        // 2. 뉴스 생성 및 주식 가격 변동 적용
        NewsGenerator newsGenerator = new NewsGenerator();
        News todayNews = newsGenerator.generateNewsAndApplyPriceChange();
        
        // 3. 뉴스 표시
        displayDailyNews(todayNews);
        
        System.out.println();
        printSuccess("새로운 하루가 시작되었습니다!");
        System.out.println("📊 변동된 주식 시장을 확인해보세요!");
        System.out.println();
        
        // 아무 키나 누르면 계속
        printPrompt("계속하려면 Enter를 누르세요");
        scanner.nextLine();
    }

    /**
     * 일일 뉴스 표시 (뉴스 객체를 직접 받아서 표시)
     */
    private static void displayDailyNews(News todayNews) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("             📰 오늘의 뉴스 📰              ");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("🏢 관련 기업: " + todayNews.getS_name());
        System.out.println((todayNews.getN_isGood() ? "📈 호재" : "📉 악재") + " 뉴스");
        System.out.println();
        System.out.println(todayNews.getN_message());
        System.out.println();
        System.out.println("💹 주식 가격 변동:");
    }
    
    public static void showPortfolio() {
        System.out.println();
        System.out.println("📈 포트폴리오");
        System.out.println("────────────────────────────────────────");
        
        User currentPlayer = model.getCurrentPlayer();
        System.out.println("💰 총 자산: " + formatCurrency(currentPlayer.getU_wallet()));
        System.out.println();             
        printPrompt("계속하려면 Enter를 누르세요");
        scanner.nextLine();
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
        return currencyFormat.format(amount)  + "원";
    }
}