package chap6;

import io.Console;

public class OperateurLogique {

    /**
     * Envois un message console permettant de de distinguer si oui ou non x fait partie d'une des intervalles
     * @param intervalle int permettant de distinguer de quelle intervalle il est question
     * @param inIt booléen repésentant l'appartenance ou non de x a l'intervalle.
     */
    private static void message(int intervalle, boolean inIt) {
        if(inIt) IO.println(String.format("x est un élement de l'intervalle %s ", intervalle));
        else IO.println(String.format("x n'est pas un élement de l'intervalle %s ", intervalle));
    }

    public static void main(String[] args) {
        double x = Console.lireDouble("un réel ? ");


        if(x >= 8 && x < 10) {
            message(1, true);
        } else message(1, true);

        if(x <= -5 || x >= 5) {
            message(2, true);
        } else message(2, false);

        if(x <= 0 || (x >= 5 && x < 10)) message(3, true);
        else message(3, false);

        if(x != 0 && x >= -5 && x < 15) message(4, true);
        else message(4, true);
    }
}
