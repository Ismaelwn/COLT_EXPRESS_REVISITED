package Modele.EntiteJeu;

import Controller.Setup.Main;
import Modele.TrainJeu.Train;
import Vue.interfaceG.fenetreJeu;

import java.util.Random;

public class Marshall extends Entite{
    public Marshall(){
        super("Mrshl", 0, true, Main.NB_BANDITS);
    }

    public void DeplaceMarshall(Train z){
        Random x = new Random();
        if(Main.NERVOSITE_MARSHALL == 0) {
            fenetreJeu.ajouterMessage("  " + "le marshall n'a pas changé de wagon ce tour ci, Wagon " + this.num_wag);
            return;
        }
        int valeur = x.nextInt(100);
        if(valeur < ((Main.NERVOSITE_MARSHALL)/2 )*100 && num_wag > 0){
            this.num_wag--;
            fenetreJeu.ajouterMessage("  "+"le marshall est passé du wagon " +(this.num_wag+1) + " au wagon " + this.num_wag);
        }else if(((Main.NERVOSITE_MARSHALL)/2) *100 <= valeur && valeur < (Main.NERVOSITE_MARSHALL)*100 && num_wag < Main.NB_WAGONS -1){
            this.num_wag++;
            fenetreJeu.ajouterMessage("  "+"le marshall est passé du wagon " +(this.num_wag-1) + " au wagon " + this.num_wag);
        }else {
            fenetreJeu.ajouterMessage("  "+"le marshall n'a pas changé de wagon ce tour ci, Wagon "+this.num_wag);
        }

        this.notifyObservers();
    }
}
