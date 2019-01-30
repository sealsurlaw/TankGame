package GameEngine;

import TankGameTeam07.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Map {

    protected ArrayList<MovableObject> movable;
    protected ArrayList<GameObject> nonmovable;
    protected ArrayList<Tile> tiles;
    protected ArrayList<Toolbar> toolbarObj;
    protected MiniMap miniMap;
    protected BufferedImage background;
    protected int tileHeight;
    protected int tileWidth;

    protected Tank player1;
    protected Tank player2;
    
    protected Game game;

    protected Map(Game game) {
        this.game = game;
        Resources.init();
    }

    public void registerMovable(KeyboardObservable keyObs) {
        keyObs.addObserver(player1);
        keyObs.addObserver(player2);
    }

    public void draw(Graphics2D gFrame, int width, int height) {
        // Entire map
        BufferedImage iMap = new BufferedImage(
                width * 4, height * 4, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gMap = (Graphics2D) iMap.getGraphics();

        // Left and right sections of split screen
        BufferedImage iLeft = new BufferedImage(
                318, 480, BufferedImage.TYPE_INT_ARGB);
        BufferedImage iRight = new BufferedImage(
                318, 480, BufferedImage.TYPE_INT_ARGB);
        BufferedImage i2 = new BufferedImage(
                640, 480, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gLeft = (Graphics2D) iLeft.getGraphics();
        Graphics2D gRight = (Graphics2D) iRight.getGraphics();
        Graphics2D g2 = (Graphics2D) i2.getGraphics();

        // Draws all movable objects
        for (int i = 0; i < movable.size(); ++i) {
            movable.get(i).draw(gMap);
        }

        // Draw Tiles
        for (int i = 0; i < tiles.size(); ++i) {
            tiles.get(i).drawTile(gMap);
        }

        // Draw Players
        player1.draw(gMap);
        player2.draw(gMap);

        // Draw movable
        for (int i = 0; i < movable.size(); ++i) {
            movable.get(i).draw(gMap);
        }

        // Follow Players
        gLeft.drawImage(iMap, 0, 0, 318, 480,
                player1.getMiddle()[0] - 158, player1.getMiddle()[1] - 240,
                player1.getMiddle()[0] + 158, player1.getMiddle()[1] + 240, null);
        gRight.drawImage(iMap, 0, 0, 318, 480,
                player2.getMiddle()[0] - 158, player2.getMiddle()[1] - 240,
                player2.getMiddle()[0] + 158, player2.getMiddle()[1] + 240, null);

        // Draw splitscreen
        g2.drawImage(iLeft, 0, 0, null);
        g2.drawImage(iRight, 322, 0, null);
        g2.setColor(Color.black);
        g2.fillRect(318, 0, 4, 480);

        // Draw toolbar objects
        for (int i = 0; i < toolbarObj.size(); ++i) {
            toolbarObj.get(i).drawToolbar(g2);
        } 
        
        Rectangle p1 = new Rectangle(
                player1.getX()/8 + 240, player1.getY()/8 + 340,4,4);
        Rectangle p2 = new Rectangle(
                player2.getX()/8 + 240, player2.getY()/8 + 340,4,4);
        
        g2.setColor(Color.green);
        g2.fill(p1);
        g2.fill(p2);

        gFrame.drawImage(i2, 0, 0, null);
    }

    public void update() {
        player1.updateState();
        player2.updateState();
        for (int i = 0; i < movable.size(); ++i) {
            movable.get(i).updateState();
        }
    }

    public void collisions() {
        for (int i = 0; i < tiles.size(); ++i) {
            if (tiles.get(i).collision(player1)) {
                tiles.get(i).checkAllCollisions(player1);
            }
            if (tiles.get(i).collision(player2)) {
                tiles.get(i).checkAllCollisions(player2);
            }
            for (int j = 0; j < movable.size(); ++j) {
                if (tiles.get(i).collision(movable.get(j))) {
                    tiles.get(i).checkAllCollisions(movable.get(j));
                }
            }
        }
        if (player1.isCollidingMovable(player2)) {
            if ((int) (Math.random() * 2) == 0) {
                player1.collision(player2);
            } else {
                player2.collision(player1);
            }
        }
        for (int i = 0; i < movable.size(); ++i) {
            if (movable.get(i).isCollidingMovable(player2)) {
                player2.collision(movable.get(i));
            }
            if (i < movable.size() && movable.get(i).isCollidingMovable(player1)) {
                player1.collision(movable.get(i));
            }
        }
        
    }

    public void addMovable(MovableObject mo) {
        movable.add(mo);
    }

    public void removeMovable(MovableObject mo) {
        movable.remove(mo);
    }
    
    public void addToolbar(Toolbar tb) {
        toolbarObj.add(tb);
    }
    
    public abstract void decodeMap();
    
    public abstract void decodeMiniMap();
    
    public abstract void nextMap();
    
}
