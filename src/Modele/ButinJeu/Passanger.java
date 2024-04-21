package Modele.ButinJeu;

import java.util.Random;

public class Passanger extends Butin{
    private Butin bagage;
    public Passanger(){
        Random random = new Random();
        int v = random.nextInt(100);
        if(v > 8) {
            bagage = new Bijou();
        }else if( v >= 8 && v < 46 ){
            bagage = new Money();
        }else{
            bagage = null;
        }
    }
    public int getValeur() {
       if( bagage != null ) return bagage.getValeur();
       else return 0;
    }
    public String toString() {
        if(bagage == null )return ("Rien (le passager n'a rien sur lui) ");
        else return( bagage.toString()+" ( Passager )");
    }

    public Butin getBagage() {
        return bagage;
    }
}
