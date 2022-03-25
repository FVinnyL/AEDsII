import java.util.*;

class AddNumbers 
{
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		System.out.println("/// SOMADOR DE NÚMEROS ///");	
		System.out.print("Primeiro número: ");	
		int X = sc.nextInt();
		System.out.print("Segundo número: ");
		int Y = sc.nextInt();
		
		System.out.println("Soma: " + Add(X,Y));
	}
	
	public static int Add(int a, int b)
	{
		return a + b;
	}	
}
