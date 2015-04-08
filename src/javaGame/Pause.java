package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Pause extends BasicGameState{

	private Image resume;
	private Image quit;
	private int menuNum = 0;
	private boolean cleared;
	private Input input;
	
	
	public Pause(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setTargetFrameRate(60);
		resume = new Image("res/pauseResume.png");
		quit = new Image("res/pauseQuit.png");
		cleared = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		if(menuNum==0)
			g.drawImage(resume, 0, 0);
		else
			g.drawImage(quit, 0, 0);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		input = gc.getInput();
		if(!input.isKeyPressed(Input.KEY_F15) && !cleared){
			input.clearKeyPressedRecord();
			cleared = true;
		}
		else if(input.isKeyPressed(Input.KEY_ENTER) && cleared){
			if(menuNum == 0){
				sbg.enterState(1);
			}
			else if(menuNum == 1){
				sbg.enterState(0);
			}
		}
		else if(input.isKeyPressed(Input.KEY_DOWN) && cleared){
			menuNum = 1;
		}
		else if(input.isKeyPressed(Input.KEY_UP) && cleared){
			menuNum = 0;
		}
		
		
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg){
		cleared = false;
	}
	
	public int getID(){
		return 3;
	}
	
}
