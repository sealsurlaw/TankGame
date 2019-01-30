package GameEngine;

import TankGameTeam07.Resources;
import TankGameTeam07.Maps.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame {
    Map map;
    KeyboardObservable keyObs;
    
    public Game(int windowWidth, int windowHeight) {
        // Add title
        super("Tank Wars");
        
        // Contruct with initial map (probably title screen)
        map = new TitleScreen(this);
        
        setupGUI(windowHeight, windowWidth);
        
        // Create a keyboard observer and passes it to the JFrame KeyListener
        keyObs = new KeyboardObservable();
        addKeyListener(keyObs.getKeyAdapter());
        
        // TODO Add movable objects to keyObs
        // e.g. for loop:
        // keyObs.addObserver(movableObj[i]);
        map.registerMovable(keyObs);
        
        // Timer to control redraw and gameloop events
        // 40ms ~ 25 FPS
        Timer gameLoopTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                // Run game loop
                Game.this.gameLoop();
                // Redraw the screen
                Game.this.repaint();
            }
        });
        
        // Start the timer
        gameLoopTimer.start();
    }
    
    // Takes over the JFrame graphics context
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        map.draw(g2, 1280, 960);
    }

    // Sets up JFrame
    private void setupGUI(int height, int width) {
        // Make window visible
        setVisible(true);
        // Window height and width ((not in that order...))
        setSize(width, height);
        // Places window in the center of the screen
        setLocationRelativeTo(null);
        // Set background, usually drawn over
        // Color set to the same color as title.png background
        setBackground(new Color(127, 122, 83));
        // User can't resize window
        setResizable(false);
        
        // Icon *.ico file converted to *.png to work
        ImageIcon icon = new ImageIcon("img/iconImage.png");
        setIconImage(icon.getImage());
        
        // When the user presses the X, close Java
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void gameLoop() {
        // Processes all actions of objects on map
        map.update();
        
        // Processes all collisions between objects
        map.collisions();
    }
    
    public void setMap(Map map){
        this.map = map;
    }
    
    public KeyboardObservable geyKeyObs() {
        return keyObs;
    }
}
