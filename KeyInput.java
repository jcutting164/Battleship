import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;


public class KeyInput extends KeyAdapter implements Serializable {


	private Game game;

    public KeyInput(Game game) {
    	this.game=game;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println(key);

        if(key==KeyEvent.VK_ESCAPE)
            System.exit(0);
        
        
        // setup stuff
        
        if(game.getCurrentState().equals("Setup") && !(game.getMouseInput().getCurrentSelected().equals(" "))){
        	// UDLR
        	
        	if(key==KeyEvent.VK_UP){
        		
        	}else if(key==KeyEvent.VK_DOWN){
        		
        	}else if(key==KeyEvent.VK_LEFT){
        		
        	}else if(key==KeyEvent.VK_RIGHT){
        		
        	}
        	
        	
        }
        
        
        
    }




    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();




    }



}