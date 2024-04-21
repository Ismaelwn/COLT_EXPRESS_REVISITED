package Vue.interfaceG;

import Controller.Setup.ControllerJeu;
import Controller.Setup.Main;
import Modele.ButinJeu.Bijou;
import Modele.ButinJeu.Magot;
import Modele.ButinJeu.Money;
import Modele.ButinJeu.Passanger;
import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Entite;
import Modele.EntiteJeu.Marshall;
import Modele.Extra.Son;
import Modele.TrainJeu.Train;
import Modele.TrainJeu.Wagon;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// je vais devoir en premier lieu créé un constructeur qui initialise le jeu
// Puis apres la fenetre graphique je pourrai m'aider du Projet s1
public class fenetreJeu extends JPanel implements Observer {
    private JFrame frame;
    private Train train;
    private ArrayList<Bandit> bandits;
    private Marshall marshall;
    private static int height, width;
    private int WagonSizeH, WagonSizeW ;
    private int gapWagon ;
    private int epaisseur = 3;
    private int decalage;

    private static JButton action, haut, arriere, avant, bas, tirer, braquer, tireArriere, tireAvant, tireHaut, tireBas;

    private Color couleur  = Color.orange;
    private static JLabel infoetat, bourse, chrono, nb_balles;
    public static JTextArea chatTextArea;
    private static JScrollPane chatScroll;

    public static JTextArea chatTextArea2;
    private static JScrollPane chatScroll2;

    private JLabel selection;


    private static final ImageIcon locomotiveImage = new ImageIcon(fenetreJeu.class.getResource("/Modele/Extra/Images/locomotive1.png"));
    private static final ImageIcon wagonImage = new ImageIcon(fenetreJeu.class.getResource("/Modele/Extra/Images/wagon2.png"));
    private static final ImageIcon chronometre = new ImageIcon(fenetreJeu.class.getResource("/Modele/Extra/Images/chrono.png"));
    private static final ImageIcon money = new ImageIcon(fenetreJeu.class.getResource("/Modele/Extra/Images/money.png"));
    private static final ImageIcon balle = new ImageIcon(fenetreJeu.class.getResource("/Modele/Extra/Images/balle.png"));


