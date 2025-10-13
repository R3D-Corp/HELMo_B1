package chap5;

import chap5.formes.Cercle;
import chap5.formes.Rectangle;

import io.Console;

public class Geometrie {
    


    private static void afficherResultat(double perimetre, double aire, String form, int arrondiVoulu) {
        String format = String.format("%%.%df", arrondiVoulu);
        System.out.printf("Le %s a une aire de " + format +"cm² et un perimètre de " + format + "cm\n", form, aire, perimetre);
    }

    private static double arrondir(double aArrondir, int decimales) {
        double facteur = Math.pow(10, decimales);
        return Math.round(aArrondir * facteur) / facteur;
    }

    public static void main(String[] args) {
        double rayon;
        double hauteur, largeur;
        double perimetre, aire;
        String forme;
        int arrondiVoulu;

        forme = Console.lireString("Avec quelle forme voulez vous travailler ? ").trim().toLowerCase();
        arrondiVoulu = Console.lireInt("A combien de chiffre après la virgule voulez vous arrondir ? ");

        arrondiVoulu = (arrondiVoulu < 1) ? 1 : arrondiVoulu;
        /*
         * Code demandé par Raphaël.
         */
        switch(forme) {
            case "cercle":
                rayon = Console.lireDouble("Rayon en cm ? ");
                perimetre = arrondir(Cercle.perimetre(rayon), arrondiVoulu);
                aire = arrondir(Cercle.aire(rayon), arrondiVoulu);

                afficherResultat(perimetre, aire, forme, arrondiVoulu);
                break;
            case "carre":
            
            case "rectangle":
                largeur = Console.lireDouble("Largeur en cm ? ");
                hauteur = Console.lireDouble("Hauteur en cm ?");

                perimetre = Math.round(Rectangle.perimetre(hauteur, largeur) * Math.pow(10, arrondiVoulu)) / (double)Math.pow(10, arrondiVoulu); 
                aire = Math.round(Rectangle.aire(hauteur, largeur) * Math.pow(10, arrondiVoulu)) / (double)Math.pow(10, arrondiVoulu); 

                afficherResultat(perimetre, aire, forme, arrondiVoulu);
                break;
            default:
                System.out.printf("Malheureusement, la forme %s n'est pas prise en charge =(", forme);
                break;
        }
    }
}
