package UCMAS_Listening;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Scanner;

public class Visual_Exercise {
	static Scanner jn = new Scanner(System.in);
	static String name, type = "Multiplication";
	static int answers[] = new int[10];
	static int input[] = new int[10];
	static String[] correction = new String[10];
	static int qno = 0;
	static int digit1, digit2; // row
	static String dp;

	public static void multiply(String dt, String tm) throws InterruptedException {
		dp = dt;
		String welcome = "*****Welcome to Visual Multiplication Listening Exercise*******";
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
			for (int i = 0; i < sum.length; i++) {
				int n1 = (int) (Math.random() * d1);
				int n2 = (int) (Math.random() * d2);
				System.out.print("\n\t\t" + n1 + " X " + n2 + " = ");
				mul = n1 * n2;
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
				saveData(dt, tm, name, type, countmarks);
				return;
			}

		}

	}

	public static void saveData(String date, String time, String name, String type, int mark) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = c.prepareStatement(
					"insert into digital_visual_exercise(Date, Time, Name, Type, Marks) values(?,?,?,?,?)");

			ps.setString(1, date);
			ps.setString(2, time);
			ps.setString(3, name);
			ps.setString(4, type);
			ps.setInt(5, mark);

			ps.execute();

			saveTotal(date, name);
			saveCorrect();
			saveInput();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Handled");
			e.printStackTrace();
		}
	}

	public static void saveTotal(String date, String name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement pm = c
					.prepareStatement("Select Marks from digital_visual_exercise where Name = ? and Date = ?");
			pm.setString(1, name);
			pm.setString(2, date);

			ResultSet rs = pm.executeQuery();
			int totalmark = 0, maxMark = 0;
			while (rs.next()) {
				totalmark += rs.getInt(1);
				maxMark += 10;
			}

			PreparedStatement pm1 = c
					.prepareStatement("insert into totalmarks (Date, Name, TotalMark, MaxMark) values(?,?,?,?)");
			pm1.setString(1, date);
			pm1.setString(2, name);
			pm1.setInt(3, totalmark);
			pm1.setInt(4, maxMark);
			pm1.execute();

		} catch (Exception e) {
			System.out.println("Total Marks Exception Handled");
			e.printStackTrace();
		}
	}

	public static void searchData(String dt) {

		System.out.println("\nResults Operation:- \n  1.Search by Date  \n  2.Search by name  \n  3.Entire Day Result");
		System.out.print("\nEnter the input----> ");
		switch (jn.nextInt()) {
		case 1: {
			System.out.print("\nEnter the Date --->");
			String date = jn.next();

			if (date.equals("today") || date.equals("Today") || date.equals("TODAY"))
				date = dt;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root",
						"root");

				PreparedStatement ps = c.prepareStatement("Select * from digital_visual_exercise where date = ?");
				ps.setString(1, date);
				ResultSet rs = ps.executeQuery();
				System.out.println("Results retriving from the database.... ");
				System.out.println("***************************");
				Thread.sleep(1000);
				while (rs.next()) {
					int sno = rs.getInt(1);
					System.out.println("Session id: " + sno);
					System.out.println("\nDate:  " + rs.getString(2));
					System.out.println("Time:  " + rs.getString(3));
					System.out.println("Name:  " + rs.getString(4));
					System.out.println("Type:  " + rs.getString(5));
					System.out.println("Marks: " + rs.getInt(6) + "/10");
					searchInput(sno);
					searchCorrect(sno);
					System.out.println("==================================================");
					Thread.sleep(2500);

				}
				System.out.println("***************************************************");
				c.close();
			} catch (Exception e) {
				System.out.println("Exception Handled");
				e.printStackTrace();
			}
			break;
		}
		case 2: {
			jn.nextLine();
			System.out.print("\nEnter the name: ");
			String name = jn.nextLine().toUpperCase();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root",
						"root");

				PreparedStatement ps = c
						.prepareStatement("Select * from digital_visual_exercise where name = ? and date = ?");
				ps.setString(1, name);
				ps.setString(2, dt);
				ResultSet rs = ps.executeQuery();
				System.out.println("Results retriving from the database.... ");
				System.out.println("***************************");
				Thread.sleep(1000);
				while (rs.next()) {
					int sno = rs.getInt(1);
					System.out.println("Session id: " + sno);
					System.out.println("\nDate:  " + rs.getString(2));
					System.out.println("Time:  " + rs.getString(3));
					System.out.println("Name:  " + rs.getString(4));
					System.out.println("Type:  " + rs.getString(5));
					System.out.println("Marks: " + rs.getInt(6) + "/10");
					searchInput(sno);
					searchCorrect(sno);
					System.out.println("==================================================");
					Thread.sleep(4000);

				}
				System.out.println("***************************");
				c.close();
			} catch (Exception e) {
				System.out.println("Exception Handled");
				e.printStackTrace();
			}

			break;
		}

		case 3: {
			System.out.println("\n  Enter the Date:-\n  1.today(" + dt + ")\n  2.Enter Date");
			System.out.print("\nEnter the input---> ");
			int sd = jn.nextInt();
			if (sd == 2) {
				System.out.print("Enter the Search Date: ");
				dt = jn.next();
				System.out.println();
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root",
						"root");

				PreparedStatement ps = c
						.prepareStatement("Select  Name, max(TotalMark), max(MaxMark) from totalmarks where Date = ?"
								+ "              group by Name");
				ps.setString(1, dt);

				ResultSet rs = ps.executeQuery();

				System.out.println(" " + dt + " -  marks from the database.... ");
				System.out.println("\n***************************");
				Thread.sleep(2000);
				int sno = 1;
				while (rs.next()) {
					System.out.println(sno + ". " + rs.getString(1) + " - " + rs.getInt(2) + "/" + rs.getInt(3));
					sno++;
					Thread.sleep(2000);
				}
				System.out.println("***************************");
				Thread.sleep(4000);
				c.close();
			} catch (Exception e) {
				System.out.println("Exception Handled");
				e.printStackTrace();
			}

			break;
		}
		default: {
			System.out.println("----> Enter the Valid input.....");
			break;
		}
		}

	}

	public static void searchCorrect(int sno) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = c.prepareStatement(
					"Select c1, c2, c3, c4, c5, c6, c7, c8, c9, c10 from correct_answers where sno = ?");
			ps.setInt(1, sno);
			ResultSet rs = ps.executeQuery();

			int i = 1, j = 0;
			while (rs.next()) {
				while (j < answers.length) {
					answers[j] = rs.getInt(i);
					j++;
					i++;
				}
			}