    private Son music;
    public fenetreJeu(Train x){
        train = x;
        width = Main.width;
        height = Main.height;
        bandits = train.getBandits();
        marshall = train.getMarshall();
        music = new Son("src/Modele/Extra/Sons/musique_theme.wav");
        selection = new JLabel("Votre séléction");

        for(int i = 0; i < bandits.size(); i++){
            bandits.get(i).addObserver(this);
        }
        for (Wagon i : train.getWagons()){
            i.addObserver(this);
        }
        marshall.addObserver(this);

        WagonSizeW = (width-120)/Main.NB_WAGONS;
        WagonSizeH = 140; // 700/NB_WAGON
        gapWagon = 60/Main.NB_WAGONS;
        decalage = 30;

        setBackground(couleur);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        chatTextArea = new JTextArea();
        chatScroll = new JScrollPane(chatTextArea);
        chatTextArea.setEditable(false);
        chatTextArea.setLineWrap(true);

        action = new JButton("Action!");
        haut = new JButton("^");
        arriere = new JButton("<");
        avant = new JButton(">");
        bas = new JButton("v");
        tirer = new JButton("Tirer");
        tireArriere = new JButton("AR");
        tireAvant= new JButton("AV");
        tireBas= new JButton("B");
        tireHaut = new JButton("H");
        braquer = new JButton("Braquer");
        infoetat = new JLabel();
        bourse = new JLabel();
        chrono = new JLabel();
        nb_balles = new JLabel();



        chatTextArea = new JTextArea();
        chatScroll = new JScrollPane(chatTextArea);
        chatTextArea.setEditable(false);
        chatTextArea.setLineWrap(true);

        chatTextArea2 = new JTextArea();
        chatScroll2 = new JScrollPane(chatTextArea2);
        chatTextArea2.setEditable(false);
        chatTextArea2.setLineWrap(true);


        action.setBorderPainted(false);
        haut.setBorderPainted(false);
        arriere.setBorderPainted(false);
        avant.setBorderPainted(false);
        bas.setBorderPainted(false);
        tirer.setBorderPainted(false);
        braquer.setBorderPainted(false);
        tireArriere.setBorderPainted(false);
        tireAvant.setBorderPainted(false);
        tireBas.setBorderPainted(false);
        tireHaut.setBorderPainted(false);

        haut.setFocusable(false);
        bas.setFocusable(false);
        arriere.setFocusable(false);
        avant.setFocusable(false);
        action.setFocusable(false);
        tirer.setFocusable(false);
        braquer.setFocusable(false);
        tireArriere.setFocusable(false);
        tireAvant.setFocusable(false);
        tireHaut.setFocusable(false);
        tireBas.setFocusable(false);

        haut.setBounds(width/2-50,20+170, 50, 50); // AU CAS OU JE VEUX AUGMENTER LA TAILLE DE LA FENETRE CHOSE QUE JE REFUSE POUR LE MOMENT
        arriere.setBounds(width/2-100,70+170, 50, 50);
        avant.setBounds(width/2,70+170, 50, 50);
        bas.setBounds(width/2-50, 70+170, 50, 50);
        infoetat.setBounds(width/2-150,100, 350, 50);
        chrono.setBounds(width/2-185,100, 200, 50);
        bourse.setBounds(width/2+140,100, 200, 50);
        action.setBounds(width/2+100, 50+170, 100, 40);
        tirer.setBounds(width/2-250,40+170 ,100, 30);
        braquer.setBounds(width/2-250,80+170 ,100, 30);
        tireHaut.setBounds(width/2-360, 190, 35,35);
        tireBas.setBounds(width/2-360, 190+70, 35,35);
        tireArriere.setBounds(width/2-360-35, 190+35, 35,35);
        tireAvant.setBounds(width/2-360+35, 190+35, 35,35);
        nb_balles.setBounds(width/2,130, 200,50);
        chatScroll.setBounds(width/2+260,0, 250,250);
        chatScroll2.setBounds(100,0, 250,175);
        selection.setBounds(85+100, 175,175, 30);


        tireBas.setVisible(false);
        tireArriere.setVisible(false);
        tireAvant.setVisible(false);
        tireHaut.setVisible(false);

        add(action);
        add(arriere);
        add(avant);
        add(haut);
        add(bas);
        add(tirer);
        add(braquer);
        add(infoetat);
        add(bourse);
        add(chrono);
        add(tireArriere);
        add(tireAvant);
        add(tireBas);
        add(tireHaut);
        add(nb_balles);
        add(chatScroll);
        add(selection);
        add(chatScroll2);

        tireArriere.setText("<");
        tireHaut.setText("^");
        tireBas.setText("v");
        tireAvant.setText(">");
        JFrame frame = new JFrame("Colt Express - Jeu ");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage((new ImageIcon("src/Modele/Extra/Images/logoExpress.png")).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));// logo de mon jeu
        frame.setTitle("Colt Express");
        frame.setResizable(false);



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(width/2-215+50,110, 280, 30);
        if(ControllerJeu.peut)paintCarre(g);
        paintWagons(g);
    }

    public void paintCarre(Graphics g){
        g.drawRect(width/2-215,110, 50, 30); //pour le temps
        g.drawRect(width/2-10, 140, 70, 30);
        g.drawRect(width/2-215+50+280,110, 100, 30);
        g.drawImage(money.getImage(), width/2+180,110, 30, 30, null);
        g.drawImage(chronometre.getImage(), width/2-215,110, 30, 30, null);
        g.drawImage(balle.getImage(),width/2+25,140, 30,30,null);
    }

    public static void ajouterMessage(String message) {
        fenetreJeu.chatTextArea.append(message + "\n"); // Ajoute un message avec un retour à la ligne
        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength()); // Fait défiler jusqu'au bas du chat
    }

    public static void ajouterMessageCurrent(String message){
        fenetreJeu.chatTextArea2.append(message + "\n"); // Ajoute un message avec un retour à la ligne
        chatTextArea2.setCaretPosition(chatTextArea2.getDocument().getLength());
    }

    public static void effacerChat() {
        chatTextArea2.setText("");
    }

    public JScrollPane getChatScroll() {
        return chatScroll2;
    }

    public JLabel getSelection() {
        return selection;
    }

    public void paintEntites(Graphics g, Wagon x ) {
        for (int i = 0 ; i < train.sharethesame(x, true).size(); i++) {
            g.setColor(Color.blue);
            Entite z = train.sharethesame(x, true).get(i);
            if (x.getNumWag() != 0) {
                g.drawString(z.getNom(), width-(+WagonSizeW/2+decalage+WagonSizeW/24+x.getNumWag() * (WagonSizeW + gapWagon)), (i * 20) + 415);
            } else {
                g.drawString(z.getNom(), width-(+WagonSizeW/2+decalage+WagonSizeW/24+x.getNumWag() * (WagonSizeW + gapWagon)), (i*20) + 440);
            }
        }
        for (int i = 0 ; i < train.sharethesame(x, false).size(); i++) {
            g.setColor(Color.blue);
            Entite z = train.sharethesame(x, false).get(i);
            g.drawString(z.getNom(),width-(+WagonSizeW/2+decalage+WagonSizeW/24+x.getNumWag() * (WagonSizeW + gapWagon)), -(i*20) + 385);
        }
        g.setColor(Color.BLACK);

    }


    public void paintWagons(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(epaisseur));
        for (int i = 0; i < Main.NB_WAGONS; i++) { // Dessiner les wagons dans l'autre sens
            //g.drawRect(decalage + i * (WagonSizeW + gapWagon), 400, WagonSizeW, WagonSizeH);
            paintContenusWagons(g, train.getWagons()[i]);
            paintEntites(g, train.getWagons()[i]);
            // Dessiner la séparation entre les wagons
            if (i != 0) {
                g.drawRect(-gapWagon + decalage + i * (WagonSizeW + gapWagon), 530, gapWagon, 2);
            }
            if(i==Main.NB_WAGONS-1){
                g.drawImage(locomotiveImage.getImage(),decalage+i*(WagonSizeW+gapWagon), 370, WagonSizeW, WagonSizeH+80 , null);
            }else {
                g.drawImage(wagonImage.getImage(),decalage+i*(WagonSizeW+gapWagon), 370, WagonSizeW, WagonSizeH+80 , null);
            }

        }
    }

    public void paintContenusWagons(Graphics g, Wagon wagon) {
        String text = "";
        //int lambda = 0;
        for (int i = 0; i < (wagon.getButins().get(0)).size(); i++) {
            if (wagon.getButins().get(0).get(i) instanceof Magot) {
                text = "Magot";
            } else if (wagon.getButins().get(0).get(i) instanceof Bijou) {
                text = "Bijou";
            } else if (wagon.getButins().get(0).get(i) instanceof Money) {
                text = "Money";
            } else if (wagon.getButins().get(0).get(i) instanceof Passanger) {
                text = "Passager";
            }
            g.setColor(Color.BLACK);
            if (wagon.getNumWag() != 0) {
                g.drawString(text, width - (+WagonSizeW + decalage + WagonSizeW / 256 + wagon.getNumWag() * (WagonSizeW + gapWagon)), (i * 20) + 420);
            } else {
                g.drawString(text, width-(+WagonSizeW+decalage+WagonSizeW/256+wagon.getNumWag() * (WagonSizeW + gapWagon)), (i*20) + 440);
            }
        }
        text = "";

        for (int i = 0; i < (wagon.getButins().get(1)).size(); i++) {
            if (wagon.getButins().get(1).get(i) instanceof Magot) {
                text = "Magot";
            } else if (wagon.getButins().get(1).get(i) instanceof Bijou) {
                text = "Bijou";
            } else if (wagon.getButins().get(1).get(i) instanceof Money) {
                text = "Money";
            } else if (wagon.getButins().get(1).get(i) instanceof Passanger) {
                text = "Passager";
            }
            g.setColor(Color.BLACK);
            if (wagon.getNumWag() != 0) {
                g.drawString(text, width - (+WagonSizeW + decalage + WagonSizeW / 256 + wagon.getNumWag() * (WagonSizeW + gapWagon)), -(i * 20) + 385);
            } else {
                g.drawString(text, width-(+WagonSizeW+decalage+WagonSizeW/256+wagon.getNumWag() * (WagonSizeW + gapWagon)), -(i*20) + 385);
            }
        }
    }

    public void update() {
        this.repaint();
    }

    public void execute() {
        for (int i = 0; i < train.getBandits().size(); i++) {
            train.getBandits().get(i).execute();
        }
    }

    public JButton getTirer() {return tirer;}

    public JButton getAction() {return action;}
    public JButton getArriere() {return arriere;}
    public JButton getAvant() {return avant;}
    public JButton getHaut(){return haut;}

    public JButton getBas(){return bas;}
    public JButton getBraquer(){return braquer;}

    public JLabel getInfoetat() {return infoetat;}

    public JLabel getBourse(){return bourse;}

    public JLabel getChrono(){return chrono;}

    public ArrayList<Bandit> getBandits() {return bandits;}

    public Train getTrain() {return train;}

    public Marshall getMarshall() {return marshall;}

    public JButton getTireArriere() {
        return tireArriere;
    }

    public JButton getTireAvant() {
        return tireAvant;
    }

    public JButton getTireBas() {
        return tireBas;
    }

    public JButton getTireHaut() {
        return tireHaut;
    }

    public JLabel getNb_balles() {return nb_balles;}
    public static JTextArea getChatTextArea(){return chatTextArea;}


    public Son getMusic() {
        return music;
    }

    public void ecranFinal() {
        frame.remove(this);
        JLabel label;
        label = new JLabel("<html> PARTIE FINIE ! <br> Classement <br> de votre Partie <br> "+ecritClassementBandit()+"  </html>");
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    public String ecritClassementBandit(){
        String txt ="";
        ArrayList<Bandit>tmp = (ArrayList<Bandit>)bandits.clone();
        txt = "<html>";
        int i = 0;
        while(i < Main.NB_BANDITS){
            Bandit max = train.max(tmp);
            txt += (i+1) + " - "+max.getNom()+" "+max.Calcul()+"$$$$<br>";
            tmp.remove(max);
            i++;
        }
        txt += "</html>";
        return txt;

    }



}




