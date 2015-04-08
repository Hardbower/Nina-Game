package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState{
	
	private Image creditRoll;
	private float yPos;
	private final float yV = .5f;
	
	public Credits(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		creditRoll = new Image("res/credits.png");
		yPos = 0;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(creditRoll, 0, yPos);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		yPos-=yV;
		if(yPos<=-1500+460){
			gc.exit();
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg){
		//upon state entrance, get time
		if(GameData.getPlaying())
			GameData.play("/Dineuhsore.mp3");
		GameData.setPlaying(true);
	}
	
	public int getID(){
		return 2;
	}
	
}
