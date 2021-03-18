package acc;

public class AlphabetThread extends Thread {
	 public void run() {
		 for (char ch = 'A'; ch <= 'Z'; ch++) {
				System.out.print(ch);
				}
	}
		
}
