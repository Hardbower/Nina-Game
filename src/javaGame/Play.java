package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	//static final variables
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int NONE = 0;
	private static final int WALL = 1;
	private static final int GROUND = 2;
	private static final int CEILING = 3;
	
	private Levels levels;
	
	//images
	private Image bg;
	private Image lookL;
	private Image lookR;
	private Image holdR;
	private Image holdL;
	private Image level;
	private Image flyR;
	private Image flyL;
	private Image fallL;
	private Image fallR;
	private Image deadR;
	private Image deadL;
	
	//strings
	private String levelLocation;
	
	//ints
	private int looking;
	private final int terminalVelocity = 13;
	private int prevContact;
	private int prevLook;

	//booleans
	private boolean dead;
	private boolean falling;
	private boolean won;
	private boolean showCo;
	
	//floats
	private float xPos;
	private float yPos;
	private float yV;
	private float gravity;
	private final float jumpStrength = -8;
	private float xLevel;
	private float yLevel;
	
	//times
	private long deathTime;
	private long timeOut;
	private long startTime;
	private long endTime;
	
	
	//constructor
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setTargetFrameRate(60);
				
		//set gravity
		gravity = 0.4f;
		
		//set booleans
		won = false;
		dead = false;
		falling = false;
		showCo = false;
		
		prevContact = 0;
		prevLook = 0;
		
		levelLocation = "";
		
		//instantiate Levels class
		levels = new Levels();
		
		//attach images
		bg = new Image("res/playBackground.png");
		holdL = new Image("res/ninjaHoldLeft.png");
		holdR = new Image("res/ninjaHoldRight.png");
		lookL = new Image("res/ninjaLookLeft.png");
		lookR = new Image("res/ninjaLookRight.png");
		level = new Image("res/pipeCityV5.png");
		flyR = new Image("res/flyRight.png");
		flyL = new Image("res/flyLeft.png");
		fallL = new Image("res/ninjaFallingLeft.png");
		fallR = new Image("res/ninjaFallingRight.png");
		deadR = new Image("res/ninjaDeadRight.png");
		deadL = new Image("res/ninjaDeadLeft.png");
		
		//set initial positions
		looking = RIGHT;
		xPos = (300/2)-20;  //middle of window
		yPos = 260;  //floating below middle
		xLevel = 0;
		yLevel = -3000;  //height of level
		//player h: 72  w: 40
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(bg, 0, 0);
		g.drawImage(level, xLevel, yLevel);
		
		//show coordinates
		if(showCo)
			g.drawString(levelLocation, 10, 10);
				
		//check contact for level 1
		int contact = levels.checkContact(1, xPos, yLevel, looking);
		
		//draw positions
		if(dead == true){ //if dead
			if(looking == RIGHT)
				g.drawImage(deadR, xPos, yPos);
			if(looking == LEFT)
				g.drawImage(deadL, xPos, yPos);
		}
		else if(falling == true){ //if not dead and falling
			if(looking==RIGHT)
				g.drawImage(fallR, xPos, yPos);
			else if(looking==LEFT)
				g.drawImage(fallL, xPos, yPos);
		}
		else{ //if not dead or falling
			if(contact == GROUND){
				//if touching the ground
				if(looking == RIGHT){
					g.drawImage(lookR, xPos, yPos);
					//flying = true;
				}
				else if(looking ==LEFT){
					g.drawImage(lookL, xPos, yPos);
					//flying = true;
				}
				if(prevContact != GROUND)
					GameData.playSFX("/land.mp3");
			}
			else if(contact == WALL){
				//if touching a wall
				if(looking == RIGHT){
					g.drawImage(holdR, xPos, yPos);
					//flying = false;
				}
				else if(looking == LEFT){
					g.drawImage(holdL, xPos, yPos);
					//flying = false;
				}
			}
			else if(contact == NONE && prevContact == WALL){
				if(looking == RIGHT && prevLook == LEFT)
					g.drawImage(flyR, xPos, yPos);
				else if(looking == LEFT && prevLook == RIGHT)
					g.drawImage(flyL, xPos, yPos);
				else if(looking == RIGHT && prevLook == RIGHT)
					g.drawImage(holdR, xPos, yPos);
				else{
					g.drawImage(holdL, xPos, yPos);
				}
				GameData.playSFX("/select menu.mp3");
			}
			else{
				if(looking == RIGHT){
					g.drawImage(flyR, xPos, yPos);
					//flying = false;
				}
				else if(looking == LEFT){
					g.drawImage(flyL, xPos, yPos);
					//flying = false;
				}
			}
		}
		prevContact = contact;
		prevLook = looking;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		//get key input
		Input input = gc.getInput();
		
		//check falling
		falling = false;
		if(yV > terminalVelocity && !dead){
			falling = true;
		}
		
		//check contact with floors/walls/ceilings
		int contact = levels.checkContact(1, xPos, yLevel, looking);
		
		//  Y axis movement
		// gravity
		yV += gravity;
		//if no contact, fall
		if(contact == NONE){
			yLevel -= yV;
		}
		//stop movement if in contact w/ wall or ground
		else if(contact == WALL || contact == GROUND){
			yV = 0;
		}
		//bump head
		else if(contact == CEILING){
			yV *= -1;
			yLevel-=3;
			yLevel -= yV;
		}
			
		//allow jumping if
		if(contact == WALL && !dead|| contact == GROUND && !dead){ //player is in contact with block and not dead
			if(input.isKeyDown(Input.KEY_UP)){
				yV = jumpStrength;
				if(looking == RIGHT){
					//move off wall to jump
					xPos--;
				}
				else if(looking == LEFT){
					//move off wall to jump
					xPos++;
				}
				//subtract velocity, which is negative jump strength
				//increases yLevel
				yLevel -= yV;
			}
		}
		
		//sets ground level of level
		if(yLevel<-2667)
			yLevel = -2667;
		
		// slows acceleration at high speed
		if(yV > 10)
			gravity = .1f;
		else
			gravity = .4f;
		
		
		//  X axis movement
		if(input.isKeyDown(Input.KEY_RIGHT) && !dead && !falling){
			if(contact == NONE || contact == GROUND || contact == WALL && looking == LEFT){
				looking = RIGHT;
				xPos += 3;
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT) && !dead && !falling){
			if(contact == NONE || contact == GROUND || contact == WALL && looking == RIGHT){
				looking = LEFT;
				xPos -= 3;
			}
		}
		
		//enforce window/level limits
		if(xPos <0)
			xPos = 0;
		if(xPos >300-40)
			xPos = 300-40;
		
		//check win
		int winning = levels.checkWin(1, xPos, yLevel);
		//if player won
		if(winning>0 && won == false){
			GameData.playSFX("/Win.mp3");
			endTime = System.currentTimeMillis();
			GameData.addTime((int)((endTime-startTime)/1000));
			won = true;
			sbg.enterState(4);
			//GameData.stopSFX();
		}
		
		//check death
		//if in contact after free fall
		if(contact > 0 && falling == true){
			GameData.playSFX("/hit ground.mp3");
			dead = true;
			yV = 0;
		}
		
		//determine time of death
		if(falling == true)
			deathTime = System.nanoTime()/1000000000;
		//time passed after death
		if(dead)
			timeOut = System.nanoTime()/1000000000;
		//if dead for 2 seconds, leave state
		if(timeOut-2 == deathTime && dead){
			sbg.enterState(5);
		}
				
		//pause menu
		if(input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_P)){
			//save current time
			endTime = System.currentTimeMillis();
			//add elapsed play time to game data
			GameData.addTime((int)((endTime-startTime)/1000));
			//enter pause menu
			sbg.enterState(3);
		}
		
		//optional commands
		//coordinates
		if(input.isKeyPressed(Input.KEY_Z))
			if(showCo)
				showCo = false;
			else
				showCo = true;
		levelLocation = Float.toString(yLevel);
		levelLocation = "y: " + Float.toString(yLevel) + "   x: "+xPos+"\n velocity: "+Float.toString(yV);
		//mute
		if(input.isKeyPressed(Input.KEY_M))
			GameData.mute();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg){
		//upon state entrance, get time
		if(GameData.getPlaying())
			GameData.play("/chipSong3Clipped.mp3");
		GameData.setPlaying(true);
		startTime = System.currentTimeMillis();
	}
	
	public int getID(){
		return 1;
	}
	
}
