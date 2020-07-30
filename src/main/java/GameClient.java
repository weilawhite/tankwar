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
        g.drawImage(pTank.getImage(), pTank.getX(), pTank.getY(), null);
    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pTank.setDirection(Direction.UP);
                break;

            case KeyEvent.VK_DOWN:
                pTank.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                pTank.setDirection(Direction.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                pTank.setDirection(Direction.RIGHT);
                break;


        }
        //  repaint();
        pTank.move();
    }

}
