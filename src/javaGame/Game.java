package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	//game name
	public static final String gameName = "CLIMB NINJA!!! CLIMB!!1";
	//game states
	public static final int menu = 0;
	public static final int play = 1;
	public static final int pause = 3;
	public static final int win = 4;
	public static final int lose = 5;
	public static final int credits = 2;
	
	public Game(String gameName){
		//calls constructor for StateBasedGame and adds game name to window
		super(gameName);
		this.addState(new Play(play));
		this.addState(new Pause(pause));
		this.addState(new Menu(menu));
		this.addState(new Win(win));
		this.addState(new Lose(lose));
		this.addState(new Credits(credits));
	}
	
	//abstract method for StateBasedGame to work
	//GameContainer used to manage FPS, etc.
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(pause).init(gc, this);
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(win).init(gc,  this);
		this.getState(lose).init(gc, this);
		this.getState(credits).init(gc, this);
		
		//set initial state
		this.enterState(menu);
	}
	
	
	public static void main(String[] args) {
		//game window
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new Game(gameName));
			//length, width, and full screen (t/f)
			agc.setDisplayMode(300, 460, false);
			agc.setShowFPS(false);
			agc.setVSync(true);
			//creates window
			agc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
