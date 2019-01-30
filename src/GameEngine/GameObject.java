package GameEngine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public abstract class GameObject {
    protected Image[] imgArray;     // All sprites of object
    protected int rotationStep;     // 360deg / total sprite images
    
    protected Image currImg;              // Current image drawn
    protected int x, y, rotation;         // Position and rotation
    
    protected Shape collisionMask;
    protected AffineTransform at;
        
    public GameObject() {
        this(null,0,0,0);
    }
    
    public GameObject(Image[] imgArray, int x, int y, int rotation) {
        this.imgArray = imgArray;
        this.x = x;
        this.y = y;
        this.rotation = rotation % 360;
        // Example: 60 image array (imgArray.length = 60)
        //          rotationStep = 360 / 60 = 6
        //          270deg / rotationStep = 270 / 6 = 45
        //          currImg = [45]
        rotationStep = 360 / imgArray.length;
        currImg = imgArray[this.rotation / rotationStep];
        collisionMask = new Rectangle(
                x,y,imgArray[0].getWidth(null),imgArray[0].getHeight(null));
    }
    
    // X location getter/setter
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    
    // Y location getter/setter
    public int getY() {
        return y;
    }
    public void seyY(int y) {
        this.y = y;
    }
    
    // Sprite rotation getter/setter
    public int getRotation() {
        return rotation;
    }
    public void setRotation(int rotation) {
        if (rotation < 0)
            rotation = 360 + rotation;
        if (imgArray.length > 1) {
            this.rotation = rotation % 360;
            currImg = imgArray[this.rotation / rotationStep];
        }
    }
    
    // Sets specific imgArray index
    public void setIndex(int index) {
        if (index < imgArray.length)
            currImg = imgArray[index];
    }
    
    public void globalizeRect(int index) {
        ((Rectangle) collisionMask).translate(
                (index%4)*320, (index/4)*480 + 32);
    }
    
    public Shape getCollisionMask() {
        return collisionMask;
    }
    
    // Handles collisons with other game objects
    public abstract void collision(GameObject other);
    
    public boolean isCollidingMovable(GameObject other) {
        Area a1 = new Area(collisionMask);
        Area a2 = new Area(other.getCollisionMask());
        a1.intersect(a2);
        
        return !a1.isEmpty();
    }
    
    // Draws image
    public void draw(Graphics2D g2) {
        g2.drawImage(currImg, x, y, null);
    }
    
    public int[] getMiddle() {
        int[] pos = new int[2];
        int xmid =  x + (currImg.getWidth(null) / 2);

        if (xmid + 158 > 1280)
            pos[0] = 1280 - 158;
        else if (xmid - 158 < 0)
             pos[0] = 158;
        else
            pos[0] = xmid;

        int ymid =  y + (currImg.getHeight(null) / 2);

        if (ymid + 240 > 992)
            pos[1] = 992 - 240;
        else if (ymid - 240 < 0)
            pos[1] = 240;
        else
            pos[1] = ymid;


        return pos;
    }
    
    public boolean isMovable() {
        return false;
    }
    
    @Override
    public String toString() {
        String str = this.getClass().toString() + "\n";
        str       += imgArray +"\n";
        str       += "x = " + x + "\n";
        str       += "y = " + y + "\n";
        str       += "rotation = " + rotation + "\n";
        return str;
    }
}