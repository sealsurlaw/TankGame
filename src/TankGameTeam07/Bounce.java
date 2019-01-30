package TankGameTeam07;


import GameEngine.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.Timer;

public class Bounce extends MovableObject {

    Map map;
    private int speed;
    private int xCounter = 0;
    private int yCounter = 0;
    boolean isFirst;

    public Bounce(int x, int y, int rotation, Map map) {
        super(Resources.getImage(Resources.Images.BOUNCING), x, y, rotation);
        this.map = map;
        speed = 1000;

        collisionMask = new Rectangle(
                x, y, 24, 24);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.removeMovable(Bounce.this);
            }
        });
        timer.start();
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

        } else if (collisionMask.intersects((Rectangle) other.getCollisionMask())) {
            if (other.getClass() == WallSolid.class
                    || other.getClass() == WallBreakable.class) {
                isFirst = true;
                int direction = moveTurn(other);
                if (direction == 0) {
                    if (rotation > 0) {
                        setRotation(180 - rotation);
                    } else if (rotation == 0) {
                        setRotation(180);
                    } else if (rotation > 270 && rotation < 360) {
                        setRotation(360 - rotation + 180);
                    }
                } else if (direction == 1) {
                    if (rotation > 90 && rotation < 180) {
                        setRotation(180 - rotation);
                    } else if (rotation == 180) {
                        setRotation(0);
                    } else if (rotation > 180) {
                        setRotation(360 - rotation - 180);
                    }
                } else if (direction == 2) {
                    if (rotation > 180 && rotation < 270) {
                        setRotation(360 - rotation);
                    } else if (rotation == 270) {
                        setRotation(90);
                    } else if (rotation > 270) {
                        setRotation(360 - rotation);
                    }
                } else if (direction == 3) {
                    if (rotation < 90) {
                        setRotation(-rotation);
                    } else if (rotation == 90) {
                        setRotation(270);
                    } else if (rotation > 90) {
                        setRotation(360 - rotation);
                    }
                }
            }
        }
    }

    public void destroy() {
        map.removeMovable(this);
    }

    private int moveTurn(GameObject other) {
        int counter = 0;
        int offset = 1;
        while (collisionMask.intersects((Rectangle2D) other.getCollisionMask())) {
            if (!isFirst) {
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
            }
            isFirst = false;
            ++counter;
            if (counter > 3) {
                counter = 0;
                ++offset;
            }
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
            collisionMask = new Rectangle(
                    x, y, 24, 24);
        }

        return counter;
    }

}
