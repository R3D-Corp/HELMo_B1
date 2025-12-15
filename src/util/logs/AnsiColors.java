package util.logs;

public enum AnsiColors {
    RESET("\u001B[0m"),
    BOLD("\u001B[1m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m");

    public String code;

    AnsiColors(String code) {
        this.code = code;
    }

    public String apply(String text) {
        return this.code + text + RESET.code;
    }

}
