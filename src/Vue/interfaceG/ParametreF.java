package Vue.interfaceG;

import Controller.Setup.ControllerMenu;
import Controller.Setup.Main;
import Modele.EntiteJeu.Marshall;
import Modele.Extra.Son;

import javax.swing.*;
import java.awt.*;


public class ParametreF extends  JPanel {

    // CHANGER LE NOMBRE D'ACTION    OK
    // CHANGER LE NOMBRE DE BANDITS  OK
    // CHANGER LE NOMBRE DE WAGONS   OK
    // CHANGER LE TEMPS AVANT DE SKIP LE CHOIX D'ACTION OK
    // CHANGER LA RESOLUTION DU JEU  OK
    // CHANGER LA NERVOSITE DU MARSHALL OK


    private JFrame frame;
    private int width, height;
    private JButton exit;
    private JButton bandit1, bandit2, bandit3, bandit4;
    private JButton action1, action2, action3, action4, action5;
    private ControllerMenu controlmenu;
    private JLabel VolumeLabel, banditsLabel, actionsLabel, wagonLabel;
    private JSlider wagonSlider, timeSlider, nervositeMarshallSlider, balleSlider, VolumeSlider, roundsSlider ;
    private JLabel timeLabel, nervositeMarshallLabel, balleLabel, roundsLabel;


