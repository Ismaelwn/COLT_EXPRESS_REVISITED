package Modele.Extra; // Elle vient de mon projet ipo

import javax.sound.sampled.*;
import java.io.File;

public class Son { // j'ai créé cette classe pour plus facilement utiliser mes sound effects
    private Clip clip; // attribut de ma classe Son
    private FloatControl volumeControl;

    public Son(String AudioRoad) { // on construit le futur sound effect a partir d'un fichier .wav
        try {
            File audPath = new File(AudioRoad);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audPath);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void jouer(boolean rejouer) { //
        if (clip != null && !clip.isRunning()){  // le param rejouer sert a savoir si on continue d'executer le son apres que l'on ai completement joué le son
            clip.setFramePosition(0);
            clip.start();
        }
        if (rejouer) { // si le boolean est true le son s'arrete donc jamais
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public FloatControl getVolumeControl() {
        return volumeControl;
    }

    public void arreter() { // une methode pour arreter le son
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }



    public Clip getClip() {
        return clip;
    }



}
