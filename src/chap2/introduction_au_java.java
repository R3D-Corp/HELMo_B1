package chap2;

class ConvertisseurPouces {
	public static void main(String[] args) {
		double lgEnPouces;
		
		System.out.println("Mon convertisseur");
		lgEnPouces = Console.lireDouble("Longueur en pouces ? ");
		System.out.println(lgEnPouces * 2.54 + " cm");
	}
}

