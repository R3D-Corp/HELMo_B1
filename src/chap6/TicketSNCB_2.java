package chap6;

import io.Console;

/**
 * Ce programme permet de déterminer le prix d'un ticket de train
 * pour un aller simple Liège-Guillemins / Bruxelles-Central selon
 * l'âge de l'usager.
 */
public class TicketSNCB_2 {

	private static final double PRIX_DE_BASE = 25.80;
	private static final double TAUX_REDUC = 0.15;

	private static boolean estValide(int age) {
		return (age >= 0 && age <= 120);
	}

	/**
	 * Méthode privée qui permet de calculer la réduction à appliquer au client en
	 * fonction de son age
	 * 
	 * @param age Age du client en int
	 * @return double reprensentant la reduction a appliquer (0-1);
	 */
	private static double calculerReductionTernaire(int age) {
		return age < 12 ? 1.0 : (age <= 25 || age >= 65 ? TAUX_REDUC : 0.0);
	}

	/**
	 * Méthode privée qui permet de calculer la réduction à appliquer
	 * au client en fonction de son age
	 * Cette méthode est plus simple a comprendre que celle ternaire.
	 * 
	 * @param age Age du client en int
	 * @return double reprensentant la reduction a appliquer (0-1);
	 */
	private static double calculerReductionConditions(int age) {
		double reduc = 0;

		if (age < 12)
			reduc = 1.0;
		else if (age <= 25)
			reduc = TAUX_REDUC;
		else if (age >= 65)
			reduc = TAUX_REDUC;

		return reduc;
	}

	/**
	 * Méthode privée qui permet de calculer la réduction à appliquer
	 * au client en fonciton de son age.
	 * Cette méthode est plus simple a comprendre que celle ternaire.
	 * Comparé a calculerReductionConditions() celle ci utilise le principe de
	 * court-circuit du return.
	 * Cette méthode semble la plus logique, mais ne respecte pas les conventions de
	 * lisibilité.
	 * 
	 * @param age Age du client en int
	 * @return double reprensentant la reduction a appliquer (0-1);
	 */
	private static double calculerReductionReturn(int age) {
		if (age < 12)
			return 1.0;
		if (age <= 25)
			return TAUX_REDUC;
		if (age >= 65)
			return TAUX_REDUC;

		return 0.0;
	}

	public static double calculerPrixTernaire(int age) {
		if(!estValide(age)) throw new IllegalArgumentException("Age incorrect");
		return PRIX_DE_BASE * (1 - calculerReductionTernaire(age));
	}

	public static double calculerPrixConditions(int age) {
		if(!estValide(age)) throw new IllegalArgumentException("Age incorrect");
		return PRIX_DE_BASE * (1 - calculerReductionConditions(age));
	}

	public static double calculerPrixReturn(int age) {
		if(!estValide(age)) throw new IllegalArgumentException("Age incorrect");
		return PRIX_DE_BASE * (1 - calculerReductionReturn(age));
	}

	public static void main(String[] args) {
		int age = Console.lireInt("Votre âge ? ");
		IO.println(String.format("Prix à payer = %.2f EUR\n", calculerPrixTernaire(age)));
	}
}
