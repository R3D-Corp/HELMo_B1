package chap8.tab1D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.Console;
import util.logs.LogEntry;
import util.logs.LogsManager;
import util.logs.LogsType;

public class LootOrDie {
	public static final LogsManager logsManager =new LogsManager("lootordie", true);


	public static void main(String[] args) throws InterruptedException, IOException {
		
		// Constantes
		final String PIEGE = "Piège";
		final String CHOIX_1 = "Creuser";
		final String CHOIX_2 = "Partir";
		final int CREUSER = 1;
		final int PARTIR = 2;
		final int ATTENTE_EN_SEC = 3;
		final int UNE_SECONDE = 1000;

		// Tableaux
		final String[] NOMS_TRESORS = { PIEGE, "Or", "Rubis", "Émeraude", "Relique ancienne" };
		final int[] VALEURS_TRESORS = { 0, 10, 200, 300, 500 };
		final double[] TAUX_APPARITION = { 0.20, 0.50, 0.15, 0.10, 0.05 };

		final List<String> TRESORS_JOUEURS = new ArrayList<>();

		logsManager.addLogs(LogEntry.createLog(LogsType.SUCCESS, "Lancement du jeu"));
		// Variables
		int choixJoueur, scoreJoueur = 0;
		String tresorAlea = null;

		// Traitement
		do {
			choixJoueur = lireChoix(CHOIX_1, CHOIX_2);

			if (choixJoueur == CREUSER) {
				tresorAlea = tresorAleatoire(NOMS_TRESORS, TAUX_APPARITION);
				logsManager.addLogs(LogEntry.createLog(String.format("Le joueur vient de creuser")));
				// Affichage du trésor trouvé
				System.out.print("Vous creusez ");
				for (int i = 1; i <= ATTENTE_EN_SEC; i++) {
					System.out.print(".");
					Thread.sleep(UNE_SECONDE);
				}
				
				ajouterTresor(TRESORS_JOUEURS, tresorAlea);
				scoreJoueur += VALEURS_TRESORS[positionDe(NOMS_TRESORS, tresorAlea)];
				System.out.printf(" %s\n\n", tresorAlea);
				logsManager.addLogs(LogEntry.createLogWithFields("Fin de tour", new String[][]{
					{"Loot", tresorAlea},
					{"Score", Integer.toString(scoreJoueur)}
				}));

			}
			
		} while (choixJoueur != PARTIR && !Objects.equals(tresorAlea, PIEGE));

		// Affichage du score final
		System.out.println();
		if (Objects.equals(tresorAlea, PIEGE)) {
			System.out.println("Vous mourez dans d'atroces souffrances 💀");
			logsManager.addLogs(LogEntry.createLog(LogsType.ERROR, String.format("Fin de partie; %s", PIEGE)));
		} else if (scoreJoueur == 0) {
			System.out.println("Vous repartez les mains vides 😢");
			logsManager.addLogs(LogEntry.createLog(String.format("Fin de partie; rien avec vous")));
		} else {
			
			System.out.println("Vour repartez avec vos trésors :");
			String s = "";
			for (String t : TRESORS_JOUEURS) {
				s += t;
			};
			
			System.out.printf("Vous avez trouver %s", s);
			System.out.printf("Score total = %d 😊\n", scoreJoueur);
			logsManager.addLogs(LogEntry.createLog(String.format("Fin de partie; score : %d", scoreJoueur)));
		}
	}

	public static String tresorAleatoire(String[] nomsTresors, double[] tauxApparition) throws IOException {
		double max = 0;
		double random = Math.random();
		String response = null;
		
		logsManager.addLogs(LogEntry.createLog(String.format("Le taux random est %f", random)));

		for (int i=0; i<nomsTresors.length; i++) {
			max += tauxApparition[i];

			String log = String.format("Recherche de trésor, %s taux de chance : %f", nomsTresors[i], max);
			logsManager.addLogs(LogEntry.createLog(log));
			if(random <= max) {
				response = nomsTresors[i];
				break;
			}

		}
		logsManager.addLogs(LogEntry.createLog(LogsType.SUCCESS, String.format("Objet aléatoire : %s", response)));
		return response;
	}

	public static int positionDe(String[] chaines, String valeur) {
		int response = -1;
		for(int i = 0; i<chaines.length; i++) {
			if(chaines[i].equals(valeur)) {
				response = i;
			}
		}
		return response;
	}
	
	public static void ajouterTresor(List<String> tresors, String nouveauTresor) {
		tresors.add(nouveauTresor);
	}

	private static int lireChoix(String choix1, String choix2) {
		final String DEMANDE_CHOIX = String.format("%s, %s ? ", formaterChoix(choix1),
				formaterChoix(choix2).toLowerCase());
		int numeroChoix = 0;
		String choixSaisi;
		do {
			choixSaisi = Console.lireString(DEMANDE_CHOIX).trim().toLowerCase();
			numeroChoix = (choix1.toLowerCase().startsWith(choixSaisi)) ? 1 : numeroChoix;
			numeroChoix = (choix2.toLowerCase().startsWith(choixSaisi)) ? 2 : numeroChoix;
		} while (numeroChoix == 0);
		return numeroChoix;
	}

	private static String formaterChoix(String choix) {
		return String.format("(%c)%s", Character.toUpperCase(choix.charAt(0)), choix.substring(1).toLowerCase());
	}
}
