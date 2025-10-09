package chap4;

import io.Console;

public class FormaterPrenom {
    private static String firstLetterUppercase(String text) {

        String[] splitted = text.split(" ");
        String toReturn = "";
        for(int i=0; i<splitted.length; i++) {
            String s = splitted[i];
            char firstLetter = Character.toUpperCase(s.charAt(0));
            String textEnd = s.substring(1).toLowerCase();
            if(i!=0) {
                toReturn = toReturn + "\s";
            }
            toReturn = toReturn + (firstLetter + textEnd);
        }
        return toReturn;
    }

    public static void main(String[] args) {
        String nom, prenom, ville;

        /**
            * Utiliser pour entrer et tester des inputs console;
         */
        Console.simulerSaisies("jean paul sartre", "FELIX", "Petit coin de paradis de mer");
        prenom = Console.lireString("Prénom ? ").trim();
        nom = Console.lireString("Nom ? ").trim();
        ville = Console.lireString("Ville ? ").trim();

        System.out.println(firstLetterUppercase(prenom));
        System.out.println(firstLetterUppercase(nom));
        System.out.println(firstLetterUppercase(ville));
    }
}
