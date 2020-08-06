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
        this.speed2 = (int) (speed / Math.sqrt(2));
    }

    int speed;
    int speed2;

    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        this.enemy = enemy;
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

    public void fire() {
        int fireX = (x + width) / 2-GameClient.bulletImage[0].getWidth(null)/2;
        int fireY = (y + height) / 2-GameClient.bulletImage[0].getHeight(null)/2;
        TankWar.gameClient.addGameObject(new Bullet(fireX,fireY,direction,enemy,GameClient.bulletImage));
    }

    public void draw(Graphics g) {
        if (!stop()) {
            determineDirection();
            move();
            collision();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public void collision() {
        if (x < 0) {
            x = 0;
        } else if (x > TankWar.gameClient.getSWidth() - width) {
            x = TankWar.gameClient.getSWidth() - width;
        }

        if (y < 0) {
            y = 0;
        } else if (y > TankWar.gameClient.getSHeight() - height) {
            y = TankWar.gameClient.getSHeight() - height;
        }

        for (GameObject object : TankWar.gameClient.getGameObjects()) {
            if (object != this) {
                if (getRectangle().intersects(object.getRectangle())) {
                    x = oldX;
                    y = oldY;
                    return;
                }
            }
        }
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