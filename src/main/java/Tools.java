import javax.swing.*;
import java.awt.*;
import java.io.File;
import javafx.scene.media.*;


public class Tools {
    public static Image getImage(String filename) {
        return new ImageIcon("assets\\images\\" + filename).getImage();
    }

    public static void playAudio(String filename) {
        Media sound =new Media(new File("assets/audios/" + filename).toURI().toString());
        MediaPlayer mediaPlayer =new MediaPlayer(sound);
        mediaPlayer.play();

    }
}
