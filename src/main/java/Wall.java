import javax.swing.*;
import java.awt.*;

public class Wall {

    int x,y,bricks;
    boolean horizontal;
    private Image image;


     Wall(int x, int y, int bricks, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.bricks = bricks;
        this.horizontal = horizontal;
        image = new ImageIcon("assets\\images\\brick.png").getImage();
    }

    public void draw(Graphics g){
         if(horizontal){
             for(int i=0;i<bricks;i++){
                 g.drawImage(image,x+i*image.getWidth(null),y,null);
             }
         }else{
             for(int i=0;i<bricks;i++){
                 g.drawImage(image,x,y+i*image.getHeight(null),null);
             }
         }


    }
}
