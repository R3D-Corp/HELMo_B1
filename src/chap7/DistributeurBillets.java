package chap7;

import io.Console;

public class DistributeurBillets {
    

    private static final int[] BILLETS_TYPE = new int[] {200, 100, 50, 20, 10, 5};
    private static final String[] COMMANDS = new String[] {"withdraw", "deposit", "exit"};

    private static int[] compterBillets(int montant) {
        int response = montant;
        int[] billets = new int[6];
        for (int i=0; i<BILLETS_TYPE.length; i++) {
            billets[i] = response / BILLETS_TYPE[i];
            response = response % BILLETS_TYPE[i];
        }
        return billets;
    }

    private static String getCommand() {
        boolean isCorrect = false;
        String s = "";
        while(!isCorrect) {
            s = Console.lireString("Commande : ").trim();
            for (int i=0; i<COMMANDS.length; i++) {
                if(s.equalsIgnoreCase(COMMANDS[i])) {
                    isCorrect = true;
                }
            }
        }
        return s;
    }

    private static int getValue() throws Exception {
        int montant = Integer.parseInt(Console.lireStringWhile("Montant en euros ? ", "\\d{1,3}"));
        if(montant / BILLETS_TYPE[BILLETS_TYPE.length - 1] != 0) {
            throw new Exception("Montant invalide", new Throwable("Incorrect value"));
        }
        return montant;
    }

    public static void main(String[] args) throws Exception {
        while(!getCommand().equals("exit")) {
            int montant = getValue();
            int[] billets = compterBillets(montant);
            for (int i=0; i<BILLETS_TYPE.length; i++) {
                IO.println(String.format("%d billet(s) de %d€", billets[i], BILLETS_TYPE[i]));
            }
        }
        System.out.println("Fin du programme");
    }
    
}