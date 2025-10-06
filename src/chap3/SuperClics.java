package chap3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Chronometre;

/**
 * Cette classe permet de réaliser un jeu de rapidité basé sur la précision des clics à la souris.
 *
 * @author Arnaud Comblin
 */
public class SuperClics {

	/* Logique du jeu */
	private final static int RAYON = 20;
	private static int position[] = new int[2];
	private static double points = 0;

	/**
	 * 
	 * @param min Le nombre minimum possible du tirage au sort
	 * @param max Le nombre maximum possible du tirage au sort
	 * @return Nombre aléatoire compris entre [min; max]
	 */
	public static void main(String[] args) {
		// Démarrage de l'interface graphique
		SuperClics superClics = new SuperClics("Super Clics", 600, 600);
		superClics.initialiser();
		superClics.afficher();
	}
	
	private static int getRandom(int min, int max) {
		return (int)(Math.random() * (max - min) + min);
	}

	private void initialiser() {
		nouvellePartie();
	}

	private void nouvellePartie() {
		Chronometre.demarrer();

		effacer();
		int x = getRandom(0, 599);
		int y = getRandom(0, 599);
		
		position = new int[] {x, y}; 
		dessinerCercle(x, y, RAYON);
	}

	private void clicSouris(int xClic, int yClic) {
		// (x1-x2) + (y1-y2)
		double xAxis = Math.pow(xClic-position[0], 2);
		double yAxis = Math.pow(yClic-position[1], 2);
		
		double distance = Math.sqrt(xAxis + yAxis);

		long TempsAuClic = Chronometre.dureeEcoulee();
		
		if(distance<=RAYON) {
			nouvellePartie();
			points = points + (10000 / TempsAuClic);
			System.out.println("Votre score : " + points);
		}
	}

	/* Interface graphique du jeu */

	private JFrame cadre;
	private Panneau panneau;
	private Map<String, Integer> valeurs;

	/**
	 * Crée une fenêtre destinée à afficher une scène 2D.
	 * 
	 * @param titre   le titre principal de la fenêtre.
	 * @param largeur la largeur exprimée en pixels de l'intérieur de la fenêtre.
	 * @param hauteur la hauteur exprimée en pixels de l'intérieur de la fenêtre.
	 */
	public SuperClics(String titre, int largeur, int hauteur) {
		cadre = new JFrame(titre);
		cadre.setFocusable(true);
		cadre.setResizable(false);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panneau = new Panneau();
		panneau.setLayout(null);
		panneau.setBackground(new Color(240, 240, 240));
		panneau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evenement) {
				clicSouris(evenement.getX(), evenement.getY());
			}
		});

		cadre.add(panneau);
		setDimensions(largeur, hauteur);

		valeurs = new HashMap<String, Integer>();
	}

	/**
	 * Affiche la fenêtre. Cette fonction doit être exécutée une fois tous les
	 * éléments ajoutés. Dans le cas contraire, certains éléments pourraient ne pas
	 * s'afficher correctement.
	 */
	public void afficher() {
		cadre.setVisible(true);
	}

	private void setDimensions(int largeur, int hauteur) {
		Dimension resolutionEcran = Toolkit.getDefaultToolkit().getScreenSize();
		largeur = Math.min(Math.max(160, largeur), (int) resolutionEcran.getWidth());
		hauteur = Math.min(Math.max(120, hauteur), (int) resolutionEcran.getHeight());
		panneau.setPreferredSize(new Dimension(largeur, hauteur));
		cadre.pack();
		panneau.effacer();
	}

	/**
	 * Enregistre une valeur entière.
	 * 
	 * @param nom    le nom sous lequel la valeur doit être enregistrée.
	 * @param valeur la valeur entière.
	 */
	public void enregistrerEntier(String nom, int valeur) {
		this.valeurs.put(nom, valeur);
	}

	/**
	 * Lit une valeur entière précédemment enregistrée.
	 * 
	 * @param nom le nom sous lequel la valeur a été enregistrée.
	 * @return la valeur entière correspondante.
	 */
	public int lireEntier(String nom) {
		return this.valeurs.get(nom);
	}

	/**
	 * Dessine un cercle sur base des coordonnées de son centre et de son rayon.
	 * 
	 * @param x     la coordonnée x du centre du cercle.
	 * @param y     la coordonnée y du centre du cercle.
	 * @param rayon le rayon du cercle.
	 */
	public void dessinerCercle(int x, int y, int rayon) {
		panneau.dessinerCercle(Color.BLACK, x, y, rayon);
	}

	/**
	 * Efface tous les dessins effectués dans la fenêtre.
	 */
	public void effacer() {
		panneau.effacer();
	}

	/**
	 * Ferme la fenêtre.
	 */
	public void fermer() {
		cadre.dispatchEvent(new WindowEvent(cadre, WindowEvent.WINDOW_CLOSING));
	}

	private class Panneau extends JPanel {

		private static final long serialVersionUID = -2259378431692527399L;

		private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		private Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

		@Override
		public void setPreferredSize(Dimension preferredSize) {
			super.setPreferredSize(preferredSize);
			bufferedImage = new BufferedImage(preferredSize.width, preferredSize.height, BufferedImage.TYPE_INT_RGB);
			graphics = (Graphics2D) bufferedImage.getGraphics();
		}

		public void effacer() {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
			repaint();
		}

		public void dessinerCercle(Color couleur, int x, int y, int rayon) {
			graphics.setColor(couleur);
			graphics.fillOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
			repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bufferedImage, 0, 0, null);
		}

	}

}