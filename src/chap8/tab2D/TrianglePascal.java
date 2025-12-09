package chap8.tab2D;

import io.Console;
import util.LogsManager;
import util.LogsManager.LogsType;
public class TrianglePascal {
    
    private static final LogsManager logsManager = new LogsManager("TriangePascal", true);

    /**
     * Fonction permettant d'afficher, chaque ligne d'un tableau 2D
     * Ici il utilise le système de logs.
     * @param t tableau 2D à afficher en console.
     */
    public static void afficher(int[][] t) {
        for (int[] ligne : t) {
            String s  = "";
            for (int element : ligne) {
                s += element;
            }
            logsManager.addLogs(s);
        }
    }


    /**
     * Fonction permettant de calculer le triangle de pascal de n ligne
     * @param nbLigne taille du triangle de pascal a calculer
     * @return Triangle de pascal sous format int[][]
     */

    public static int[][] trianglePascal(int nbLigne) {
        int[][] triangle = new int[nbLigne][];

        for (int i = 0; i < triangle.length; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1;
            triangle[i][i] = 1;

            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
            
        }
        return triangle;
    }

    public static void main(String[] args) {
        int nbLigne = Console.lireInt("Nombre de ligne ? ");
        logsManager.addLogs(LogsType.WARNING, String.format("Requested Pascal's triangle of %d line(s)", nbLigne));       
        int[][] triangle = trianglePascal(nbLigne);
        afficher(triangle);
    }
}