package TankGameTeam07;

import GameEngine.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Bullet extends MovableObject {

    Map map;
    private int speed;
    private int xCounter = 0;
    private int yCounter = 0;

    public Bullet(int x, int y, int rotation, Map map) {
        super(Resources.getImage(Resources.Images.BULLET), x, y, rotation);
        this.map = map;
        speed = 1000;
        collisionMask = new Rectangle(
                x + 6, y + 8, 12, 8);
        at = new AffineTransform();
        at.rotate(Math.toRadians(-rotation),
                collisionMask.getBounds().getCenterX(),
                collisionMask.getBounds().getCenterY());
        collisionMask = at.createTransformedShape(collisionMask);
    }

    @Override
    public void updateState() {
        int tx = 0;
        int ty = 0;
        
        if (x < -32 || y < -32 || x > 1312 || y > 992) {
            destroy();
        }
        xCounter += Math.cos(rotation * Math.PI / 180) * speed;
        tx = x;
        x += xCounter / 100;
        xCounter = xCounter % 100;
        tx -= x;

        yCounter += Math.sin(rotation * Math.PI / 180) * speed;
        ty = y;
        y -= yCounter / 100;
        yCounter = yCounter % 100;
        ty -= y;
        
        at = new AffineTransform();
        at.translate(-tx, -ty);
        collisionMask = at.createTransformedShape(collisionMask);
    }

    @Override
    public void collision(GameObject other) {
        if (other.isMovable()) {
            
        }
        else if (collisionMask.intersects((Rectangle) other.getCollisionMask())) {
            if (other.getClass() == WallSolid.class) {
                map.addMovable(new Explosion(
                        Resources.getImage(
                                Resources.Images.EXPLOSION_SMALL),x, y, map));
                destroy();
            }
            if (other.getClass() == WallBreakable.class) {
                map.addMovable(new Explosion(
                        Resources.getImage(Resources.Images.EXPLOSION_SMALL),
                        (int) other.getCollisionMask().getBounds2D().getMinX(),
                        (int) other.getCollisionMask().getBounds2D().getMinY(),
                        map));
                WallBreakable wall = (WallBreakable) other;
                wall.destroy();
                wall.getTile().redrawTile();
                wall.getTile().redrawMini();
                map.decodeMiniMap();
                destroy();
            }
        }
    }

    public void destroy() {
        map.removeMovable(this);
    }

}
