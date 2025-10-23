package chap6;

import io.Console;

/**
 * Ce programme permet d'identifier le pays d'origine d'un numéro
 * de téléphone sur base de son indicatif.
 */
public class NumerosInternationaux {

	public void main(String[] args) {
		// Variables
		String numTel;
		String paysOrigine;

		// Acquisition
		numTel = Console.lireString("Numéro international ? ").trim();

		// Traitement
		// +31 ou 0031 -> Pays-Bas
		// +32 ou 0032 -> Belgique
		// +33 ou 0033 -> France
		// +34 ou 0034 -> Espagne
		// +35 ou 0035 -> Portugal
		// +225 ou 00225 -> Côte d'Ivoire

		String indicatif = numTel.startsWith("+") ? numTel.substring(0, 3) : numTel.substring(0, 4);

		paysOrigine = switch (indicatif) {
			case "+31", "0031" -> "Pays-Bas";
			case "+32", "0032" -> "Belgique";
			case "+33", "0033" -> "France";
			case "+34", "0034" -> "Espagne";
			case "+35", "0035" -> "Portugal";
			case "+225", "0225" -> "Côte d'Ivoire";

			default -> "Inconnu";
		};

		IO.println("Pays d'origine = " + paysOrigine);
	}

}
