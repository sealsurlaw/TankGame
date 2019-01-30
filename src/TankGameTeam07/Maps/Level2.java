package TankGameTeam07.Maps;

import TankGameTeam07.*;
import GameEngine.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level2 extends Map {
    enum Sym {
        WALL_S, WALL_B, TANK1, TANK2, EMPTY
    }

    Sym X = Sym.WALL_S;
    Sym O = Sym.WALL_B;
    Sym T = Sym.TANK1;
    Sym t = Sym.TANK2;
    Sym i = Sym.EMPTY;

    Sym[][] map;
    
    private String winner = "";

    public Level2(Game game) {
        super(game);
        
        tileHeight = 32;
        tileWidth = 32;
        
        //nonmovable = new ArrayList<>();
        movable = new ArrayList<>();
        tiles = new ArrayList<>();
        toolbarObj = new ArrayList<>();
        
        background = Resources.getImage(Resources.Images.BACKGROUND)[0];
        
        map = new Sym[][]{
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, O, i, i, i, i, i, i, i, i, i, i, i, i, X, X, X, X},
            {X, i, T, i, O, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, O, i, i, i, i, i, i, i, i, i, i, i, X, X, X, X, X},
            {X, i, i, i, O, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, O, i, i, i, i, i, i, i, i, i, i, X, X, X, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, O, i, i, i, i, i, i, i, i, i, X, X, X, X, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, O, O, i, i, i, O, i, i, i, i, i, i, i, i, X, X, X, X, X, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, O, O, i, i, i, O, i, i, i, i, i, i, i, X, X, X, X, X, X, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, X, X, X, i, i, i, i, i, O, O, i, i, i, i, i, i, i, i, i, i, X, X, X, X, X, X, X, X, X, X},
            {X, i, i, i, i, i, i, i, i, i, X, X, X, i, i, i, i, i, X, X, X, X, X, O, X, O, O, O, X, X, X, X, X, X, X, X, X, X, X, X},
            {X, i, i, i, O, i, i, i, i, i, i, i, i, i, i, i, i, X, X, X, X, X, X, X, X, O, O, O, X, X, X, X, i, i, i, i, i, i, i, X},
            {X, i, i, O, O, i, i, i, i, i, i, i, i, i, i, i, X, X, X, X, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, i, O, O, O, i, O, i, i, i, i, i, i, O, O, O, X, X, i, i, i, i, i, O, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, O, O, O, O, i, O, i, i, i, i, i, O, O, O, O, O, i, i, i, i, i, i, O, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, O, O, O, O, i, O, i, i, i, i, O, O, O, X, O, O, O, i, i, i, i, i, O, i, i, i, i, i, i, O, O, i, i, i, i, i, i, i, X},
            {X, O, O, O, O, i, O, i, i, X, O, O, O, X, X, X, O, O, O, i, i, i, i, O, X, i, i, i, i, i, O, O, i, i, i, i, i, i, i, X},
            {X, O, O, O, O, X, O, X, X, X, O, O, X, X, X, X, X, O, O, i, i, i, i, i, X, i, i, i, i, i, O, O, i, i, i, i, i, i, i, X},
            {X, O, O, O, O, X, O, X, X, X, O, O, O, X, X, X, O, O, O, i, i, i, i, i, X, i, i, i, i, i, O, O, i, i, i, i, t, i, i, X},
            {X, O, O, O, O, i, O, i, i, i, O, O, O, O, X, O, O, O, i, i, i, i, i, i, X, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, O, O, O, i, i, O, i, i, O, O, O, O, O, O, O, O, O, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, O, O, O, i, i, O, i, O, O, O, O, i, i, i, O, O, O, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, O, O, O, i, i, O, O, O, O, i, i, i, i, i, i, O, O, O, X, X, X, X, X, O, O, X, X, X, X, X, X, i, i, i, i, i, i, i, X},
            {X, O, O, O, i, i, O, O, O, i, i, i, i, i, i, i, i, O, X, X, X, X, X, X, O, O, X, X, X, X, X, X, i, i, i, i, i, i, i, X},
            {X, i, O, O, O, O, O, O, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, i, i, O, O, O, O, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, i, i, i, X, X, i, i, i, i, i, i, i, X},
            {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
        };
        
        decodeMap();
        
        miniMap = new MiniMap(240, 340, tiles);
        toolbarObj.add(miniMap);       
        
        decodeMiniMap();
        
        //Resources.playSound(Resources.Sounds.MUSIC);
    }

    @Override
    public void decodeMap() {
        int numTilesWidth = (32 * map[0].length) / 320;
        int numTilesHeight = (32 * map.length) / 480;
        int totTiles = numTilesWidth * numTilesHeight;
        
        for (int i = 0; i < totTiles; ++i) {
            tiles.add(new TankTile(i));
        }
        
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                int index = (j / 10) + (i / 15)*4;
                switch(map[i][j]) {
                    case WALL_S:
                        tiles.get(index).addObject(new WallSolid(
                                (j%10)*tileWidth,(i%15)*tileHeight));
                        break;
                    case WALL_B:
                        tiles.get(index).addObject(new WallBreakable(
                                (j%10)*tileWidth,(i%15)*tileHeight,
                                tiles.get(index)));
                        break;
                    case TANK1:
                        PlayerControls player1Ctl = new PlayerControls(
                            KeyEvent.VK_W,
                            KeyEvent.VK_S,
                            KeyEvent.VK_A,
                            KeyEvent.VK_D,
                            KeyEvent.VK_Q,
                            KeyEvent.VK_E);
                        player1 = (Tank) new Tank(
                                Resources.Images.TANK1,
                                i*tileHeight, i*tileHeight,
                                0, player1Ctl, this, "Player 2");
                        Toolbar p1health = new HealthBar(110, 400, player1);
                        Toolbar p1lives = new Lives(105, 430, 3);
                        Toolbar p1special = new Special(65, 430, 3);
                        toolbarObj.add(p1health);
                        toolbarObj.add(p1lives);
                        toolbarObj.add(p1special);
                        player1.addHealth(p1health);
                        player1.addLives(p1lives);
                        player1.addSpecial(p1special);
                        
                        break;
                    case TANK2:
                        PlayerControls player2Ctl = new PlayerControls(
                            KeyEvent.VK_UP,
                            KeyEvent.VK_DOWN,
                            KeyEvent.VK_LEFT,
                            KeyEvent.VK_RIGHT,
                            KeyEvent.VK_PERIOD,
                            KeyEvent.VK_SLASH);
                        player2 = (Tank) new Tank(
                                Resources.Images.TANK2,
                                i*tileHeight, i*tileHeight,
                                0, player2Ctl, this, "Player 1");
                        Toolbar p2health = new HealthBar(430, 400, player2);
                        Toolbar p2lives = new Lives(425, 430, 3);
                        Toolbar p2special = new Special(545, 430, 3);
                        toolbarObj.add(p2health);
                        toolbarObj.add(p2lives);
                        toolbarObj.add(p2special);
                        player2.addHealth(p2health);
                        player2.addLives(p2lives);
                        player2.addSpecial(p2special);
                        break;
                }
            }
        }
    }
    
    @Override
    public void decodeMiniMap() {
        miniMap.redraw();
    } 
    
    @Override
    public void nextMap(){
        Map nextMap = new Level2(game);
        game.setMap(nextMap);
        nextMap.registerMovable(game.geyKeyObs());
    }
    
    public void displayRetryMenu(String winner) {
        player1.disable();
        player2.disable();
        MenuButton retry = new MenuButton(220, 150, false, this, winner);
        game.geyKeyObs().addObserver(retry);
        this.addToolbar(retry);
    }
}
