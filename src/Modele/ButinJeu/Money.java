package Modele.ButinJeu;

import java.util.Random;

public class Money extends Butin{
    private int valeur;
    public Money(){ // j'initialise l'argent de chaque passager de maniere aleatoire
        Random random = new Random();
        valeur = random.nextInt(500)+1; // entre 0 et 500
    }
    public Money(int valeure){
        valeur = valeure;
    }
    public int getValeur() {
        return valeur;
    }
    public String toString() {
        return(" Money : " + valeur +" $$$");
    }
}
