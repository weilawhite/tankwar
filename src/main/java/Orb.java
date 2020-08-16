import java.awt.*;
import java.util.List;

//暫定只有一種Orb
public class Orb extends MoveObject {
    public Orb(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        this.setSpeed(30);


        new Thread(() -> {
            int fireX, fireY;
            for (int i = 0; i < 4; i++) {
                collision();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!alive) {
                    break;
                }

                fireX = this.x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2;
                fireY = this.y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2;
                for (Direction dir : Direction.values()
                ) {
                    if (Math.abs(dir.ordinal() - direction.ordinal()) != 4) {
                        TankWar.gameClient.addGameObject(new Bullet(fireX, fireY, dir, enemy, GameClient.bulletImage));
                    }
                }
            }
            //alive=false;
            collision();
            System.out.println("Orb Thread Dead");
        }).start();

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
            if (object == this) {
                //System.out.println("打到自己");
                continue;
            }
            //不對所有坦克判斷
            if (object instanceof Tank && ((Tank) object).enemy == enemy) {
                continue;
            }

            //碰撞牆壁
            if (getRectangle().intersects(object.getRectangle()) && object instanceof Wall) {
                //System.out.println("碰牆壁消失!");
                alive = false;
                return true;
            }
            if (getRectangle().intersects(object.getRectangle()) && object instanceof Tank) {
                //System.out.println("Orb kill");
                object.alive = false;

            }
        }
        return false;
    }
}
