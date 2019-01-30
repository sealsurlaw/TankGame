package TankGameTeam07;

import GameEngine.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TankTile extends Tile{
    
    public TankTile(int index) {
        super(index, Resources.getImage(Resources.Images.BACKGROUND)[0]);
    }
    
    @Override
    public void drawTile(Graphics2D g2) {
        g2.drawImage(tile, (INDEX % 4) * WIDTH, (INDEX / 4) * HEIGHT * 2 + 32, null);
    }

    @Override
    public BufferedImage redrawMini() {
        mini = new BufferedImage(
                WIDTH/8, HEIGHT*2/8, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gmini = (Graphics2D) mini.getGraphics();
        gmini.setColor(Color.black);
        gmini.fill((new Rectangle(0, 0, WIDTH/8, HEIGHT*2/8)));
        for (int i = 0; i < objects.size(); ++i) {
            if (objects.get(i).getClass() == WallSolid.class) {
                gmini.setColor(Color.blue);
            }
            else if (objects.get(i).getClass() == WallBreakable.class) {
                gmini.setColor(Color.red);
            }
            Rectangle rect = new Rectangle(
                    objects.get(i).getX()/8, objects.get(i).getY()/8, 4, 4);
            gmini.fill(rect);
        }
       
        return mini;
    }
}