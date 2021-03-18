package acc;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccountApp {
	public static void main(String[] args) throws FileNotFoundException {
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		Account b = new Account();
		b.accountinfo();

		while (run) {
			try {
				System.out.println("----------------------------------------");
				System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.기간별조회 | 5.종료");
				System.out.println("----------------------------------------");
				System.out.print("선택 : ");
				int menuNo = Integer.parseInt(sc.nextLine());
				switch (menuNo) {
				case 1:
					b.deposit();
					break;
				case 2:
					b.witdraw();
					break;
				case 3:
					b.printBalance();
					break;
				case 4:
					b.date_check();
					break;
				case 5:
					run = b.shutdown(run);
					break;
				default:
					System.out.println("잘못누르셨습니다.");
					break;
				}
			} catch (java.util.InputMismatchException | java.lang.NumberFormatException e) {
				System.out.println("잘못누르셨습니다.");
				sc = new Scanner(System.in);
			}
		}
		sc.close();
	}
}
