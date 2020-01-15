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
        		System.out.println("forever true");
        		String[][] temp = game.getBoardP1();
        		int shipcount=0;
        		for(int i = 0; i<game.getBoardP1().length; i++){
        			shipcount=0;
        			for(int j = 0; j<game.getBoardP1()[0].length; j++){
        				System.out.println("mi boi: "+game.getShipHash().get(game.getCurrentSelection()));
        				System.out.println(game.getShipHash());
        				System.out.println("jaja: "+game.getCurrentSelection());
        				if(shipcount>game.getShipHash().get(game.getCurrentSelection())){
        					break;
        				}
        				if(temp[i][j].contains("$")){
        					//shipcount++;
        					temp[i][j+1]+=game.getBoardP1()[i][j];
        					
        					
        					// must do removal process but adjusted to only remove first one
        					temp[i][j]=game.removeCharSingle(game.getBoardP1()[i][j], "$");
        					temp[i][j]=game.removeCharSingle(game.getBoardP1()[i][j], game.getCurrentSelection());


        					
        				}
        			}
        		}
        		
        		game.setBoardP1(temp);
        		
        	}
        	
        	
        }
        
        
        
    }




    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();




    }



}