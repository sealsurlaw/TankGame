package GameEngine;

import java.awt.event.KeyEvent;

public class PlayerControls {
    final public int UP;
    final public int DOWN;
    final public int LEFT;
    final public int RIGHT;
    final public int SHOOT;
    final public int SPECIAL;
    final public int ESC;
    
    // Specifies controls for different instances of the same class (i.e. Tank)
    public PlayerControls(int up, int down, int left, int right, int shoot, int special) {
        UP = up;
        DOWN = down;
        LEFT = left;
        RIGHT = right;
        SHOOT = shoot;
        SPECIAL = special;
        ESC = KeyEvent.VK_ESCAPE;
    }
}