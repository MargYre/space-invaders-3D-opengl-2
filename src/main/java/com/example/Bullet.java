package com.example;

import com.jogamp.opengl.GL2;
import java.nio.FloatBuffer;
import com.jogamp.common.nio.Buffers;

public class Bullet {
    private float x, y, z;
    private float speed = 0.01f;
    private boolean active = true;
    private static FloatBuffer vertexBuffer;
    
    private static final float[] VERTICES = {
        -0.01f, -0.01f, 0.0f,
         0.01f, -0.01f, 0.0f,
         0.01f,  0.01f, 0.0f,
        -0.01f,  0.01f, 0.0f
    };

    public Bullet(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.z = 0;
        
        if (vertexBuffer == null) {
            vertexBuffer = Buffers.newDirectFloatBuffer(VERTICES);
        }
    }

    public void draw(GL2 gl) {
        if (!active) return;

        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        
        // Couleur du projectile (jaune)
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexBuffer);

        // Dessine le projectile comme un quad
        gl.glDrawArrays(GL2.GL_QUADS, 0, 4);

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }

    public void update() {
        if (!active) return;
        y += speed;
        
        // Désactive le projectile s'il sort de l'écran
        if (y > 1.0f) active = false;
    }

    public boolean isActive() { return active; }
    public float getX() { return x; }
    public float getY() { return y; }
    public void deactivate() { active = false; }
}