import Controller.Setup.Main;
import Modele.ActionJeu.Action;
import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Direction;
import Modele.EntiteJeu.*;
import Modele.EntiteJeu.Direction;
import Modele.Extra.Son;
import Modele.TrainJeu.Train;
import Modele.ActionJeu.*;
import Modele.ButinJeu.*;
import Modele.TrainJeu.Wagon;
import Vue.interfaceG.fenetreJeu;
import org.junit.Test;
import org.junit.Before;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class TestClass {

    Train chuchu1;
    Bandit j1;
    Bandit j2;
    Bandit j3;


    @Before
    public void CreeTest() {
        Main.chargerParametres("src/parametreForTest");
        fenetreJeu.chatTextArea = new JTextArea();
        fenetreJeu.chatTextArea2 = new JTextArea();
        fenetreJeu.ajouterMessage("");
        fenetreJeu.ajouterMessageCurrent("");
        chuchu1 = new Train(Main.NB_WAGONS);
        j1 = chuchu1.getBandits().get(0);
        j2 = chuchu1.getBandits().get(1);
        j3 = chuchu1.getBandits().get(2);


    }

    // Test des fonctions

    @Test
    public void testPerdBalles() {
        j1.perdBalles();
        assertEquals(j1.getBalles(), 9);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 8);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 7);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 6);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 5);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 4);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 3);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 2);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 1);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 0);
        j1.perdBalles();
        assertEquals(j1.getBalles(), 0);

    }

    @Test
    public void testAjouteButins() {
        this.CreeTest();
        j1.ajouteButin(null);
        assertEquals(j1.getButins().size(), 0);
        j1.ajouteButin(new Money());
        assertEquals(j1.getButins().size(), 1);
    }

    @Test
    public void testCalcul() {
        j1.ajouteButin(new Money(400));
        j1.ajouteButin(new Money(250));
        j1.ajouteButin(new Money(50));
        assertEquals(j1.Calcul(), 700);
        assertEquals(j2.Calcul(), 0);
        assertEquals(j1.getButins().size(), 3);
    }

    @Test
    public void testPerdButins() {
        j1.ajouteButin(new Money(400));
        j1.ajouteButin(new Money(250));
        j1.ajouteButin(new Money(50));
        assertEquals(j1.getButins().size(), 3);
        j1.perdButin();
        assertEquals(j1.getButins().size(), 2);
        j1.perdButin();
        assertEquals(j1.getButins().size(), 1);
        j1.perdButin();
        assertEquals(j1.getButins().size(), 0);
    }

    @Test
    public void testAjouteActions() {
        int premiervidetourdavant = j1.premier_vide();
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        assertNotEquals(premiervidetourdavant, j1.premier_vide());
        premiervidetourdavant = j1.premier_vide();
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        assertNotEquals(premiervidetourdavant, j1.premier_vide());

    }

    @Test
    public void testPremierVide() {
        assertEquals(j1.premier_vide(), 0);
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        assertEquals(j1.premier_vide(), 1);
        j1.ajouteAction(new Deplace(j1, Direction.AVANT));
        assertEquals(j1.premier_vide(), 0);

    }


    @Test
    public void testExecute() {
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        int prev = j1.getNum_wag();
        j1.execute();
        assertEquals(j1.getNum_wag(), prev);
    }


    @Test
    public void testToutEstNul() {
        assertEquals(j1.toutEstNull(), true);
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        assertEquals(j1.toutEstNull(), false);
        j1.execute();
        assertEquals(j1.toutEstNull(), true);
    }

    @Test
    public void testSetin() {
        assertEquals(j1.getIn(), false);
        j1.setIn(true);
        assertEquals(j1.getIn(), true);
    }

    @Test
    public void testSetNum_wag() {
        int prev = j1.getNum_wag();
        j1.setNum_wag(0);
        System.out.println(j1.getNum_wag());
        assertNotEquals(j1.getNum_wag(), prev);
        assertEquals(j1.getNum_wag(), 0);
    }

    @Test
    public void testDeplaceMarshall() {
        int prev = chuchu1.getMarshall().getNum_wag();
        chuchu1.getMarshall().setNum_wag(chuchu1.getWagons().length - 1);
        chuchu1.getMarshall().DeplaceMarshall(chuchu1);
        assertNotEquals(chuchu1.getMarshall().getNum_wag(), prev);
        prev = chuchu1.getMarshall().getNum_wag();


    }


    @Test
    public void testShareTheSame() {
        // Créer un train avec un wagon et des entités
        Train train = new Train(5);
        Bandit bandit1 = train.getBandits().get(0); // Supposons qu'un bandit est créé
        Bandit bandit2 = train.getBandits().get(1); // Supposons qu'un autre bandit est cré


        // Définir le wagon et l'état d'être à l'intérieur ou à l'extérieur
        bandit1.setNum_wag(0);
        chuchu1.getMarshall().setIn(true);
        bandit2.setNum_wag(0);
        bandit2.setIn(true);


        // Tester avec les entités à l'intérieur du wagon
        ArrayList<Entite> entitesInterieures = train.sharethesame(chuchu1.getWagons()[0], true);
        assertEquals(2, entitesInterieures.size()); // Marshall et Bandit 2 doivent être dans le wagon
        //assertTrue(entitesInterieures.contains(chuchu1.getMarshall())); // Marshall doit être présent
        assertTrue(entitesInterieures.contains(bandit2)); // Bandit 2 doit être présent
        assertFalse(entitesInterieures.contains(bandit1)); // Bandit 1 ne doit pas être présent

        // Tester avec les entités à l'extérieur du wagon
        ArrayList<Entite> entitesExterieures = train.sharethesame(chuchu1.getWagons()[0], false);
        assertEquals(1, entitesExterieures.size()); // Seul Bandit 1 doit être à l'extérieur du wagon
        assertFalse(entitesExterieures.contains(chuchu1.getMarshall())); // Marshall ne doit pas être présent
        assertFalse(entitesExterieures.contains(bandit2)); // Bandit 2 ne doit pas être présent
        assertTrue(entitesExterieures.contains(bandit1)); // Bandit 1 doit être présent

        bandit1.setNum_wag(1);
        bandit1.setIn(true);
        bandit2.setNum_wag(0);
        bandit2.setIn(true);

        // Tester avec les entités à l'intérieur du wagon 1
        entitesInterieures = train.sharethesame(train.getWagons()[1], true);
        assertEquals(1, entitesInterieures.size()); // Seul Bandit 1 doit être dans le wagon 1
        assertTrue(entitesInterieures.contains(bandit1)); // Bandit 1 doit être présent dans le wagon 1
        assertFalse(entitesInterieures.contains(bandit2)); // Bandit 2 ne doit pas être présent dans le wagon 1

        // Tester avec les entités à l'extérieur du wagon 1
        entitesExterieures = train.sharethesame(train.getWagons()[1], false);
        assertEquals(0, entitesExterieures.size()); // Aucune entité ne doit être à l'extérieur du wagon 1
        assertFalse(entitesExterieures.contains(bandit1)); // Bandit 1 ne doit pas être présent à l'extérieur du wagon 1
        assertFalse(entitesExterieures.contains(bandit2)); // Bandit 2 ne doit pas être présent à l'extérieur du wagon 1


    }

    @Test
    public void testGetNext() {
        ArrayList<Bandit> x = chuchu1.getBandits();
        assertEquals(chuchu1.getNext(j1), 1);
        assertEquals(chuchu1.getNext(j2), 2);
        assertEquals(chuchu1.getNext(j3), 0);
    }

    @Test
    public void testAddButin() {
        Wagon tmpw = chuchu1.getWagons()[0];
        int tmp = chuchu1.getWagons()[0].getButins().get(0).size();
        tmpw.addButin(new Money(), true);
        assertEquals(chuchu1.getWagons()[0].getButins().get(0).size(), tmp + 1);
        tmpw.addButin(new Money(), true);
        assertEquals(chuchu1.getWagons()[0].getButins().get(0).size(), tmp + 2);

    }

    @Test
    public void testRandomStuff() {
        Wagon tmpw = new Wagon();
        Money x1 = new Money(30);
        Money x2 = new Money(15);
        tmpw.addButin(x1, true);
        tmpw.addButin(x2, true);
        int size = tmpw.getButins().get(0).size();
        Butin x = tmpw.randomStuff(true);
        assertTrue(x.getValeur() == x1.getValeur() || x.getValeur() == x2.getValeur());
        assertEquals(tmpw.getButins().get(0).size(), size - 1);
    }

    @Test
    public void testBanditsDeplacement1() {
        int tmp;
        tmp = j1.getNum_wag();
        j1.ajouteAction(new Deplace(j1, Direction.AVANT));
        j1.execute();
        assertEquals(j1.getNum_wag(), tmp - 1);

        tmp = j1.getNum_wag();
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        j1.execute();
        assertEquals(j1.getNum_wag(), tmp + 1);

        j1.ajouteAction(new Deplace(j1, Direction.BAS));
        j1.execute();
        assertEquals(j1.getIn(), true);

        j1.ajouteAction(new Deplace(j1, Direction.HAUT));
        j1.execute();
        assertEquals(j1.getIn(), false);
    }

    @Test
    public void testBanditsDeplacement2() {
        int tmp;
        j1.setNum_wag(0);
        tmp = j1.getNum_wag();
        j1.ajouteAction(new Deplace(j1, Direction.AVANT));
        j1.execute();
        assertEquals(j1.getNum_wag(), j1.getNum_wag());

        j1.setNum_wag(Main.NB_WAGONS - 1);
        tmp = j1.getNum_wag();
        j1.ajouteAction(new Deplace(j1, Direction.ARRIERE));
        j1.execute();
        assertEquals(j1.getNum_wag(), Main.NB_WAGONS - 1);

        j1.setIn(true);
        j1.ajouteAction(new Deplace(j1, Direction.BAS));
        j1.execute();
        assertEquals(j1.getIn(), true);

        j1.setIn(false);
        j1.ajouteAction(new Deplace(j1, Direction.HAUT));
        j1.execute();
        assertEquals(j1.getIn(), false);
    }

    @Test
    public void testBraquer1() {//braquer ou on peut braquer
        j1.setIn(true);
        assertEquals(j1.Calcul(), 0);
        chuchu1.getWagons()[j1.getNum_wag()].addButin(new Money(400), true);
        int size = chuchu1.getWagons()[j1.getNum_wag()].getButins().get(0).size();
        j1.ajouteAction(new Braquer(j1, null, chuchu1.getWagons()[j1.getNum_wag()]));
        j1.execute();
        assertEquals(size - 1, chuchu1.getWagons()[j1.getNum_wag()].getButins().get(0).size());
    }

    @Test
    public void testBraquer2() { //Braquer dans le vent
        int size = chuchu1.getWagons()[j1.getNum_wag()].getButins().get(0).size();
        j1.ajouteAction(new Braquer(j1, null, chuchu1.getWagons()[j1.getNum_wag()]));
        j1.execute();
        assertEquals(size, chuchu1.getWagons()[j1.getNum_wag()].getButins().get(0).size());
    }

    @Test
    public void testTirer1() { //Bandit 1 en haut Bandit 2 en haut
        j2.setNum_wag(Main.NB_WAGONS - 2);
        j2.ajouteButin(new Money());
        int size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.AVANT, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 1);
    }

    @Test
    public void testTirer3() {
        j1.setNum_wag(Main.NB_WAGONS - 2);
        j2.setNum_wag(Main.NB_WAGONS - 3);
        j2.ajouteButin(new Money());
        int size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.AVANT, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 1);
    }


    @Test
    public void testTirer2() {
        j1.setIn(true);
        j2.setIn(true);
        j2.setNum_wag(Main.NB_WAGONS - 2);
        j2.ajouteButin(new Money());
        int size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.AVANT, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 1);

        j1.setNum_wag(Main.NB_WAGONS - 2);
        j2.setNum_wag(Main.NB_WAGONS - 3);
        j2.ajouteButin(new Money());
        size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.AVANT, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 2);

        j1.setNum_wag(Main.NB_WAGONS - 2);
        j2.setNum_wag(Main.NB_WAGONS - 2);
        j2.ajouteButin(new Money());
        size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.BAS, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 3);

        j2.setNum_wag(Main.NB_WAGONS - 1);
        j2.ajouteButin(new Money());
        size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.ARRIERE, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 4);
    }

    @Test
    public void testTirer5() { //bandit1 en haut bandit2 en bas et vice versa
        j1.setIn(false);
        j2.setIn(true);
        j3.setNum_wag(0);
        j2.setNum_wag(Main.NB_WAGONS - 2);
        j1.setNum_wag(Main.NB_WAGONS - 2);
        j2.ajouteButin(new Money());
        int size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.BAS, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(0).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 1);

        j1.setIn(true);
        j2.setIn(false);
        j2.ajouteButin(new Money());
        size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.HAUT, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 2);

    }

    @Test
    public void testTirer4() {
        j2.setNum_wag(Main.NB_WAGONS - 1);
        j1.setNum_wag(Main.NB_WAGONS - 2);
        j3.setIn(true);
        j2.ajouteButin(new Money());
        int size = chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size();
        assertEquals(j2.getButins().size(), 1);
        j1.ajouteAction(new Tirer(j1, Direction.ARRIERE, chuchu1));
        j1.execute();
        assertEquals(j2.getButins().size(), 0);
        assertEquals(chuchu1.getWagons()[j2.getNum_wag()].getButins().get(1).size(), size + 1);
        assertEquals(j1.getBalles(), Main.NB_BULLETS - 1);
    }


    @Test
    public void testMaxBandit() {
        j1.ajouteButin(new Money(500));
        j2.ajouteButin(new Money(250));
        assertEquals(chuchu1.max(chuchu1.getBandits()).getIdJoueur(), j1.getIdJoueur());
    }
    // Test Constructeur

    @Test
    public void testBraquerConstructor() {
        // Arrange
        Bandit bandit = j1;
        Direction direction = null; // Direction arbitraire pour le test
        Wagon wagon = new Wagon(); // Créez un wagon arbitraire pour le test

        // Act
        Braquer braquer = new Braquer(bandit, direction, wagon);

        // Assert
        assertNotNull(braquer);
        assertEquals(bandit, braquer.getk());
        assertEquals(direction, braquer.getd());
        assertEquals(wagon, braquer.getwagon());
    }

    @Test
    public void testDeplaceConstructor() {
        // Arrange
        Bandit bandit = j1;
        Direction direction = Direction.AVANT; // Direction arbitraire pour le test

        // Act
        Deplace deplace = new Deplace(bandit, direction);

        // Assert
        assertNotNull(deplace);
        assertEquals(bandit, deplace.getk());
        assertEquals(direction, deplace.getd());
    }

    @Test
    public void testTirerConstructor() {
        // Arrange
        Bandit bandit = j1;
        Direction direction = Direction.AVANT; // Direction arbitraire pour le test
        Train train = chuchu1;

        // Act
        Tirer tirer = new Tirer(bandit, direction, train);

        // Assert
        assertNotNull(tirer);
        assertEquals(bandit, tirer.getk());
        assertEquals(direction, tirer.getd());
        assertEquals(train, tirer.gettrain());
    }

    @Test
    public void testBijouConstructor() {
        // Arrange & Act
        Bijou bijou = new Bijou();

        // Assert
        assertNotNull(bijou);
        assertEquals(500, bijou.getValeur());
    }

    @Test
    public void testMagotConstructor() {
        // Arrange & Act
        Magot magot = new Magot();

        // Assert
        assertNotNull(magot);
        assertEquals(1000, magot.getValeur());
    }
    @Test
    public void testConstructorMoney2() {
        // Arrange & Act
        Money money = new Money();

        // Assert
        assertNotNull(money);
        int value = money.getValeur();
        // Vérifie si la valeur se trouve entre 1 et 500
        assertTrue(value >= 1 && value <= 500, "La valeur doit être entre 1 et 500");
    }

    @Test
    public void testConstructorMoney1() {
        // Arrange
        int expectedValue = 100;

        // Act
        Money money = new Money(expectedValue);

        // Assert
        assertNotNull(money);
        assertEquals("La valeur devrait être égale à celle spécifiée dans le constructeur", expectedValue, money.getValeur());
    }

        @Test
        public void testPassagerConstructor() {
            // Arrange & Act
            Passanger passanger = new Passanger();

            // Assert
            assertNotNull(passanger);
            assertNotNull(passanger.getBagage());
            assertTrue(passanger.getValeur() >= 0, "La valeur de l'argent doit être supérieure ou égale à 0");
        }
        @Test
        public void testBanditConstructor() {
            // Arrange
            int id = 1;
            String nom = "Test Bandit";
            int wagon = 2;

            // Act
            Bandit bandit = new Bandit(id, nom, wagon);

            // Assert
            assertNotNull(bandit);
            assertEquals(id, bandit.getIdJoueur());
            assertEquals(nom, bandit.getNom());
            assertEquals(wagon, bandit.getNum_wag());
            assertEquals(false, bandit.getIn());
        }

    @Test
    public void testConstructorMarshall() {
        // Arrange
        String expectedNom = "Mrshl";
        int expectedNumWag = 0;
        boolean expectedIn = true;
        int expectedId = Main.NB_BANDITS; // Supposons qu'il y ait déjà un certain nombre de bandits créés

        // Act
        Marshall marshall = new Marshall();

        // Assert
        assertEquals(expectedNom, marshall.getNom());
        assertEquals(expectedNumWag, marshall.getNumWag());
        assertEquals(expectedIn, marshall.isIn());
        assertEquals(expectedId, marshall.getId());
    }
    @Test
    public void testConstructorSon() {
        // Arrange
        String cheminAudio = "src/Modele/Extra/Sons/duel_western.wav";

        // Act
        Son son = new Son(cheminAudio);

        // Assert
        assertNotNull(son.getClip());
    }
    @Test
    public void testTrainConstructor() {
        // Création d'un train avec 5 wagons et 10 bandits
        Train train = new Train(5);

        // Vérification du nombre de wagons
        Wagon[] wagons = train.getWagons();
        assertEquals(5, wagons.length); // Doit être égal à 5

        // Vérification du nombre de bandits
        ArrayList<Bandit> bandits = train.getBandits();
        assertEquals(Main.NB_BANDITS, bandits.size()); // Doit être égal à 10

        // Vérification du marshall
        Marshall marshall = train.getMarshall();
        assertEquals("Mrshl", marshall.getNom()); // Le nom du marshall doit être "Marshall"
        assertEquals(0, marshall.getNum_wag()); // Le marshall doit être dans le premier wagon
        assertEquals(true, marshall.getIn()); // Le marshall doit être à l'intérieur du wagon
    }

    @Test
    public void testWagonConstructorWithNumWagon() {
        Wagon wagon = new Wagon(0);

        // Test du numéro de wagon
        assertEquals(0, wagon.getNumWag());
    }

    @Test
    public void testWagonDefaultConstructor() {
        Wagon wagon = new Wagon();

        // Test du nombre de butins sur le toit
        assertEquals(0, wagon.getButins().get(0).size());

        // Test du nombre de butins à l'intérieur du wagon
        assertEquals(0, wagon.getButins().get(1).size());

        // Test du numéro de wagon
        assertEquals(-1, wagon.getNumWag());
    }





    }












