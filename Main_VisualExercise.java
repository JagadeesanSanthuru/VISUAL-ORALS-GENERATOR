package UCMAS_Listening;

import java.util.Date;
import java.util.Scanner;

public class Main_VisualExercise {
	    static Scanner jn = new Scanner(System.in);
	    static Date d = new Date();
	    static String dt = "", tm = "";
	    
	public static void main(String[] args) {
		System.out.println("\n\t\t\t UCMAS Mental Arithmetic Visual Listening Exercise");
		String dte = (d.getDate()<10)?"0"+d.getDate():""+d.getDate();
		dt+= dte+"."+(d.getMonth()+1)+"."+("20"+(d.getYear()-100));
     	tm+= d.getHours()+":"+((d.getMinutes()<10)?"0"+d.getMinutes():d.getMinutes());
		System.out.println("Date : "+dt);
		System.out.println("Time : "+tm);
		boolean decide = true;
		do {
			System.out.println("\n\n\t\t\t Listening Operations\n   1. Addition\n   2. Multiplication\n   3. Division\n   4. Results\n   5. Generate PDF\n   6. Exit\n");
			System.out.print("Your input --> ");
			switch (jn.nextInt()) {
			case 1:{try {
				addition.addition(dt, tm);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;}
			case 2:{ try {
				Visual_Exercise.multiply(dt, tm);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;}
			case 3:{System.out.println();
			        try {
						Division.Divide(dt, tm);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;}
			case 4:{  
				System.out.println();
				String welcome = "           *****Welcome to UCMAS Data base*****";
			for (int i = 0; i < welcome.length(); i++) {
				   System.out.print(welcome.charAt(i));
				   try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Visual_Exercise.searchData(dt);
			
			String exit = "*** Datas retrived successfully :-) ***";
			   for (int i = 0; i < exit.length(); i++) {
				   System.out.print(exit.charAt(i));
				   try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			    
				break;}
			
			case 5:{
				Visual_Exercise.printData(dt);
				System.out.println();
				String pdf = "           *****PDF GENERATED SUCCESSFULLY*****";
				for (int i = 0; i < pdf.length(); i++) {
					   System.out.print(pdf.charAt(i));
					   try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				 try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case 6:{String exit = "******>>>You Exited Successfully<<<******";
			   for (int i = 0; i < exit.length(); i++) {
				   System.out.print(exit.charAt(i));
				   try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			   decide = false;
				break;}
			default:{
				System.out.println("Give the valid input");
				break;}
			}
		} while (decide);
	}

}
