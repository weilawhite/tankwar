import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankWar {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JFrame frame2=new JFrame();

        final int Msw = 1024;
        final int Msh = 768;
        GameClient gameClient = new GameClient(Msw, Msh);
        frame.add(gameClient);
        //frame.setResizable(false);
        //frame.add(new GameClient(Msw,Msh));
        frame.setTitle("TankGame Trial");
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameClient.repaint();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
                int p=e.getKeyCode();
                System.out.println(p);
                switch (p){
                    case 38:;
                    case 40:;
                    case 37:;
                    case 39:;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
/*
        frame2.add(new GameClient(200,500));
        frame2.setLocation(Msw,0);
        //附著在主視窗右側
        //frame2.setLocationRelativeTo(frame);
        frame2.setTitle("Status");
        frame2.setVisible(true);
        //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
*/
    }
}
