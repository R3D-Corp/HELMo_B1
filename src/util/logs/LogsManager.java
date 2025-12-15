package util.logs;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import util.Date;

public class LogsManager {

    private final static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private List<LogEntry> logs = new ArrayList<LogEntry>();
    private boolean verbose;
    private String path;

    private void appendLog(LogEntry log) {
        try (FileWriter writer = new FileWriter(this.path, true)) {
            writer.write(GSON.toJson(log));
            writer.write("\n");
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    public void addLogs(LogEntry log) {
        this.logs.add(log);
        this.appendLog(log);
        if(verbose) log.print();
    }

    public LogsManager(String name, boolean verbose) {
        this.verbose = verbose;
        String[] date = Date.formaterDate(Date.aujourdhui());

        String folderPath = String.format("data/logs/%s/", name);
        this.path = String.format(String.format("%s%s_%s_%s_%s.json", folderPath, name, date[0], date[1], date[2]));
        
        if(!Files.exists(Paths.get(folderPath))) {
            try {
                Files.createDirectories(Paths.get(folderPath));
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        Path filePath = Paths.get(this.path );
        if(!Files.exists(filePath)) {
        	try {
        		Files.createFile(filePath);
        	} catch(IOException e) {
        		IO.println(e);
        	}
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.path))) {
        	
        	Type type = new TypeToken<List<LogEntry>>() {}.getType();
            this.logs = GSON.fromJson(reader, type);
            
            if(this.logs == null) {
            	this.logs = new ArrayList<LogEntry>();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

/** TODO IDEE
    **Filtrage et recherche :**
    - [ ] Méthode pour filtrer les logs par plage de dates
    - [ ] Méthode pour rechercher les logs contenant un mot-clé spécifique
    - [ ] Méthode pour obtenir les logs d'une certaine heure/minute

    **Gestion des fichiers :**
    - [ ] Méthode pour exporter les logs dans d'autres formats (CSV, TXT)
    - [ ] Méthode pour archiver les anciens logs
    - [ ] Méthode pour obtenir la taille du fichier de logs

    **Statistiques :**
    - [ ] Méthode pour compter les logs par type
    - [ ] Méthode pour obtenir le nombre total de logs
    - [ ] Méthode pour afficher un résumé/statistiques des logs

    **Affichage amélioré :**
    - [ ] Méthode pour afficher les logs paginés
    - [ ] Méthode pour afficher les N derniers logs
    - [ ] Méthode pour afficher les logs avec une limite de lignes

    **Configuration :**
    - [ ] Méthode pour changer le niveau de verbosité dynamiquement
    - [ ] Méthode pour définir un niveau minimum de log à afficher
    - [ ] Méthode pour ajouter des préfixes personnalisés

    **Suppression :**
    - [ ] Méthode pour supprimer les logs d'un certain type
 */