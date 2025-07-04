package Run;

import model.domain.User;
import view.ConsoleUI;

public class Main {
	public static void main(String[] args)
	{
		User player = ConsoleUI.createPlayer();
		System.out.println("\n===게임 시작===");
		
		//플레이어 정보
		ConsoleUI.createPlayer();
        int currentDay = 1;
		ConsoleUI.printGameStatus(currentDay, player);
		
		
	}
}
