package chap8;

import java.io.IOException;
import java.util.Arrays;

import io.Console;

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
        String regex = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
        String name = Console.lireString("Votre nom");
        String ddn = Console.lireStringWhile("Date de naissance (jj/mm/aaaa) ? ", "Format de date incorrect (jj/mm/aaaa)", regex);
        
        IO.println(Arrays.toString(extraireDate(ddn)));
    }
}
