package Vue.interfaceG;

import Controller.Setup.ControllerJeu;
import Controller.Setup.Main;
import Modele.Extra.Son;
import Modele.TrainJeu.Train;

import javax.swing.*;
import java.awt.*;

public class MenuStart extends JPanel {

    // LANCER JEU
    // MENU EN LUI MEME
    // PARAMETRE
    // QUITTER
    private JFrame frame;
    private int width, height;
    private Image image;

    private JButton exit, parametre, start_game;
    private fenetreJeu jeu;
    private ControllerJeu controllerJeu;
    private Son music;
    public MenuStart() {
        width = 1270 / 2;
        height = 620 / 2;
        image = new ImageIcon("src/Modele/Extra/Images/logoExpress.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        jeu = null;
        controllerJeu = null;
        exit = new JButton();
        parametre = new JButton(" Parametres ");
        start_game = new JButton(" Commencer la partie  ");

        exit.setIcon(new ImageIcon(new ImageIcon("src/Modele/Extra/Images/croix.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        exit.setBorderPainted(false);
        start_game.setBorderPainted(false);
        parametre.setBorderPainted(false);

        start_game.setFocusable(false);
        exit.setFocusable(false);
        parametre.setFocusable(false);

        exit.setOpaque(false); // Rendre le bouton transparent
        exit.setContentAreaFilled(false);

        exit.setBounds(width-40, 0, 40, 40);
        start_game.setBounds(width/2-90, height/2+25, 180, 27);
        parametre.setBounds(width/2-60, height/2+60, 120, 27);

        add(exit);
        add(start_game);
        add(parametre);

        JFrame frame = new JFrame("Colt Express");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage((new ImageIcon("src/Modele/Extra/Images/logoExpress.png")).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));// logo de mon jeu
        frame.setTitle("Colt Express - Menu");
        frame.setResizable(false);
        this.repaint();
        music = new Son("src/Modele/Extra/Sons/duel_western.wav");
        music.getVolumeControl().setValue(Main.volume);
        music.jouer(true);
    }

    public void setJeu(fenetreJeu jeu) {
        this.jeu = jeu;
    }

    public Son getMusic() {
        return music;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getParametre() {
        return parametre;
    }

    public JButton getStart_game() {
        return start_game;
    }

    public void dispose(){
        frame.dispose();
        music.arreter();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public fenetreJeu getJeu() {
        return jeu;
    }

    public void setControllerJeu(ControllerJeu x) {
        controllerJeu = x;
    }

    public ControllerJeu getControllerJeu() {
        return controllerJeu;
    }
}

