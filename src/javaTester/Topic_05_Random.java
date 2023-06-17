package javaTester;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		Random rand = new Random();

		System.out.println(rand.nextInt(9999)); //Nên dùng
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextFloat());
		System.out.println(rand.nextLong());
		System.out.println("automation" + rand.nextInt(999)+ "@gmail.com");
	}

}
