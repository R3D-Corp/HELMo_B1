package chap4;

import io.Console;
import util.Date;


/*
    Not working. 
    I'm working on it.
*/


public class DateAnniversaire {

    private static int[] dateFIRST = new int[3];
    private static int iteration = 0;
    
    // Méthode with indexOf et Substring
    private static void extractDOB(String dob) {
        int index = dob.indexOf("/");
        if(index == -1) {
            dateFIRST[iteration] = Integer.parseInt(dob);
            iteration = 0;

            return;
        }

        String value = dob.substring(0, index);
        String rest = dob.substring(index+1, dob.length());

        dateFIRST[iteration] = Integer.parseInt(value);
        iteration++;
        
        extractDOB(rest);
    }

    /**
     * Méthode avec String.split;
     * Cette fonction reçoit une chaîne de caractère la décompose et renvoie les valeurs jour, mois et année.
     * 
     * @param dob La date en string de format ("jj/mm/aaaa")
     * @return un tableau int[] de structure {jour, mois, année} 
     */

    private static int[] extractDOB_SPLIT(String dob) {
        String[] dates = dob.split("/");
        int[] date = new int[dates.length];
        for(int i=0; i<dates.length; i++) {
            date[i] = Integer.parseInt(dates[i]);
        }

        return date;
    }

    private static void calculerAge(String dob) {
        int[] date = extractDOB_SPLIT(dob);

        int anneeAjd = Date.anneeAuj();
        int age = anneeAjd - date[2];
        int numJourAjd = Date.numeroJour();
        int numJourAniv = Date.numeroJour(date[0], date[1], anneeAjd);

        System.out.println(age - Math.min(1, numJourAniv / numJourAjd));
    }

    public static void main(String[] args) {
        String prenom;
        prenom = Console.lireString("Prénom ? ");
        System.out.printf("Bienvenue %s \n", prenom);

        String dob = Console.lireString("DOB ? ");
        calculerAge(dob);
    }
   
}
