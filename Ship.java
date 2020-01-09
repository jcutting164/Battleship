import java.awt.image.BufferedImage;


public class Ship {

	private int size;
	private boolean isValid=true;
	
	// rotation is a variable 0 through 3 that defines which way the ships are aligned
	// 0 is the default
	private int rotation = 0;
	private BufferedImage ship; 
	
	
	public Ship(int size){
		this.size=size;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}


	public int getRotation() {
		return rotation;
	}


	public void setRotation(int rotation) {
		this.rotation = rotation;
	}


	public BufferedImage getShip() {
		return ship;
	}


	public void setShip(BufferedImage ship) {
		this.ship = ship;
	}
	
	
	
	
}
