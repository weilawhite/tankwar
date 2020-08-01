import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;
    // private int speed;
    private Tank pTank;

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
        pTank.setSpeed(5);
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
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs=pTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //pTank.setDirection(Direction.UP);
                dirs[0]=true;
                break;
            case KeyEvent.VK_DOWN:
                //pTank.setDirection(Direction.DOWN);
                dirs[1]=true;
                break;
            case KeyEvent.VK_LEFT:
                //pTank.setDirection(Direction.LEFT);
                dirs[2]=true;
                break;
            case KeyEvent.VK_RIGHT:
                //pTank.setDirection(Direction.RIGHT);
                dirs[3]=true;
                break;
        }
        //  repaint();
        //pTank.move();
    }


    public void keyReleased(KeyEvent e) {
        boolean[] dirs=pTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //pTank.setDirection(Direction.UP);
                dirs[0]=false;
                break;
            case KeyEvent.VK_DOWN:
                //pTank.setDirection(Direction.DOWN);
                dirs[1]=false;
                break;
            case KeyEvent.VK_LEFT:
                //pTank.setDirection(Direction.LEFT);
                dirs[2]=false;
                break;
            case KeyEvent.VK_RIGHT:
                //pTank.setDirection(Direction.RIGHT);
                dirs[3]=false;
                break;
        }
        //  repaint();
        //pTank.move();
    }
}
