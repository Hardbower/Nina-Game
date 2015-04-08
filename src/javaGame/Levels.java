package javaGame;

public class Levels {
	
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int NONE = 0;
	private static final int WALL = 1;
	private static final int GROUND = 2;
	private static final int CEILING = 3;
	
	public int checkContact(int level, float xPos, float yLevel, int looking){
		int contact = 0;
		if(level==1)
			contact = checkLevel1(xPos, yLevel, looking);
		
		return contact;
	}
	
	public int checkWin(int level, float xPos, float yLevel){
		int winning = 0;
		if(level ==1 && xPos+40 >284 && yLevel+260+72>=550)
			winning = 1;
		return winning;
	}
	
	//returns
	// NONE = 0
	// WALL = 1;
	// GROUND = 2;
	// CEILING = 3;
	
	private int checkLevel1(float x, float y, int looking){
		int contact = NONE;
		if(y<=-2736+74 && x>53){
			contact = GROUND;
		}
		else if(x<53 && y>-2445 && y<-2432){
			contact = GROUND;
		}
		else if(x>141-40 && y<-2196 && y>-2210){
			contact = GROUND;
		}
		else if(x>212-40 && y>-2882+260 && y<-2860+260){
			contact = CEILING;
		}
		else if(x>145-40 && x<213-40 && y>-2634+260+16 && y<-2500+260){
			contact = CEILING;
		}
		else if(x<53 && y<-2764+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>212-40 && y>-2882+260+16 && y<-2196 && looking == RIGHT){
			contact = WALL;
		}
		else if(x>140-40 && y>-2634+260+16 && y<-2528+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<73 && x>65 && y>-2442+260+16 && y<-2214+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>65-40 && x <73-40 && y>-2442+260+16 && y<-2214+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<178 && x>172 && y>-2272+260+16 && y<-2120+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>172-40 && x <178-40 && y>-2272+260+16 && y<-2120+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x>266-40 && x <270-40 && y>-2104+260+16 && y<-1836+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<90 && x>85 && y>-1934+260+16 && y<-1732+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x<90 && y<-1732+260+72 && y>-1750+260+72){
			contact = GROUND;
		}
		else if(x<162 && x>156 && y>-1618+260+16 && y<-1382+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>156-40 && x <162-40 && y>-1618+260+16 && y<-1382+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<88 && x>82 && y>-1434+260+16 && y<-1270+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>82-40 && x <88-40 && y>-1434+260+16 && y<-1270+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<242 && x>236 && y>-1434+260+16 && y<-1270+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>236-40 && x <242-40 && y>-1434+260+16 && y<-1270+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x>290-40 && y>-1254+260+16 && y<-868+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<10 && y>-1084+260+16 && y<-698+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x<88 && x>82 && y>-1078+260+16 && y<-914+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>82-40 && x <88-40 && y>-1078+260+16 && y<-914+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x>70-40 && x<274 && y<-732+260+72 && y>-745+260+72){
			contact = GROUND;
		}
		else if(x>274-40 && y>-732+260+16 && y<-492+260+72 && looking == RIGHT){
			contact = WALL;
		}
		else if(x<230-40 && x>26 && y<-541+260+72 && y>-560+260+72){
			contact = GROUND;
		}
		else if(x<26 && y>-546+260+16 && y<-308+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>150-40 && x<154-40 && y>-380+260+16 && y<-346+260+72 && looking == RIGHT){
			contact = WALL;
 		}
		else if(x>150-40 && y<-346+260+72 && y>-360+260+72){
			contact = GROUND;
		}
		else if(x<138 && x>133 && y>-242+260+16 && y<-208+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x<138 && x>40 && y<-208+260+72 && y>-230+260+72){
			contact = GROUND;
		}
		else if(x<40 && y<-90+260+72 && y>-100+260+72){
			contact = GROUND;
		}
		else if(x<40 && y>-208+260+16 && y<-90+260+72 && looking == LEFT){
			contact = WALL;
		}
		else if(x>126-40 && y<-90+260+72 && y>-100+260+72){
			contact = GROUND;
		}
		return contact;
	}
}
