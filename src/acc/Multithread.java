package acc;

public class Multithread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new DigitThread();
		Thread t2 = new DigitThread();
		Thread t3 = new AlphabetThread();

		
		t1.start();
		t2.start();
		t3.start();
		// 스레드를 생성
//		thread.start(); // 스레드를 시작
//		for (char ch = 'A'; ch <= 'Z'; ch++) {
//		System.out.print(ch);
//		}
//		
		
}

}
