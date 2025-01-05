import com.jogamp.opengl.*;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.ArrayList;
import java.util.Iterator;

public class SpaceInvadersRenderer implements GLEventListener {
    private float playerX = 0.0f;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private final float PLAYER_SPEED = 0.1f;
    private GLUT glut;
    private long lastTime = System.currentTimeMillis();
    private int score = 0;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.1f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        
        // Initialisation du GLUT pour les formes 3D
        glut = new GLUT();
        
        // Création des aliens
        initializeAliens();
    }

    private void initializeAliens() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                float x = (col - 4) * 1.5f;
                float y = 3.0f - row * 1.2f;
                aliens.add(new Alien(x, y));
            }
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Configuration de la caméra
        gl.glTranslatef(0.0f, -2.0f, -15.0f);
        
        // Configuration de la lumière
        float[] lightPosition = {0.0f, 10.0f, 0.0f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);

        // Mise à jour du jeu
        updateGame();
        
        // Rendu des éléments
        renderPlayer(gl);
        renderAliens(gl);
        renderProjectiles(gl);
    }

    private void updateGame() {
        long currentTime = System.currentTimeMillis();
        float deltaTime = (currentTime - lastTime) / 1000.0f;
        lastTime = currentTime;

        // Mise à jour des projectiles
        Iterator<Projectile> projIter = projectiles.iterator();
        while (projIter.hasNext()) {
            Projectile proj = projIter.next();
            proj.update(deltaTime);
            
            // Vérification des collisions avec les aliens
            Iterator<Alien> alienIter = aliens.iterator();
            while (alienIter.hasNext()) {
                Alien alien = alienIter.next();
                if (proj.checkCollision(alien)) {
                    alienIter.remove();
                    projIter.remove();
                    score += 100;
                    break;
                }
            }
            
            // Suppression des projectiles hors écran
            if (proj.isOutOfBounds()) {
                projIter.remove();
            }
        }

        // Mise à jour des aliens
        for (Alien alien : aliens) {
            alien.update(deltaTime);
        }
    }

    private void renderPlayer(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(playerX, -4.0f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        
        // Dessin du vaisseau joueur
        gl.glPushMatrix();
        gl.glScalef(0.5f, 0.2f, 0.5f);
        glut.glutSolidCube(1.0f);
        gl.glPopMatrix();
        
        gl.glPopMatrix();
    }

    private void renderAliens(GL2 gl) {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        for (Alien alien : aliens) {
            gl.glPushMatrix();
            gl.glTranslatef(alien.x, alien.y, 0.0f);
            gl.glScalef(0.4f, 0.4f, 0.4f);
            glut.glutSolidSphere(0.5, 10, 10);
            gl.glPopMatrix();
        }
    }

    private void renderProjectiles(GL2 gl) {
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        for (Projectile proj : projectiles) {
            gl.glPushMatrix();
            gl.glTranslatef(proj.x, proj.y, 0.0f);
            glut.glutSolidSphere(0.1, 8, 8);
            gl.glPopMatrix();
        }
    }

    public void movePlayer(float direction) {
        playerX += direction * PLAYER_SPEED;
        playerX = Math.max(-5.0f, Math.min(5.0f, playerX));
    }

    public void shoot() {
        projectiles.add(new Projectile(playerX, -4.0f));
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        float aspect = (float) width / height;
        gl.glFrustum(-aspect, aspect, -1.0, 1.0, 5.0, 60.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}
}