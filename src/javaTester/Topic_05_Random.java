package javaTester;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		System.out.println("automation" + getRandomNumber() + "@gmail.com");
		System.out.println("automation" + getRandomNumber() + "@gmail.com");
		System.out.println("automation" + getRandomNumber() + "@gmail.com");
		System.out.println("automation" + getRandomNumber() + "@gmail.com");
	}
	
	//Do hàm main là statuc, nên để hàm main gọi được 1 hàm khác thì hàm đó cũng phải là static
	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
