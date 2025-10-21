package chap6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.Console;

// [1 et 97]
// 97 - (tot % 97)
public class RegistreNational {

    private static long caluclateValidityNumber(long nRegistre) {
        return (97) - nRegistre % 97;
    }

    private static boolean isValid(long nRegistre, int validityNumber) {
        if(validityNumber < 1 || validityNumber > 97) throw new IllegalArgumentException("Le code de sécurité est invalide.");
        if(!(validityNumber == caluclateValidityNumber(nRegistre)) ) {
            System.out.println("salut");
            System.out.println(Long.parseLong("2" + String.valueOf(nRegistre)));
            return validityNumber == Long.parseLong("2" + String.valueOf(nRegistre));
        }
        return (validityNumber == caluclateValidityNumber(nRegistre));
    }

    private static long extractRegistre(String registre) {
        String[] component = registre.split("\\.");
        String tot = "";

        for(int i=0; i<component.length - 1; i++) {
            tot += (component[i].startsWith("^0") && i<=2) ? component[i].replace("0", "") : component[i].trim();
        }
        return Long.parseLong(tot);
    }

    private static int extractValidityNumber(String registre) {
        String[] component = registre.split("\\.");

        if(component.length != 5) throw  new IllegalArgumentException("Registre invalide");
        return Integer.parseInt(component[5 - 1]);
    }

    public static void main(String[] args) {

        String nRegistre = Console.lireString("Votre numéro de registre (xx.xx.xx-xxx.xx) ? ").trim();
        String regex = "\\d{2}\\.\\d{2}\\.\\d{2}-\\d{3}\\.\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nRegistre);

        if(matcher.find()) {
            nRegistre = nRegistre.replace("-", ".");
            
            boolean isValid = isValid(extractRegistre(nRegistre), extractValidityNumber(nRegistre));
            IO.println(isValid);
            
            if(!isValid) IO.println(String.format("Invalide. Attendu : %d", caluclateValidityNumber(extractRegistre(nRegistre))));
        } else {
            throw new IllegalArgumentException("Registre invalide");
        }
    }
}