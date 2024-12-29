package com.example;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.example.game.core.Game;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        // Obtient le profil GL par défaut
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        
        // Crée le canvas GL
        GLCanvas canvas = new GLCanvas(capabilities);
        Game game = new Game();
        canvas.addGLEventListener(game);
        
        // Crée la fenêtre
        JFrame frame = new JFrame("Space Invaders");
        frame.setSize(800, 600);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}