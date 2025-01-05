package main.java.game.entities;

public class Projectile {
    public float x, y;
    private final float SPEED = 10.0f;

    public Projectile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float deltaTime) {
        y += SPEED * deltaTime;
    }

    public boolean isOutOfBounds() {
        return y > 10.0f;
    }

    public boolean checkCollision(Alien alien) {
        float dx = x - alien.x;
        float dy = y - alien.y;
        return Math.sqrt(dx * dx + dy * dy) < 0.5f;
    }
}