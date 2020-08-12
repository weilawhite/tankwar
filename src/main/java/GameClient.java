import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;
    private Tank pTank;
    private List<Tank> eTanks = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<GameObject> gameObjects = new ArrayList<>();
    public static Image[] bulletImage = new Image[8];
    public static Image[] bricksImage = {Tools.getImage("brick.png")};
    public static Image[] pTankImage = new Image[8];
    public static Image[] eTankImage = new Image[8];
    String[] sub = {"U.png", "RU.png", "R.png", "RD.png", "D.png", "LD.png", "L.png", "LU.png"};

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
        for (int i = 0; i < 8; i++) {
            pTankImage[i] = Tools.getImage("iTank" + sub[i]);
            eTankImage[i] = Tools.getImage("eTank" + sub[i]);
            bulletImage[i] = Tools.getImage("missile" + sub[i]);
        }
        pTank = new Tank(500, 200, Direction.DOWN, pTankImage);
        pTank.setSpeed(10);
        gameObjects.add(pTank);
        gameReset();
        gameObjects.add(new Wall(250, 150, 15, true, bricksImage));
        gameObjects.add(new Wall(150, 200, 15, false, bricksImage));
        gameObjects.add(new Wall(800, 200, 15, false, bricksImage));

    }

    GameClient() {
        this(800, 600);
    }


    //遊戲重置(包含起始) 設定敵人位置 移除所有子彈 設定玩家位置
    public void gameReset() {


        Iterator<GameObject> iterator1 = gameObjects.iterator();
        while (iterator1.hasNext()) {
            if (iterator1.next() instanceof Orb) {
                iterator1.remove();
            }
        }

        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Bullet) {
                iterator.remove();
            }
        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                gameObjects.add(new Tank(350 + i * 80, 500 + j * 80, Direction.UP, true, eTankImage));
            }
        }

        pTank.setX(500);
        pTank.setY(200);
        pTank.setDirection(Direction.DOWN);

    }

    public int getSHeight() {
        return SHeight;
    }

    public int getSWidth() {
        return SWidth;
    }

    //判斷條件:剩餘物件之中找到屬於Tank物件,但不是友方Tank,則認定沒有贏
    //如果贏了則執行遊戲重置
    public void checkWin() {
        boolean gameWin = true;
        for (GameObject object : gameObjects) {
            if (object instanceof Tank && object != pTank /*&& !(object instanceof Bullet)*/) {
                gameWin = false;
                break;
            }
            if (object instanceof Orb) {
                gameWin = false;
                break;
            }

        }

        if (gameWin) {
            System.out.println("finish");
            System.out.println("請稍等重置......");


            gameReset();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, getSWidth(), getSHeight());
        for (GameObject gameobject : gameObjects) {
            gameobject.draw(g);
        }
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            if (!(iterator.next().alive)) {
                iterator.remove();
            }
        }
        checkWin();
        //System.out.println(gameObjects.size());
    }

    //CTRL:一般射擊 Q:八向射擊 W:速射 E:三向射擊
    public void keyPressed(KeyEvent e) {
        boolean[] dirs = pTank.getDirs();
        checkWin();
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
                pTank.fire();
                System.out.println("Fire!");
                break;
            case KeyEvent.VK_Q:
                pTank.eightDirectionFire();
                System.out.println("8 Fire!");
                break;
            case KeyEvent.VK_W:
                pTank.fastFire();
                System.out.println("Fast Fire!");
                break;
            case KeyEvent.VK_E:
                pTank.tripleFire();
                System.out.println("3 Fire!");
                break;
            case KeyEvent.VK_R:
                pTank.frozenOrb();
                System.out.println("Orb");
                break;
        }
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

    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }
}
