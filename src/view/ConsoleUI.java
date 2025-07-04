package view;

import model.domain.User;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private static final NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.KOREA);
    
    // 게임 시작 - 플레이어 생성
    public static User createPlayer() {
        printTitle();
        System.out.println();
        System.out.println("🎮 미니투자게임에 오신 것을 환영합니다!");
        System.out.println("💰 초기 자산 1,000만원으로 투자를 시작하세요!");
        System.out.println();
        
        String playerName = "";
        while(playerName.trim().isEmpty()) {
            printPrompt("플레이어 이름을 입력하세요");
            playerName = scanner.nextLine();
            
            if(playerName.trim().isEmpty()) {
                printError("이름을 입력하세요.");
            }
        }
        
        // User 객체 생성 (초기 자산 1000만원)
        User newUser = new User(playerName, 10000000);
        
        printSuccess("환영합니다, " + playerName + "님!");
        System.out.println("💰 초기 자산: " + formatCurrency(10000000));
        System.out.println();
        pauseScreen();
        
        return newUser;
    }
    
    // 사용자 정보 출력
    public static void displayUserInfo(User user) {
        System.out.println("👤 플레이어 정보");
        System.out.println("────────────────────────────────────────");
        System.out.println("이름: " + user.getU_name());
        System.out.println("보유 자산: " + formatCurrency(user.getU_wallet()));
        System.out.println();
    }
    
    // 사용자 자산 업데이트 후 알림
    public static void notifyWalletUpdate(User user, int previousAmount) {
        int difference = user.getU_wallet() - previousAmount;
        
        if (difference > 0) {
            printSuccess("💰 자산이 " + formatCurrency(difference) + " 증가했습니다!");
        } else if (difference < 0) {
            printWarning("💸 자산이 " + formatCurrency(Math.abs(difference)) + " 감소했습니다.");
        }
        
        System.out.println("현재 보유 자산: " + formatCurrency(user.getU_wallet()));
    }
    
    // 자산 부족 경고
    public static void warnInsufficientFunds(User user, int requiredAmount) {
        printError("자산이 부족합니다!");
        System.out.println("필요 금액: " + formatCurrency(requiredAmount));
        System.out.println("보유 자산: " + formatCurrency(user.getU_wallet()));
        System.out.println("부족 금액: " + formatCurrency(requiredAmount - user.getU_wallet()));
    }
    
    // 게임 타이틀
    public static void printTitle() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║          💰 미니투자게임 💰            ║");
        System.out.println("╚════════════════════════════════════════╝");
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
    
    // 일시정지 (Enter 키 대기)
    public static void pauseScreen() {
        System.out.println("📋 계속하려면 Enter를 누르세요...");
        try {
            System.in.read();
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // 예외 처리
        }
    }
}