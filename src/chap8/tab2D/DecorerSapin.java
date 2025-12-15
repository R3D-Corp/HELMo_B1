package chap8.tab2D;

import util.LogsManager;
import util.Random;
import util.LogsManager.LogsType;

public class DecorerSapin {

	private static final LogsManager logsManager = new LogsManager("DecorerSapin", true);

	public static char[][] aleatoireBoule(char[][] sapin) {
		for(int i=0; i<sapin.length; i++) {
			for(int j=0; j<sapin[i].length; j++) {
				if(sapin[i][j] == '.' && Random.getInclude(1, 10) >= 8) {
					sapin[i][j] = 'o';
				}
			}
		}
		return sapin;
	}


	public static char[][] constuireSapin(int length) {
		final int AXIS_X = 1 + (length * 2);
		final int totalRows = length + 1;

		final char[][] sapin = new char[totalRows][];

		for(int i = 0; i<length; i++) {
			final int NUMBER_DOTS = 1 + (i * 2);
			final int NUMBER_SPACE = (AXIS_X - NUMBER_DOTS) / 2;

			char[] row = new char[AXIS_X];

			for(int j=0; j<row.length; j++) {
				if(j < NUMBER_SPACE) {
					row[j] = ' ';
				} else if(j==NUMBER_SPACE) {
					row[j] = '/';
				} else if(j < ((NUMBER_SPACE) + NUMBER_DOTS)) {
					row[j] = '.';
				} else if(j == NUMBER_SPACE + NUMBER_DOTS) {
					row[j] = '\\';
				} else {
					row[j] = ' ';
				}
			}	
			sapin[i] = row;
		}
		return sapin;
	}

	public static void main(String[] args) {
		char[][] sapin = constuireSapin(3);
		
		// Placer une étoile au sommet du sapin (dernière case de la 1ère ligne)
		sapin[0][sapin[0].length-1] = '*';
		// Agrandir le sapin en doublant les lignes 2 et 3
		
		// Placer aléatoirement une boule de Noël 'o' par ligne en remplaçant un '.'
		sapin = aleatoireBoule(sapin);
		afficher(sapin);
	}
	

	public static char[][] sapin() {
		return new char[][] {
		    "    ^".toCharArray(),
		    "   /.\\".toCharArray(),
		    "  /...\\".toCharArray(),
		    " /.....\\".toCharArray(),
		    "/.......\\".toCharArray(),
		    "^^^[_]^^^".toCharArray()
		};
	}
	
	public static void afficher(char[][] image) {
		logsManager.addLogs(LogsType.ERROR, image.toString());
		for (char[] ligne : image) {
			for (char car : ligne) {
				System.out.print(car);
			}
			System.out.println();
		}
	}
}
