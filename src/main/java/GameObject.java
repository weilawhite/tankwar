import java.awt.*;

public abstract class GameObject {
    public boolean alive;
    protected int x,y,oldX,oldY;
    protected Image[] image;
    protected int width;
    protected int height;

    public GameObject(int x, int y, Image[] image) {
        alive=true;
        this.x = x;
        this.y = y;
        this.image = image;
        this.width=image[0].getWidth(null);
        this.height=image[0].getHeight(null);
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }

    public abstract void draw(Graphics g);
}
