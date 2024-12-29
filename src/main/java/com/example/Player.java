package com.example;

public class Player {
    private float x, y, z;
    private float speed = 0.1f;
    
    public void moveLeft() {
        x -= speed;
    }
    
    public void moveRight() {
        x += speed;
    }
    
    public void draw(GL2 gl) {
        // Dessin du vaisseau
    }
}