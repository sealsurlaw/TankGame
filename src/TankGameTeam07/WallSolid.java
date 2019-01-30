package TankGameTeam07;

import GameEngine.GameObject;

public class WallSolid extends GameObject{
    
    public WallSolid(int x, int y) {
        super(Resources.getImage(Resources.Images.WALL1), x, y, 0);
    }

    public void collision(GameObject other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}