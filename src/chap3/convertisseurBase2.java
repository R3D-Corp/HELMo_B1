package chap3;

import io.Console;

public class convertisseurBase2 {
	
	private static long[] binary = new long[128];
	private static int iteration = 0;
	
	private static void comptage(long value) {
		long divide2 = value / 2;
		
		if(value > 0) {
			binary[iteration] = value % 2;
			iteration++;
			//IO.println(value);
			comptage(divide2);
		}
	}
	
	public static void main(String[] args) {
		
		long number10;
		
		number10 = Console.lireLong("Entier ? ");
					
		comptage(number10);
		String result = "";
		for(int i=iteration-1; i>=0; i--) {
			result = result + binary[i];
		}
		IO.println("Binary : " + result);
		IO.println("Nombre de bits : " + result.length());
	} 
}
