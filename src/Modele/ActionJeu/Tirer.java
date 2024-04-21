package Modele.ActionJeu;

import Controller.Setup.Main;
import Modele.ButinJeu.Butin;
import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Direction;
import Modele.EntiteJeu.Entite;
import Modele.TrainJeu.Train;
import Modele.TrainJeu.Wagon;
import Vue.interfaceG.fenetreJeu;

import java.util.ArrayList;
import java.util.Random;

public class Tirer extends Action {
    private Train train;

    public String toString() {
        return (k.getNom()+" ( wagon "+k.getNum_wag()+" ) veut tirer dans la direction "+ d);
    }

    public Tirer(Bandit ki, Direction c, Train x) {
        super(ki, c);
        train = x;
    }

    public void executer() {
        if (k.getBalles() == 0) {
            fenetreJeu.ajouterMessage("  "+k.getNom() + " (Wagon " + k.getNom() + ") n'a plus de balles pour tirer sur quelqu'un");
        }
        ArrayList<Entite> tmp;
        Random x = new Random();
        int pt;
        Wagon current;
        switch (d) {
            case HAUT:
                current = train.getWagons()[k.getNum_wag()];
                tmp = train.sharethesame(current, false);
                tmp.remove(k);
                tmp.remove(train.getMarshall());
                if (tmp.size() == 0) {
                    k.perdBalles();
                    if (k.getIn())
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " + k.getNum_wag() + ") ) vers le toit dans le vent " + " (" + k.getBalles() + " Balles restantes)");
                    else
                        fenetreJeu.ajouterMessage("  "+k.getNom() + " (étant en Haut du wagon " + k.getNum_wag() + ") au niveau du toit le haut dans le vent " + " (" + k.getBalles() + " Balles restantes)");
                    return;
                } else {
                    int value = x.nextInt(tmp.size());
                    k.perdBalles();
                    if (k.getIn())
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " + k.getNum_wag() + ") a tiré vers le toit sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                    else
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon " + k.getNum_wag() + ") a tiré au niveau du toit sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                    Butin q = ((Bandit) tmp.get(value)).perdButin();
                    train.getWagons()[((Bandit) tmp.get(value)).getNum_wag()].addButin(q, ((Bandit) tmp.get(value)).getIn());
                }
                break;
            case BAS:
                current = train.getWagons()[k.getNum_wag()];
                tmp = train.sharethesame(current, true);
                tmp.remove(k);
                tmp.remove(train.getMarshall());
                if (tmp.size() == 0) {
                    k.perdBalles();
                    if (k.getIn()) fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " + k.getNum_wag() + ") a tiré dans le  wagon dans le vent " + " (" + k.getBalles() + " Balles restantes)");
                    else fenetreJeu.ajouterMessage("  "+k.getNom() + " (étant en Haut du wagon " + k.getNum_wag() + ") a tiré vers l'interieur du wagon dans le vent " + " (" + k.getBalles() + " Balles restantes)");
                    return;
                } else {
                    int value = x.nextInt(tmp.size());
                    k.perdBalles();
                    if (k.getIn()) fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " + k.getNum_wag() + ") a tiré dans le wagon sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                    else fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon " + k.getNum_wag() + ") a tiré vers l'interieur du wagon sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                    Butin q = ((Bandit) tmp.get(value)).perdButin();
                    train.getWagons()[((Bandit) tmp.get(value)).getNum_wag()].addButin(q, ((Bandit) tmp.get(value)).getIn());
                }
                break;
            case AVANT:
                if (k.getIn()) {
                    if (k.getNum_wag() - 1 < 0) {
                        k.perdBalles();
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " +( k.getNum_wag() ) + " a tiré dans le vent vers l'avant du wagon, hors train  "  + " (" + k.getBalles() + " Balles restantes)");
                    }else{
                        current = train.getWagons()[k.getNum_wag()-1];
                        k.perdBalles();
                        tmp = train.sharethesame(current, true);
                        tmp.remove(k);
                        tmp.remove(train.getMarshall());
                        if(tmp.size() == 0){
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon a tiré vers le wagon avant " +( k.getNum_wag() ) + "  dans le vent, hors train "  + " (" + k.getBalles() + " Balles restantes)");
                        }else {
                            int value = x.nextInt(tmp.size());
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " + k.getNum_wag() + ") a tiré à l'interieur du wagon avant" +( k.getNum_wag()-1 ) + "  sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                            Butin q = ((Bandit) tmp.get(value)).perdButin();
                            train.getWagons()[((Bandit) tmp.get(value)).getNum_wag()].addButin(q, ((Bandit) tmp.get(value)).getIn());
                        }

                    }

                }else{
                    if (k.getNum_wag() - 1 < 0) {
                        k.perdBalles();
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon "+ k.getNum_wag() + ") a tiré dans le vent vers l'avant du wagon, hors train  " + k.getNum_wag() + " (" + k.getBalles() + " Balles restantes)");
                    }else{
                        current = train.getWagons()[k.getNum_wag()-1];
                        k.perdBalles();
                        tmp = train.sharethesame(current, false);
                        tmp.remove(k);
                        tmp.remove(train.getMarshall());
                        if(tmp.size() == 0){
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon "+ k.getNum_wag() + ") a tiré sur le toit du wagon avant " +( k.getNum_wag()-1 ) + " dans le vent " + " (" + k.getBalles() + " Balles restantes)");
                        }else {
                            int value = x.nextInt(tmp.size());
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon " + k.getNum_wag() + ") a tiré sur le toit du wagon avant " +( k.getNum_wag()-1 ) + "  sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                            Butin q = ((Bandit) tmp.get(value)).perdButin();
                            train.getWagons()[((Bandit) tmp.get(value)).getNum_wag()].addButin(q, ((Bandit) tmp.get(value)).getIn());
                              }

                    }

                }
                break;

                case ARRIERE:

                if (k.getIn()) {
                    if (k.getNum_wag() + 1  > Main.NB_WAGONS-1) {
                        k.perdBalles();
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon a tiré dans le vent vers l'arriere du wagon, hors train  " + " (" + k.getBalles() + " Balles restantes)");
                    }else{
                        current = train.getWagons()[k.getNum_wag()+1];
                        k.perdBalles();
                        tmp = train.sharethesame(current, true);
                        tmp.remove(k);
                        tmp.remove(train.getMarshall());
                        if(tmp.size() == 0){
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon "+ k.getNum_wag() + " a tiré vers le wagon arriere "+ (k.getNum_wag()+1)+" dans le vent  (" + k.getBalles() + " Balles restantes)");
                        }else {
                            int value = x.nextInt(tmp.size());
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Bas du wagon " + k.getNum_wag() + ") a tiré à l'interieur du wagon arriere "+ (k.getNum_wag()+1)+" sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                            Butin q = ((Bandit) tmp.get(value)).perdButin();
                            train.getWagons()[((Bandit) tmp.get(value)).getNum_wag()].addButin(q, ((Bandit) tmp.get(value)).getIn());
                             }

                    }

                }else{
                    if (k.getNum_wag() + 1 > Main.NB_WAGONS-1) {
                        k.perdBalles();
                        fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon "+ k.getNum_wag() + ") a tiré dans le vent vers l'arriere du wagon  " + (k.getNum_wag()+1) + " (" + k.getBalles() + " Balles restantes)");
                    }else{
                        current = train.getWagons()[k.getNum_wag()+1];
                        k.perdBalles();
                        tmp = train.sharethesame(current, false);
                        tmp.remove(k);
                        tmp.remove(train.getMarshall());
                        if(tmp.size() == 0){
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon "+ k.getNum_wag() + ") a tiré sur le toit du wagon arriere " +( k.getNum_wag()+1 ) + " dans le vent (" + k.getBalles() + " Balles restantes)");
                        }else {
                            int value = x.nextInt(tmp.size());
                            fenetreJeu.ajouterMessage("  "+k.getNom() + "(étant en Haut du wagon " + k.getNum_wag() + ") a tiré sur le toit du wagon arriere " +( k.getNum_wag()+1 ) + "  sur " + tmp.get(value).getNom() + " (" + k.getBalles() + " Balles restantes)");
                            Butin q = ((Bandit) tmp.get(value)).perdButin();
                            train.getWagons()[((Bandit) tmp.get(value)).getNum_wag()].addButin(q, ((Bandit) tmp.get(value)).getIn());

                        }

                    }

                }
                break;

        }

        k.notifyObservers();
    }

    public Train gettrain() {
        return train;
    }
}



