package chap8.tab2D;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.logs.LogEntry;
import util.logs.LogsManager;
import util.logs.LogsType;

public class Sokoban extends JPanel implements KeyListener {

	LogsManager logsManager = new LogsManager("Sokoban", true);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Sokoban::new);
	}

	/**
	 * Fonction exécutée lorsqu'un joueur presse une touche du clavier.
	 * 
	 * @param direction  - La direction choisie par le joueur pour son déplacement.
	 * @param murs       - La référence du tableau contenant les positions (i, j)
	 *                   des murs du niveau.
	 * @param r - La référence du tableau contenant les positions (i, j)
	 *                   des rangements du niveau.
	 * @param caisses    - La référence du tableau contenant les positions (i, j)
	 *                   des caisses du niveau.
	 */
	private void touchePressee(Direction direction, int[][] murs, int[][] r, int[][] caisses) {
		// logsManager.addLogs(String.format("Mouvement demandé\ndirection : %s\nmurs : %s\nrangements : %s\ncaisses : %s", direction, murs, r, caisses));
		logsManager.addLogs(LogEntry.createLogWithFields(LogsType.EVIDENCE, "Mouvement demandé", new String[][]{
			{"Direction", direction.toString()},
			{"Murs", Arrays.deepToString(murs)},
			{"Rangements", Arrays.deepToString(r)},
			{"Caisses", Arrays.deepToString(caisses)},
		}));
		int[] positionJoueur = positionSuivante(joueur.clone(), direction);

		for(int[] mur : murs) {
			if(positionJoueur[0] == mur[0] && positionJoueur[1] == mur[1]) return;
		}

		for(int[] caisse : caisses) {
			if(positionJoueur[0] == caisse[0] && positionJoueur[1] == caisse[1]) return;
		}
		joueur = positionJoueur;
		
		for(int rangement = 0; rangement<r.length; rangement++) {
			int[] temp = r[rangement];
			if(positionJoueur[0] == temp[0] && positionJoueur[1] == temp[1]) {
				rangements = retirerLigne(r, rangement);
			}
		}

		if(r.length<=0) {
			afficherMessage("Féliciations ! Niveau suivant.");
			niveauSuivant();
		}
	}

	/**
	 * Calcule la position d'arrivée d'un déplacement sur base de la position (i, j)
	 * de départ et de la direction du déplacement.
	 * 
	 * @param depart    - La référence du tableau contenant la position de départ.
	 * @param direction - La direction du déplacement.
	 * @return La référence d'un tableau contenant la position d'arrivée du
	 *         déplacement effectué.
	 */
	private int[] positionSuivante(int[] depart, Direction direction) {
		switch(direction) {
			case DROITE :
				depart[1]++; 
				break;
			case GAUCHE : 
				depart[1]--;
				break;
			case HAUT : 
				depart[0]--;
			 	break;
			case BAS : 
				depart[0]++;
				break;
			default : 
				break;
		}
		return depart;
	}



	/**
	 * Passe au niveau suivant.
	 */
	private void niveauSuivant() {
		numNiveau = numNiveau + 1;
		chargerNiveau(numNiveau);
	}

	/**
	 * Affiche un message dans une boîte de dialogue.
	 * 
	 * @param message - Le message à afficher.
	 */
	private void afficherMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	private static final long serialVersionUID = 3617321166567789220L;

	// Directions pour les déplacements du joueur
	enum Direction {
		AUCUNE, HAUT, DROITE, BAS, GAUCHE
	}

	// Constantes
	private static final String CHEMIN_IMAGES = "sokoban\\images\\";
	private static final String CHEMIN_NIVEAUX = "sokoban\\niveaux\\";
	private static final int TAILLE_CASE = 64;
	private static final int NB_CASES_VERTICALES = 8;
	private static final int NB_CASES_HORIZONTALES = 8;

	// Variables
	private int[][] murs;
	private int[][] caisses;
	private int[][] rangements;
	private int[] joueur;
	private int numNiveau = 1;

	// Images
	private Image imgMur, imgSol, imgCaisse, imgRangement, imgCaisseSurRangement, imgJoueur;

	// Fenêtre principale
	private JFrame frame;

	public Sokoban() {
		chargerImages();
		chargerNiveau(numNiveau);

		frame = new JFrame("Sokoban");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(NB_CASES_HORIZONTALES * TAILLE_CASE + 20, NB_CASES_VERTICALES * TAILLE_CASE + 40);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(this);
		frame.setVisible(true);
	}

	private void chargerImages() {
		imgMur = new ImageIcon(CHEMIN_IMAGES + "mur.png").getImage();
		imgSol = new ImageIcon(CHEMIN_IMAGES + "sol.png").getImage();
		imgCaisse = new ImageIcon(CHEMIN_IMAGES + "caisse.png").getImage();
		imgRangement = new ImageIcon(CHEMIN_IMAGES + "rangement.png").getImage();
		imgCaisseSurRangement = new ImageIcon(CHEMIN_IMAGES + "caisse_sur_rangement.png").getImage();
		imgJoueur = new ImageIcon(CHEMIN_IMAGES + "joueur.png").getImage();
	}

	private void chargerNiveau(int numFichier) {
		final String CHEMIN_FICHIER = String.format("%sniveau%02d.txt", CHEMIN_NIVEAUX, numFichier);
		try {
			java.util.List<String> lignes = Files.readAllLines(Paths.get(CHEMIN_FICHIER));

			murs = new int[0][];
			caisses = new int[0][];
			rangements = new int[0][];
			joueur = null;

			for (int i = 0; i < lignes.size(); i++) {
				for (int j = 0; j < lignes.get(i).length(); j++) {
					char car = lignes.get(i).charAt(j);

					if (car == ' ') {
						continue;
					}

					int[] position = { i, j };

					switch (car) {
					case '#' -> murs = ajouterLigne(murs, position);
					case '$' -> caisses = ajouterLigne(caisses, position);
					case '.' -> rangements = ajouterLigne(rangements, position);
					case '*' -> {
						caisses = ajouterLigne(caisses, position);
						rangements = ajouterLigne(rangements, position);
					}
					case '@' -> joueur = position;
					case '+' -> {
						joueur = position;
						rangements = ajouterLigne(rangements, position);
					}
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur chargement niveau : '" + CHEMIN_FICHIER + "'");
		}
	}

	private static int[][] ajouterLigne(int[][] t, int[] ligne) {
		int[][] nouveau = Arrays.copyOf(t, t.length + 1);
		nouveau[nouveau.length - 1] = ligne;
		return nouveau;
	}

	private static int[][] retirerLigne(int[][] t, int numeroLigne) {
		int[][] temp = new int[t.length - 1][2];
		int tempIndex = 0;
		for(int i=0; i<t.length; i++) {
			if(i==numeroLigne) {
				break;
			}
			temp[tempIndex] = t[i];
			tempIndex++;
		}
		return temp;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Afficher le sol
		for (int i = 0; i < NB_CASES_VERTICALES; i++) {
			for (int j = 0; j < NB_CASES_HORIZONTALES; j++) {
				g.drawImage(imgSol, j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE, null);
			}
		}

		// Afficher les murs
		for (int[] ij : murs) {
			g.drawImage(imgMur, ij[1] * TAILLE_CASE, ij[0] * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE, null);
		}

		// Afficher les rangements
		for (int[] ij : rangements) {
			g.drawImage(imgRangement, ij[1] * TAILLE_CASE, ij[0] * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE, null);
		}

		// Afficher les caisses
		for (int[] ij : caisses) {
			int i = 0;
			while (i < rangements.length && !Arrays.equals(rangements[i], ij)) {
				i++;
			}
			Image img = imgCaisse;
			if (i < rangements.length) {
				img = imgCaisseSurRangement;
			}
			g.drawImage(img, ij[1] * TAILLE_CASE, ij[0] * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE, null);
		}

		// Afficher le joueur
		g.drawImage(imgJoueur, joueur[1] * TAILLE_CASE, joueur[0] * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE, null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction direction = switch (e.getKeyCode()) {
		case KeyEvent.VK_UP, KeyEvent.VK_Z -> Direction.HAUT;
		case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> Direction.DROITE;
		case KeyEvent.VK_DOWN, KeyEvent.VK_S -> Direction.BAS;
		case KeyEvent.VK_LEFT, KeyEvent.VK_Q -> Direction.GAUCHE;
		default -> Direction.AUCUNE;
		};
		touchePressee(direction, murs, rangements, caisses);
		repaint();
	}
}