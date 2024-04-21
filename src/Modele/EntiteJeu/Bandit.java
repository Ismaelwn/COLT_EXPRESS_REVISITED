package Modele.EntiteJeu;

import Modele.ButinJeu.Butin;
import Controller.Setup.Main;
import Modele.ActionJeu.*;
import Vue.interfaceG.fenetreJeu;

import java.util.ArrayList;
import java.util.Random;

public class Bandit extends Entite{
    //private int idJoueur; // numero du joueur, < au nombre de joueurs

    private ArrayList<Butin> butin; // plus simple de stocker sous forme d'un ensemble

    private Action[] actions;
    private int balles;

    // private int HP; // Pour la suite

    public Bandit(int id, String nom, int wag){
        super(nom, wag, false, id);
        butin = new ArrayList<>(0);
        actions = new Action[Main.NB_ACTIONS];
        balles = Main.NB_BULLETS;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public boolean estFull(){ // on enregistre "toutes les commandes tapées"
        for( Action x : actions ){ // et on garde que les NB_ACTIONS derniere
            if( x == null ){// cette fonction sert a verifier que tout est a null
                //System.out.println(x);
                return false;
            }
        }
        return true;
    }

    public int premier_vide(){
        for(int i = 0 ; i < actions.length; i++){
            if( actions[i] == null){
                //System.out.println(i);
                return i;
            }
        }
        return 0;
    }

    public void ajouteAction(Action x){
        if(!estFull()){
            actions[this.premier_vide()] = x;
            fenetreJeu.ajouterMessageCurrent(x.toString());
            //System.out.println(this.premier_vide());
        }else{
            for(int i = 1; i < actions.length-1;i++){
                actions[i-1] = actions[i];
            }
            actions[actions.length-1] = x;
        }
    }
    public boolean isIn() {
        return in;
    }

    public boolean toutEstNull(){
        for(Action x : actions){
            if( x != null ){
                return false;
            }
        }
        return true;
    }


    public void execute() {
        if (actions.length > 0 && actions[0]!=null) {
            actions[0].executer();
            for(int i = 0; i < actions.length-1;i++){
                actions[i] = actions[i+1];
            }
            actions[actions.length-1] = null;
            notifyObservers();
        }else{
            fenetreJeu.ajouterMessage(this.getNom()+"(wagon "+this.getNum_wag()+") n'a pas joué ce tour ");
        }
    }


    public Action[] getActions() {
        return actions;
    }

    public Action[] Getactions() {
        return actions;
    }


    public boolean getIn() {
        return in;
    }

    public void setIn(boolean b) {
        in = b;
    }

    public int Calcul(){
        notifyObservers();
        int rtrn = 0;
        for(Butin x : butin){
            rtrn += x.getValeur();
        }
        return rtrn;
    }
    public void ajouteButin( Butin x ){
        if(x != null ) butin.add(x);
        notifyObservers();
    }

    public void perdBalles(){
        if(balles>0) balles--;
        notifyObservers();
    }
    public int getBalles(){
        return balles;
    }

    public Butin perdButin(){
        Random tmp = new Random();
        Butin rtrn;
        if(butin.size() == 0  ) {
            rtrn = null;
            fenetreJeu.ajouterMessage("  "+this.getNom()+" (wagon "+this.getNum_wag()+") n'a rien perdu car il n'a rien sur lui "); // EN ATTENDAT DE DECIDER DU FAIT QUE SI LE JOUEUR N'A PLUS RIEN A PARIER IL EST ELIMINE DU JEU
        }else {
            int nxt = tmp.nextInt(butin.size());
            rtrn = butin.get(nxt);
            butin.remove(nxt);
            fenetreJeu.ajouterMessage("  "+this.getNom()+" (wagon "+this.getNum_wag()+") a perdu : "+rtrn.toString());
        }
        notifyObservers();
        return rtrn;
    }

    public ArrayList<Butin> getButins() {
        return butin;
    }


}

