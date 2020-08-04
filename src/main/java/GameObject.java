import java.awt.*;

public abstract class GameObject {
    private int x,y;
    private Image image;

    public GameObject(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    abstract void draw(Graphics g)
    ;
}
