import java.awt.*;
import java.util.Random;

public class Tank extends MoveObject implements SuperFire {

    boolean[] dirs = {false, false, false, false};
    Direction[] di = {Direction.UP, Direction.UP_RIGHT, Direction.RIGHT, Direction.DOWN_RIGHT, Direction.DOWN, Direction.DOWN_LEFT, Direction.LEFT, Direction.UP_LEFT};


    public boolean[] getDirs() {
        return dirs;
    }

    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        this.direction = direction;
        this.enemy = enemy;
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
        int fireX = x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
        int fireY = y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
        TankWar.gameClient.addGameObject(new Bullet(fireX, fireY, direction, enemy, GameClient.bulletImage));

        if(!enemy){
            Tools.playAudio("shoot.wav");
        }
    }

    public void eightDirectionFire() {
        int fireX = x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
        int fireY = y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
        for (Direction dir : Direction.values()
        ) {
            TankWar.gameClient.addGameObject(new Bullet(fireX, fireY, dir, enemy, GameClient.bulletImage));
        }
        String audioFile=new Random().nextBoolean()?"supershoot.wav":"supershoot.aiff";
        Tools.playAudio(audioFile);
    }

    public void tripleFire() {
        //System.out.println(direction.ordinal());
        int tm = direction.ordinal();
        int tl = tm - 1;
        int tr = tm + 1;
        if (tl == -1) tl = 7;
        if (tr == 8) tr = 0;

        int fireX = x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
        int fireY = y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
        Bullet t1 = new Bullet(fireX, fireY, di[tl], enemy, GameClient.bulletImage);
        Bullet t2 = new Bullet(fireX, fireY, di[tm], enemy, GameClient.bulletImage);
        Bullet t3 = new Bullet(fireX, fireY, di[tr], enemy, GameClient.bulletImage);

        TankWar.gameClient.addGameObject(t1);
        TankWar.gameClient.addGameObject(t2);
        TankWar.gameClient.addGameObject(t3);
    }

    public void fastFire() {
        int fireX = x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
        int fireY = y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
        Bullet ff = new Bullet(fireX, fireY, direction, enemy, GameClient.bulletImage);
        ff.setSpeed(70);
        TankWar.gameClient.addGameObject(ff);
    }

    public void frozenOrb() {
        int fireX = x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
        int fireY = y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
        TankWar.gameClient.addGameObject(new Orb(fireX, fireY, direction, enemy, GameClient.bulletImage));

    }

    public void draw(Graphics g) {
        if (!stop()) {
            determineDirection();
            move();
            collision();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public boolean collisionBound() {

        boolean C = false;
        if (x < 0) {
            x = 0;
            C = true;
        } else if (x > TankWar.gameClient.getSWidth() - width) {
            x = TankWar.gameClient.getSWidth() - width;
            C = true;

        }

        if (y < 0) {
            y = 0;
            C = true;

        } else if (y > TankWar.gameClient.getSHeight() - height) {
            y = TankWar.gameClient.getSHeight() - height;
            C = true;

        }
        return C;
    }

    public boolean collision() {
        collisionBound();
        for (GameObject object : TankWar.gameClient.getGameObjects()) {
            if (object != this) {
                if (getRectangle().intersects(object.getRectangle())) {
                    x = oldX;
                    y = oldY;

                    return true;
                }
            }
        }
        return false;
    }

    private boolean stop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void superFire() {
        int fireX = x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
        int fireY = y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
        for (Direction dir : Direction.values()
        ) {
            TankWar.gameClient.addGameObject(new Bullet(fireX, fireY, dir, enemy, GameClient.bulletImage));
        }
        String audioFile=new Random().nextBoolean()?"supershoot.wav":"supershoot.aiff";
        Tools.playAudio(audioFile);
    }
}