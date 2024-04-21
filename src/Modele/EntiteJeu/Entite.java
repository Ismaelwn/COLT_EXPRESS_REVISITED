package Modele.EntiteJeu;

import Modele.TrainJeu.Observable;

public abstract class Entite extends Observable {
    protected String nom;
    protected int idJoueur; // surtout utile pour les assertions
    protected int num_wag;
    protected boolean in;
    public Entite(String a, int num, boolean x, int id){
        nom = a;
        num_wag = num;
        in = x;
        idJoueur = id;
    }



    public int getNum_wag() {
        return num_wag;
    }

    public String getNom() {
        return nom;
    }

    public boolean getIn() {
        return in;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIn(boolean x) {
         in = x;
    }

    public void setNum_wag(int i) {
        num_wag = i;
    }


    public int getNumWag() {
        return num_wag;
    }

    public boolean isIn() {
        return in;
    }

    public int getId() {
        return idJoueur;
    }
}

