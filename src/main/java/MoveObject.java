import java.awt.*;

public abstract class MoveObject extends GameObject {

    Direction direction;
    boolean enemy;
    int speed;
    int speed2;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        this.speed2 = (int) (speed / Math.sqrt(2));
    }

    public MoveObject(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public MoveObject(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        this.enemy = enemy;
        setSpeed(30);
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
        oldX = x;
        oldY = y;
        speed2 = (int) (speed / Math.sqrt(2));
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

    public void draw(Graphics g) {

        move();
        collision();

        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public boolean collisionBound() {
        if (x < 0) {
            x = 0;
            return true;
        } else if (x > TankWar.gameClient.getSWidth() - width) {
            x = TankWar.gameClient.getSWidth() - width;
            return true;
        }

        if (y < 0) {
            y = 0;
            return true;

        } else if (y > TankWar.gameClient.getSHeight() - height) {
            y = TankWar.gameClient.getSHeight() - height;
            return true;
        }
        return false;
    }

    public abstract void collision();
}