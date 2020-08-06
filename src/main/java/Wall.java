import java.awt.*;

public class Wall extends GameObject {

    int bricks;
    boolean horizontal;


     Wall(int x, int y, int bricks, boolean horizontal,Image[] image) {
        super(x,y,image);
        this.bricks = bricks;
        this.horizontal = horizontal;
    }

    public void draw(Graphics g){
         if(horizontal){
             for(int i=0;i<bricks;i++){
                 g.drawImage(image[0],x+i*width,y,null);
             }
         }else{
             for(int i=0;i<bricks;i++){
                 g.drawImage(image[0],x,y+i*height,null);
             }
         }


    }
}
