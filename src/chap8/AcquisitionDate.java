package chap8;

import java.io.IOException;
import java.util.Arrays;

import io.Console;
import util.UsersManager;

public class AcquisitionDate {


    private static int[] extraireDate(String date) {
        String[] data = date.split("/");
        int[] response = new int[data.length];

        for(int i=0; i<data.length; i++) {
            response[i] = Integer.parseInt(data[i]);
        }

        return response;
    } 
    public static void main(String[] args) throws IOException {

        UsersManager manager = new UsersManager("data/users.json");
        
        
        String regex = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
        String name = Console.lireString("Votre nom");
        String ddn = Console.lireStringWhile("Date de naissance (jj/mm/aaaa) ? ", "Format de date incorrect (jj/mm/aaaa)", regex);
        
        manager.createUser(name, "test", ddn);
        IO.println(Arrays.toString(extraireDate(ddn)));
    }
}
