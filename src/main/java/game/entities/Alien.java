package main.java.game.entities;

public class Alien {
    public float x, y;
    //private float direction = 1.0f;
    private float speed = 0.5f;
    private float amplitude = 0.5f;
    private float originalX;
    private float time = 0;

    public Alien(float x, float y) {
        this.x = x;
        this.y = y;
        this.originalX = x;
    }

    public void update(float deltaTime) {
        time += deltaTime;
        x = originalX + (float)Math.sin(time) * amplitude;
        y -= speed * deltaTime * 0.1f;
    }
}