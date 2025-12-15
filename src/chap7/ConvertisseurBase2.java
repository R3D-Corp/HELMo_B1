package chap7;

import io.Console;

import util.logs.LogsManager;
import util.logs.LogEntry;

public class ConvertisseurBase2 {
	private static LogsManager logsManager = new LogsManager("ConvertisseurBase2", true);
	
	private static String convert(long value) {
		String response = "";
		while(value > 0) {
			response = (int)value % 2 + response;
			value = value / 2;
		}
		return response;
	}

	public static void main(String[] args) {
		long number10 = Long.parseLong(Console.lireStringWhile("Entier ? ", "\\d{1,50000}"));
		String result = convert(number10);
		
		logsManager.addLog(LogEntry.createLogFromArray("Résultat", new String[][] {
			{"Nombre de base", Long.toString(number10)},
			{"Convertit en binaire", result},
			{"Nombre de bits", Integer.toString(result.length())}
		}));	
	}
}
