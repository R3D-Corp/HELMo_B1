package util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    private String path, name;
    private List<Logs> logs;
    private boolean verbose;

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
        private boolean verbose;

        public void print() {
            if(verbose) {
                String regex = "^\\[\\d{2}:\\d{2}:\\d{2}]";
                Pattern pattern = Pattern.compile(regex);
    
                Matcher matcher = pattern.matcher(this.value);
    
                String s = String.format("%s%s[%s]%s%s%s%s", 
                    AnsiColors.BOLD, 
                    this.type.value, 
                    this.type.text, 
                    AnsiColors.RESET,
                    this.type.value,
                    matcher.find() ? matcher.replaceFirst("") : this.value,
                    AnsiColors.RESET
                );
                IO.println(s);
            }
        };

        public Logs(LogsType type, boolean verbose, String s) {
            this.type = type;
            this.value = s;
            this.verbose = verbose;
        }
    }


    public static void clearLogs(String path) throws IOException {

        List<Logs> emptyLog = new ArrayList<>();
        try (FileWriter writer = new FileWriter(path)) {
            GSON.toJson(emptyLog, writer);
        }
    }

    private void saveLogs()  {
        try (FileWriter writer = new FileWriter(this.path)) {
            GSON.toJson(this.logs, writer);
        } catch(Exception e) {
            IO.println(e);
        }
    }

    public void printLogsFromType(LogsType type) {
        for(Logs l : this.logs) {
            if(l.type == type) l.print();
        }
    }

    public void printLastLog() {
        this.logs.get(this.logs.size() - 1).print();
    }

    public void addLogs(LogsType type, String s, boolean verbose) {
        String[] temp = Date.formaterDate(Date.temps());

        String time = String.format("%s:%s:%s", temp[0], temp[1], temp[2]);
        Logs log = new Logs(type, verbose, String.format("[%s] - %s", time, s));
        log.print();
        logs.add(log);

        this.saveLogs();
    }

    public void addLogs(LogsType type, String s)  {
        addLogs(type, s, true);
    }

    public void addLogs(String s) {
        addLogs(LogsType.INFO, s, true);
    }

    public void clearLogs()  {
        logs.clear();
        this.saveLogs();
    }
    
    /**
     * Classe permettant l'utilisation de l'outil de logs sauvegardée en json avec gestion du type. 
     * @param name Nom du manager de logs. ex (Programme1)
     * @param verbose Est ce que cette instance de logs va communiquer en console.
     * @since 1.0
     * @author R3D
     */
    public LogsManager(String name, boolean verbose) {
        this.verbose = verbose;
        this.name = name;
        String[] date = Date.formaterDate(Date.aujourdhui());

        String folderPath = String.format("data/logs/%s/", name);
        this.path = String.format(String.format("%s%s_%s_%s_%s.json", folderPath, name, date[0], date[1], date[2]));
        
        if(!Files.exists(Paths.get(folderPath))) {
            try {
                Files.createDirectories(Paths.get(folderPath));
            } catch (IOException e) {
                IO.println(e);
            }
        }

        Path filePath = Paths.get(this.path );
        if(!Files.exists(filePath)) {
            String emptyJsonContent = "[]";
            try {
                Files.write(filePath, emptyJsonContent.getBytes(StandardCharsets.UTF_8));
            } catch(IOException e) {
                IO.println("Error while creating json file : "+e.getMessage());
            }
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.path))) {
            this.logs = GSON.fromJson(reader, List.class);
        } catch (Exception e) {
            IO.println(e);
        }

    }
}