//				
			System.out.println("Correct answers  :" + Arrays.toString(answers));
			c.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}
	}
	
	public static void searchCorrect1(int sno) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = c.prepareStatement(
					"Select c1, c2, c3, c4, c5, c6, c7, c8, c9, c10 from correct_answers where sno = ?");
			ps.setInt(1, sno);
			ResultSet rs = ps.executeQuery();

			int i = 1, j = 0;
			while (rs.next()) {
				while (j < answers.length) {
					answers[j] = rs.getInt(i);
					j++;
					i++;
				}
			}
//				
			fw.write("Correct answers  :" + Arrays.toString(answers)+"\n\n");
			c.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}
	}

	public static void searchInput(int sno) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = c
					.prepareStatement("Select y1, y2, y3, y4, y5, y6, y7, y8, y9, y10 from your_answers where sno = ?");
			ps.setInt(1, sno);
			ResultSet rs = ps.executeQuery();

			int i = 1, j = 0;
			while (rs.next()) {
				while (j < input.length) {
					input[j] = rs.getInt(i);
					j++;
					i++;
				}
			}
//		
			System.out.println("Your answers    : " + Arrays.toString(input));
			c.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}
	}

	public static void searchInput1(int sno, String name) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = c
					.prepareStatement("Select y1, y2, y3, y4, y5, y6, y7, y8, y9, y10 from your_answers where sno = ?");
			ps.setInt(1, sno);
			ResultSet rs = ps.executeQuery();

			int i = 1, j = 0;
			while (rs.next()) {
				while (j < input.length) {
					input[j] = rs.getInt(i);
					j++;
					i++;
				}
			}
