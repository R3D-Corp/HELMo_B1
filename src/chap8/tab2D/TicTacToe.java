package chap8.tab2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.LogsManager;
import util.LogsManager.LogsType;

public class TicTacToe extends JFrame {

	private static LogsManager logsManager = new LogsManager("tictactoe", true);
	public static void main(String[] args) {
		SwingUtilities.invokeLater(TicTacToe::new);
		logsManager.addLogs(LogsType.INFO, "Le TicTacToe est lancé");
	}

	/**
	 * Fonction exécutée lorsqu'un joueur clique sur l'une des cases de la grille.
	 * 
	 * @param grille - La référence du tableau à 2 dimensions mémorisant l'état de
	 *               la grille de jeu.
	 * @param joueur - Le symbole du joueur qui a la main ('X' ou 'O').
	 * @param i      - L'indice de la ligne de la case sélectionnée par le joueur
	 *               (de 0 à 2).
	 * @param j      - L'indice de la colonne de la case sélectionnée par le joueur
	 *               (de 0 à 2).
	 */
	private void clicSouris(char[][] grille, char joueur, int i, int j) {
		if (grille[i][j] == CASE_VIDE) {
			grille[i][j] = joueur;
			logsManager.addLogs(LogsType.WARNING, String.format("%s VIENT DE PRENDRE %d, %d", grille[i][j], i, j));

			if(victoire(grille)) {
				super.repaint();
				logsManager.addLogs(LogsType.SUCCESS, String.format("Victoire de %s", joueur));
				afficherMessage(String.format("Le joueur %s vient de gagné la partie", joueur));
				nouvellePartie();

			} else if(grilleComplete(grille)) {
				logsManager.addLogs(LogsType.ERROR, String.format("Egalité de la partie"));
				afficherMessage("Egalité");
			} else {
				logsManager.addLogs(LogsType.INFO, "Tour suivant");
				joueurSuivant();
			}
		}
	}

	/**
	 * Détermine si un joueur est parvenu à réaliser un alignement de 3 symboles.
	 * 
	 * @param grille - La référence du tableau à 2 dimensions mémorisant l'état de
	 *               la grille de jeu.
	 * @return {@code true} si un alignement est trouvé, {@code false} dans le cas
	 *         contraire.
	 */
	private boolean victoire(char[][] grille) {
		for(int i=0; i<grille.length; i++) {
			if(grille[i][0] != CASE_VIDE && grille[i][0] == grille[i][1] && grille[i][1] == grille[i][2]) {
				return true;
			};

			if(grille[0][i] != CASE_VIDE && grille[0][i] == grille[1][i] && grille[1][i] == grille[2][i]) {
				return true;
			};
		};

		if(grille[0][0] != CASE_VIDE && grille[0][0] == grille[1][1] && grille[1][1] == grille[2][2]) {
			return true;
		} else if(grille[2][0] != CASE_VIDE && grille[2][0] == grille[1][1] && grille[1][1] == grille[0][2]) {
			return true;
		}

		return false;
	}



	/**
	 * Détermine si la grille est entièrement complétée.
	 * 
	 * @param grille - La référence du tableau à 2 dimensions mémorisant l'état de
	 *               la grille de jeu.
	 * @return {@code true} si aucune case de la grille n'est vide, {@code false}
	 *         dans le cas contraire.
	 */
	private boolean grilleComplete(char[][] grille) {
		boolean reponse = true;
		for(int i=0; i<grille.length; i++) {
			for(int j=0; j<grille[i].length; j++) {
				if(grille[i][j] == CASE_VIDE) {
					reponse = false;
					break;
				}
 			}
		}
		return reponse;
	}

	/**
	 * Donne la main au joueur suivant.
	 */
	private void joueurSuivant() {
		joueur = (joueur == SYMBOLE_X) ? SYMBOLE_O : SYMBOLE_X;
	}

	/**
	 * Affiche un message dans une boîte de dialogue.
	 * 
	 * @param message - Le message à afficher.
	 */
	private void afficherMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Réinitialise les données du jeu pour recommencer une partie.
	 */
	private void nouvellePartie() {
		grille = new char[3][3];
		joueur = SYMBOLE_X;
		repaint();
	}

	private static final long serialVersionUID = -3829764199950643324L;

	private final int TAILLE_CASE = 150;
	private final char CASE_VIDE = '\0';
	private final char SYMBOLE_X = 'X';
	private final char SYMBOLE_O = 'O';

	private char[][] grille = new char[3][3];
	private char joueur = SYMBOLE_X;

	public TicTacToe() {
		super("Morpion");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setExtendedState(JFrame.NORMAL);
		setMaximizedBounds(new Rectangle(3 * TAILLE_CASE, 3 * TAILLE_CASE));

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				dessinerGrille(g2);
				dessinerSymboles(g2);
			}
		};

		panel.setPreferredSize(new Dimension(3 * TAILLE_CASE, 3 * TAILLE_CASE));

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int j = e.getX() / TAILLE_CASE;
				int i = e.getY() / TAILLE_CASE;
				clicSouris(grille, joueur, i, j);
				repaint();
			}
		});

		add(panel);
		pack();
		setVisible(true);
	}

	private void dessinerGrille(Graphics2D g2) {
		g2.setColor(new Color(60, 60, 60));
		g2.setStroke(new BasicStroke(5));

		for (int i = 1; i < 3; i++) {
			g2.drawLine(i * TAILLE_CASE, 20, i * TAILLE_CASE, 3 * TAILLE_CASE - 20);
			g2.drawLine(20, i * TAILLE_CASE, 3 * TAILLE_CASE - 20, i * TAILLE_CASE);
		}
	}

	private void dessinerSymboles(Graphics2D g2) {
		int padding = TAILLE_CASE / 4;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char c = grille[i][j];
				if (c == CASE_VIDE)
					continue;

				int x = j * TAILLE_CASE + padding;
				int y = i * TAILLE_CASE + padding;
				int w = TAILLE_CASE - 2 * padding;
				int h = TAILLE_CASE - 2 * padding;

				// Ombre
				g2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g2.setColor(new Color(0, 0, 0, 50));

				if (c == SYMBOLE_X) {
					g2.drawLine(x + 4, y + 4, x + w + 4, y + h + 4);
					g2.drawLine(x + w + 4, y + 4, x + 4, y + h + 4);
				} else {
					g2.drawOval(x + 4, y + 4, w, h);
				}

				// Symbole
				g2.setColor(c == SYMBOLE_X ? new Color(220, 20, 60) : new Color(30, 144, 255));
				
				if (c == SYMBOLE_X) {
					g2.drawLine(x, y, x + w, y + h);
					g2.drawLine(x + w, y, x, y + h);
				} else {
					g2.drawOval(x, y, w, h);
				}
			}
		}
	}
}