package Modele.ActionJeu;

import Modele.ButinJeu.Butin;
import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Direction;
import Modele.TrainJeu.Wagon;
import Vue.interfaceG.fenetreJeu;

public class Braquer extends Action {
    private Wagon wagon;
    public Braquer(Bandit ki, Direction d, Wagon xd) {
        super(ki, d);
        wagon = xd;
    }

    public void executer() {
        Butin tmp = wagon.randomStuff(k.getIn());
        k.ajouteButin(tmp);
        if(tmp != null) fenetreJeu.ajouterMessage("  "+k.getNom()+" a obtenu la récompense "+ tmp.toString());
        else if(k.getIn())fenetreJeu.ajouterMessage("  "+k.getNom()+" ne peut rien recuperer dans le wagon "+ k.getNum_wag());
             else fenetreJeu.ajouterMessage("  "+k.getNom()+" ne peut rien recuperer sur le toit du wagon "+ k.getNum_wag());
        k.notifyObservers();
    }


    public String toString() {
        return (k.getNom()+" ( wagon "+k.getNum_wag()+" ) veut braquer dans la direction "+ d);
    }

    public Wagon getwagon() {
        return wagon;
    }
}
