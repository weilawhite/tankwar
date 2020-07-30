import javax.swing.*;
import java.awt.*;

public class Tank {
    private int x;
    private int y;
    Direction direction;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Image getImage(){
        if (direction==Direction.UP){
            return new ImageIcon("assets\\images\\itankU.png").getImage();
        }
        if (direction==Direction.DOWN){
            return new ImageIcon("assets\\images\\itankD.png").getImage();
        }
        if (direction==Direction.LEFT){
            return new ImageIcon("assets\\images\\itankL.png").getImage();
        }
        if (direction==Direction.RIGHT){
            return new ImageIcon("assets\\images\\itankR.png").getImage();
        }
        return null;
    }
}
