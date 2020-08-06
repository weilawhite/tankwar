import java.awt.*;

public abstract class GameObject {
    protected int x,y;
    protected Image[] image;
    protected int width;
    protected int height;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width=image[0].getWidth(null);
        this.height=image[0].getHeight(null);
    }

    public abstract void draw(Graphics g);
}
