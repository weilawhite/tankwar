import java.awt.*;

public class Explosion extends GameObject {

    public Explosion(int x, int y, Image[] image) {
        super(x, y, image);
        new Thread(() -> {
            while (alive) {
                if (++frame > image.length-1) {
                    alive = false;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.drawImage(image[frame], x, y, null);
        }
    }
}
