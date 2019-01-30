package TankGameTeam07;

import GameEngine.*;
import TankGameTeam07.Maps.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Tank extends MovableObject implements Observer {

    final private PlayerControls pc;
    final private int speed;
    final private Map map;
    final private String name;
    Set<Integer> keyPressed = new HashSet<>();

    private int xCounter = 0;
    private int yCounter = 0;

    private int counter = 0;
    private int specialCounter = 0;

    final private int CCW = 1;
    final private int CW = -1;

    private HealthBar healthBar;
    private Lives lives;
    private Special special;

    private boolean canShoot;
    private boolean canSpecial;
    
    private boolean enableMovement;

    public Tank(Resources.Images im, int x, int y,
            int rotation, PlayerControls pc, Map map, String name) {
        super(Resources.getImage(im), x, y, rotation);
        this.pc = pc;
        this.map = map;
        this.name = name;
        speed = 300;
        collisionMask = new Rectangle(
                x + 6, y + 9, 52, 45);
        at.rotate(Math.toRadians(-rotation),
                collisionMask.getBounds().getCenterX(),
                collisionMask.getBounds().getCenterY());
        collisionMask = at.createTransformedShape(collisionMask);

        canShoot = true;
        
        enableMovement = true;
        
    }

    // Notification sent from the KeyboardObservable caught in this method
    @Override
    public void update(Observable o, Object arg) {
        KeyboardObservable ko = (KeyboardObservable) o;
        keyPressed = ko.getKey();
    }

    @Override
    public void collision(GameObject other) {
        if (other.isMovable()) {
            if (other.getClass() == Tank.class) {
                healthBar.subHealth(1);
                moveTurn(other);
            }
            else if (other.getClass() == Bullet.class) {
                healthBar.subHealth(10);
                map.addMovable(new Explosion(
                        Resources.getImage(Resources.Images.EXPLOSION_SMALL),
                        (int) other.getCollisionMask().getBounds2D().getMinX(),
                        (int) other.getCollisionMask().getBounds2D().getMinY(),
                        map));
                Bullet bullet = (Bullet) other;
                bullet.destroy();
            }
            else if (other.getClass() == Pickup.class) {
                Pickup pk = (Pickup) other;
                special.setIndex(pk.getIndex());
                pk.destroy();
            }
           else if (other.getClass() == Rocket.class) {
                healthBar.subHealth(45);
                map.addMovable(new Explosion(
                        Resources.getImage(Resources.Images.EXPLOSION_LARGE),
                        (int) other.getCollisionMask().getBounds2D().getMinX()-20,
                        (int) other.getCollisionMask().getBounds2D().getMinY()-20,
                        map));
                Rocket rocket = (Rocket) other;
                rocket.destroy();
            }
            else if (other.getClass() == Bounce.class) {
                healthBar.subHealth(30);
                map.addMovable(new Explosion(
                        Resources.getImage(Resources.Images.EXPLOSION_LARGE),
                        (int) other.getCollisionMask().getBounds2D().getMinX()-20,
                        (int) other.getCollisionMask().getBounds2D().getMinY()-20,
                        map));
                Bounce bounce = (Bounce) other;
                bounce.destroy();
            }
        } else if (collisionMask.intersects((Rectangle2D) other.getCollisionMask())) {
            if (other.getClass() == WallSolid.class) {
                moveTurn(other);
            }
            else if (other.getClass() == WallBreakable.class) {
                moveTurn(other);
            }
        }
    }

    @Override
    public void updateState() {
        //special.setIndex(0);
        if(special.getIndex() == 2){
            healthBar.addHealth(1);
        }
        
        if (!canShoot) {
            ++counter;
        }
        if (counter == 15) {
            counter = 0;
            canShoot = true;
        }
        
        if (!canSpecial) {
            ++specialCounter;
        }
        if (specialCounter == 50) {
            specialCounter = 0;
            canSpecial = true;
        }

        if (keyPressed.contains(pc.UP) && enableMovement) {
            // Calculate forward
            xCounter += Math.cos(rotation * Math.PI / 180) * speed;
            x += xCounter / 100;
            xCounter = xCounter % 100;

            yCounter += Math.sin(rotation * Math.PI / 180) * speed;
            y -= yCounter / 100;
            yCounter = yCounter % 100;

        }
        if (keyPressed.contains(pc.DOWN) && enableMovement) {
            // Calculate backward
            xCounter += Math.cos(rotation * Math.PI / 180) * speed;
            x -= xCounter / 100;
            xCounter = xCounter % 100;

            yCounter += Math.sin(rotation * Math.PI / 180) * speed;
            y += yCounter / 100;
            yCounter = yCounter % 100;

        }
        if (keyPressed.contains(pc.LEFT) && enableMovement) {
            rotate(CCW);
        }
        if (keyPressed.contains(pc.RIGHT) && enableMovement) {
            rotate(CW);
        }
        if (keyPressed.contains(pc.SHOOT) && enableMovement) {
            // Calculate shoot
            if (canShoot) {
                int xCannon = (x + 26)
                        + (int) (Math.cos(Math.toRadians(rotation)) * 36) - 6;
                int yCannon = (y + 32)
                        - (int) (Math.sin(Math.toRadians(rotation)) * 36) - 12;
                map.addMovable(new Bullet(xCannon, yCannon, rotation, map));
            }
            canShoot = false;
        }
        if (keyPressed.contains(pc.SPECIAL) && enableMovement) {
            if (canSpecial) {
                int xCannon = (x + 24)
                        + (int) (Math.cos(Math.toRadians(rotation)) * 45);
                int yCannon = (y + 24)
                        - (int) (Math.sin(Math.toRadians(rotation)) * 45);
                if(special.getIndex() == 0)
                    map.addMovable(new Rocket(xCannon, yCannon, rotation, map));
                if(special.getIndex() == 1)
                    map.addMovable(new Bounce(xCannon, yCannon, rotation, map));
            }
            canSpecial = false;
        }
        if (keyPressed.contains(pc.ESC)) {
            System.exit(0);
        }
        collisionMask = new Rectangle(
                x + 6, y + 9, 52, 45);
        at = new AffineTransform();
        at.rotate(Math.toRadians(-rotation),
                collisionMask.getBounds().getCenterX(),
                collisionMask.getBounds().getCenterY());
        collisionMask = at.createTransformedShape(collisionMask);
    }

    public void addHealth(Toolbar hb) {
        healthBar = (HealthBar) hb;
    }

    public void addLives(Toolbar lv) {
        lives = (Lives) lv;
    }
    
    public void addSpecial(Toolbar sp){
        special = (Special) sp;
    }

    private void rotate(int direction) {
        int rotSpeed = 5 * direction;
        this.setRotation(this.getRotation() + rotSpeed);
    }

    private void moveTurn(GameObject other) {
        int counter = 0;
        int offset = 1;
        while (other.isMovable() ? isCollidingMovable(other)
                : collisionMask.intersects(
                        (Rectangle2D) other.getCollisionMask())) {
            switch (counter) {
                case 0:
                    x -= offset;
                    break;
                case 1:
                    x += offset;
                    break;
                case 2:
                    y -= offset;
                    break;
                case 3:
                    y += offset;
                    break;
            }
            ++counter;
            if (counter > 3) {
                counter = 0;
                ++offset;
            }
            switch (counter) {
                case 0:
                    x += offset;
                    break;
                case 1:
                    x -= offset;
                    break;
                case 2:
                    y += offset;
                    break;
                case 3:
                    y -= offset;
                    break;
            }
            collisionMask = new Rectangle(
                    x + 6, y + 9, 52, 45);
            at = new AffineTransform();
            at.rotate(Math.toRadians(-rotation),
                    collisionMask.getBounds().getCenterX(),
                    collisionMask.getBounds().getCenterY());
            collisionMask = at.createTransformedShape(collisionMask);
        }
    }

    public void die() {
        lives.loseLife();
        healthBar.addHealth(100);
        map.addMovable(new Pickup(x + 16, y + 16, map));
        map.addMovable(new Explosion(
                Resources.getImage(Resources.Images.EXPLOSION_LARGE),
                x, y, map));
        x = (int) (Math.random() * 1216) + 32;
        y = (int) (Math.random() * 896) + 32;
        while (x < 0 || x > 1216
                || y < 0 || y > 896) {
            x = (int) (Math.random() * 1216) + 32;
            y = (int) (Math.random() * 896) + 32;
        }
        if (lives.getLife() == 0)
            ((Level1) map).displayRetryMenu(name);
    }
    
    public void disable() {
        enableMovement = false;
    }
    
    public Lives getTankLive(){
        return lives;
    }
}
