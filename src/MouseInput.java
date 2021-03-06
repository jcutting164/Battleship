import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.Clip;

public class MouseInput implements MouseListener, MouseMotionListener{
        //where initialization occurs:
        //Register for mouse events on blankArea and the panel.
     
	private Game game;
	private int setupRow=-1;
	private int setupColumn=-1;
	private int gameRow=-1;
	private int gameColumn=-1;
	private String currentSelected="";





	public MouseInput(Game game){
		this.game=game;
	}




	public void mousePressed(MouseEvent e) {

		if(game.getCurrentState().equals("Setup") && e.getY()/72<11 && e.getX()/72<11 && e.getY()>71 && e.getX()>71){
			setupRow=e.getY()/72 - 1;
			setupColumn=e.getX()/72 - 1;

			// check for ship present
			if(!(game.getCurrentBoard()[setupRow][setupColumn].equals(" "))){
				// then there is a piece here
				currentSelected=game.getCurrentBoard()[setupRow][setupColumn];
				game.removeSelection(game.getCurrentBoard());
				game.setSelectedCoords(new ArrayList<ArrayList<Integer>>());

				game.selectAll(currentSelected, game.getCurrentBoard());

				// TO BE DONE: ADD SELECTION COORDS TO ARRAY IN THE SELECT ALL FUNCTION IN MAIN GAME

			}else{
				currentSelected="";
			}


		}else{
			setupRow=-1;
			setupColumn=-1;
		}


		if(game.getCurrentState().equals("Menu")){
			if(new Rectangle(860, 100, 256, 96).intersects(new Rectangle(e.getX(),e.getY(),1,1))){
				game.setCurrentState("Setup");
			}
		}else if(game.getCurrentState().equals("Setup")){
			if(new Rectangle(900,500,164,64).intersects(new Rectangle(e.getX(),e.getY(),1,1))){
				if(game.isPlayerOneSetup()){
					game.setPlayerOneSetup(false);
					game.setCurrentBoard(game.getBoardP2());

				}
				else if(!game.isPlayerOneSetup()){
					// transition to game mode
					game.setCurrentState("game");
					game.setCurrentBoard(game.getBoardP2());
					game.setPlayerOneTurn(true);
				}
			}
		}else if(game.getCurrentState().equals("game")&& e.getY()/game.getTs()<11 && e.getX()/game.getTs()<11 && e.getY()>game.getTs()-1 && e.getX()>game.getTs()-1){
			gameRow=e.getY()/game.getTs() - 1;
			gameColumn=e.getX()/game.getTs() - 1;
			
			String s = game.getCurrentBoard()[gameRow][gameColumn];
			/* EACH SHIP WILL BE A COLOR AT THE MOMENT UNTIL GRAPHICS ARE ADDED TO FILL SPACE
	   		 C = Carrier / RED
	   		 B = Battleship / ORANGE
	   		 R = Cruiser / YELLOW
	   		 S = Submarine / GREEN
	   		 D = Destroyer / BLUE
	   		 */
			if((s.contains("C") || (s.contains("B")) || (s.contains("R")) || (s.contains("S")) || (s.contains("D"))) && !s.contains("H") ){
				// then there is a hit
				game.getCurrentBoard()[gameRow][gameColumn]+="H";
				if(game.isPlayerOneTurn()){
					System.out.println("kills: "+game.getC_kills_P1());
                    if(s.contains("C")){
                        game.setC_kills_P1(game.getC_kills_P1()+1);
                        if(game.getC_kills_P1() == 5){
                            PlaySound(new File("res/carrier.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("B")){
                        game.setB_kills_P1(game.getB_kills_P1()+1);
                        if(game.getB_kills_P1() == 4){
							PlaySound(new File("res/battleship.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("R")){
                        game.setR_kills_P1(game.getR_kills_P1()+1);
                        if(game.getR_kills_P1() == 3){
							PlaySound(new File("res/cruiser.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("S")){
                        game.setS_kills_P1(game.getS_kills_P1()+1);
                        if(game.getS_kills_P1() == 3){
							PlaySound(new File("res/sub.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("D")){
                        game.setD_kills_P1(game.getD_kills_P1()+1);
                        if(game.getD_kills_P1() == 2){
							PlaySound(new File("res/destroyer.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }
					if(game.getC_kills_P1() + game.getD_kills_P1() + game.getB_kills_P1() + game.getR_kills_P1() + game.getS_kills_P1() == 17){
						game.setCurrentState("end");
					}
                }else{
                    if(s.contains("C")){
                        game.setC_kills_P2(game.getC_kills_P2()+1);
                        if(game.getC_kills_P2() == 5){
							PlaySound(new File("res/carrier.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("B")){
                        game.setB_kills_P2(game.getB_kills_P2()+1);
                        if(game.getB_kills_P2() == 4){
							PlaySound(new File("res/battleship.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("R")){
                        game.setR_kills_P2(game.getR_kills_P2()+1);
                        if(game.getR_kills_P2() == 3){
							PlaySound(new File("res/cruiser.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("S")){
                        game.setS_kills_P2(game.getS_kills_P2()+1);
                        if(game.getS_kills_P2() == 3){
							PlaySound(new File("res/sub.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }else if(s.contains("D")){
                        game.setD_kills_P2(game.getD_kills_P2()+1);
                        if(game.getD_kills_P2() == 2){
							PlaySound(new File("res/destroyer.wav"));
                        }else{
							PlaySound(new File("res/hit.WAV"));

						}
                    }

                    if(game.getC_kills_P2() + game.getD_kills_P2() + game.getB_kills_P2() + game.getR_kills_P2() + game.getS_kills_P2() == 17){
                    	game.setCurrentState("end");
					}


                }




            }else if(!s.contains("H") && !s.contains("M")){

			    PlaySound(new File("res/miss.WAV"));

				game.getCurrentBoard()[gameRow][gameColumn]+="M";


				// switch turn on a miss
				game.setPlayerOneTurn(!game.isPlayerOneTurn());



			}
			
			
			
		}


	}

	static void PlaySound(File Sound){
	    try{
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(Sound));
	        clip.start();

        }catch(Exception e){

        }
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
		}else if(game.getCurrentState().equals("game")&&e.getY()/game.getTs()<11 && e.getX()/game.getTs()<11 && e.getY()>game.getTs()-1 && e.getX()>game.getTs()-1){
			// clear all hovers then reasign
			game.removeSelection(game.getCurrentBoard());
			System.out.println("happening");
			game.getCurrentBoard()[e.getY()/game.getTs() - 1][e.getX()/game.getTs() - 1]+="#";
			
		}else if(game.getCurrentState().equals("game")){
			game.removeSelection(game.getCurrentBoard());
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