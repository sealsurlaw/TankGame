package GameEngine;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import TankGameTeam07.Resources;
import java.awt.geom.AffineTransform;

public abstract class MovableObject extends GameObject{
    
    public MovableObject(Image[] imgArray, int x, int y, int rotation) {
        super(imgArray, x, y, rotation);
        at = new AffineTransform();
        at.rotate(Math.toRadians(-rotation),
                collisionMask.getBounds().getCenterX(),
                collisionMask.getBounds().getCenterY());
        collisionMask = at.createTransformedShape(collisionMask);
    }
    
    // For testing
    public MovableObject() {
        this(Resources.getImage(Resources.Images.TANK1),100,100,0);
    }

    public int getImageHeight() {
        return currImg.getHeight(null);
    }

    public int getImageWidth() {
        return currImg.getWidth(null);
    }
    
    @Override
    public boolean isMovable() {
        return true;
    }
    
    public abstract void updateState();

}