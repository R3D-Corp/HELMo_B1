package util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LogsManager {

    private final static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private String path;
    private List<Logs> logs;
    private boolean requestPrint;

    public enum LogsType {
        INFO(AnsiColors.BLUE, "INFO"),
        WARNING(AnsiColors.YELLOW, "WARNING"),
        ERROR(AnsiColors.RED, "ERROR"),
        EVIDENCE(AnsiColors.PURPLE, "EVIDENCE"),
        SUCCESS(AnsiColors.GREEN, "SUCCESS");

        private String value, text;

        LogsType(String color, String text) {
            this.value = color;
            this.text = text;
        }
    }

    private class AnsiColors {
        public static final String RESET = "\u001B[0m";
        public static final String BOLD = "\u001B[1m";

        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
    }

    private class Logs {
        private LogsType type;
        private String value;

        public Logs(LogsType type, String s) {
            this.type = type;
            this.value = s;
        }
    }


    public static void clearLogs(String path) throws IOException {
        new JsonCreator(path);

        List<Logs> emptyLog = new ArrayList<>();
        try (FileWriter writer = new FileWriter(path)) {
            GSON.toJson(emptyLog, writer);
        }
    }

    private static void printLog(Logs log) {
        String regex = "^\\[\\d{2}:\\d{2}:\\d{2}]";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(log.value);

        String s = String.format("%s%s[%s]%s%s%s%s", 
            AnsiColors.BOLD, 
            log.type.value, 
            log.type.text, 
            AnsiColors.RESET,
            log.type.value,
            matcher.find() ? matcher.replaceFirst("") : log.value,
            AnsiColors.RESET
        );

        IO.println(s);
    }

    private void saveLogs() throws IOException {
        try (FileWriter writer = new FileWriter(this.path)) {
            GSON.toJson(this.logs, writer);
        }
    }

    public void printLogsFromType(LogsType type) {
        for(Logs l : this.logs) {
            if(l.type == type) printLog(l);
        }
    }

    public void printLastLog() throws Exception {
        if(!this.requestPrint) throw new Exception("Can't print logs which are requested to no print", new Throwable("Requested print on LogsManager no verbose"));
        printLog(this.logs.get(this.logs.size() - 1));
    }

    public void addLogs(LogsType type, String s) throws IOException {
        String[] temp = Date.formaterDate(Date.temps());


        String time = String.format("%s:%s:%s", temp[0], temp[1], temp[2]);
        Logs log = new Logs(type, String.format("[%s] - %s", time, s));
        logs.add(log);
        if(this.requestPrint) printLog(log);

        this.saveLogs();
    }

    public void clearLogs() throws IOException {
        logs.clear();
        this.saveLogs();
    }

    public LogsManager(String path, boolean requestPrint)  {
        this.requestPrint = requestPrint;
        String[] date = Date.formaterDate(Date.aujourdhui());

        String[] temp = path.split("\\.");
        if (temp.length != 2)
            throw new IllegalArgumentException("Votre chemin de fichier est incorrect");


        this.path = String.format("%s_%s_%s_%s.%s", temp[0], date[0], date[1], date[2], temp[1]);
        
        new JsonCreator(this.path);
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.path))) {
            this.logs = GSON.fromJson(reader, List.class);
        } catch (Exception e) {
            IO.println(e);
        }

    }
}
