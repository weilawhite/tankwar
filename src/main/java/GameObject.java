import java.awt.*;

public abstract class GameObject {
    protected int x,y;
    protected Image image;

    public GameObject(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public abstract void draw(Graphics g);
}
