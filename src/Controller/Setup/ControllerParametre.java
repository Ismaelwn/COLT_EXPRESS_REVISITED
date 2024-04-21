package Controller.Setup;

import Modele.Extra.Son;
import Vue.interfaceG.ParametreF;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerParametre implements ActionListener {
    private ParametreF parametreF;
    private JButton bandit1, bandit2, bandit3, bandit4;
    private JButton action1, action2, action3, action4, action5;
    private JSlider wagonSlider, timeSlider, nervositeMarshallSlider, balleSlider, VolumeSlider, roundsSlider ;
    private JButton exit;
    private ControllerMenu controllerMenu;

    public ControllerParametre(ParametreF parametre){
        parametreF = parametre;
        exit = parametreF.getExit();
        exit.addActionListener(this);
        controllerMenu = parametre.getControlmenu();
        bandit1 = parametreF.getBandit1();
        bandit2 = parametreF.getBandit2();
        bandit3 = parametreF.getBandit3();
        bandit4 = parametreF.getBandit4();
        action1 = parametreF.getAction1();
        action2 = parametreF.getAction2();
        action3 = parametreF.getAction3();
        action4 = parametreF.getAction4();
        action5 = parametreF.getAction5();
        VolumeSlider = parametreF.getVolumeSlider();
        wagonSlider = parametreF.getWagonSlider();
        timeSlider = parametreF.getTimeSlider();
        nervositeMarshallSlider = parametreF.getNervositeMarshallSlider();
        balleSlider = parametreF.getBalleSlider();
        roundsSlider = parametreF.getRoundsSlider();
        exit.addActionListener(this);
        bandit1.addActionListener(this);
        bandit2.addActionListener(this);
        bandit3.addActionListener(this);
        bandit4.addActionListener(this);
        action1.addActionListener(this);
        action2.addActionListener(this);
        action3.addActionListener(this);
        action4.addActionListener(this);
        action5.addActionListener(this);

        timeSlider.addChangeListener(x -> {
            Main.time = timeSlider.getValue();
            System.out.println("Temps pour mettre en place les actions : " + Main.time);
            Main.sauvegarderParametres("src/Modele/parametre");
        });
        wagonSlider.addChangeListener(x -> {
            Main.NB_WAGONS = wagonSlider.getValue();
            System.out.println("Nombre de wagons sélectionné : " + Main.NB_WAGONS);
            Main.sauvegarderParametres("src/Modele/parametre");
        });
        nervositeMarshallSlider.addChangeListener(x -> {
            Main.NERVOSITE_MARSHALL = nervositeMarshallSlider.getValue()/10;
            System.out.println("Nervosité du Marshall : " + Main.NERVOSITE_MARSHALL);
            Main.sauvegarderParametres("src/Modele/parametre");
        });

        roundsSlider.addChangeListener(x -> {
            Main.NB_ROUNDS = roundsSlider.getValue();
            System.out.println("Nombre de rounds : " + Main.NB_ROUNDS);
            Main.sauvegarderParametres("src/Modele/parametre");
        });

        wagonSlider.addChangeListener(x -> {
            Main.NB_WAGONS = wagonSlider.getValue();
            System.out.println("Nombre de wagons sélectionné : " + Main.NB_WAGONS);
            Main.sauvegarderParametres("src/Modele/parametre");
        });
        nervositeMarshallSlider.addChangeListener(x -> {
            Main.NERVOSITE_MARSHALL = nervositeMarshallSlider.getValue()/10;
            System.out.println("Nervosité du Marshall : " + Main.NERVOSITE_MARSHALL);
            Main.sauvegarderParametres("src/Modele/parametre");
        });

        roundsSlider.addChangeListener(x -> {
            Main.NB_ROUNDS = roundsSlider.getValue();
            System.out.println("Nombre de rounds : " + Main.NB_ROUNDS);
            Main.sauvegarderParametres("src/Modele/parametre");
        });

        VolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controllerMenu.getMenu().getMusic().getVolumeControl().setValue((float) VolumeSlider.getValue());
                Main.volume = VolumeSlider.getValue();
                System.out.println("Volume général du jeu : " + Main.volume);
                Main.sauvegarderParametres("src/Modele/parametre");
            }

        });

        balleSlider.addChangeListener(x -> {
            Main.NB_BULLETS = balleSlider.getValue();
            System.out.println("Nombre de balles sélectionné : " + Main.NB_BULLETS);
            Main.sauvegarderParametres("src/Modele/parametre");
        });

    }


    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == exit){
            parametreF.dispose();
            Main.sauvegarderParametres("src/Modele/parametre");

        }

        if(e.getSource() == bandit1){
            Main.NB_BANDITS = 1;
            System.out.println("Nombre de bandits sélectionné : 1");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == bandit2){
            Main.NB_BANDITS = 2;
            System.out.println("Nombre de bandits sélectionné : 2");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == bandit3){
            Main.NB_BANDITS = 3;
            System.out.println("Nombre de bandits sélectionné : 3");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == bandit4){
            Main.NB_BANDITS = 4;
            System.out.println("Nombre de bandits sélectionné : 4");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == action1){
            Main.NB_ACTIONS = 1;
            System.out.println("Nombre d'actions sélectionné : 1");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == action2){
            Main.NB_ACTIONS = 2;
            System.out.println("Nombre d'actions sélectionné : 2");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == action3){
            Main.NB_ACTIONS = 3;
            System.out.println("Nombre d'actions sélectionné : 3");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == action4){
            Main.NB_ACTIONS = 4;
            System.out.println("Nombre d'actions sélectionné : 4");
            Main.sauvegarderParametres("src/Modele/parametre");
        }
        if(e.getSource() == action5){
            Main.NB_ACTIONS = 5;
            System.out.println("Nombre d'actions sélectionné : 5");
            Main.sauvegarderParametres("src/Modele/parametre");
        }

    }


}
