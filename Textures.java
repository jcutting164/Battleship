


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.imageio.*;


public class Textures implements Serializable {

  //  SpriteSheet SS_Player;
	  SpriteSheet SS_Menu; 

  //  private transient BufferedImage BI_Player;
	  private BufferedImage BI_Menu;


//    private transient InputStream IS_BI_Player;
	  private InputStream IS_Menu;


 //   public transient BufferedImage[] Player_WalkLeft = new BufferedImage[2];
	  public BufferedImage Menu;


    public Textures(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            //BI_Player = loader.loadImage("res/SS_Player.png");
        	IS_Menu=new FileInputStream("res/BattleshipMenu.png");
        	BI_Menu = ImageIO.read(IS_Menu);

        //    IS_BI_Player = new FileInputStream("res/Player/SS_Player.png");
        //    BI_Player = ImageIO.read(IS_BI_Player);
        			

         // SEE EXAMPLE



        }catch(Exception e){
            e.printStackTrace();
        }
// EXAMPLE
        ///SS_Player = new SpriteSheet(BI_Player);
        SS_Menu=new SpriteSheet(BI_Menu);


        getTextures();

    }

    private void getTextures(){
    	/* EXAMPLE CODE
        Player_WalkLeft[0] = SS_Player.grabImage(3, 1, 19, 74);
        Player_WalkLeft[1] = SS_Player.grabImage(5, 1, 19, 74);
*/
    	Menu=SS_Menu.getSubImage(0, 0, 1200, 800);

    }
    //private void assignTex(Game game){
   //     this.game.knuckles;
   // }






}