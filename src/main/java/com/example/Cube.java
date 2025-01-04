package com.example;

import com.jogamp.opengl.GL2;
import java.nio.FloatBuffer;
import com.jogamp.common.nio.Buffers;

public class Cube {
    private float x, y, z;
    private float rotationY;
    private float size;
    private boolean isAlive;
    private static FloatBuffer vertexBuffer;

    // Les sommets d'un cube
    private static final float[] VERTICES = {
        // Face avant
        -1, -1,  1,
         1, -1,  1,
         1,  1,  1,
        -1,  1,  1,
        // Face arrière
        -1, -1, -1,
        -1,  1, -1,
         1,  1, -1,
         1, -1, -1,
    };

    public Cube(float x, float y, float z, float size) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.isAlive = true;
        
        // Initialise le buffer une seule fois pour tous les cubes
        if (vertexBuffer == null) {
            vertexBuffer = Buffers.newDirectFloatBuffer(VERTICES);
        }
    }

    public void draw(GL2 gl) {
        if (!isAlive) return;

        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glRotatef(rotationY, 0, 1, 0);
        gl.glScalef(size, size, size);
        
        gl.glColor3f(1.0f, 0.0f, 0.0f);  // Rouge
        
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexBuffer);

        // Dessine les faces du cube en utilisant les indices
        gl.glDrawArrays(GL2.GL_QUADS, 0, 8);

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }

    public void update() {
        if (!isAlive) return;
        rotationY += 1.0f;
        if (rotationY >= 360.0f) {
            rotationY = 0.0f;
        }
    }

    public boolean isAlive() { return isAlive; }
    public void destroy() { this.isAlive = false; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getSize() { return size; }
}