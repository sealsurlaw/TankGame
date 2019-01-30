package TankGameTeam07;

import GameEngine.*;
import java.awt.Rectangle;

public class Pickup extends MovableObject{
    private Map map;
    private int index;
    
    public Pickup(int x, int y, Map map) {
        super(Resources.getImage(Resources.Images.PICKUP), x, y, 0);
        this.map = map;
        index = (int) (Math.random() * 3);
        setIndex(index);
    }
    
    public int getIndex(){
        return index;
    }

    @Override
    public void updateState() {
    }

    @Override
    public void collision(GameObject other) {
    }
    
    public void destroy() {
        map.removeMovable(this);
    }
    
}