//		
			fw.write(( name  +"'s answers : " + Arrays.toString(input))+"\n");
			c.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}
	}
	public static void saveCorrect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection cc1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = cc1.prepareStatement(
					"insert into correct_answers(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10) values(?,?,?,?,?,?,?,?,?,?)");
//				System.out.println(Arrays.toString(answers));
			ps.setInt(1, answers[0]);
			ps.setInt(2, answers[1]);
			ps.setInt(3, answers[2]);
			ps.setInt(4, answers[3]);
			ps.setInt(5, answers[4]);
			ps.setInt(6, answers[5]);
			ps.setInt(7, answers[6]);
			ps.setInt(8, answers[7]);
			ps.setInt(9, answers[8]);
			ps.setInt(10, answers[9]);
			ps.executeUpdate();
			cc1.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}

	}

	public static void saveInput() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection cc1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root", "root");

			PreparedStatement ps = cc1.prepareStatement(
					"insert into your_answers(y1, y2, y3, y4, y5, y6, y7, y8, y9, y10) values(?,?,?,?,?,?,?,?,?,?)");
//				System.out.println(Arrays.toString(answers));
			ps.setInt(1, input[0]);
			ps.setInt(2, input[1]);
			ps.setInt(3, input[2]);
			ps.setInt(4, input[3]);
			ps.setInt(5, input[4]);
			ps.setInt(6, input[5]);
			ps.setInt(7, input[6]);
			ps.setInt(8, input[7]);
			ps.setInt(9, input[8]);
			ps.setInt(10, input[9]);
			ps.executeUpdate();
			System.out.println(" *****Marks Stored successfully in the database*****");

			cc1.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}

	}

	static File F;
	static FileWriter fw;

	public static void printData(String dt) {
		F = new File("F:\\Jagadeesan Santhuru\\ABACUS ONLINE\\UCMAS VALASARAVAKKAM\\marks online\\VisualOrals.pdf");
		try {
			fw = new FileWriter(F);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		if (F.canWrite()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root",
						"root");

				PreparedStatement ps = c.prepareStatement("Select * from digital_visual_exercise where date = ?");
				ps.setString(1, dt);
				ResultSet rs = ps.executeQuery();
				fw.write("\n\t\tVisual Orals - Console Generator\n\n");
				fw.write("  Marks for the Day("+dt+")\n\n");
				fw.write("***************************************************\n");
				
				while (rs.next()) {
					int sno = rs.getInt(1);
					fw.write("Session id: "+sno+"\n");
					fw.write("Name:  " + rs.getString(4)+"\n");
					fw.write("Time:  " + rs.getString(3)+"\n");
					fw.write("Type:  " + rs.getString(5)+"\n");
					fw.write("Marks: " + rs.getInt(6) + "/10 \n");
					searchInput1(sno, rs.getString(4) );
					searchCorrect1(sno);
					fw.write("====================================================\n");
                    
				}
				printTotal(dt);
				fw.write("***************************************************");
				fw.write("\nThank you for using visual orals console generator");
				fw.write("\n\n - Code developed by Jagadeesan Santhuru");
			   
			} catch (Exception E) {
				E.printStackTrace();
			}
            
		}
	  try {
		fw.flush();
		fw.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	}
	
	public static void printTotal(String dt) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/visual_orals_db", "root",
					"root");

			PreparedStatement ps = c
					.prepareStatement("Select  Name, max(TotalMark), max(MaxMark) from totalmarks where Date = ?"
							+ "              group by Name");
			ps.setString(1, dt);

			ResultSet rs = ps.executeQuery();

			fw.write(" (" + dt + ") Total marks Student wise....\n ");
			fw.write("***************************\n");
			Thread.sleep(2000);
			int sno = 1;
			while (rs.next()) {
				fw.write(sno + ". " + rs.getString(1) + " - " + rs.getInt(2) + "/" + rs.getInt(3)+"\n");
				sno++;
			}
			fw.write("***************************\n");
			c.close();
		} catch (Exception e) {
			System.out.println("Exception Handled");
			e.printStackTrace();
		}

		
	}
	}
