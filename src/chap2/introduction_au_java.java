package chap2;

import io.Console;

class ConvertisseurPouces {
	public static void main(String[] args) {
		double lgEnPouces;
		
		IO.println("Mon convertisseur");
		lgEnPouces = Console.lireDouble("Longueur en pouces ? ");
		IO.println(lgEnPouces * 2.54 + " cm");
	}
}

