package chap6;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.Console;

// [1 et 97]
// 97 - (tot % 97)
public class RegistreNational {

    public static boolean isValid(String registreNational) {
        String regex = "\\d{2}\\.\\d{2}\\.\\d{2}-\\d{3}\\.\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(registreNational);

        if (!matcher.find())
            throw new IllegalArgumentException("Format invalide");

        String s = registreNational.replace("-", ".");
        long nRegistre = extractRegistre(s);
        int validityNumber = extractValidityNumber(s);

        if (validityNumber < 1 || validityNumber > 97)
            throw new IllegalArgumentException("Le code de sécurité est invalide.");
        int[] correctNumber = caluclateValidityNumber(nRegistre);

        if (!(correctNumber[0] == validityNumber)) {
            return validityNumber == correctNumber[1];
        }
        return true;
    }

    private static int[] caluclateValidityNumber(long nRegistre) {
        int firstValidityNumber = (int) (97 - nRegistre % 97);
        int secondValidityNumber = (int) (97 - (nRegistre + 2e9) % 97);

        return new int[] { firstValidityNumber, secondValidityNumber };
    }

    private static long extractRegistre(String registre) {
        String[] component = registre.split("\\.");
        String tot = "";

        for (int i = 0; i < component.length - 1; i++) {
            tot += (component[i].startsWith("^0") && i <= 2) ? component[i].replace("0", "") : component[i].trim();
        }
        return Long.parseLong(tot);
    }

    private static int extractValidityNumber(String registre) {
        String[] component = registre.split("\\.");

        if (component.length != 5)
            throw new IllegalArgumentException("Registre invalide");
        return Integer.parseInt(component[5 - 1]);
    }
}