import javax.swing.*;
import java.awt.*;

public class Tools {
    public static Image getImage(String filename){
        return new ImageIcon("assets\\images\\"+filename).getImage();
    }
}
