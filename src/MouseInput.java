import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{
        //where initialization occurs:
        //Register for mouse events on blankArea and the panel.
       
	private Game game;
	
	public MouseInput(Game game){
		this.game=game;
	}

    public void mousePressed(MouseEvent e) {
    	
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(game.getCurrentState().equals("Menu")){
			game.setStartSelect(false);
			if(new Rectangle(860, 100, 256, 96).intersects(new Rectangle(e.getX(),e.getY(),e.getX()+1,e.getY()+1))){
				game.setStartSelect(true);
			}
		}
		
	}


}