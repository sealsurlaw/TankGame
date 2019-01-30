package GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Tile {
    final protected int INDEX;
    final protected BufferedImage BACKGROUND;
    final protected int WIDTH;
    final protected int HEIGHT;
    protected ArrayList<GameObject> objects;
    protected BufferedImage tile;
    protected BufferedImage mini;
    protected Rectangle collisionMask;

    public Tile(int index, BufferedImage bg) {
        INDEX = index;
        BACKGROUND = bg;
        HEIGHT = BACKGROUND.getHeight();
        WIDTH = BACKGROUND.getWidth();
        objects = new ArrayList<>();
        collisionMask = new Rectangle(
                (INDEX % 4) * WIDTH, (INDEX / 4) * HEIGHT * 2 + 32, 320, 480);
    }

    public void addObject(GameObject obj) {
        obj.globalizeRect(INDEX);
        objects.add(obj);
        redrawTile();
    }

    public BufferedImage getTile() {
        return tile;
    }
    
    public BufferedImage getMiniMap() {
        return mini;
    }

    public void redrawTile() {
        tile = new BufferedImage(
                WIDTH, HEIGHT * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gTile = (Graphics2D) tile.getGraphics();

        gTile.drawImage(BACKGROUND, 0, 0, null);
        gTile.drawImage(BACKGROUND, 0, HEIGHT, null);
        
        for (int i = 0; i < objects.size(); ++i) {
            objects.get(i).draw(gTile);
        }
    }
    
    public abstract BufferedImage redrawMini();
    
    public Rectangle getCollisionMask() {
        return collisionMask;
    }
    
    public boolean collision(GameObject obj) {
        return obj.getCollisionMask().intersects(this.collisionMask);
    }
    
    public void checkAllCollisions(GameObject obj) {
        for (int i = 0; i < objects.size(); ++i) {
            obj.collision(objects.get(i));
        }
    }
    
    public void removeObject(GameObject go) {
        objects.remove(go);
    }
    
    public abstract void drawTile(Graphics2D g2);

}
