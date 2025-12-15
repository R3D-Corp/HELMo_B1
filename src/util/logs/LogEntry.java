package util.logs;

import util.Date;

public class LogEntry {

    private LogsType type;
    private String value;
    private String[][] fields;
    private String[] timestamp;

    private static LogEntry createLog(LogsType logsType, String value, String[][] fields) {
        return new LogEntry(logsType, value, fields);
    }

    public static LogEntry createLogFromText(LogsType logsType, String text) {
        return createLog(logsType, text, null);
    }

    public static LogEntry createLogFromText(String text) {
        return createLog(LogsType.INFO, text, null);
    }

    public static LogEntry createLogFromArray(LogsType logsType, String text, String[][] arrays) {
        return createLog(logsType, text, arrays);
    }

    public static LogEntry createLogFromArray(String text, String[][] arrays) {
        return createLog(LogsType.INFO, text, arrays);
    }

    public void print() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.value);
        if(this.fields != null) {
            for(String[] field : this.fields) {
                builder.append("\n");
                builder.append(field[0]);
                builder.append("\s:\s");
                builder.append(field[1]);
            }
        }        
        String message = builder.toString();

        StringBuilder prefixeBuilder = new StringBuilder();
        prefixeBuilder.append(this.type.color.code);
        prefixeBuilder.append(AnsiColors.BOLD.code);
        prefixeBuilder.append(String.format("[%s] ", this.type.label));
        prefixeBuilder.append(AnsiColors.RESET.code);
        prefixeBuilder.append("\s");
        prefixeBuilder.append(message);

        IO.println(prefixeBuilder.toString());
    }

    private LogEntry(LogsType logsType, String value, String[][] fields) {
        this.type = logsType;
        this.value = value;
        this.fields = fields;
        this.timestamp = Date.formaterDate(Date.aujourdhui());
    }

    

}
