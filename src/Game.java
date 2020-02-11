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
// STATES ARE HEREEEEEEEE
    private String[] STATES = {"Menu", "Game","Setup"};
    private String currentState="Menu";
    static Textures tex;
    private boolean startSelect=false;
    private boolean doneSelect=false;
    private boolean playerOneSetup=true;
    private boolean playerOneTurn=true;
    
    // will be used to highlight which ship is being selected
    // NEED TO IMPLEMENT SYSTEM TO TRACK WHICH SQUARE IS SELECTED (LIKE CHECKERS BUT ACCOMODATE FOR LABELS (A-J) (1-10)
    private String currentSelection="";
    
    private String alpha = "ABCDEFGHIJ";
    private int ts;
    
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

	private String[][] currentBoard=boardP1;


	private String boardP2[][] = {
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
    
    
    private HashMap<String,Integer> shipHash= new HashMap<>();
    
    
    
    // Coordinate arraylist of all selected pieces
    private ArrayList<ArrayList<Integer>> selectedCoords=new ArrayList<>();
    
    
    
    
    
       
    
    private Window window;

    public Game() {
       shipHash.put("C",5);
       shipHash.put("B",4);
       shipHash.put("R",3);
       shipHash.put("S",3);
       shipHash.put("D",2);
       
       



       keyInput = new KeyInput(this);

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
            
           System.out.println(mouseInput.getSetupColumn());
           System.out.println(mouseInput.getSetupRow());
           System.out.println("current selected: "+mouseInput.getCurrentSelected());
           for(int i = 0; i<selectedCoords.size(); i++){
        	   System.out.println(selectedCoords.get(i));
           }



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
        	
        	if(playerOneSetup){
        		
        		// displaying the grid (A-J, 1-10)
            	g.setColor(new Color(51,204,255));
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
            	
            	g.setFont(new Font("Serif", Font.BOLD, 30));

            	g.drawString("Click to select a ship",800,100);
            	g.drawString("Arrows to move your ship",800,200);
            	g.drawString("Press R to rotate your ship",800,300);
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
            			if(boardP1[j][i].contains("$")){
            				g.setColor(Color.cyan);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}else if(boardP1[j][i].contains("C")){
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
            	
            	
            	// button for continuation after setup?
            	
            	if(doneSelect){
            		g.setColor(Color.red);
                	g.fillRect(901,499,163,65);
            	}else{
            		g.setColor(Color.white);
                	g.fillRect(901,499,163,65);
            	}
            	
            	
            	g.setColor(Color.black);
            	Rectangle base = new Rectangle(900,500,164,64);
            	
            	g.drawRect((int)base.getX(), (int)base.getY(), (int)base.getWidth(), (int)base.getHeight());
            	g.drawRect((int)base.getX()-1, (int)base.getY()-1, (int)base.getWidth()+2, (int)base.getHeight()+2);
            	g.drawString("DONE",938,538);
            	g.drawString("Player 1", 805,50);
        		
        		
        	}else{
        		// displaying the grid (A-J, 1-10)
            	g.setColor(new Color(51,204,255));
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
            	
            	g.setFont(new Font("Serif", Font.BOLD, 30));

            	g.drawString("Click to select a ship",800,100);
            	g.drawString("Arrows to move your ship",800,200);
            	g.drawString("Press R to rotate your ship",800,300);
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
            	
            	
            	for(int i = 0; i<boardP2.length; i++){
            		for(int j = 0; j<boardP2[0].length; j++){
            			if(boardP2[j][i].contains("$")){
            				g.setColor(Color.cyan);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}else if(boardP2[j][i].contains("C")){
            				g.setColor(Color.red);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}else if(boardP2[j][i].contains("B")){
            				g.setColor(Color.orange);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}else if(boardP2[j][i].contains("R")){
            				g.setColor(Color.yellow);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}else if(boardP2[j][i].contains("S")){
            				g.setColor(Color.green);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}else if(boardP2[j][i].contains("D")){
            				g.setColor(Color.blue);
            				g.fillRect((72)+   +i*72+1,(72)+   1+(72*j),71,71);
            			}
            		}
            	}
            	
            	
            	// button for continuation after setup?
            	
            	if(doneSelect){
            		g.setColor(Color.red);
                	g.fillRect(901,499,163,65);
            	}else{
            		g.setColor(Color.white);
                	g.fillRect(901,499,163,65);
            	}
            	
            	
            	g.setColor(Color.black);
            	Rectangle base = new Rectangle(900,500,164,64);
            	
            	g.drawRect((int)base.getX(), (int)base.getY(), (int)base.getWidth(), (int)base.getHeight());
            	g.drawRect((int)base.getX()-1, (int)base.getY()-1, (int)base.getWidth()+2, (int)base.getHeight()+2);
            	g.drawString("DONE",938,538);
            	g.drawString("Player 2", 805,50);
        	}
        	
        	
        	
        	
        	
        	
        }else if(currentState.equals("game")){
        	// start of gameplay / hitting


			// NEED: 2 boards: one to show hits and misses, the other to show where YOU have been hit


			// left board: FOR PICKING A NEW HIT / DISPLAYING MISSES
			// START OF LEFT BOARD

			ts = 47;


			g.setColor(new Color(51,204,255));
			g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);


			g.setColor(Color.gray);
			g.fillRect(1,1,ts-1,ts-1);
			g.setColor(Color.black);
			g.drawRect(0, 0, ts, ts);
			for(int j = 0; j<11; j++){
				for(int i = 0; i<11; i++){
					g.setColor(Color.gray);
					g.fillRect(i*ts+1,1+(ts*j),ts-1,ts-1);
					g.setColor(Color.black);
					g.drawRect(0+(ts*i), 0+(ts*j), ts, ts);
				}
			}

			// putting grid letters and numbers
			// LETTERS:
			g.setFont(new Font("Serif", Font.BOLD, 20));
			for(int i = 0; i<10; i++){
				if(i<5){
					g.drawString(alpha.substring(i,i+1), (i*ts+1)+70, 30);
					g.drawString(""+(i+1), 15, (i*ts+1)+85);


				}
				else{
					g.drawString(alpha.substring(i,i+1), (i*ts+1)+60, 30);
					g.drawString(""+(i+1), 15, (i*ts+1)+80);


				}

			}

			g.setFont(new Font("Serif", Font.BOLD, 30));

			//g.drawString("Click to select a ship",800,100);
			//g.drawString("Arrows to move your ship",800,200);
			//g.drawString("Press R to rotate your ship",800,300);
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
					if(boardP1[j][i].contains("$")){
						g.setColor(Color.cyan);
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
						
					}else if(boardP1[j][i].contains("#")){
						g.setColor(new Color(106,13,173));
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
					}else if(boardP1[j][i].contains("H")){
						g.setColor(Color.red.darker().darker());
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
					}else if(boardP1[j][i].contains("M")){
						g.setColor(Color.white);
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
					}
				}
			}


			////// END OF LEFT BOARD

			// START OF RIGHT BOARD FOR SHOWING WHERE YOU HAVE BEEN HIT ALREADY///////////////
			///////////////////////
			///////////////////////

			///////////////////////

			///////////////////////
			///////////////////////
			///////////////////////
			///////////////////////
			///////////////////////
			///////////////////////
			///////////////////////
			///////////////////////

			int shiftX=685;





			g.setColor(Color.gray);
			g.fillRect(1+shiftX,1,ts-1,ts-1);
			g.setColor(Color.black);
			g.drawRect(shiftX, 0, ts, ts);
			for(int j = 0; j<11; j++){
				for(int i = 0; i<11; i++){
					g.setColor(Color.gray);
					g.fillRect(i*ts+1+shiftX,1+(ts*j),ts-1,ts-1);
					g.setColor(Color.black);
					g.drawRect(shiftX+(ts*i), 0+(ts*j), ts, ts);
				}
			}

			// putting grid letters and numbers
			// LETTERS:
			g.setFont(new Font("Serif", Font.BOLD, 20));
			for(int i = 0; i<10; i++){
				if(i<5){
					g.drawString(alpha.substring(i,i+1), (i*ts+1)+70+shiftX, 30);
					g.drawString(""+(i+1), 15+shiftX, (i*ts+1)+85);


				}
				else{
					g.drawString(alpha.substring(i,i+1), (i*ts+1)+60+shiftX, 30);
					g.drawString(""+(i+1), 15+shiftX, (i*ts+1)+80);


				}

			}

			g.setFont(new Font("Serif", Font.BOLD, 30));

			//g.drawString("Click to select a ship",800,100);
			//g.drawString("Arrows to move your ship",800,200);
			//g.drawString("Press R to rotate your ship",800,300);
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
					if(boardP1[j][i].contains("$")){
						g.setColor(Color.cyan);
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
						
					}else if(boardP1[j][i].contains("#")){
						g.setColor(new Color(106,13,173));
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
					}else if(boardP1[j][i].contains("H")){
						g.setColor(Color.red.darker().darker());
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
					}else if(boardP1[j][i].contains("M")){
						g.setColor(Color.white);
						g.fillRect((ts)+   +i*ts+1,(ts)+   1+(ts*j),ts-1,ts-1);
					}
				}
			}



			/////////// END OF RIGHT BOARD




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
	
	
	
	

	public String[][] getBoardP1() {
		return boardP1;
	}

	public void setBoardP1(String[][] boardP1) {
		this.boardP1 = boardP1;
	}
	
	
	public void selectAll(String shipType, String[][] board){
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[0].length; j++){
				if(board[i][j].contains(shipType)){
					board[i][j]+="$";
					
					ArrayList<Integer> tempCoords=new ArrayList<>();
	        		tempCoords.add(i); tempCoords.add(j);
	        		selectedCoords.add(tempCoords);					
				
				}
			}
		}
	}
	// deselects the whole board
	public void removeSelection(String[][] board){
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[0].length; j++){
				if(board[i][j].contains("$") || board[i][j].contains("#")){
					String temp = board[i][j];
					String giveback="";
					for(int loop = 0; loop<temp.length(); loop++){
						if(!(temp.substring(loop,loop+1).contains("$")||temp.substring(loop,loop+1).contains("#"))){
							giveback+=temp.substring(loop,loop+1);
						}
					}
					board[i][j]=giveback;
				}
			}
		}
	}
	// removes a character out of a 2d array based on strings
	public void removeChar(String[][] board, String removal){
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[0].length; j++){
				if(board[i][j].contains(removal)){
					String temp = board[i][j];
					String giveback="";
					for(int loop = 0; loop<temp.length(); loop++){
						if(!temp.substring(loop,loop+1).contains("$")){
							giveback+=temp.substring(loop,loop+1);
						}
					}
					board[i][j]=giveback;
				}
			}
		}
	}
	// removes a character out of a singular string 
	public void removeChar(String start, String removal){
		String end="";
		for(int i = 0; i<start.length(); i++){
			if(!(start.substring(i,i+1).equals(removal))){
				end+=start.substring(i,i+1);
			}
			
		}
	}
	
	// removes a character out of a singular string AND will stop after the first time it finds one
	
	public String removeCharSingle(String start, String removal){
		String end="";
		for(int i = 0; i<start.length(); i++){
			if(!(start.substring(i,i+1).equals(removal))){
				end+=start.substring(i,i+1);
			}else{
				break;
			}
			
		}
		return end;
	}
	
	
	public KeyInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyInput keyInput) {
		this.keyInput = keyInput;
	}

	public MouseInput getMouseInput() {
		return mouseInput;
	}

	public void setMouseInput(MouseInput mouseInput) {
		this.mouseInput = mouseInput;
	}

	public String getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(String currentSelection) {
		this.currentSelection = currentSelection;
	}
	

	public ArrayList<ArrayList<Integer>> getSelectedCoords() {
		return selectedCoords;
	}

	public void setSelectedCoords(ArrayList<ArrayList<Integer>> selectedCoords) {
		this.selectedCoords = selectedCoords;
	}
	
	

	public HashMap<String, Integer> getShipHash() {
		return shipHash;
	}

	public void setShipHash(HashMap<String, Integer> shipHash) {
		this.shipHash = shipHash;
	}
	/* EACH SHIP WILL BE A COLOR AT THE MOMENT UNTIL GRAPHICS ARE ADDED TO FILL SPACE
	 C = Carrier / RED
	 B = Battleship / ORANGE
	 R = Cruiser / YELLOW
	 S = Submarine / GREEN
	 D = Destroyer / BLUE
	 */
	public boolean listContains(ArrayList<ArrayList<Integer>> list, String[][] board){
		boolean result=false;
		for(int i = 0; i<list.size(); i++){
			String a = board[list.get(i).get(0)][list.get(i).get(1)];
			if(! (a.contains("C") || a.contains("B") || a.contains("R") || a.contains("S") || a.contains("D"))){
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean listContains(ArrayList<ArrayList<Integer>> list){
		boolean result=false;
		for(int i = 0; i<list.size(); i++){
			String a = currentBoard[list.get(i).get(0)][list.get(i).get(1)];
			if(! (a.contains("C") || a.contains("B") || a.contains("R") || a.contains("S") || a.contains("D"))){
				result = true;
				break;
			}
		}
		return result;
	}




	public boolean isPlayerOneSetup() {
		return playerOneSetup;
	}

	public void setPlayerOneSetup(boolean playerOneSetup) {
		this.playerOneSetup = playerOneSetup;
	}

	public boolean isDoneSelect() {
		return doneSelect;
	}

	public void setDoneSelect(boolean doneSelect) {
		this.doneSelect = doneSelect;
	}

	public String[][] getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(String[][] currentBoard) {
		this.currentBoard = currentBoard;
	}

	public String[][] getBoardP2() {
		return boardP2;
	}

	public void setBoardP2(String[][] boardP2) {
		this.boardP2 = boardP2;
	}

	
	
	public int getTs() {
		return ts;
	}

	public void setTs(int ts) {
		this.ts = ts;
	}

	public static void main(String[] args) {
        new Game();

    }


}