package TankGameTeam07.Maps;

import TankGameTeam07.*;
import GameEngine.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Rectangle;

public class TitleScreen extends Map {
    private BufferedImage titleScreen;
    private MenuButton start;
    
    public TitleScreen(Game game) {
        super(game);
     
        movable = new ArrayList<>();
        tiles = new ArrayList<>();
        toolbarObj = new ArrayList<>();
        titleScreen = Resources.getImage(Resources.Images.TITLE)[0];
        Resources.playSound(Resources.Sounds.MUSIC);
        
        toolbarObj = new ArrayList<>();        
        start = new MenuButton(220, 320, true, this, null);
        toolbarObj.add(start);
    }
    
    @Override
    public void draw(Graphics2D gFrame, int width, int height){
        BufferedImage bufferedScreen = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gScreen = (Graphics2D) bufferedScreen.getGraphics();
        //gScreen.setBackground(new Color(127, 122, 83));
        gScreen.setColor(new Color(127, 122, 83));
        gScreen.fill(new Rectangle(0,0,width,height));
        gScreen.drawImage(titleScreen, 70, 20, null);
        
        for (int i = 0; i < toolbarObj.size(); ++i) {
            toolbarObj.get(i).drawToolbar(gScreen);
        } 
        gFrame.drawImage(bufferedScreen, 0, 0, null);
    }
    
    @Override
    public void update() {
    }
    
    @Override 
    public void nextMap(){
        Map nextMap = new Level1(game);
        game.setMap(nextMap);
        nextMap.registerMovable(game.geyKeyObs());
    }
    
    @Override
    public void collisions(){
    }
    
    @Override
    public void registerMovable(KeyboardObservable keyObs) {
        keyObs.addObserver(start);
    }

    @Override
    public void decodeMap() {
    }
    
    @Override
    public void decodeMiniMap() {
    }
}
