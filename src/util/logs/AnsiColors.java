package util.logs;
public enum AnsiColors {

    RESET("\u001B[0m"),
    BOLD("\u001B[1m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m");

    private final String code;

    AnsiColors(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public String apply(String text) {
        // Shortcut: color + text + reset
        return this.code + text + RESET.code;
    }

    @Override
    public String toString() {
        return code;
    }
}
