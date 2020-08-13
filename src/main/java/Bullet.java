import java.awt.*;
import java.util.List;

public class Bullet extends MoveObject {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) return;
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public boolean collision() {
        boolean collisionBound = collisionBound();
        if (collisionBound) {
            alive = false;
            return true;
        }

        List<GameObject> objects = TankWar.gameClient.getGameObjects();

        for (GameObject object : objects
        ) {
            //子彈碰子彈
            if (object instanceof Bullet || object instanceof Explosion) {
                //System.out.println("打到自己");
                continue;
            }
            //自己的子彈?
            if (object instanceof Tank && ((Tank) object).enemy == enemy) {
                //System.out.println("擊中友軍");
                continue;
            }
            //碰撞
            if (getRectangle().intersects(object.getRectangle())) {
                //System.out.println("boom!");
                alive = false;
                if (object instanceof Tank) {
                    ((Tank) object).alive = false;
                    //System.out.println("擊中敵人!");
                }
                TankWar.gameClient.addGameObject(new Explosion(x,y,GameClient.explosionImage));
                return true;
            }
        }

        return false;
    }

}
