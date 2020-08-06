import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;
    private Tank pTank;
    private List<Tank> eTanks = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<GameObject> gameObjects = new ArrayList<>();
    public static Image[] bulletImage=new Image[8];
    //private List

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameClient(int SWidth, int SHeight) {
        this.SHeight = SHeight;
        this.SWidth = SWidth;
        this.setPreferredSize(new Dimension(SWidth, SHeight));
        init();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }).start();
    }

    private void init() {

        Image[] bricksImage = {Tools.getImage("brick.png")};
        Image[] pTankImage = new Image[8];
        Image[] eTankImage = new Image[8];
        String[] sub = {"U.png", "D.png", "L.png", "R.png", "LU.png", "LD.png", "RU.png", "RD.png"};

        for (int i = 0; i < 8; i++) {
            pTankImage[i] = Tools.getImage("iTank" + sub[i]);
            eTankImage[i] = Tools.getImage("eTank" + sub[i]);
            bulletImage[i]=Tools.getImage("missile"+sub[i]);
        }

        pTank = new Tank(500, 200, Direction.UP, pTankImage);
        pTank.setSpeed(10);
        gameObjects.add(pTank);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                gameObjects.add(new Tank(350 + i * 80, 500 + j * 80, Direction.UP, true, eTankImage));
            }
        }

        gameObjects.add(new Wall(250, 150, 15, true, bricksImage));
        gameObjects.add(new Wall(150, 200, 15, false, bricksImage));
        gameObjects.add(new Wall(800, 200, 15, false, bricksImage));

    }

    GameClient() {
        this(800, 600);
    }

    public int getSHeight() {
        return SHeight;
    }

    public int getSWidth() {
        return SWidth;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, getSWidth(), getSHeight());
        for (GameObject gameobject : gameObjects) {
            gameobject.draw(g);
        }

    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = pTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
            case KeyEvent.VK_CONTROL:
                System.out.println("Fire!");
                break;
            case KeyEvent.VK_0:
                System.out.println("0");
                break;
        }
    }

    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = pTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }

    }
}
