package TankGameTeam07;

import GameEngine.*;
import java.awt.image.BufferedImage;

public class Explosion extends MovableObject{
    Map map;
    int index;
    
    public Explosion(BufferedImage[] img, int x, int y, Map map) {
        super(img, x, y, 0);
        this.map = map;
        index = 0;
        if (img.length == 6)
            Resources.playSound(Resources.Sounds.EXPLOSION_SMALL);
        if (img.length == 7)
            Resources.playSound(Resources.Sounds.EXPLOSION_LARGE);
    }

    @Override
    public void collision(GameObject other) {
        // EMPTY
    }

    @Override
    public void updateState() {
        currImg = imgArray[index];
        ++index;
        if (index == imgArray.length) {
            map.removeMovable(this);
        }
    }
    
}