    public ParametreF(ControllerMenu t)  {
        width = 400;
        height = 800;
        exit = new JButton();
        exit.setIcon(new ImageIcon(new ImageIcon("src/Modele/Extra/Images/croix.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        setBackground(Color.white);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        controlmenu = t;

        exit.setFocusable(false);
        exit.setOpaque(false); // Rendre le bouton transparent
        exit.setContentAreaFilled(false);
        exit.setBounds(width-40, 0, 40, 40);
        exit.setBorderPainted(false);
        add(exit);

        bandit1 = new JButton("1");
        bandit1.setBounds(width/3-75, 120, 50, 30);
        add(bandit1);

        bandit2 = new JButton("2");
        bandit2.setBounds(width/3, 120, 50, 30);
        add(bandit2);

        bandit3 = new JButton("3");
        bandit3.setBounds(width/3+75, 120, 50, 30);
        add(bandit3);

        bandit4 = new JButton("4");
        bandit4.setBounds(width/3+150, 120, 50, 30);
        add(bandit4);

        // Boutons pour chaque action
        action1 = new JButton("1");
        action1.setBounds(width/3-100, 190, 50, 30);
        add(action1);

        action2 = new JButton("2");
        action2.setBounds(width/3-25, 190, 50, 30);
        add(action2);

        action3 = new JButton("3");
        action3.setBounds(width/3+50, 190, 50, 30);
        add(action3);

        action4 = new JButton("4");
        action4.setBounds(width/3+125, 190, 50, 30);
        add(action4);

        action5 = new JButton("5");
        action5.setBounds(width/3+200, 190, 50, 30);
        add(action5);

        // Labels
        VolumeLabel = new JLabel("Volume du jeu :");
        VolumeLabel.setBounds(width/2-65, 20, 150, 20);
        add(VolumeLabel);

        banditsLabel = new JLabel("Nombre de bandit :");
        banditsLabel.setBounds(width/2-75, 90, 150, 20);
        add(banditsLabel);

        actionsLabel = new JLabel("Nombre d'Actions maximum :");
        actionsLabel.setBounds(width/2-100, 160, 200, 20);
        add(actionsLabel);

        timeLabel = new JLabel(" Temps pour mettre en place les actions :");
        timeLabel.setBounds(width/2-125, 230, 250, 20);
        add(timeLabel);

        wagonLabel = new JLabel(" Nombre de wagons :");
        wagonLabel.setBounds(width/2-75, 320, 250, 20);
        add(wagonLabel);

        nervositeMarshallLabel = new JLabel(" Nervosité du Marshall :");
        nervositeMarshallLabel.setBounds(width/2-75, 400, 250, 20);
        add(nervositeMarshallLabel);

        balleLabel = new JLabel(" Nombre de balles  :");
        balleLabel.setBounds(width/2-75, 470, 250, 20);
        add(balleLabel);

        roundsLabel = new JLabel(" Nombre de Rounds  :");
        roundsLabel.setBounds(width/2-75, 540, 250, 20);
        add(roundsLabel);

        roundsSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, Main.NB_ROUNDS);
        roundsSlider.setBounds(width/2-200, 560, 400, 50);
        roundsSlider.setMajorTickSpacing(5);
        roundsSlider.setPaintTicks(true);
        roundsSlider.setPaintLabels(true);
        roundsSlider.setSnapToTicks(true);
        add(roundsSlider);

        balleSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, Main.NB_BULLETS);
        balleSlider.setBounds(width/2-85, 490, 150, 50);
        balleSlider.setMajorTickSpacing(1);
        balleSlider.setPaintTicks(true);
        balleSlider.setPaintLabels(true);
        balleSlider.setSnapToTicks(true);
        add(balleSlider);

        wagonSlider  = new JSlider(JSlider.HORIZONTAL, 2, 10, Main.NB_WAGONS);
        wagonSlider.setBounds(width/2-85, 345, 150, 50);
        wagonSlider.setMajorTickSpacing(1);
        wagonSlider.setPaintTicks(true);
        wagonSlider.setPaintLabels(true);
        wagonSlider.setSnapToTicks(true);
        add(wagonSlider);

        nervositeMarshallSlider  = new JSlider(JSlider.HORIZONTAL, 0, 10, (int)(Main.NERVOSITE_MARSHALL*10)); // Min: 1, Max: 10, Valeur initiale: 5
        nervositeMarshallSlider.setBounds(width/2-180, 420, 350, 50);
        nervositeMarshallSlider.setMajorTickSpacing(1);
        nervositeMarshallSlider.setPaintTicks(true);
        nervositeMarshallSlider.setPaintLabels(true);
        nervositeMarshallSlider.setSnapToTicks(true);                                                                                                                     //wagonSlider.addChangeListener(e -> {
        add(nervositeMarshallSlider);

        timeSlider  = new JSlider(JSlider.HORIZONTAL, 5, 60, Main.time); // Min: 1, Max: 10, Valeur initiale: 5
        timeSlider.setBounds(width/2-180, 260, 350, 50);
        timeSlider.setMajorTickSpacing(5);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeSlider.setSnapToTicks(true);
        add(timeSlider);

        VolumeSlider  = new JSlider(JSlider.HORIZONTAL, -80, 6, Main.volume);
        VolumeSlider.setBounds(width/2-180, 40, 350, 50);
        add(VolumeSlider);




        JFrame frame = new JFrame("Colt Express");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        frame.setIconImage((new ImageIcon("src/Modele/Extra/Images/logoExpress.png")).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));// logo de mon jeu
        frame.setTitle("Colt Express -Parametres");
        frame.setResizable(false);


    }



    public JButton getExit() {
        return exit;
    }

    public void dispose(){
        frame.dispose();
    }


    // Getters pour les boutons de sélection de bandit
    public JButton getBandit1() {
        return bandit1;
    }

    public JButton getBandit2() {
        return bandit2;
    }

    public JButton getBandit3() {
        return bandit3;
    }

    public JButton getBandit4() {
        return bandit4;
    }

    // Getters pour les boutons d'action
    public JButton getAction1() {
        return action1;
    }

    public JButton getAction2() {
        return action2;
    }

    public JButton getAction3() {
        return action3;
    }

    public JButton getAction4() {
        return action4;
    }

    public JButton getAction5() {
        return action5;
    }


    // Getters pour les sliders
    public JSlider getWagonSlider() {
        return wagonSlider;
    }

    public JSlider getTimeSlider() {
        return timeSlider;
    }

    public JSlider getNervositeMarshallSlider() {
        return nervositeMarshallSlider;
    }

    public JSlider getBalleSlider() {
        return balleSlider;
    }

    public JSlider getVolumeSlider() {
        return VolumeSlider;
    }

    public ControllerMenu getControlmenu() {
        return controlmenu;
    }

    public JSlider getRoundsSlider(){
        return roundsSlider;
    }
}


