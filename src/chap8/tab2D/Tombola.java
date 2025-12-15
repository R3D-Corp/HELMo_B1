package chap8.tab2D;

import java.util.ArrayList;
import java.util.List;

import io.Console;
import util.LogsManager;
import util.LogsManager.LogsType;

public class Tombola {
	private static final LogsManager logsManager = new LogsManager("Tombola", false);
	private static final List<Vendeur> vendeurs = new ArrayList<Vendeur>();
	

	public static void enregisterBillet(String nomEntree, int valeurEntree) {
		logsManager.addLogs("Engrestirement de billets demandé");
		Vendeur vendeurChoisis = null;

		for(Vendeur vendeur : vendeurs) {
			if(vendeur.name.toLowerCase().equals(nomEntree)) {
				vendeurChoisis = vendeur;
				break;
			}
		}

		if(vendeurChoisis == null) {
			logsManager.addLogs(LogsType.ERROR, String.format("Aucun vendeur nommée %s trouvé", nomEntree));
			return;
		}

		for(Vendeur vendeur : vendeurs) {
			if(vendeur.rechercherBillets(valeurEntree)) {
				logsManager.addLogs(LogsType.ERROR, String.format("Billet %d déja enregistré comme vendu", valeurEntree));
				return;
			}
		}
		vendeurChoisis.vendreBillet(valeurEntree);
	}

	public static int compterBillets() {
		int tousLesTickets = 0;

		for(Vendeur vendeur : vendeurs) {
			tousLesTickets += vendeur.compterBillets();
		}
		return tousLesTickets;
	}

	public static String rechercherBillets(int billetRecherchee) {
		logsManager.addLogs(String.format("Recherche du billet %d demandée", billetRecherchee));
		for(Vendeur vendeur : vendeurs) {
			if(vendeur.rechercherBillets(billetRecherchee)) {
				logsManager.addLogs(LogsType.SUCCESS, String.format("La recherche  à réussi avec comme résultat %s", vendeur.name));
				return vendeur.name;
			};
		}
		logsManager.addLogs(LogsType.ERROR, "Aucun vendeur trouvé lors de la recherche");
		return "Aucun vendeur trouvé";
	};

	public static void main(String[] args) {
		// Tableaux
		final String[] NOMS_VENDEURS = { "Amir", "Lotte", "Yasmina", "Michael", "Julie" };
		
		for(String vendeur : NOMS_VENDEURS) {
			vendeurs.add(new Vendeur(vendeur));
		}

		// Variables
		final int QUITTER = 4;
		int choix;
		
		// Traitement
		do {
			// Affichage du menu
			System.out.println("TOMBOLA\n-------");
			System.out.println("1. Enregistrer des billets vendus");
			System.out.println("2. Compter les billets vendus");
			System.out.println("3. Chercher le vendeur d'un billet");
			System.out.println("4. Quitter");
			choix = Console.lireInt("Choix ? ");
			
			// Traitement du choix
			if (choix == 1) {
				String vendeur = Console.lireString("Quel vendeur a vendu ? ").trim().toLowerCase();
				int billet = Console.lireInt("Quel billet a été vendu ? ");
				enregisterBillet(vendeur, billet);
			} else if (choix == 2) {
				IO.println(compterBillets());
			} else if (choix == 3) {
				int billetRecherchee = Console.lireInt("Quel billet recherchez vous ? ");
				IO.println(rechercherBillets(billetRecherchee));
			} else if (choix != QUITTER) {
				System.out.println("Choix incorrect !\n");
			}
			System.out.println();
			
		} while (choix != QUITTER);
		
		System.out.println("Fin de l'application.");
	}
	
}
