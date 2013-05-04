import java.util.Scanner;
public class part6 {
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		int x, y;
		System.out.println("Please enter two integers!");
		x = input.nextInt();
		y = input.nextInt();
		
		if (x < y){
			int temp = x;
			x = y;
			y = temp;
		}
		math(x,y);

	}
	public static void math (int x, int y){
		if (x%y == 0){
			System.out.print("The gcd is "+ y);
		}
		else {
			x = x%y;
			math(y,x);
		}
	}
}
