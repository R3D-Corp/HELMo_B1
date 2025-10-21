package chap3;

import io.Console;

class circle {

	public void main(String[] args) {
		double rayon = Console.lireDouble("Rayon ? ");
		double air = Math.PI * (Math.pow(rayon, 2));
		double circon = Math.PI * (rayon * 2);
		IO.println("Aire : " + air);
		IO.println("Circonférence : " + circon);
	}
}