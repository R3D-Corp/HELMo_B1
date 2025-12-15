package util.logs;

import util.Date;


public class LogEntry {
    private LogsType type;
    private String message;
    private String[] timestamp;
    @SuppressWarnings("unused")
	private String[][] arrays;

    /**
     * Créer une LogEntry avec un type LogsType et un texte string simple.
     * @param type Type de la log entrain d'être créer
     * @param message Message contenu dans la log.
     * @return Une LogEntry à ajouter dans le LogsManager
     */
    private static LogEntry createLogFromText(LogsType type, String message, String[][] arrays) {
    	return new LogEntry(type, message, arrays);
    }
    

    
    private static LogEntry createLogFromArrays(LogsType type, String title, String[][] arrays) {
        StringBuilder s = new StringBuilder();
        s.append(title);
        for(String[] array : arrays) {
            s.append(String.format("\n%s : %s", array[0], array[1]));
        };
        return createLogFromText(type, s.toString(), arrays);
    }

    public static LogEntry createLog(LogsType type, String message) {
        return createLogFromText(type, message, null);
    }

    public static LogEntry createLog(String message) {
        return createLogFromText(LogsType.INFO, message, null);
    }

    public static LogEntry createLogWithFields(String title, String[][] arrays) {
        return createLogFromArrays(LogsType.INFO, title, arrays);
    }

    public static LogEntry createLogWithFields(LogsType type, String title, String[][] arrays) {
        return createLogFromArrays(type, title, arrays);
    }

    public LogEntry(LogsType type, String message, String[][] arrays) {

        this.type = type;
        this.timestamp = Date.formaterDate(Date.aujourdhui());	
        this.message = message;
        this.arrays = arrays;
    }
    

    public void print() {
        StringBuilder builder = new StringBuilder();
        builder.append(AnsiColors.BOLD.apply(this.type.label));
        builder.append("\s");
        builder.append(this.message);
        IO.println(this.type.color.apply(builder.toString()));
    };

    public LogsType getType() {return type;}
    public String[] getTimestamp() {return timestamp;}
    public String getMessage() {return message;}
}
