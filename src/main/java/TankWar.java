import javax.swing.*;

public class TankWar {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        JFrame frame2=new JFrame();

        final int Msw=900;
        final int Msh=500;

        frame.add(new GameClient(Msw,Msh));
        frame.setTitle("TankGame Trial");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame2.add(new GameClient(400,500));
        frame2.setLocation(Msw,0);
        //附著在主視窗右側
        //frame2.setLocationRelativeTo(frame);
        frame2.setTitle("Status");
        frame2.setVisible(true);
        //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();

    }
}
