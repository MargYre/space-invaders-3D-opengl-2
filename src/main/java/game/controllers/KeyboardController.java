package main.java.game.controllers;

import java.awt.event.*;
import main.java.game.renderer.SpaceInvadersRenderer;

public class KeyboardController extends KeyAdapter {
    private SpaceInvadersRenderer renderer;

    public KeyboardController(SpaceInvadersRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                renderer.movePlayer(-1.0f);
                break;
            case KeyEvent.VK_RIGHT:
                renderer.movePlayer(1.0f);
                break;
            case KeyEvent.VK_SPACE:
                renderer.shoot();
                break;
        }
    }
}