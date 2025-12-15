package util.logs;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.Date;

public class LogsManager {

    private final static Gson GSON = new GsonBuilder()
        .create();


    private List<LogEntry> logs = new ArrayList<LogEntry>();
    private String name, path;
    private boolean verbose;


    private void appendLog(LogEntry log) {
        try (FileWriter writer = new FileWriter(this.path, true)) {
            writer.append("\n");
            writer.append(GSON.toJson(log));
        } catch(Exception e) {
            IO.println(e);
        }
    }

    /**
     * Classe permettant l'utilisation de l'outil de logs sauvegardée en json avec gestion du type. 
     * @since 1.1
     * @author R3D
     */
    public void addLog(LogEntry log) {
        logs.add(log);
        this.appendLog(log);
        log.print();
    }

    /**
     * Fonction ajoutant au logManager une log de type "type" et de texte "s";
     * Fonction venant de l'avant 1.1;
     * @deprecated
     * @param type Type de la log.
     * @param s Texte a mettre dans la log.
     * @author R3D
     * @since 1.0
     */
    public void addLogs(LogsType type, String s)  {
        this.addLog(LogEntry.createLogFromText(type, s));
    }

    public void addLogs(String s) {
        this.addLog(LogEntry.createLogFromText(LogsType.INFO, s));
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
                System.err.println("Error while creating the directory" + e);
            }
        }

        Path filePath = Paths.get(this.path);
        if(!Files.exists(filePath)) {
            String emptyJsonContent = "";
            try {
                Files.write(filePath, emptyJsonContent.getBytes(StandardCharsets.UTF_8));
            } catch(IOException e) {
                System.err.println("Error while creating json file : " + e.getMessage());
            }
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.path))) {
            this.logs = GSON.fromJson(reader, List.class);
            if(this.logs == null) {
                this.logs = new ArrayList<LogEntry>();
            }
        } catch (Exception e) {
            IO.println(e);
        }

    }
}
