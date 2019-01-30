package TankGameTeam07;

import GameEngine.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HealthBar extends Toolbar {
    Rectangle bar;
    Tank tank;
    int health;
    int x;
    int y;
    
    public HealthBar(int xCor, int yCor, Tank tank) {
        super(xCor,yCor);
        this.x = xCor;
        this.y = yCor;
        this.tank = tank;
        health = 100;
        bar = new Rectangle(x, y, 100, 20);
    }
    
    public void subHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            tank.die();
        }
    }
    
    public void addHealth(int recover) {
        health += recover;
        if (health > 100) {
            health = 100;
        }
    }
    
    @Override
    public void drawToolbar(Graphics2D g2) {
        g2.setColor(Color.green);
        g2.draw(bar);
        g2.fillRect(x, y, health, 20);
    }
}
