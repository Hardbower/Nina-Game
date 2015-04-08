package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Lose extends BasicGameState{

	private Image gOver;
	private Input input;
	private boolean cleared;
	
	public Lose(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gOver = new Image("res/gameOver.png");
		cleared = false;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(gOver, 0, 0);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		input = gc.getInput();
		if(!input.isKeyPressed(Input.KEY_F15) && !cleared){
			input.clearKeyPressedRecord();
			cleared = true;
		}
		else if(input.isKeyPressed(Input.KEY_ENTER) && cleared){
			GameData.setReInit(1);
			sbg.enterState(0);
			GameData.stopMusic();
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg){
		//upon state entrance, get time
		if(GameData.getPlaying())
			GameData.play("/Dineuhsore.mp3");
		GameData.setPlaying(true);
		cleared = false;
	}
	
	public int getID(){
		return 5;
	}
	
}
