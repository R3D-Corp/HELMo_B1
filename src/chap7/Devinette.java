package chap7;

import io.Console;

public class Devinette {

    private static final int MAX_GAME = 3;
    public static void main(String[] args) {
        boolean isRunning = true;
        int aDeviner, proposition;
        int iteration = 0;
        String regex = "^(?:10|[1-9])$";

        // aDeviner = Random.getInclude(1, 10);
        aDeviner = 3;
        while(isRunning) {
            IO.println("================");
            iteration++;
            if(iteration <= MAX_GAME) {
                proposition = Integer.parseInt(Console.lireStringWhile("Entier de 1 à 10 : ", "Input incorrect", regex));
        
                if(proposition == aDeviner) {
                    isRunning = false;
                    IO.println("Bravo tu as trouvé le nombre");
                    break;
                }
                else if(proposition < aDeviner) IO.println("Le nombre est plus grand");
                else IO.println("Le nombre est plus petit");
        
                IO.println(String.format("Il vous reste %d tentative(s)", MAX_GAME - iteration));
                continue;
            } else if(iteration == MAX_GAME+1) {
                IO.println("Malheureusement vous avez perdu.");
                break;
            };
        }
    }
}
