import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;



public class Game extends Canvas implements Runnable{


    /**
     *
     */
    private static final long serialVersionUID  = -1300776496297261616L;


    // height is 960
    public static final float WIDTH = 1200, HEIGHT = 820;

    //temp

    private transient Thread thread;
    private boolean running = false;
    
    private KeyInput keyInput;
    private MouseInput mouseInput;

    private String[] STATES = {"Menu", "Game"};
    private String currentState="Menu";
    static Textures tex;
    private boolean startSelect=false;
    
    // will be used to highlight which ship is being selected
    // NEED TO IMPLEMENT SYSTEM TO TRACK WHICH SQUARE IS SELECTED (LIKE CHECKERS BUT ACCOMODATE FOR LABELS (A-J) (1-10)
    private String currentSelection="";
    
    
    private String alpha = "ABCDEFGHIJ";
    
    private String boardP1[][] = {
            //board of player one where the ships are being put
    		/* EACH SHIP WILL BE A COLOR AT THE MOMENT UNTIL GRAPHICS ARE ADDED TO FILL SPACE
    		 C = Carrier / RED
    		 B = Battleship / ORANGE
    		 R = Cruiser / YELLOW
    		 S = Submarine / GREEN
    		 D = Destroyer / BLUE
    		 */
            {"C", "C", "C", "C", "C", " ", " ", " "," "," " },
            {"B", "B", "B", "B", " ", " ", " ", " "," "," " },
            {"R", "R", "R", " ", " ", " ", " ", " "," "," " },
            {"S", "S", "S", " ", " ", " ", " ", " "," "," " },
            {"D", "D", " ", " ", " ", " ", " ", " "," "," " },
            {" ", " ", " ", " ", " ", " ", " ", " "," "," " },
            {" ", " ", " ", " ", " ", " ", " ", " "," "," " },
            {" ", " ", " ", " ", " ", " ", " ", " "," "," " },
            {" ", " ", " ", " ", " ", " ", " ", " "," "," " },
            {" ", " ", " ", " ", " ", " ", " ", " "," "," " }


    };
    
    
       
    
    private Window window;

    public Game() {
    	


       keyInput = new KeyInput();

        this.addKeyListener(keyInput);
        
        mouseInput=new MouseInput(this);
        this.addMouseListener(mouseInput);        
        
        this.addMouseMotionListener(mouseInput);
        
/*
        mouseInput= new MouseInput(this);
        this.addMouseListener(mouseInput);

*/

        window = new Window(WIDTH, HEIGHT, "Battleship", this);
        tex=new Textures();


    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }



    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {

        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(running) {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running) {
                render();
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
            }
        }
        stop();
    }


    private void tick() {
        try{
            
           




        }catch(Exception e){
            e.printStackTrace();
        }






    }

   

	private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        // renders the board
        Graphics2D g2d = (Graphics2D)g;
        
        
        if(currentState.equals("Menu")){
        	tex=new Textures();
        	g.drawImage(tex.Menu,0,0,1200,800,null);
        	g.setColor(Color.black);
        	g.drawRect(860, 100, 256, 96);
        	g.drawRect(861,101,256,96);
        	g.drawRect(862,102,256,96);
        	g.drawRect(863,103,256,96);
        	
        	
        	if(startSelect){
        		g.setColor(Color.red);
        		g.fillRect(860, 100, 256, 96);
        	}
        	g.setColor(Color.black);
        	
        	
        	g.setFont(new Font("Serif", Font.BOLD, 45));
        	g.drawString("Start",932, 160);
        	
        	
        }else if(currentState.equals("Setup")){
        	// displaying the grid (A-J, 1-10)
        	g.setColor(Color.blue);
        	g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
        	
        	
        	g.setColor(Color.gray);
        	g.fillRect(1,1,71,71);
        	g.setColor(Color.black);
        	g.drawRect(0, 0, 72, 72);
        	for(int j = 0; j<11; j++){
        		for(int i = 0; i<11; i++){
            		g.setColor(Color.gray);
                	g.fillRect(i*72+1,1+(72*j),71,71);
                	g.setColor(Color.black);
                	g.drawRect(0+(72*i), 0+(72*j), 72, 72);
            	}
        	}
        	
        	// putting grid letters and numbers
        	// LETTERS:
        	g.setFont(new Font("Serif", Font.BOLD, 20));
        	for(int i = 0; i<10; i++){
        		if(i<5){
        			g.drawString(alpha.substring(i,i+1), (i*75)+90, 30);
        			g.drawString(""+(i+1), 30, (i*75)+105);


        		}
        		else{
        			g.drawString(alpha.substring(i,i+1), (i*75)+80, 30);
        			g.drawString(""+(i+1), 30, (i*75)+90);


        		}

        	}
        	// DISPLAYING SHIPS: SYMBOLS FOR NOW (will be used for the double array boards
        	
        	// COLORS FOR NOW
        	// displaying the actual boards

        	
        	/* EACH SHIP WILL BE A COLOR AT THE MOMENT UNTIL GRAPHICS ARE ADDED TO FILL SPACE
		   		 C = Carrier / RED
		   		 B = Battleship / ORANGE
		   		 R = Cruiser / YELLOW
		   		 S = Submarine / GREEN
		   		 D = Destroyer / BLUE
		   		 */
        	
        	
        	for(int i = 0; i<boardP1.length; i++){
        		for(int j = 0; j<boardP1[0].length; j++){
        			if(boardP1[j][i].contains("C")){
        				g.setColor(Color.red);
        				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
        			}else if(boardP1[j][i].contains("B")){
        				g.setColor(Color.orange);
        				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
        			}else if(boardP1[j][i].contains("R")){
        				g.setColor(Color.yellow);
        				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
        			}else if(boardP1[j][i].contains("S")){
        				g.setColor(Color.green);
        				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
        			}else if(boardP1[j][i].contains("D")){
        				g.setColor(Color.blue);
        				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
        			}
        		}
        	}
        	
        	
        	
        	
        	
        	
        }
        
        

       


        g.dispose();
        bs.show();



    }

    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return var = max;
        else if(var<= min)
            return var = min;
        else
            return var;
    }


    public static float clampJ(float var, float min, float max) {
        if(var >= max)
            return max;
        else if(var <= min)
            return min;
        else
            return var;
    }
    
    public static Textures getInstance(){
        return tex;
    }
    
    


    public String[] getSTATES() {
		return STATES;
	}

	public void setSTATES(String[] sTATES) {
		STATES = sTATES;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	
	

	public boolean isStartSelect() {
		return startSelect;
	}

	public void setStartSelect(boolean startSelect) {
		this.startSelect = startSelect;
	}

	public static void main(String[] args) {
        new Game();

    }


}