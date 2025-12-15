package util.logs;

public enum LogsType {
    INFO(AnsiColors.BLUE, "INFO"),
    WARNING(AnsiColors.YELLOW, "WARNING"),
    ERROR(AnsiColors.RED, "ERROR"),
    EVIDENCE(AnsiColors.PURPLE, "EVIDENCE"),
    SUCCESS(AnsiColors.GREEN, "SUCCESS");

    public AnsiColors color;
    public String label;

    LogsType(AnsiColors color, String label) {
        this.color = color;
        this.label = label;
    }
}
