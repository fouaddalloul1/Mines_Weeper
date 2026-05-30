package Sound;

import Screen.Home;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundEffect {
     Clip clip;
     FloatControl fc;
     Float currentVolume=0f;
    Float prevVolume=0f;
    boolean mute = false ;

    public void setFile(String soundName){
        try{
            URL soundFileName = Home.class.getClassLoader().getResource(soundName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);
            clip = AudioSystem.getClip();
            clip.open(sound);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        }
        catch(Exception e){

        }
    }

    public void play(){

        try{clip.setFramePosition(0);
            clip.start();
        }catch (Exception e){e.getStackTrace();}
    }
    public void stop(){
        clip.stop();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    long clipTimePosition;
    boolean pause =false;
    public void pause(){
        if(pause==false) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            pause=true;
        }
    }
    public void resume(){
        if(pause){
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();
        loop();
        pause=false;
        }
    }

    public void mute(){
        if(mute==false){
            prevVolume= currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute=true;
        }
        else if(mute){
            currentVolume = prevVolume;
            fc.setValue(currentVolume);
            mute=false;
        }
    }
    public void control(Float vol){
        currentVolume=vol;
        if(currentVolume>6f)currentVolume=6f;
        fc.setValue(currentVolume);
    }

}
