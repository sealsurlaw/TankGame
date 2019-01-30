package TankGameTeam07;

import GameEngine.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Lives extends Toolbar{
    private int numLives;
    private BufferedImage[] heart;
    
    public Lives(int x, int y, int lives) {
        super(x, y);
        numLives = lives;
        heart = Resources.getImage(Resources.Images.HEART);
    }
    
    public void loseLife() {
        numLives -= 1;
    }
    
    public int getLife(){
        return numLives;
    }

    @Override
    public void drawToolbar(Graphics2D g2) {
        for (int i = 0; i < 3; ++i) {
            if (i < numLives)
                g2.drawImage(heart[0], x + (i*40), y, null);
            else
                g2.drawImage(heart[1], x + (i*40), y, null);
        }
    }
    
}
