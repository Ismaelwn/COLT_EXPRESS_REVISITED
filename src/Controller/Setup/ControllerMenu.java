package Controller.Setup;

import Vue.interfaceG.MenuStart;
import Vue.interfaceG.ParametreF;
import Modele.Extra.Son;
import Modele.TrainJeu.Train;
import Vue.interfaceG.fenetreJeu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu implements ActionListener {

    private JButton exitMenu, parametre, start_game;
    private MenuStart menu;
    private fenetreJeu jeu;



    public ControllerMenu(MenuStart z){
        exitMenu = z.getExit();
        start_game = z.getStart_game();
        parametre = z.getParametre();

        exitMenu.addActionListener(this);
        start_game.addActionListener(this);
        parametre.addActionListener(this);
        menu = z;
        this.jeu = jeu;

    }

    public MenuStart getMenu() {
        return menu;
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitMenu) {
            menu.dispose();
        }
           if(e.getSource() == start_game) {
                menu.dispose();
                Main.chargerParametres("src/Modele/parametre");
                menu.setJeu(new fenetreJeu(new Train(Main.NB_WAGONS)));
                menu.getJeu();
                //System.out.println(" NB_WAGONS= " + Main.NB_WAGONS+ " NB_ACTIONS= " + Main.NB_ACTIONS+ " NB_BANDITS " + Main.NB_BANDITS+" NB_BULLETS= " + Main.NB_BULLETS + " NERVOSITE_MARSHALL= " +Main.NERVOSITE_MARSHALL + " time= " + Main.time+" volume= " + Main.volume + " NB_ROUNDS "+Main.NB_ROUNDS);
                menu.setControllerJeu(new ControllerJeu(menu.getJeu()));
                menu.getControllerJeu().executeJeu();
            }
            if(e.getSource() == parametre ){
               ParametreF p = new ParametreF(this);
               ControllerParametre f = new ControllerParametre(p);
        }

    }

}
