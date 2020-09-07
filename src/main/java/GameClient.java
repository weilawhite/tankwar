import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;
    private Tank pTank;
    private List<Tank> eTanks = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    public static Image[] bulletImage = new Image[8];
    public static Image[] bricksImage = {Tools.getImage("brick.png")};
    public static Image[] pTankImage = new Image[8];
    public static Image[] eTankImage = new Image[8];
    public static Image[] explosionImage = new Image[11];
    String[] sub = {"U.png", "RU.png", "R.png", "RD.png", "D.png", "LD.png", "L.png", "LU.png"};
    boolean over = false;

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameClient(int SWidth, int SHeight) {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        this.SHeight = SHeight;
        this.SWidth = SWidth;
        this.setPreferredSize(new Dimension(SWidth, SHeight));
        loadImages();
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
        over = false;
        gameObjects.clear();

        pTank = new Tank(500, 0, Direction.DOWN,false, pTankImage,5);
        pTank.setSpeed(10);
        gameObjects.add(pTank);
        setEnemy();
        setWalls();

    }

    GameClient() {
        this(800, 600);
    }

    private void loadImages(){
        for (int i = 0; i < 8; i++) {
            pTankImage[i] = Tools.getImage("iTank" + sub[i]);
            eTankImage[i] = Tools.getImage("eTank" + sub[i]);
            bulletImage[i] = Tools.getImage("missile" + sub[i]);
        }
        for (int i = 0; i < 11; i++) {
            explosionImage[i] = Tools.getImage(i + ".png");
        }
    }

    private void setWalls(){
        gameObjects.add(new Wall(250, 150, 15, true, bricksImage));
        gameObjects.add(new Wall(150, 200, 15, false, bricksImage));
        gameObjects.add(new Wall(800, 200, 15, false, bricksImage));
    }

    //設定敵人位置
    private void setEnemy() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                gameObjects.add(new EnemyTank(350 + i * 80, 500 + j * 80, Direction.UP, true, eTankImage,3));
            }
        }
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
            init();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, getSWidth(), getSHeight());
        for (GameObject gameobject : gameObjects) {
            gameobject.draw(g);
        }

        for (GameObject object : gameObjects) {
            if (!object.alive) {
                gameObjects.remove(object);
            }
        }
        /*
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            if (!(iterator.next().alive)) {
                iterator.remove();
            }
        }
        */
        checkStatus();

        if(over){
            g.setFont(new Font(null,Font.BOLD,100));
            g.setColor(Color.RED);
            g.drawString("HA NOOB !",150,300);
        }else{
            g.setFont(new Font(null,Font.BOLD,50));
            g.setColor(Color.RED);
            g.drawString("HP:"+pTank.hp,50,50);

        }
        checkWin();
        //System.out.println(gameObjects.size());
    }

    private void checkStatus() {
        if (pTank.alive == false) {
            over = true;
        }
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
                if (!over) {
                    pTank.fire();
                    System.out.println("Fire!");
                }
                break;
            case KeyEvent.VK_Q:
                if (!over) {
                    pTank.superFire();
                    System.out.println("8 Fire!");
                }
                break;
            case KeyEvent.VK_W:
                if (!over) {
                    pTank.fastFire();
                    System.out.println("Fast Fire!");
                }
                break;
            case KeyEvent.VK_E:
                if (!over) {
                    pTank.tripleFire();
                    System.out.println("3 Fire!");
                }
                break;
            case KeyEvent.VK_R:
                if (!over) {
                    pTank.frozenOrb();
                    System.out.println("Orb");
                }
                break;
            case KeyEvent.VK_F2:
                if (over) {
                    init();
                    over = false;
                }
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
