package chap8.tab2D;

import java.util.ArrayList;
import java.util.List;

public class Vendeur {


    public String name;
    private List<Integer> billetsVendu = new ArrayList<Integer>();
    
    
    public void vendreBillet(int billets) {
        this.billetsVendu.add(billets);
    }

    public int compterBillets() {
        return this.billetsVendu.size();
    }

    public boolean rechercherBillets(int billet) {
        for(int b : this.billetsVendu) {
            if(b == billet) {
                return true;
            }
        }
        return false;
    }

    public Vendeur(String name) {
        this.name = name;
    }



}
