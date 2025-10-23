package chap5.formes;

public final class Rectangle {
    /**
     * Permet de calculer le permiètre d'un rectangle d'hauteur H et de largeur L;
     * @param hauteur Hauteur du rectangle en type double
     * @param largeur Largeur du rectangle en type double
     * @return perimètre du rectangle en type double
     */
    public static double perimetre(double hauteur, double largeur) {
        if(hauteur <= 0 || largeur <= 0) throw new IllegalArgumentException("Hauteur et Largeur doivent être > 0");

        return 2 * (largeur + hauteur);
    }
    
    /**
     * Permet de calculer l'aire d'un rectangle d'hauteur H et de largeur L;
     * @param hauteur Hauteur du rectangle en type double
     * @param largeur Largeur du rectangle en type double
     */
    public static double aire(double hauteur, double largeur) {
        if(hauteur <= 0 || largeur <= 0) throw new IllegalArgumentException("Hauteur et Largeur doivent être > 0");

        return hauteur * largeur;
    }
}