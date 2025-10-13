package chap5.formes;

public final class Rectangle {
    /**
     * Permet de calculer le permiètre d'un rectangle d'hauteur H et de largeur L;
     * @param hauteur Hauteur du rectangle en type double
     * @param largeur Largeur du rectangle en type double
     * @return perimètre du rectangle en type double
     */
    public static double perimetre(double hauteur, double largeur) {
        return (2 * hauteur) + (2 * largeur);
    }
    
    /**
     * Permet de calculer l'aire d'un rectangle d'hauteur H et de largeur L;
     * @param hauteur Hauteur du rectangle en type double
     * @param largeur Largeur du rectangle en type double
     */
    public static double aire(double hauteur, double largeur) {
        return hauteur * largeur;
    }
}
