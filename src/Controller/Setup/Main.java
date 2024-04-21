package Controller.Setup;

import Modele.Extra.Son;
import Modele.TrainJeu.Train;
import Vue.interfaceG.MenuStart;
import Vue.interfaceG.ParametreF;
import Vue.interfaceG.fenetreJeu;

import java.io.*;


public class Main {
    public static int NB_WAGONS = 7; // Max 10 pour des raisons d'affichages
    public static int NB_ACTIONS = 2;
    public static int NB_BANDITS = 2; // J'ai préferé l'appelé NB_BANDITS AU LIEU DE NB_JOUEURS
    public static int NB_BULLETS = 4;
    public static double NERVOSITE_MARSHALL = 1;
    public static int time = 10;
    public static int width =1270;
    public static int height =620;
    public static int volume = 0;
    public static int NB_ROUNDS = 5;
    public static void main(String[] args) {
        chargerParametres("src/Modele/parametre");
        MenuStart feur = new MenuStart();
        ControllerMenu z = new ControllerMenu(feur);

    }

    public static void sauvegarderParametres(String fichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write("NB_WAGONS=" + NB_WAGONS);
            writer.newLine();
            writer.write("NB_ACTIONS=" + NB_ACTIONS);
            writer.newLine();
            writer.write("NB_BANDITS=" + NB_BANDITS);
            writer.newLine();
            writer.write("NB_BULLETS=" + NB_BULLETS);
            writer.newLine();
            writer.write("NERVOSITE_MARSHALL=" + NERVOSITE_MARSHALL);
            writer.newLine();
            writer.write("time=" + time);
            writer.newLine();
            writer.write("width=" + width);
            writer.newLine();
            writer.write("height=" + height);
            writer.newLine();
            writer.write("volume=" + volume);
            writer.newLine();
            writer.write("NB_ROUNDS=" + NB_ROUNDS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void chargerParametres(String fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split("=");
                if (parts.length == 2) {
                    String variable = parts[0].trim();
                    String valeur = parts[1].trim();
                    switch (variable) {
                        case "NB_WAGONS":
                            NB_WAGONS = Integer.parseInt(valeur);
                            break;
                        case "NB_ACTIONS":
                            NB_ACTIONS = Integer.parseInt(valeur);
                            break;
                        case "NB_BANDITS":
                            NB_BANDITS = Integer.parseInt(valeur);
                            break;
                        case "NB_BULLETS":
                            NB_BULLETS = Integer.parseInt(valeur);
                            break;
                        case "NERVOSITE_MARSHALL":
                            NERVOSITE_MARSHALL = Double.parseDouble(valeur);
                            break;
                        case "time":
                            time = Integer.parseInt(valeur);
                            break;
                        case "width":
                            width = Integer.parseInt(valeur);
                            break;
                        case "height":
                            height = Integer.parseInt(valeur);
                            break;
                        case "volume":
                            volume = Integer.parseInt(valeur);
                            break;
                        case "NB_ROUNDS":
                            NB_ROUNDS = Integer.parseInt(valeur);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    }



