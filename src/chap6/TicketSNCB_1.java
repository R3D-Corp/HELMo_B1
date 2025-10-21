package chap6;

import io.Console;

/**
 * Ce programme permet de déterminer le prix d'un ticket de train
 * pour un aller simple Liège-Guillemins / Bruxelles-Central selon
 * l'âge de l'usager.
 */
public class TicketSNCB_1 {

	private static final double PRIX_DE_BASE = 25.80;
	private static final double TAUX_REDUC_JEUNE = 0.15;

	private static double calculerPrix(int age) {
		return PRIX_DE_BASE * (1 - ((age < 26) ? TAUX_REDUC_JEUNE : 0));
	}

	public static void main(String[] args) {
		int age = Console.lireInt("Votre âge ? ");
		IO.println(String.format("Prix à payer = %.2f EUR\n", calculerPrix(age)));
	}

}
