package chap4;

import io.Console;

public class DateAnniversaire {
    
    public static void main(String[] args) {
        
        String prenom;
        prenom = Console.lireString("Prénom ? ");
    
        System.out.printf("Bienvenue %s", prenom);
    }

}
