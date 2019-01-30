package TankGameTeam07;

import GameEngine.*;
import TankGameTeam07.Maps.*;

public class TankGame {
    Game game;
    
    TankGame() {
        game = new Game(640, 480);
    }
    
    public static void main(String args[]) {
        TankGame tg = new TankGame();
    }
}
