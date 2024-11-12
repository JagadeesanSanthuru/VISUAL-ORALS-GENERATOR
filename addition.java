package UCMAS_Listening;

import java.util.Arrays;
import java.util.Scanner;

public class addition {

	static Scanner jn = new Scanner(System.in);
	static String name, type = "Addition";
	static int answers[] = new int[10];
	static int input[] = new int[10];
	static String[] correction = new String[10];
	static int qno = 0, d1;

	public static void addition(String dt, String tm) throws InterruptedException {
		String welcome = "*****Welcome to Visual Addition Listening Exercise*******";
		for (int i = 0; i < welcome.length(); i++) {
			System.out.print(welcome.charAt(i));
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("\n Enter the Student name : ");
		name = jn.nextLine().toUpperCase();
		System.out.print("\n Enter the no of rows: ");
		int nr = jn.nextInt();
		System.out.print("\n Enter the no of digit: ");
		d1 = jn.nextInt();
		d1 = (d1 == 1) ? 9 : ((d1 == 2) ? 99 : ((d1 == 3) ? 999 : 9999));
//		System.out.println(d1);
		System.out.print("\n Enter the time (in Seconds) : ");
		int sec = jn.nextInt();
		int countmarks = 0, questioncount = 1;

		while (questioncount <= 11) {
			int sum[] = new int[nr]; // rows
			int add = 0;
			System.out.println("\n  Question No: " + questioncount++);
			for (int i = 0; i < sum.length; i++) {
				if (i / 2 == 0 || i == 1) {
					sum[i] = (int) (Math.random() * d1); // digit
					System.out.println("\t\t\t" + sum[i]);
					add += sum[i];
				} else if ((i / 2 == 1 || i != 1)) {
					sum[i] = (int) ((Math.random() * d1) - (Math.random() * d1)); // digit
					System.out.println("\t\t\t" + sum[i]);
					add += sum[i];
				}
				Thread.sleep(300);
			}

			System.out.println("\n");
			if (sec > 1) {
				for (int i = sec; i >= 1; i--) { // time(seconds)
					System.out.print(i + ",");
					if (i == 0) {
						System.out.print(" - ");
						System.out.println("time up");
					}
					Thread.sleep(1000);
				}
			}
			System.out.print("  Say the answer  :   ");
			int answer = jn.nextInt();
			answers[qno] = add;
			input[qno] = answer;
			qno++;
			if (add == answer)
				countmarks++;
//				System.out.println((answer == add) ? "Yes, your answer is correct" : "No, answer is : " + add);
			if (questioncount == 11) {
				for (int i = 0; i < correction.length; i++) {
					if (answers[i] != input[i])
						correction[i] = " X ";
					else
						correction[i] = " C ";
				}
//				System.out.println("\n" + name + ", you scored Total marks : " + countmarks + "/10");
//				System.out.println("\nYour Answers         : " + Arrays.toString(input));
//				System.out.println("Correction           : " + Arrays.toString(correction));
//				System.out.println("Correct answers      : " + Arrays.toString(answers));
				saveCorrect();
				saveInput();
				Visual_Exercise.saveData(dt, tm, name, type, countmarks);
				System.out.println("\n bye bye - " + name);
				return;
			}

		}

	}

	public static void saveCorrect() {
		Visual_Exercise.answers = answers;
//		System.out.println(Arrays.toString(Visual_Exercise.answers);
	}

	public static void saveInput() {
		Visual_Exercise.input = input;
//		System.out.println(Arrays.toString(Visual_Exercise.input));
	}

}
