package Modele.ButinJeu;

public class Magot extends Butin {
    private final int valeur;

    public Magot(){
        valeur = 1000;
    }
    public int getValeur() {

        return valeur;

    }

    public String toString() {
        return(" Magot : " + valeur +" $$$");
    }
}
