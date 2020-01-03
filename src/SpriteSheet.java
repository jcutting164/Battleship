import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SpriteSheet implements Serializable {

    private transient BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }


    public BufferedImage grabImage(int col, int row, int width, int height){

        //BufferedImage img = image.getSubimage((col * 32)- 32, (row*32)-32, width, height);

        BufferedImage img = image.getSubimage((col*width)-width, (row*height)-height, width, height);


        return img;

    }

    public BufferedImage getSubImage(int x, int y, int width, int height){

        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }

}