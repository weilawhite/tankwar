import javax.swing.*;

public class Tank {
    private JButton yesButton;
    private JButton noButton;
    private JPanel Test;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tank");
        frame.setContentPane(new Tank().Test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
