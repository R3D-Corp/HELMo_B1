package util;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private List<String> commands = new ArrayList<String>();

    public void registerCommand(List<String> commands) {
        this.commands = new ArrayList<String>();
        for (String c : commands) {
            this.commands.add(c.toLowerCase().trim());
        };


    };
    /**
     * Méthode permettant d'ajouter à la table de commande.
     * @param command La commande à ajouter.
     */
    public void registerCommand(String command) {
        this.commands.add(command);
    };

    /**
     * Méthode permettant de vider la table de commande.
     */
    public void clearCommands() {
        this.commands = new ArrayList<String>();
    }
    /**
     * Méthode permettant de vérifier si un String est une commande valide;
     * @param input String dont on veux vérifier l'existence
     * @return la commande entrée forcément en minuscule.;
     */
    public String checkCommand(String input) {
        String response = null;
        for (String s : this.commands) {
            if(input.toLowerCase().equals(s)) {
                response = s;
                break;
            }
        }
        return response;
    }

    // public int 
    
}
