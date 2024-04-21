package Modele.ButinJeu;

public class Bijou extends Butin{
    private final int valeur = 500;
    public int getValeur() {
        return valeur;
    }

    public String toString() {
        return(" Bijou : " + valeur +" $$$");
    }


}
