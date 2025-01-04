package com.example;

import com.jogamp.opengl.GL2;
import java.nio.FloatBuffer;
import com.jogamp.common.nio.Buffers;

public class Player {
    private float x, y, z;
    private float speed = 0.02f;
    private static FloatBuffer vertexBuffer;
    
    // Les sommets du vaisseau (triangle simple)
    private static final float[] VERTICES = {
        -0.05f, -0.05f, 0.0f,  // Gauche
         0.05f, -0.05f, 0.0f,  // Droite
         0.0f,   0.05f, 0.0f   // Sommet
    };

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.z = 0;
        
        // Initialise le buffer une seule fois
        if (vertexBuffer == null) {
            vertexBuffer = Buffers.newDirectFloatBuffer(VERTICES);
        }
    }

    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        
        // Couleur du vaisseau (blanc)
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexBuffer);

        // Dessine le vaisseau comme un triangle
        gl.glDrawArrays(GL2.GL_TRIANGLES, 0, 3);

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }

    public void moveLeft() {
        x -= speed;
        // Limite les mouvements pour rester dans l'écran
        if (x < -0.95f) x = -0.95f;
    }

    public void moveRight() {
        x += speed;
        // Limite les mouvements pour rester dans l'écran
        if (x > 0.95f) x = 0.95f;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    // Méthode pour tirer
    public Bullet shoot() {
        return new Bullet(x, y + 0.1f);
    }
}