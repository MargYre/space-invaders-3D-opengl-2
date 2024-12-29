package com.example;

public class Bullet {
    private float x, y, z;
    private float speed = 0.2f;
    private boolean active = true;
    
    public void update() {
        y += speed;  // Monte vers le haut
    }
    
    public void draw(GL2 gl) {
        // Dessin du projectile
    }
}