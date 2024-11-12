package UCMAS_Listening;

import java.util.Date;
import java.util.Scanner;

public class Division {
	static Scanner jn = new Scanner(System.in);
	static String name, type = "Division";
	static int answers[] = new int[10];
	static int input[] = new int[10];
	static String[] correction = new String[10];
	static int qno = 0;
	static int digit1, digit2; // row

	public static void Divide(String dt, String tm) throws InterruptedException {
		String welcome = "*****Welcome to Visual **>Division<** Listening Exercise*******";
		for (int i = 0; i < welcome.length(); i++) {
			System.out.print(welcome.charAt(i));
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("\nEnter the Student name: ");
		name = jn.nextLine().toUpperCase();
		System.out.print("\nNo of first number(digit): ");
		int d1 = jn.nextInt();
		System.out.print("\nNo of second number(digit): ");
		int d2 = jn.nextInt();
		System.out.print("\n Enter the time (in Seconds) : ");
		int sec = jn.nextInt();
		d1 = (d1 == 1) ? 9 : ((d1 == 2) ? 99 : ((d1 == 3) ? 999 : 9999));
		d2 = (d2 == 1) ? 9 : ((d2 == 2) ? 99 : ((d2 == 3) ? 999 : 9999));
//	    digit1 = ""; digit2="";
//	    int j =0;
//	    while(j<mtype.length()) {
//	    	while(isDigit(mtype.charAt(j))) {
//	    		digit1+=mtype.charAt(j++);
//	    	}
//	    	if(mtype.charAt(j)=='x'||mtype.charAt(j)=='X'||mtype.charAt(j)=='*') {
//	    		j++;
//	    		while(isDigit(mtype.charAt(j))&&j<mtype.length()) {
//		    		digit2+=mtype.charAt(j++);
//		    	}
//	    	}
//	    	if(j==mtype.length()) {
//	    		j--;
//	    	}
//	    }
//	    
//	    int d1 = Integer.parseInt(digit1);
//	    int d2 = Integer.parseInt(digit2);
//	    System.out.println("first digit: " +d1);
//	    System.out.println("second digit: " +d2);
		int countmarks = 0, questioncount = 1;

		while (questioncount <= 11) {
			int sum[] = new int[1]; // rows
			int mul = 0;
			System.out.println("\n  Question No: " + questioncount++);
			for (int i = 0; i < sum.length; ) {
				int n1 = (int) (Math.random() * d1);
				int n2 = (int) (Math.random() * d2+1);
				if(n1%n2==0) {
				System.out.print("\n\t\t" + n1 + " / " + n2 + " = ");
				mul = n1 / n2;
				Thread.sleep(300);
				i++;
			}
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
			answers[qno] = mul;
			input[qno] = answer;
			qno++;
			if (mul == answer)
				countmarks++;
//				System.out.println((answer == add) ? "Yes, your answer is correct" : "No, answer is : " + add);
			if (questioncount == 11) {
				for (int i = 0; i < correction.length; i++) {
					if (answers[i] != input[i])
						correction[i] = " X";
					else
						correction[i] = " C";
				}
//				System.out.println("\n" + name + ", you scored Total marks : " + countmarks + "/10");
//				System.out.println("\nYour Answers         : " + Arrays.toString(input));
//				System.out.println("Correction           : " + Arrays.toString(correction));
//         		System.out.println("Correct answers      : " + Arrays.toString(answers));
				System.out.println("\n bye bye - " + name);
				saveCorrect();
				saveInput();
				Visual_Exercise.saveData(dt, tm, name, type, countmarks);
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
