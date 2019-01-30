package TankGameTeam07;

import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Resources {

    private static HashMap<Images, BufferedImage[]> imgHash;

    public static enum Images {
        BACKGROUND, BOUNCING, EXPLOSION_LARGE, EXPLOSION_SMALL, HEART, PICKUP, ROCKET, BULLET, TANK1,
        TANK2, TITLE, WALL1, WALL2, WEAPON
    }

    public static enum Sounds {
        EXPLOSION_LARGE, EXPLOSION_SMALL, MUSIC
    }

    //Initializes hashmap with Image and Sound objects
    public static void init() {
        imgHash = new HashMap<>();
        ////IMAGES////
        //BACKGROUND
        imgHash.put(Images.BACKGROUND, loadImage("Background"));
        //BOUNCING
        imgHash.put(Images.BOUNCING, loadImage("Bouncing", 60));
        //EXPLOSION_LARGE
        imgHash.put(Images.EXPLOSION_LARGE, loadImage("Explosion_large", 7));
        //EXPLOSION_SMALL
        imgHash.put(Images.EXPLOSION_SMALL, loadImage("Explosion_small", 6));
        //HEART
        imgHash.put(Images.HEART, loadImage("Heart", 2));
        //PICKUP
        imgHash.put(Images.PICKUP, loadImage("Pickup", 4));
        //ROCKET
        imgHash.put(Images.ROCKET, loadImage("Rocket", 60));
        //BULLET
        imgHash.put(Images.BULLET, loadImage("Shell", 60));
        //TANK1
        imgHash.put(Images.TANK1, loadImage("Tank1", 60));
        //TANK2
        imgHash.put(Images.TANK2, loadImage("Tank2", 60));
        //TITLE
        imgHash.put(Images.TITLE, loadImage("Title"));
        //WALL1
        imgHash.put(Images.WALL1, loadImage("Wall1"));
        //WALL2
        imgHash.put(Images.WALL2, loadImage("Wall2"));
        //WEAPON
        imgHash.put(Images.WEAPON, loadImage("Weapon", 3));
    }

    // Gets Image objects
    public static BufferedImage[] getImage(Images im) {
        return imgHash.get(im);
    }
    
    public static void playSound(Sounds snd) {
        String path = "";
        if (snd == Sounds.EXPLOSION_LARGE) path = "Explosion_large.wav";
        if (snd == Sounds.EXPLOSION_SMALL) path = "Explosion_small.wav";
        if (snd == Sounds.MUSIC) path = "Music.mid";

        try {
            AudioInputStream audioIn =
                    AudioSystem.getAudioInputStream(new File("sound/" + path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            if (snd == Sounds.MUSIC) {
                clip.setLoopPoints(0, -1);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static BufferedImage[] loadImage(String path, int numImg) {
        BufferedImage[] img = new BufferedImage[numImg];
        try {
            if (numImg == 1) {
                path = "img/" + path + "/" + path + ".png";
                img[0] = ImageIO.read(new File(path));
            } else {
                for (int i = 0; i < numImg; ++i) {
                    String pathNew = "img/" + path + "/" + path + "-" + i + ".png";
                    img[i] = ImageIO.read(new File(pathNew));
                }
            }
        } catch (IOException e) {
            System.err.println("\"" + path + "\" does not exist.");
        }
        return img;
    }

    private static BufferedImage[] loadImage(String path) {
        return loadImage(path, 1);
    }

}
