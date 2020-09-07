import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {

    public EnemyTank(int x, int y, Direction direction, boolean enemy, Image[] image,int hp) {
        super(x, y, direction, enemy, image,hp);
    }

    public void ai() {
        Random random = new Random();
        if (random.nextInt(90) == 1) {
            dirs = new boolean[4];

            if (random.nextInt(3) > 1) {
                return;
            }
            getNewDirection();

        }

        if (random.nextInt(60) == 1) {
            fire();
        }
    }

    private void getNewDirection() {
        Random random = new Random();
        int dir = random.nextInt(Direction.values().length);

        if (dir % 2 == 0) {
            dirs[dir / 2] = true;
        } else {
            if (dir == Direction.UP_LEFT.ordinal()) {
                dirs[0] = true;
                dirs[2] = true;
            } else if (dir == Direction.UP_RIGHT.ordinal()) {
                dirs[0] = true;
                dirs[3] = true;
            } else if (dir == Direction.DOWN_LEFT.ordinal()) {
                dirs[1] = true;
                dirs[2] = true;
            } else if (dir == Direction.DOWN_RIGHT.ordinal()) {
                dirs[1] = true;
                dirs[3] = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        ai();
        super.draw(g);
    }

    @Override
    public boolean collision() {
        boolean r;
        r=super.collision();
        if (r) {
            getNewDirection();
            return true;
        } else {
            return false;
        }
    }
}
