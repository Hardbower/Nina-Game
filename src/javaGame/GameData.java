package javaGame;

import Audio.MusicPlayer;

public class GameData {
	private static int reinit;
	private static int time;
	private static int level;
	private static int place;
	private static boolean playing;
	private static boolean musicPlaying;
	private static MusicPlayer music;
	private static MusicPlayer sfx;
	private static String currentSong;
	private static boolean mute;
	private static int highScore = 8675309;

	
	public GameData(){
		reinit = 0;
		level = 0;
		playing = false;
		currentSong = "";
		time = 0;
		highScore = 8675309;
	}
	
	public static void play(String song){
		if(!song.equals(currentSong)){
			if(musicPlaying)
				music.stop();
			music = new MusicPlayer(song);
			music.play();
			currentSong = song;
		}
	}
	public static void playSFX(String sound){
		
		sfx = new MusicPlayer(sound);
		sfx.play();
	}
	public static void stopSFX(){
		sfx.stop();
	}
	public static boolean isPlaying(){
		boolean playing;
		if(!currentSong.equals(""))
			playing = true;
		else
			playing = false;
		return playing;
	}
	
	public static void mute(){
		if(!mute){
			mute = true;
			music.stop();
		}
		else{
			mute = false;
			music.play();
		}
	}
	public static void stopMusic(){
		try{
			music.stop();
		}
		catch(Exception e){}
	}
	public static boolean getPlaying(){
		return playing;
	}
	public static void setPlaying(boolean play){
		playing = play;
	}
	public static void setMusicPlaying(boolean play){
		musicPlaying = play;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int tmpLevel){
		level = tmpLevel;
	}
	public void setPlace(int tmpPlace){
		place = tmpPlace;
	}
	public static int getPlace(){
		checkTime();
		return place;
	}
	public static int getReInit(){
		return reinit;
	}
	public static void setReInit(int value){
		if(value == 1){
			reset();
		}
		reinit = value;
	}
	public static int getTime(){
		return time;
	}
	public static void setTime(int tmpTime){
		time = tmpTime;
	}
	public static void addTime(int add){
		time +=add;
	}
	private static void checkTime(){
		if(level == 0){
			if(time>=25)
				place = 3;
			if(time<25)
				place = 2;
			if(time<16)
				place = 1;
			if(time<5)
				place = 0;
		}
	}
	private static void reset(){
		time = 0;
		level = 0;
		place = 0;
	}
	public static int getHighScore(){
		return highScore;
	}
	public static void confirmScore(){
		if( time < highScore)
			highScore = time;
	}
}
