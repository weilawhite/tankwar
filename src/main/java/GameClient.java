import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int SHeight;
    private int SWidth;

    public GameClient(int SWidth, int SHeight) {
        this.SHeight = SHeight;
        this.SWidth = SWidth;
        this.setPreferredSize(new Dimension(SWidth,SHeight));
    }

    GameClient(){
        this(800,600);
    }
}
