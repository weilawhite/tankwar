import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;
    private Tank pTank;

    public GameClient(int SWidth, int SHeight) {
        this.SHeight = SHeight;
        this.SWidth = SWidth;
        this.setPreferredSize(new Dimension(SWidth, SHeight));
        init();
    }

    private void init() {
        pTank = new Tank(500, 200, Direction.UP);

    }

    GameClient() {
        this(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        //g.drawImage(new ImageIcon("assets\\images\\itankD.png").getImage(), 400, 500, null);
        g.drawImage(pTank.getImage(), pTank.getX(), pTank.getY(), null);
    }

}
