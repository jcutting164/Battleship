import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInput implements MouseListener, MouseMotionListener{
        //where initialization occurs:
        //Register for mouse events on blankArea and the panel.
     
	private Game game;
	private int setupRow=-1;
	private int setupColumn=-1;
	private String currentSelected="";
	public MouseInput(Game game){
		this.game=game;
	}

    public void mousePressed(MouseEvent e) {
    	if(game.getCurrentState().equals("Setup") && e.getY()/72<11 && e.getX()/72<11 && e.getY()>71 && e.getX()>71){
    		setupRow=e.getY()/72 - 1;
        	setupColumn=e.getX()/72 - 1;
        	
        	// check for ship present 
        	if(!(game.getBoardP1()[setupRow][setupColumn].equals(" "))){
        		// then there is a piece here
        		currentSelected=game.getBoardP1()[setupRow][setupColumn];
        		game.removeSelection(game.getBoardP1());
        		game.setSelectedCoords(new ArrayList<ArrayList<Integer>>());
        		
        		game.selectAll(currentSelected, game.getBoardP1());
        		
        		// TO BE DONE: ADD SELECTION COORDS TO ARRAY IN THE SELECT ALL FUNCTION IN MAIN GAME
        		
        	}else{
        		currentSelected="";
        	}
        	
        	
    	}else{
    		setupRow=-1;
    		setupColumn=-1;
    	}
    	
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    	if(game.getCurrentState().equals("Menu")){
			if(new Rectangle(860, 100, 256, 96).intersects(new Rectangle(e.getX(),e.getY(),1,1))){
				game.setCurrentState("Setup");
			}
		}else if(game.getCurrentState().equals("Setup")){
			if(new Rectangle(900,500,164,64).intersects(new Rectangle(e.getX(),e.getY(),1,1))){
				if(game.isPlayerOneSetup())
					game.setPlayerOneSetup(false);
				else if(!game.isPlayerOneSetup()){
					// transition to game mode
					//game.setCurrentState();
				}
			}
		}
    }

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		if(game.getCurrentState().equals("Menu")){
			game.setStartSelect(false);
			if(new Rectangle(860, 100, 256, 96).intersects(new Rectangle(e.getX(),e.getY(),1,1))){
				game.setStartSelect(true);
			}
		}else if(game.getCurrentState().equals("Setup")){
			game.setDoneSelect(false);
			if(new Rectangle(900,500,164,64).intersects(new Rectangle(e.getX(),e.getY(),1,1))){
				// will control done
				game.setDoneSelect(true);
			}
		}
		
		
		
	}

	public int getSetupRow() {
		return setupRow;
	}

	public void setSetupRow(int setupRow) {
		this.setupRow = setupRow;
	}

	public int getSetupColumn() {
		return setupColumn;
	}

	public void setSetupColumn(int setupColumn) {
		this.setupColumn = setupColumn;
	}

	public String getCurrentSelected() {
		return currentSelected;
	}

	public void setCurrentSelected(String currentSelected) {
		this.currentSelected = currentSelected;
	}
	
	
	
	
	


}