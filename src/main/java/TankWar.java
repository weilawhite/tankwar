import javax.swing.*;

public class TankWar {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.add(new GameClient(900,500));
        frame.setTitle("TankGame");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
