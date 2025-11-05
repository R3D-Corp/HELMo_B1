package chap7;

import io.Console;
import util.Random;

public class Devinette {
    public static void main(String[] args) {
        boolean isRunning = true;
        int aDeviner, proposition;
        String regex = "^(?:10|[1-9])$";

        aDeviner = Random.getInclude(1, 10);
        while(isRunning) {
            proposition = Integer.parseInt(Console.lireStringWhile("Entier de 1 à 10 : ", "Input incorrect", regex));

            if(proposition < aDeviner) IO.println("Le nombre est plus grand");
            if(proposition > aDeviner) IO.println("Le nombre est plus petit");

            if(proposition == aDeviner) isRunning = !isRunning;
        }
        IO.println("Bravo tu as trouvé le nombre");
    }
}
