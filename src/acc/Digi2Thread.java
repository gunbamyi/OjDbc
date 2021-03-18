package acc;

public class Digi2Thread extends Thread {
	public void run() {
		for (int cnt = 0; cnt < 10; cnt++) {
		System.out.print(cnt);
		}
	}
		
}
