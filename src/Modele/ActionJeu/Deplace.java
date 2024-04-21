package Modele.ActionJeu;

import Controller.Setup.Main;
import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Direction;
import Vue.interfaceG.fenetreJeu;

public class Deplace extends Action{

    public String toString() {
        return (k.getNom()+" ( wagon "+k.getNum_wag()+" ) veut aller dans la direction "+ d);
    }

    public Deplace(Bandit ki, Direction d) {
        super(ki, d);
    }
    public void executer(){
        switch(d){
            case ARRIERE : if(k.getNum_wag() < Main.NB_WAGONS-1){
                fenetreJeu.ajouterMessage("  "+k.getNom()+" a reculé d'un wagon "+ k.getNum_wag() +"->"+(k.getNum_wag()+1));
                k.setNum_wag(k.getNum_wag()+1);
            }else{
                if(k.getIn()) {
                    fenetreJeu.ajouterMessage("  "+k.getNom() + " est deja dans le dernier wagon");
                }else{
                    fenetreJeu.ajouterMessage("  "+k.getNom() + " est deja sur le dernier wagon");
                }
            }
                break;
            case AVANT: if(k.getNum_wag() > 0){
                fenetreJeu.ajouterMessage("  "+k.getNom()+" a avancé d'un wagon : " + k.getNum_wag() +"->"+(k.getNum_wag()-1));
                k.setNum_wag(k.getNum_wag()-1);
            }else{
                if(k.getIn()) {
                    fenetreJeu.ajouterMessage("  "+k.getNom() + " est deja dans le premier wagon");
                }else{
                    fenetreJeu.ajouterMessage("  "+k.getNom() + " est deja sur le premier wagon");
                }
            }
                break;
            case HAUT : if(k.getIn()){
                k.setIn(false);
                fenetreJeu.ajouterMessage("  "+k.getNom()+" est monté sur le toit du wagon : "+ k.getNum_wag());
            }else{
                fenetreJeu.ajouterMessage("  "+k.getNom() + " est deja sur le toit du wagon : "+ k.getNum_wag());
            }
                break;
            case BAS : if(!k.getIn()){
                k.setIn(true);
                fenetreJeu.ajouterMessage("  "+k.getNom()+" est rentré dans un wagon : "+ k.getNum_wag());
            }else{
                fenetreJeu.ajouterMessage("  "+k.getNom() + " est deja dans un wagon : "+ k.getNum_wag());
            }
                break;
        }
        k.notifyObservers();
    }
}



