import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankWar {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JFrame frame2 = new JFrame();

        //主視窗寬度 Msw 1024 主視窗高度 Msh 768
        int Msw = 1024;
        int Msh = 768;
        final GameClient gameClient = new GameClient(Msw, Msh);
        final Status status=new Status(150,150);

        frame.add(gameClient);
        frame.setTitle("TankGame Trial");
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameClient.keyPressed(e);
                status.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });

        frame2.add(status);
        frame2.setLocation(Msw,0);
        //附著在主視窗右側
        frame2.setTitle("Status");
        frame2.setVisible(true);
        frame2.pack();

    }
}
