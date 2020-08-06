import java.awt.*;

public class Bullet extends Tank {
    public Bullet(int x, int y, Direction direction, Image[] image) {
        super(x, y, direction, image);
    }

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed=15;
    }

    @Override
    public void draw(Graphics g) {
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public void collision() {

    }
}
