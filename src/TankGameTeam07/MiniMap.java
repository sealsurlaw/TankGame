
package TankGameTeam07;

import GameEngine.Toolbar;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import GameEngine.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MiniMap extends Toolbar {
    private Rectangle bar;
    private ArrayList<Tile> tiles;
    private ArrayList<BufferedImage> minimap;
    int x;
    int y;
    
    public MiniMap(int xCor, int yCor, ArrayList<Tile> tiles) {
        super(xCor,yCor);
        this.x = xCor;
        this.y = yCor;
        this.tiles = tiles;
        redraw();
        bar = new Rectangle(x, y, 160, 120);
    }
    
    public void redraw() {
        minimap = new ArrayList<>();
        for (int i = 0; i < tiles.size(); ++i) {
            minimap.add(tiles.get(i).redrawMini());
        }
    }
    
    @Override
    public void drawToolbar(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.draw(bar);
        for (int i = 0; i < minimap.size(); ++i) {
            g2.drawImage(minimap.get(i), (i%4)*40 + 240, (i/4)*60 + 340, null);
        }
    }
}
