package chap5.formes;

public final class Cercle {
    /**
     * Fonction permettant de calculer le perimètre d'un cercle de rayon r
     * @param rayon Rayon du cercle en type double
     * @return perimètre du cercle en double
     */
    public static double perimetre(double rayon) {
        return 2 * Math.PI * rayon;
    }

    /**
     * Fonction permettant de calculer l'aire d'un cercle de rayon r
     * @param rayon Rayon du cercle en type double
     * @return aire du cercle en double
     */
    public static double aire(double rayon) {
        return Math.PI * Math.pow(rayon, 2);
    }
}
