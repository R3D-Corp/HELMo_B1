package chap3;

import io.Console;

public class SommeDurees {
	
	public static int prendreTemps(int maximum, String message) { // chaque paramètre d'une fonction nécessite un type ici int et String 
		int time = Console.lireInt(message); // Récupère une valeur forcément. 
		IO.println(time);
		if(time>maximum || time<0) { // Vérifier que la valeur n'est pas supérieur a maximum ou n'est pas inférieur a 0
			IO.println("Oh-Oh vous devez prendre une valeur entre O et " + maximum); // Message d'erreur.
			time = prendreTemps(maximum, message);
		}
		return time;
	}
	
	
	public static void main(String[] args) {
		int heures1 = prendreTemps(9999, "Combien d'heures ? ") , minutes1 = prendreTemps(60, "Combien de minutes ? ") , secondes1 = prendreTemps(60, "Combien de secondes ? ");
		int heures2 = 3, minutes2 = 38, secondes2 = 45;
		int heuresTot, minutesTot, secondesTot;
		
		
		int secondesIncr = (secondes1 + secondes2) / 60;
		secondesTot = (secondes1 + secondes2) % 60;
		
		int minutesIncr = (minutes1 + minutes2 + secondesIncr) / 60;
		minutesTot = (minutes1 + minutes2 + secondesIncr) % 60;
		heuresTot = heures1 + heures2 + minutesIncr;
		IO.println(heuresTot + "h" + minutesTot + "min" + secondesTot + "sec");
	}
}