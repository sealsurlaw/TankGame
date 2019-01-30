package TankGameTeam07;

import GameEngine.*;
import java.awt.geom.Rectangle2D;

public class WallBreakable extends GameObject{
    Tile tile;
    Map map;
    
    public WallBreakable(int x, int y, Tile tile) {
        super(Resources.getImage(Resources.Images.WALL2), x, y, 0);
        this.tile = tile;
    }

    public void collision(GameObject other) {
//        if (other.getCollisionMask().intersects((Rectangle2D) collisionMask)) {
//            if (other.getClass() == Bullet.class) {
//                System.out.println("cvbdfbdf");
//                Bullet bullet = (Bullet) other;
//                bullet.destroy();
//                tile.redrawTile();
//                map.decodeMiniMap();
//                tile.redrawMini();
//                
//                destroy();
//            }
//        }
    }
    
    public void destroy() {
        tile.removeObject(this);
    }
    
    public Tile getTile() {
        return tile;
    }
    
}
