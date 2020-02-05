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
            			// NOTICE: the reset is to the original board space: " "
            			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]=" ";
            			
            			// modifies the current selected coords
            			ArrayList<Integer> temp2=new ArrayList<Integer>();
            			// these lines decide the movement
            			temp2.add(game.getSelectedCoords().get(i).get(0)-1);
            			temp2.add(game.getSelectedCoords().get(i).get(1));
            			temp.add(temp2);
            		}
            		
            		// to check for stopping movement we will check for pieces in temp
            		// if there are pieces, then we will NOT add to selected coords in game
            		// the rest HAS to run so that it will readd the pieces to where they were already
            		
            		/* EACH SHIP WILL BE A COLOR AT THE MOMENT UNTIL GRAPHICS ARE ADDED TO FILL SPACE
           		 C = Carrier / RED
           		 B = Battleship / ORANGE
           		 R = Cruiser / YELLOW
           		 S = Submarine / GREEN
           		 D = Destroyer / BLUE
           		 */
            		boolean add=true;
            		for(int i = 0; i<temp.size(); i++){
            			String check=game.getBoardP1()[temp.get(i).get(0)][temp.get(i).get(1)];
            			if(check.contains("D") || check.contains("C") || check.contains("B") || check.contains("R") || check.contains("S")|| check.contains("S") || temp.get(i).get(0)==9 || temp.get(i).get(0)==0 || temp.get(i).get(1)==9  || temp.get(i).get(1)==0){
            				add=false;
            				break;
            			}
            		}
            		
            		// readds the temp list to the real list that game is using
            		if(add)
            			game.setSelectedCoords(temp);
            		
            	 		
            		// readds the ships to the board via the selected coords
            		
            		for(int i = 0; i<game.getSelectedCoords().size(); i++){
            			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]+=("$"+game.getMouseInput().getCurrentSelected());
            		}
        		
        		
        		
        		// end
        		
        		

        	}else if(key==KeyEvent.VK_DOWN){
        		System.out.println("down");
        		
        		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			
            		// clears what is currently selected

        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]=" ";
        			
        			// modifies the current selected coords
        			ArrayList<Integer> temp2=new ArrayList<Integer>();
        			// these lines decide the movement
        			temp2.add(game.getSelectedCoords().get(i).get(0)+1);
        			temp2.add(game.getSelectedCoords().get(i).get(1));
        			temp.add(temp2);
        		}
        		
        		boolean add=true;
        		for(int i = 0; i<temp.size(); i++){
        			String check=game.getBoardP1()[temp.get(i).get(0)][temp.get(i).get(1)];
        			if(check.contains("D") || check.contains("C") || check.contains("B") || check.contains("R") || check.contains("S")|| check.contains("S") ||temp.get(i).get(0)==9 || temp.get(i).get(0)==0 || temp.get(i).get(1)==9  || temp.get(i).get(1)==0){
        				add=false;
        				break;
        			}
        		}
        		
        		// readds the temp list to the real list that game is using
        		if(add)
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

        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]=" ";
        			
        			// modifies the current selected coords
        			ArrayList<Integer> temp2=new ArrayList<Integer>();
        			// these lines decide the movement
        			temp2.add(game.getSelectedCoords().get(i).get(0));
        			temp2.add(game.getSelectedCoords().get(i).get(1) - 1);
        			temp.add(temp2);
        		}
        		
        		// readds the temp list to the real list that game is using
        		boolean add=true;
        		for(int i = 0; i<temp.size(); i++){
        			String check=game.getBoardP1()[temp.get(i).get(0)][temp.get(i).get(1)];
        			if(check.contains("D") || check.contains("C") || check.contains("B") || check.contains("R") || check.contains("S")|| check.contains("S") ||temp.get(i).get(0)==9 || temp.get(i).get(0)==0 || temp.get(i).get(1)==9  || temp.get(i).get(1)==0){
        				add=false;
        				break;
        			}
        		}
        		
        		// readds the temp list to the real list that game is using
        		if(add)
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

        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]=" ";
        			
        			// modifies the current selected coords
        			ArrayList<Integer> temp2=new ArrayList<Integer>();
        			// these lines decide the movement
        			temp2.add(game.getSelectedCoords().get(i).get(0));
        			temp2.add(game.getSelectedCoords().get(i).get(1) + 1);
        			temp.add(temp2);
        		}
        		
        		boolean add=true;
        		for(int i = 0; i<temp.size(); i++){
        			String check=game.getBoardP1()[temp.get(i).get(0)][temp.get(i).get(1)];
        			if(check.contains("D") || check.contains("C") || check.contains("B") || check.contains("R") || check.contains("S") || temp.get(i).get(0)>9 || temp.get(i).get(0)==9 || temp.get(i).get(0)==0 || temp.get(i).get(1)==9  || temp.get(i).get(1)==0){
        				add=false;
        				break;
        			}
        		}
        		
        		// readds the temp list to the real list that game is using
        		if(add)
        			game.setSelectedCoords(temp);
        		
        	 		
        		// readds the ships to the board
        		
        		for(int i = 0; i<game.getSelectedCoords().size(); i++){
        			System.out.println("jajaja "+"$"+game.getCurrentSelection());
        			System.out.println(game.getCurrentSelection());
        			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]+=("$"+game.getMouseInput().getCurrentSelected());
        		}
        		
        		
        	}else if(key==KeyEvent.VK_R){
        		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        		// x values are the same -> rotate to be vertical
        		if(game.getSelectedCoords().get(0).get(0)==game.getSelectedCoords().get(1).get(0)){
        			
        			// ADD MOVE BACK AND RESELECTION
            		
            		
            		for(int i = 1; i<game.getSelectedCoords().size(); i++){
            			ArrayList<Integer> temp2=new ArrayList<Integer>();
            			// constant x value of the pivot (first in the list)
            			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]=" ";

            			// subtracts i from the y value to move it down 
            			temp2.add((game.getSelectedCoords().get(i).get(0)) -i);
            			temp2.add(game.getSelectedCoords().get(0).get(1));
            			System.out.println("orig y coord: "+game.getSelectedCoords().get(i).get(1));
            			
            			temp.add(temp2);
            		}
            		
            		System.out.println("Desired: "+temp);
            		
            		
            		
        			
        		}else{
        			// rotate to be horizontal

            		
            		for(int i = 1; i<game.getSelectedCoords().size(); i++){
            			ArrayList<Integer> temp2=new ArrayList<Integer>();
            			// constant x value of the pivot (first in the list)
            			game.getBoardP1()[game.getSelectedCoords().get(i).get(0)][game.getSelectedCoords().get(i).get(1)]=" ";

            			// subtracts i from the y value to move it down 
            			temp2.add(game.getSelectedCoords().get(0).get(0));

            			temp2.add((game.getSelectedCoords().get(i).get(1)) -i);
            			System.out.println("orig y coord: "+game.getSelectedCoords().get(i).get(1));
            			
            			temp.add(temp2);
            		}
            		
            		System.out.println("Desired: "+temp);
        			
        			
        			
        		}
        		// THIS IS THE CONSTANT OF ROTATION
        		// confirms the readdition of pieces on the board
        		// NEED: temp for the rotation of varying pieces
        		
    			game.getBoardP1()[game.getSelectedCoords().get(0).get(0)][game.getSelectedCoords().get(0).get(1)]=" ";

        		
        		boolean add=true;
        		for(int i = 0; i<temp.size(); i++){
        			String check=game.getBoardP1()[temp.get(i).get(0)][temp.get(i).get(1)];
        			if(check.contains("D") || check.contains("C") || check.contains("B") || check.contains("R") || check.contains("S") ||temp.get(i).get(0)==9 || temp.get(i).get(0)==0 || temp.get(i).get(1)==9  || temp.get(i).get(1)==0){
        				add=false;
        				break;
        			}
        		}
        		
        		
        		// readds the temp list to the real list that game is using
        		
        		if(add){
        			ArrayList<Integer> addPivot = new ArrayList<Integer>();
        			addPivot.add(game.getSelectedCoords().get(0).get(0));
        			addPivot.add(game.getSelectedCoords().get(0).get(1));
        			temp.add(addPivot);
        			game.setSelectedCoords(temp);
        			

        		}
        		
        
        		
        	 		
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