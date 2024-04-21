package Modele.TrainJeu;

import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Entite;
import Modele.EntiteJeu.Marshall;
import java.util.ArrayList;

import static Controller.Setup.Main.NB_BANDITS;
import static Controller.Setup.Main.NB_WAGONS;


public class Train extends Observable {
    private Wagon[] train;
    private ArrayList<Bandit> bandits;
    private Marshall marshall;



    public Train(int x){
        if( x < 2 )throw new IllegalArgumentException("le nombre de wagons minimales est de 2");
        bandits = new ArrayList<>();
        train = new Wagon[x];

        for(int i = 0; i < x;i++){
            Wagon y = new Wagon(i);
            train[i] = y;
        }
        Bandit J;
        for(int i = 0; i < NB_BANDITS; i++){
            J = new Bandit(i,"Bandit"+(i+1),NB_WAGONS-1);
            this.add(J);
        }
        marshall = new Marshall();


    }
    public void add(Bandit x){
        this.bandits.add(x);
    }

    public void remove(Bandit x){this.bandits.remove(x);}

    public Marshall getMarshall() {
        return marshall;
    }

    public Wagon[] getWagons(){
        return train;
    }

    public ArrayList<Bandit> getBandits() {
        return bandits;
    }

    public ArrayList<Entite> sharethesame(Wagon wagon, boolean in){
        ArrayList<Entite> rtrn = new ArrayList<>(0);
        if(marshall.getNum_wag() == wagon.getNumWag() && marshall.getIn() == in){
            rtrn.add(marshall);
        }
        for( int i = 0; i < bandits.size(); i++){
            if(bandits.get(i).getNum_wag() == wagon.getNumWag() && bandits.get(i).getIn() == in ){
                rtrn.add(bandits.get(i));
            }
        }

        return rtrn;
    }

     public int getNext(Bandit x){
        int tmp = 0; // SA SERT JUSTE A EVITER LERREUR DINITIALISRION IL Y AURA TOUJOURS UN RESULTAT
        for(int i = 0; i< bandits.size();i++){
            if(x.getIdJoueur() == bandits.get(i).getIdJoueur()){
                tmp = i;
            }
        }
        if(tmp+1>bandits.size()-1 ){
            return 0;
        }else{
            return tmp+1;
        }
     }

    public Bandit max(ArrayList<Bandit>bandit){
        int max = 0;
        Bandit tmp = null;
        for(Bandit x : bandit){
            if(x.Calcul() >= max){
                max = x.Calcul();
                tmp = x;
            }
        }
        return tmp;
    }



    }








