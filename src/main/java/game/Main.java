package main.java.game;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;
import java.awt.event.*;

import main.java.game.renderer.SpaceInvadersRenderer;
import main.java.game.controllers.KeyboardController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configuration de la fenêtre principale
            JFrame frame = new JFrame("Space Invaders 3D");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Configuration du contexte OpenGL
            GLProfile glProfile = GLProfile.getDefault();
            GLCapabilities glCapabilities = new GLCapabilities(glProfile);
            GLCanvas canvas = new GLCanvas(glCapabilities);

            // Création du renderer et ajout des listeners
            SpaceInvadersRenderer renderer = new SpaceInvadersRenderer();
            canvas.addGLEventListener(renderer);
            
            // Ajout des contrôles clavier
            KeyboardController controller = new KeyboardController(renderer);
            canvas.addKeyListener(controller);
            canvas.setFocusable(true);

            // Configuration de l'animateur
            FPSAnimator animator = new FPSAnimator(canvas, 60);
            
            frame.add(canvas);
            frame.setVisible(true);
            animator.start();
        });
    }
}