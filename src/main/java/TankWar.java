import javax.swing.*;

public class TankWar {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        JFrame frame2=new JFrame();
        frame.add(new GameClient(900,500));
        frame.setTitle("TankGame Trial");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame2.add(new GameClient(200,500));
        frame2.setTitle("Status");
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
    }
}
