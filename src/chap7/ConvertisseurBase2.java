package chap7;

import io.Console;

public class ConvertisseurBase2 {
	private static String convert(long value) {
		String response = "";
		while(value > 0) {
			response = (int)value % 2 + response;
			value = value / 2;
		}
		return response;
	}

	public static void main(String[] args) {
		long number10;
		
		number10 = Long.parseLong(Console.lireStringWhile("Entier ? ", "\\d{1,50000}"));
					
		String result = convert(number10);
		IO.println("Binary : " + result);
		IO.println("Nombre de bits : " + result.length());
	}
}
