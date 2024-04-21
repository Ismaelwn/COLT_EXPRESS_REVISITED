package Modele.TrainJeu;

import Modele.ButinJeu.*;
import java.util.ArrayList;
import java.util.Random;

public class Wagon extends Observable {
    private int numWag;
    private ArrayList<ArrayList<Butin>> butins; // 0 pour le wagon  // 1 pour le toit


    public Wagon(int numWa) { // initialisation aleatoire du nombre de personnes/ butins en respectant les 4 'stuff' par wagon
        numWag = numWa;
        butins = new ArrayList<ArrayList<Butin>>();
        ArrayList<Butin> toit = new ArrayList<>(4);
        ArrayList<Butin> wagon = new ArrayList<>(4);
        //for (int i = 0; i < 4; i++) wagon.add(null);
        //for (int i = 0; i < 4; i++) toit.add(null);
        butins.add(toit);
        butins.add(wagon);
        if (numWag == 0) {
            butins.get(0).add(new Magot());
            Random random = new Random();
            int p;
            for (int i = 0; i < 4; i++) {
                p = random.nextInt(100);
                if (p < 22) {
                    butins.get(0).add(new Bijou());
                } else if (p < 44 && p >= 22) {
                    butins.get(0).add(new Money());
                }
            }
        } else {
            Random random = new Random();
            int p;
            for (int i = 0; i < 4; i++) {
                p = random.nextInt(100);
                if (p < 25) {
                    butins.get(0).add(new Bijou());
                } else if (p < 55 && p >= 25) {
                    butins.get(0).add(new Money());
                } else if (p >= 55 && p < 90) {
                    butins.get(0).add(new Passanger());
                } //else {
                    //butins.get(0).add(null);
                //}
            }
        }
    }

    public Wagon() { // initialisation aleatoire du nombre de personnes/ butins en respectant les 4 'stuff' par wagon
        numWag = -1;
        butins = new ArrayList<ArrayList<Butin>>();
        ArrayList<Butin> toit = new ArrayList<>(4);
        ArrayList<Butin> wagon = new ArrayList<>(4);
        butins.add(toit);
        butins.add(wagon);
    }

    public ArrayList<ArrayList<Butin>> getButins() {
        return butins;
    }


    public int getNumWag() {
        return numWag;
    }

    public Butin randomStuff(boolean in) {
        Random tmp = new Random();
        int nbr;
        Butin rtrn = null;
        if (in && butins.get(0).size() > 0) {
            nbr = tmp.nextInt(this.butins.get(0).size());
            rtrn = butins.get(0).get(nbr);
            butins.get(0).remove(nbr);
        } else if( !in && butins.get(1).size() > 0) {
            nbr = tmp.nextInt(this.butins.get(1).size());
            rtrn = butins.get(1).get(nbr);
            butins.get(1).remove(nbr);
        }
        notifyObservers();
        return rtrn;
    }

    public void addButin(Butin x, boolean i) {
        if (i && x != null) {
            if( x instanceof Passanger && ((Passanger) x).getBagage() instanceof Money) {
                if (((Passanger) x).getBagage().getValeur() == 0) {
                    return;
                } else {
                    this.butins.get(0).add(new Money(x.getValeur()));
                }

            }else if(x instanceof Passanger && ((Passanger) x).getBagage() instanceof Bijou){
                this.butins.get(0).add(new Bijou());
            }else{
                this.butins.get(0).add(x);
            }
        } else if (!i && x != null) {
            if( x instanceof Passanger && ((Passanger) x).getBagage() instanceof Money) {
                if (((Passanger) x).getBagage().getValeur() == 0) {
                    return;
                } else {
                    this.butins.get(1).add(new Money(x.getValeur()));
                }

            }else if(x instanceof Passanger && ((Passanger) x).getBagage() instanceof Bijou){
                this.butins.get(1).add(new Bijou());
            }else{
                this.butins.get(1).add(x);
            }
        }
        notifyObservers();
    }
}





