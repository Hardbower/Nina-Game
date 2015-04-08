package Audio;

import javaGame.GameData;

import javax.sound.sampled.*;


public class MusicPlayer {

	private Clip clip;
	
	public MusicPlayer(String song){
		
		
		try{
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(song));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels()*2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if(clip == null) return;
		stop();
		GameData.setMusicPlaying(true);
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	public void stop(){
		if(clip.isRunning())
			clip.stop();
		GameData.setMusicPlaying(false);
	}
	
	public void close(){
		stop();
		GameData.setMusicPlaying(false);
		clip.close();
	}
}
