import java.util.Scanner;
public class lab05 {
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String name; int age;
		System.out.println("What is your name?!");
		name = input.next();
		System.out.println("How old are you?!");
		age = input.nextInt();
		
		while (age <= 30){
			age = age + 2;
		System.out.println(name + " your age in two years is " + age );
		}// part 1
		
		int numberOfPlayer;
		
		numberOfPlayer = 2;
		while (numberOfPlayer <= 23){
			numberOfPlayer = numberOfPlayer * 3;
			System.out.println("The new number of players in game is " + numberOfPlayer);
		}//part 2
		
		String str1; int salary=0; 
		System.out.println("Please enter your name or Q to quit");
		str1 = input.next();
		
		while (!str1.equals("Q")){
			if (str1.equals("Fan")){
				System.out.println("Hi Fan! I am yourself! Please enter your salary: ");
				salary = input.nextInt();
				int output = salary*2;
				System.out.println("Your salary just doubled to" + output + "!!");
			}
			else {
				System.out.println("Unfortunately you are not Fan..Please" +
						"quit the program now.");
			}
			
			System.out.println("Please enter your name or Q to quit");
			str1 = input.next();
			}// part 3
		
		String grade; 
		int total = 0; 
		int gradeCounter=0;
		double average;
		int A=100, B=90, C=80, D=70, E=60, F=50, N=0;
		System.out.println("You can enter your letter grade or press " +
				"Q to quit!");
		grade = input.next();
		
		
		while (!grade.equals("Q")){
			grade = input.next();
		if (grade.equals("A")){
			gradeCounter = gradeCounter + 1;
			total = total + A;
		}
		if (grade.equals("B")){
			gradeCounter = gradeCounter + 1;
			total = total + B;
		}
		if (grade.equals("C")){
			gradeCounter = gradeCounter + 1;
			total = total + C;
		}
		if (grade.equals("D")){
			gradeCounter = gradeCounter + 1;
			total = total + D;
		}
		if (grade.equals("E")){
			gradeCounter = gradeCounter + 1;
			total = total + E;
		}
		if (grade.equals("F")){
			gradeCounter = gradeCounter + 1;
			total = total + F;
		}
		if (grade.equals("N")){
			gradeCounter = gradeCounter + 1;
			total = total + N;
		}
		if (gradeCounter != 0){
			average = total / gradeCounter ; 
			System.out.println("Your average is " + average);
			if (average > 97 ){
				System.out.println("You got an A+!!");
			}
			else if (average > 93 ){
				System.out.println("You got an A!!");
			}
			else if (average > 90 ){
				System.out.println("You got an A-!!");
			}
			else if (average > 87 ){
				System.out.println("You got an B+!!");
			}
			else if (average > 87 ){
				System.out.println("You got an B!!");
			}
			else if (average > 80 ){
				System.out.println("You got an B-!!");
			}
			else if (average > 77 ){
				System.out.println("You got an C+!!");
			}
			else if (average > 73 ){
				System.out.println("You got an C!!");
			}
			else if (average > 70 ){
				System.out.println("You got an C-!!");
			}
			else if (average > 67 ){
				System.out.println("You got an D+!!");
			}
			else if (average > 63 ){
				System.out.println("You got an D!!");
			}
			else if (average > 60 ){
				System.out.println("You got an D-!!");
			}
			else if (average > 57 ){
				System.out.println("You got an E+!!");
			}
			else if (average > 53 ){
				System.out.println("You got an E!!");
			}
			else if (average > 50 ){
				System.out.println("You got an E-!!");
			}
			else if (average > 47 ){
				System.out.println("You got an F+!!");
			}
			else if (average > 43 ){
				System.out.println("You got an F!!");
			}
			else if (average > 40 ){
				System.out.println("You got an F-!!");
			}
			
			else if (average <= 40 ){
				System.out.println("I am sorry but I am afraid you have to retake the course!");
			}

		}
		System.out.println("You can enter your letter grade or press " +
				"Q to quit!");
		//part5
	}
	}
}

