import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {

    Direction direction;
    boolean[] dirs = {false, false, false, false};
    boolean enemy;

    public boolean[] getDirs() {
        return dirs;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        this.speed2=(int)(speed/Math.sqrt(2));
    }

    int speed;
    int speed2;
    public Tank(int x, int y, Direction direction,Image[] image) {
        this(x,y,direction,false,image);
    }
    public Tank(int x, int y, Direction direction,boolean enemy,Image[] image) {
        super(x,y,image);
        this.direction = direction;
        this.enemy=enemy;
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

    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed2;
                x -= speed2;
                break;
            case UP_RIGHT:
                y -= speed2;
                x += speed2;
                break;
            case DOWN_LEFT:
                y += speed2;
                x -= speed2;
                break;
            case DOWN_RIGHT:
                y += speed2;
                x += speed2;
                break;
        }
    }
/*
    public Image getImage() {

        String name=enemy?"etank":"itank";

        if (direction == Direction.UP) {
            return new ImageIcon("assets\\images\\"+name+"U.png").getImage();
        }
        if (direction == Direction.DOWN) {
            return new ImageIcon("assets\\images\\"+name+"D.png").getImage();
        }
        if (direction == Direction.LEFT) {
            return new ImageIcon("assets\\images\\"+name+"L.png").getImage();
        }
        if (direction == Direction.RIGHT) {
            return new ImageIcon("assets\\images\\"+name+"R.png").getImage();
        }
        if (direction == Direction.UP_LEFT) {
            return new ImageIcon("assets\\images\\"+name+"LU.png").getImage();
        }
        if (direction == Direction.DOWN_LEFT) {
            return new ImageIcon("assets\\images\\"+name+"LD.png").getImage();
        }
        if (direction == Direction.UP_RIGHT) {
            return new ImageIcon("assets\\images\\"+name+"RU.png").getImage();
        }
        if (direction == Direction.DOWN_RIGHT) {
            return new ImageIcon("assets\\images\\"+name+"RD.png").getImage();
        }
        return null;
    }
*/
    public void determineDirection() {
        if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.UP;
        } else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.UP_RIGHT;
        } else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.RIGHT;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.DOWN_RIGHT;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.DOWN;
        } else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.DOWN_LEFT;
        } else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.LEFT;
        } else if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.UP_LEFT;
        }

    }

    public void draw(Graphics g) {
        if (!stop()) {
            determineDirection();
            move();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    private boolean stop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

}