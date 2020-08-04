import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;
    private Tank pTank;
    private List<Tank> eTanks = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();

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
        pTank = new Tank(500, 200, Direction.UP);
        pTank.setSpeed(10);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                eTanks.add(new Tank(350 + i * 80, 500 + j * 80, Direction.UP, true));
            }
        }
        /*eTanks.add(new Tank(200,100,Direction.UP));
        eTanks.add(new Tank(300,100,Direction.UP));*/
        walls.add(new Wall(250, 150, 15, true));
        walls.add(new Wall(150, 200, 15, false));
        walls.add(new Wall(800, 200, 15, false));

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
        //g.drawImage(new ImageIcon("assets\\images\\itankD.png").getImage(), 400, 500, null);
        //g.drawImage(pTank.getImage(), pTank.getX(), pTank.getY(), null);
        pTank.draw(g);

        for (Tank tank : eTanks
        ) {
            tank.draw(g);
        }
        for (Wall wall : walls
        ) {
            wall.draw(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = pTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //pTank.setDirection(Direction.UP);
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                //pTank.setDirection(Direction.DOWN);
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                //pTank.setDirection(Direction.LEFT);
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                //pTank.setDirection(Direction.RIGHT);
                dirs[3] = true;
                break;
        }
        //  repaint();
        //pTank.move();
    }


    public void keyReleased(KeyEvent e) {
        boolean[] dirs = pTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //pTank.setDirection(Direction.UP);
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                //pTank.setDirection(Direction.DOWN);
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                //pTank.setDirection(Direction.LEFT);
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                //pTank.setDirection(Direction.RIGHT);
                dirs[3] = false;
                break;
        }
        //  repaint();
        //pTank.move();
    }
}
