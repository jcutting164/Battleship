import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;


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
        	
        	/* KEY
        	 * UP: -y1
        	 * DOWN: +y1
        	 * LEFT: -x1
        	 * RIGHT: x+1
        	 */

        	
        	
        	if(key==KeyEvent.VK_UP){
        		System.out.println("up");
        		
        		// getting the movement coords to check if available before actually movement
        		

        		

        			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
            		for(int i = 0; i<game.getSelectedCoords().size(); i++){
            			
                		// clears what is currently selected

            			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]="";
            			
            			// modifies the current selected coords
            			ArrayList<Integer> temp2=new ArrayList<Integer>();
            			// these lines decide the movement
            			temp2.add(game.getSelectedCoords().get(i).get(0)-1);
            			temp2.add(game.getSelectedCoords().get(i).get(1));
            			temp.add(temp2);
            		}
            		
            		// readds the temp list to the real list that game is using
            		game.setSelectedCoords(temp);
            		
            	 		
            		// readds the ships to the board
            		
            		for(int i = 0; i<game.getSelectedCoords().size(); i++){
            			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]+=("$"+game.getMouseInput().getCurrentSelected());
            		}
        		
        		
        		
        		// end
        		
        		

        	}else if(key==KeyEvent.VK_DOWN){
        		System.out.println("down");
        		
        		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			
            		// clears what is currently selected

        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]="";
        			
        			// modifies the current selected coords
        			ArrayList<Integer> temp2=new ArrayList<Integer>();
        			// these lines decide the movement
        			temp2.add(game.getSelectedCoords().get(i).get(0)+1);
        			temp2.add(game.getSelectedCoords().get(i).get(1));
        			temp.add(temp2);
        		}
        		
        		// readds the temp list to the real list that game is using
        		game.setSelectedCoords(temp);
        		
        	 		
        		// readds the ships to the board
        		
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]+=("$"+game.getMouseInput().getCurrentSelected());
        		}

        	}else if(key==KeyEvent.VK_LEFT){
        		System.out.println("left");
        		
        		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			
            		// clears what is currently selected

        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]="";
        			
        			// modifies the current selected coords
        			ArrayList<Integer> temp2=new ArrayList<Integer>();
        			// these lines decide the movement
        			temp2.add(game.getSelectedCoords().get(i).get(0));
        			temp2.add(game.getSelectedCoords().get(i).get(1) - 1);
        			temp.add(temp2);
        		}
        		
        		// readds the temp list to the real list that game is using
        		game.setSelectedCoords(temp);
        		
        	 		
        		// readds the ships to the board
        		
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]+=("$"+game.getMouseInput().getCurrentSelected());
        		}
        		

        	}else if(key==KeyEvent.VK_RIGHT){
        		System.out.println("right");
        		System.out.println(game.getCurrentSelection());
        		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			
            		// clears what is currently selected

        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]="";
        			
        			// modifies the current selected coords
        			ArrayList<Integer> temp2=new ArrayList<Integer>();
        			// these lines decide the movement
        			temp2.add(game.getSelectedCoords().get(i).get(0));
        			temp2.add(game.getSelectedCoords().get(i).get(1) + 1);
        			temp.add(temp2);
        		}
        		
        		// readds the temp list to the real list that game is using
        		game.setSelectedCoords(temp);
        		
        	 		
        		// readds the ships to the board
        		
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			System.out.println("jajaja "+"$"+game.getCurrentSelection());
        			System.out.println(game.getCurrentSelection());
        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]+=("$"+game.getMouseInput().getCurrentSelected());
        		}
        		
        		
        	}
        	
        	
        }
        
        
        
    }




    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();




    }



}