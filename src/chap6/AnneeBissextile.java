package chap6;

import io.Console;

public class AnneeBissextile {

    /**
     * Fonction qui determine si une année est bissextile ou non
     * @param annee l'année en int a vérifier
     * @return boolean qui correspond au caractère bissextile de l'année donnée.
     */
    public static boolean estBissextile(int annee) {
        return ((annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0);
    }

    public static void main(String[] args) {
        int annee = Console.lireInt("Année ? ");

        if(estBissextile(annee)) {
            IO.println(String.format("L'année %s est bissextile", annee));
        } else {
            IO.println(String.format("L'année %s n'est pas bissextile", annee));
        }
    }
}
