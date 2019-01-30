package TankGameTeam07;

import GameEngine.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Special extends Toolbar {
    private int index;
    private BufferedImage[] images;
    private BufferedImage currImage;       
    private int timeLeft;
            
    //index:
    //  0 ROCKET
    //  1 BOUNCING
    //  2 HEALTH
    public Special(int x, int y, int index){
        super(x,y);
        this.index = index;
        images = Resources.getImage(Resources.Images.WEAPON);
        if(index < 3)
            currImage = images[index];
        else
            currImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        timeLeft = 0;
        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                --timeLeft;
                if (timeLeft <= 0) {
                    clearImage();
                }
            }
        });
        timer.start();
    }
    
    public void setIndex(int index){
        this.index = index;
        currImage = images[index];
        timeLeft = 500;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void clearImage(){
        index = 3;
        currImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    }
    
    public void setTimeLeft(int tl){
        timeLeft = tl;
    }
    
    @Override
    public void drawToolbar(Graphics2D g2) {
        g2.setColor(Color.lightGray);
        g2.fillRect(x, y, 32, 32);
        g2.setColor(Color.gray);
        g2.fillRect(x, y + 32 - (int)(timeLeft/500.0*32), 32, (int)(timeLeft/500.0*32));
        g2.setColor(Color.black);
        g2.drawRect(x, y, 32, 32);
        g2.drawImage(currImage, x+8, y+8, null);
    }
    
}
