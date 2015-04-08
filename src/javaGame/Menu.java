package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{

	private Image menuStart;
	private Image menuQuit;
	private int menuNum=0;
	private String highScore;
	
	public Menu(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		GameData.setReInit(0);
		menuStart = new Image("res/mainMenu.png");
		menuQuit = new Image("res/mainMenu2.png");
		highScore = "";
		
	}
	
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		if(menuNum==0)
			g.drawImage(menuStart, 0, 0);
		else
			g.drawImage(menuQuit, 0, 0);
		
		g.drawString(highScore, 80, 430);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		if(GameData.getHighScore() == 8675309)
			highScore = "";
		else{
			highScore = "HIGH SCORE: "+ Integer.toString(GameData.getHighScore());
		}
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_ENTER)){
			if(menuNum == 0){
				GameData.playSFX("/enter.mp3");
				sbg.enterState(1);
				GameData.stopSFX();
			}
			else if(menuNum == 1){
				sbg.enterState(2);
				GameData.stopSFX();
				GameData.stopMusic();
			}
		}
		if(input.isKeyPressed(Input.KEY_DOWN)){
				GameData.playSFX("/menu select.mp3");
				//GameData.playSFX("/select menu.mp3");
				menuNum = 1;
		}
		if(input.isKeyPressed(Input.KEY_UP)){
				GameData.playSFX("/menu select.mp3");
				//GameData.playSFX("/select menu.mp3");
				menuNum = 0;
		}
	}
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
		if(GameData.getReInit() == 1)
			gc.reinit();
	}
	
	public int getID(){
		return 0;
	}
	
}
