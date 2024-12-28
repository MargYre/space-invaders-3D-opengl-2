package com.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class App {
    private long window;

    public void run() {
        try {
            System.out.println("Démarrage de l'application");
            init();
            loop();
            cleanup();
        } catch (Exception e) {
            System.err.println("Erreur critique:");
            e.printStackTrace();
        }
    }

    private void init() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        // Création de la fenêtre
        window = GLFW.glfwCreateWindow(800, 600, "OpenGL Test", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Configuration du contexte OpenGL
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1); // Enable v-sync
        GLFW.glfwShowWindow(window);
        
        // Cette ligne est CRUCIALE - elle initialise les capacités OpenGL
        GL.createCapabilities();
        
        // Configuration de la vue 3D
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glFrustum(-1, 1, -1, 1, 1.5, 20.0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
        // Position de la caméra
        glTranslatef(0, 0, -3);
        
        // Activation du test de profondeur
        glEnable(GL_DEPTH_TEST);
    }

    private void loop() {
        // Angle de rotation
        float angle = 0;
        
        while (!GLFW.glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Sauvegarde de la matrice courante
            glPushMatrix();
            
            // Rotation du triangle
            glRotatef(angle, 0, 1, 0);  // Rotation autour de l'axe Y
            
            // Dessin du triangle
            glBegin(GL_TRIANGLES);
                // Sommet rouge (en haut)
                glColor3f(1.0f, 0.0f, 0.0f);    // Rouge
                glVertex3f(0.0f, 1.0f, 0.0f);    // Sommet haut
                
                // Sommet vert (en bas à droite)
                glColor3f(0.0f, 1.0f, 0.0f);    // Vert
                glVertex3f(1.0f, -1.0f, 0.0f);   // Sommet droit
                
                // Sommet bleu (en bas à gauche)
                glColor3f(0.0f, 0.0f, 1.0f);    // Bleu
                glVertex3f(-1.0f, -1.0f, 0.0f);  // Sommet gauche
            glEnd();
            
            // Restauration de la matrice
            glPopMatrix();
            
            // Mise à jour de l'angle de rotation
            angle += 0.5f;
            if (angle >= 360.0f) {
                angle = 0;
            }
            
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    private void cleanup() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public static void main(String[] args) {
        new App().run();
    }
}