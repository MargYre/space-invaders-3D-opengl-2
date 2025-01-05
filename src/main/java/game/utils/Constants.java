package main.java.game.utils;

public class Constants {
    // Dimensions de la fenêtre
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    // Paramètres du joueur
    public static final float PLAYER_SPEED = 0.1f;
    public static final float PLAYER_START_X = 0.0f;
    public static final float PLAYER_Y = -4.0f;
    public static final float PLAYER_WIDTH = 0.5f;
    public static final float PLAYER_HEIGHT = 0.2f;
    
    // Paramètres des aliens
    public static final int ALIEN_ROWS = 3;
    public static final int ALIEN_COLS = 8;
    public static final float ALIEN_SPACING_X = 1.5f;
    public static final float ALIEN_SPACING_Y = 1.2f;
    public static final float ALIEN_START_Y = 3.0f;
    public static final float ALIEN_SIZE = 0.4f;
    public static final float ALIEN_MOVEMENT_SPEED = 0.5f;
    public static final float ALIEN_AMPLITUDE = 0.5f;
    
    // Paramètres des projectiles
    public static final float PROJECTILE_SPEED = 10.0f;
    public static final float PROJECTILE_SIZE = 0.1f;
    public static final float PROJECTILE_COLLISION_DISTANCE = 0.5f;
    
    // Paramètres de la caméra
    public static final float CAMERA_Z = -15.0f;
    public static final float CAMERA_Y = -2.0f;
    public static final float NEAR_PLANE = 5.0f;
    public static final float FAR_PLANE = 60.0f;
    
    // Paramètres de score
    public static final int ALIEN_KILL_SCORE = 100;
    
    // Limites du jeu
    public static final float GAME_BOUNDARY_LEFT = -5.0f;
    public static final float GAME_BOUNDARY_RIGHT = 5.0f;
    public static final float PROJECTILE_MAX_Y = 10.0f;
    
    // Paramètres d'animation
    public static final int FPS = 60;
    
    // Couleurs (RGB)
    public static final float[] COLOR_PLAYER = {0.0f, 1.0f, 0.0f};  // Vert
    public static final float[] COLOR_ALIEN = {1.0f, 0.0f, 0.0f};   // Rouge
    public static final float[] COLOR_PROJECTILE = {1.0f, 1.0f, 0.0f}; // Jaune
    public static final float[] COLOR_BACKGROUND = {0.0f, 0.0f, 0.1f}; // Bleu foncé
    
    // Paramètres de lumière
    public static final float[] LIGHT_POSITION = {0.0f, 10.0f, 0.0f, 1.0f};
    
    private Constants() {
        // Constructeur privé pour empêcher l'instanciation
    }
}