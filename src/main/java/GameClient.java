import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;

    public GameClient(int SHeight, int SWidth) {
        this.SHeight = SHeight;
        this.SWidth = SWidth;
        this.setPreferredSize(new Dimension(SHeight,SWidth));
    }

    GameClient(){
        this(800,600);
    }
}
