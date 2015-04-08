package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Win extends BasicGameState{

	private Image first;
	private Image second;
	private Image third;
	private Image none;
	private String time;
	private Input input;
	private boolean cleared;
	
	public Win(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		first = new Image("res/1stPlace.png");
		second = new Image("res/2ndPlace.png");
		third = new Image("res/3rdPlace.png");
		none = new Image("res/podiums.png");
		time = "";
		cleared = false;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		if(GameData.getPlace()==1)
			g.drawImage(first, 0, 0);
		else if(GameData.getPlace()==2)
			g.drawImage(second, 0, 0);
		else if(GameData.getPlace()==3)
			g.drawImage(third, 0, 0);
		else
			g.drawImage(none, 0, 0);
		g.drawString(time, 110, 10);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		time = "TIME: " + Integer.toString(GameData.getTime()) + "s";
		
		input = gc.getInput();
		if(!input.isKeyPressed(Input.KEY_F15) && !cleared){
			input.clearKeyPressedRecord();
			cleared = true;
		}
		else if(input.isKeyPressed(Input.KEY_ENTER) && cleared){
			//time is compared to high score
			System.out.println(Integer.toString(GameData.getTime()));
			GameData.confirmScore();
			GameData.stopMusic();
			GameData.setReInit(1);
			sbg.enterState(0);
		}
		
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg){
		//upon state entrance, get time
		if(GameData.getPlaying())
			GameData.play("/chipSong1Clipped.mp3");
		GameData.setPlaying(true);
		cleared = false;
	}
	
	public int getID(){
		return 4;
	}
	
}
