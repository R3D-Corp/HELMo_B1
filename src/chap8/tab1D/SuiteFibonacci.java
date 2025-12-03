package chap8.tab1D;

import java.util.Arrays;

import io.Console;

public class SuiteFibonacci {

    /**
     * Crée une table long[] de taille length avec table[0] = 0 & table[1] = 1;
     * 
     * @param length Taille de la table long[] a créer.
     * @return
     */
    private static long[] createArray(int length) {
        long[] response = new long[length];
        response[1] = 1;

        return response;
    }

    /**
     * Démarre un programme qui affiche la suite de fibonacci jusqu'a au chiffre
     * demandé par l'utilisateur.
     * 
     * @param args null
     */
    public static void main(String[] args) {

        int length = Integer.parseInt(Console.lireStringWhile("Jusqu'a quelle itération voulez vous connaitre ? ", "Erreur", "\\d{1,5}"));
        if (length == 1)
            throw new IllegalArgumentException("Erreur lors de l'acquistion !", new Throwable("fibonacci's length == 1"));

        long[] t = createArray(length);
        for (int i = 1; i < t.length - 1; i++) {
            t[i + 1] = t[i] + t[i - 1];
        }
        IO.println(Arrays.toString(t));
    }
}
