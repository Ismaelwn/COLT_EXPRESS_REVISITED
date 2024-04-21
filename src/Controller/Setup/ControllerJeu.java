package Controller.Setup;

import Modele.ActionJeu.Action;
import Modele.ActionJeu.Braquer;
import Modele.ActionJeu.Deplace;
import Modele.ActionJeu.Tirer;
import Modele.ButinJeu.Butin;
import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Direction;
import Modele.EntiteJeu.Entite;
import Modele.EntiteJeu.Marshall;
import Modele.Extra.Son;
import Modele.TrainJeu.Train;
import Modele.TrainJeu.Wagon;
import Vue.interfaceG.fenetreJeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class ControllerJeu implements ActionListener {

    private boolean phaseAction, phasePlanification, visible;
    private Bandit actuel;
    public Bandit current;
    private fenetreJeu conway;
    private Train train;
    private ArrayList<Bandit> bandits;
    private Marshall marshall;
    private JButton haut, bas, arriere, avant, tirer, braquer, action, tireH, tireB, tireAV, tireAR;
    private JLabel bourse, infoetat, chrono, nbBalles;
    private Son music;
    public static boolean peut;

    private int nb_rounds;
    private final int nb_appuies;
    private int cmptr;



    public ControllerJeu(fenetreJeu q) {
        conway = q;
        visible = false;
        phaseAction = false;
        phasePlanification = true;
        music = q.getMusic();
        nb_appuies = Main.NB_BANDITS*Main.NB_ACTIONS;
        cmptr = 0;
        peut = true;

        train = conway.getTrain();
        bandits = conway.getBandits();
        actuel = train.getBandits().get(0);
        current = bandits.get(0);
        marshall = conway.getMarshall();
        action = conway.getAction();
        braquer = conway.getBraquer();
        bas = conway.getBas();
        haut = conway.getHaut();
        avant = conway.getAvant();
        arriere = conway.getArriere();
        tirer = conway.getTirer();
        tireH = conway.getTireHaut();
        tireAR = conway.getTireArriere();
        tireB = conway.getTireBas();
        tireAV = conway.getTireAvant();
        bourse = conway.getBourse();
        infoetat = conway.getInfoetat();
        chrono = conway.getChrono();
        nbBalles = conway.getNb_balles();
        nb_rounds = 0;


        action.addActionListener(this);
        tirer.addActionListener(this);
        braquer.addActionListener(this);
        avant.addActionListener(this);
        arriere.addActionListener(this);
        bas.addActionListener(this);
        haut.addActionListener(this);
        tireH.addActionListener(this);
        tireB.addActionListener(this);
        tireAR.addActionListener(this);
        tireAV.addActionListener(this);

        music.getVolumeControl().setValue(Main.volume);


    }


    public void executeJeu() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // Sinon ca ne marche pas hors de la main
        executorService.submit(() -> {
            music.jouer(true);
            while (nb_rounds<Main.NB_ROUNDS) {
                peut = true;
                cmptr = 0;
                for (int i = 0; i < bandits.size(); i++) {
                    long time = System.currentTimeMillis();
                    current = bandits.get(i);
                    current.notifyObservers();
                    infoetat.setText(" Phase de planification : Tour de " + bandits.get(i).getNom());
                    nbBalles.setText(bandits.get(i).getBalles()+"");
                    bourse.setText(current.Calcul()+"");
                    while (!bandits.get(i).estFull() && (System.currentTimeMillis() - time)/1000 < Main.time ){chrono.setText((System.currentTimeMillis()-time)/1000+""); System.out.println("Phase de planification");}; // Temps POUR AJOUTER LES ACTIONS
                    conway.effacerChat();
                    conway.getChatScroll().setVisible(true);
                    conway.getSelection().setVisible(true);


                }
                peut = false;
                conway.getChatScroll().setVisible(false);
                conway.getSelection().setVisible(false);
                phasePlanification = false;
                phaseAction = true;
                infoetat.setText("               Phase d'actions ");
                bourse.setText("");
                nbBalles.setText("");
                chrono.setText("");
                while ( cmptr < nb_appuies){
                    System.out.println("Phase d'actions");
                } // POUR LAISSER LE TEMPS D'EXECUTER LES ACTIONS
                phasePlanification = true;
                phaseAction = false;
                conway.getChatScroll().setVisible(true);
                conway.getSelection().setVisible(true);
                nb_rounds++;
                peut = true;

            }
            conway.ecranFinal();
        });
    }


    public boolean toutEstVide(){
        for(Bandit x : bandits){
            if(!x.toutEstNull()){
                return false;
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (phasePlanification) {
                Action AvanceJ = new Deplace(current, Direction.AVANT);
                Action ReculeJ = new Deplace(current, Direction.ARRIERE);
                Action HautJ = new Deplace(current, Direction.HAUT);
                Action BASJ = new Deplace(current, Direction.BAS);
                Action BraquerJ = new Braquer(current, null, train.getWagons()[current.getNum_wag()]);
                Action TirerArJ = new Tirer(current, Direction.ARRIERE, train);
                Action TirerAvJ = new Tirer(current, Direction.AVANT, train);
                Action TirerHJ = new Tirer(current, Direction.HAUT, train);
                Action TirerBJ = new Tirer(current, Direction.BAS, train);

                if (e.getSource() == arriere) {
                    visible = false;
                    tireB.setVisible(false);
                    tireAR.setVisible(false);
                    tireAV.setVisible(false);
                    tireH.setVisible(false);
                    current.ajouteAction(ReculeJ);
                }
                if (e.getSource() == avant) {
                    visible = false;
                    tireB.setVisible(false);
                    tireAR.setVisible(false);
                    tireAV.setVisible(false);
                    tireH.setVisible(false);
                    current.ajouteAction(AvanceJ);
                }
                if (e.getSource() == haut) {
                    visible = false;
                    tireB.setVisible(false);
                    tireAR.setVisible(false);
                    tireAV.setVisible(false);
                    tireH.setVisible(false);
                    current.ajouteAction(HautJ);
                }
                if (e.getSource() == bas) {
                    visible = false;
                    tireB.setVisible(false);
                    tireAR.setVisible(false);
                    tireAV.setVisible(false);
                    tireH.setVisible(false);
                    current.ajouteAction(BASJ);
                }
                if (e.getSource() == braquer) {
                    visible = false;
                    tireB.setVisible(false);
                    tireAR.setVisible(false);
                    tireAV.setVisible(false);
                    tireH.setVisible(false);
                    current.ajouteAction(BraquerJ);

                }
                if( e.getSource() == tirer){
                    visible = true;
                    tireB.setVisible(true);
                    tireAR.setVisible(true);
                    tireAV.setVisible(true);
                    tireH.setVisible(true);
                }
                if( visible && e.getSource() == tireAV){
                        current.ajouteAction(TirerAvJ);
                        visible = false;
                        tireB.setVisible(false);
                        tireAR.setVisible(false);
                        tireAV.setVisible(false);
                        tireH.setVisible(false);
                }
                if( visible && e.getSource() == tireAR){
                    current.ajouteAction(TirerArJ);
                    visible = false;
                    tireB.setVisible(false);
                    tireAR.setVisible(false);
                    tireAV.setVisible(false);
                    tireH.setVisible(false);
                }
                    if(visible && e.getSource() == tireB){
                        current.ajouteAction(TirerBJ);
                        visible = false;
                        tireB.setVisible(false);
                        tireAR.setVisible(false);
                        tireAV.setVisible(false);
                        tireH.setVisible(false);
                    }
                    if(visible && e.getSource() == tireH){
                        current.ajouteAction(TirerHJ);
                        visible = false;
                        tireB.setVisible(false);
                        tireAR.setVisible(false);
                        tireAV.setVisible(false);
                        tireH.setVisible(false);
                    }
            } else if( phaseAction ) {
                if (e.getSource() == action) {
                    if (actuel.getIdJoueur()==0) {
                        marshall.DeplaceMarshall(train);
                        this.monterSurToit(train, marshall);
                    }
                    actuel.execute();
                    actuel = train.getBandits().get(train.getNext(actuel));
                    cmptr++;
                    this.monterSurToit(train, marshall);

                }


            }
        }

    public void monterSurToit(Train train, Marshall marshall) {
        Wagon wagonMarshall = train.getWagons()[marshall.getNum_wag()];
        ArrayList<Entite> entitesDansWagon = train.sharethesame(wagonMarshall, true);
        entitesDansWagon.remove(marshall);
        for (Entite entite : entitesDansWagon) {
            if (entite instanceof Bandit) {
                Bandit bandit = (Bandit) entite;
                fenetreJeu.ajouterMessage("  "+bandit.getNom() + " a croisé le Marshall et est monté sur le toit ! : "+bandit.getNum_wag());
                Butin q = bandit.perdButin();
                bourse.setText(current.Calcul()+" $$$ ");
                train.getWagons()[bandit.getNum_wag()].addButin(q,bandit.getIn());
                bandit.setIn(false); // Met le bandit hors du wagon
                bandit.notifyObservers();
            }
        }

        wagonMarshall.notifyObservers();
    }

}
