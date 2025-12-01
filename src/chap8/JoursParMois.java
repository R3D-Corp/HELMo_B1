package chap8;

import java.util.List;

import io.Console;

public class JoursParMois {
    public static final List<Integer> MOIS_PAS_BISSEXTILE = List.of(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    /**
     * Fonction permettant de savoir si une année donnée est bissextile
     * @param annee l'année dont on veut connaitre le caractère bissextile
     * @return le caractère bissextile de la fonction,
     * @since 1.0
     * @author R3D
     */
    private static boolean estBissextile(int annee) {
        return (annee % 4 == 0 && annee % 100 != 0 || annee % 400 == 0);

    }

    /**
     * Fonction permettant d'obtenir le nombre de jour d'un mois d'une année spécifié
     * @param mois Le mois dont on veut connaitre le nombre de jour.
     * @param annee L'année dont on veut connaitre le nombre de jour du mois.
     * @return Le nombre de jours dans le mois de l'année spécifié.
     * @since 1.0
     * @author R3D
     */
    public static int joursParMois(int mois, int annee) {
        if(mois == 2) {
            return estBissextile(annee) ? 29 : MOIS_PAS_BISSEXTILE.get(mois-1);
        }

        return MOIS_PAS_BISSEXTILE.get(mois-1);
    }
    
    /**
     * Programme permettant a l'utilisateur d'obtenir le nombre de jours dans le mois de l'année qu'il souhaite.
     * @param args void
     * @Output nombre de jours.
     * @Since 1.0
     * @Author R3D
     * 
     */
    public static void main(String[] args) {
        String regex_mois = "^(?:0?[1-9]|1[0-2])$";
        
        int mois = Integer.parseInt(Console.lireStringWhile("Mois de (1 à 12) ? ", "Mois incorrect", regex_mois));
        int annee = Integer.parseInt(Console.lireStringWhile("Année ? ", "Année incorrecte", "\\d{1,5}"));

        IO.println(String.format("Il y'a %d jours dans le mois %d/%d", joursParMois(mois, annee), mois, annee));
    }

}
