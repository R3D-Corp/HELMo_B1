package chap8.tab1D;

import java.util.Arrays;

import io.Console;

public class DistributeurBillets {
	
	// Tableau constant, represantant le type de billets que la machine peut rendre.
	// private static final int[] VALEURS_BILLETS = new int[] {200, 100, 50, 20, 10, 5};
	private static final int[] VALEURS_BILLETS = new int[] {200, 50, 100, 25, 19, 20, 10, 5};

	private static void trierBillets() {
		for(int i = 0; i<VALEURS_BILLETS.length / 2; i++) {
			int max = VALEURS_BILLETS[i];
			for(int j = i; j<VALEURS_BILLETS.length; j++) {
				if(max<VALEURS_BILLETS[j]) {
					int temp = VALEURS_BILLETS[j];
					VALEURS_BILLETS[j] = max;
					VALEURS_BILLETS[i] = temp;
				}
			}
		};
		IO.println(Arrays.toString(VALEURS_BILLETS));
	}

	public static void main(String[] args) {

		trierBillets();

		int[] billetsRendu = new int[VALEURS_BILLETS.length];
		int montant;

		montant = Console.lireInt("Montant en euros ? ");
		if(montant % VALEURS_BILLETS[VALEURS_BILLETS.length-1] != 0) {
			throw new IllegalArgumentException("Erreur lors de la saisie, montant invalide", new Throwable("Montant invalide"));
		}

		for(int i=0; i<VALEURS_BILLETS.length; i++) {
			int v = VALEURS_BILLETS[i];
			int billetRendu = billetsRendu[i] = montant / v;

			IO.println(String.format("%d fois %d", billetRendu, v));
			montant = montant % v;
		};
	}
	
}