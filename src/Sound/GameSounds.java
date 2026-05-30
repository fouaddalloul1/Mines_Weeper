package Sound;

import Screen.Home;

public class GameSounds implements Runnable{
    public static SoundEffect systemSe ;
    public static SoundEffect musicGame;
    public static SoundEffect wonEffect;
    public static SoundEffect loseEffect;


    public static void initWonEffect(){
        wonEffect = new SoundEffect();
        wonEffect.setFile("Sound/Sounds/BombEffect.wav");
    }
    public static void initLoseEffect(){
        loseEffect = new SoundEffect();
        loseEffect.setFile("Sound/Sounds/gameOverEffect.wav");//change
    }
    private static void initMusic(){
        musicGame = new SoundEffect();
        musicGame.setFile("Sound/Sounds/alheba.wav");
        musicGame.control(-14.0f);//-14.0f
        musicGame.loop();
        GameSounds.musicGame.play();
    }
    private static void initSystemSound(){

        systemSe = new SoundEffect();
        systemSe.setFile("Sound/Sounds/Pop2.wav");
//        systemSe.loop();
    }
    public static void startGameSounds(){
        initSystemSound();
        initMusic();
        initGameEffect();
        initWonEffect();
        initLoseEffect();

    }
    public static SoundEffect gameEffect;
    private static void initGameEffect(){

        gameEffect = new SoundEffect();
        gameEffect.setFile("Sound/Sounds/BombEffect.wav");//change
    }


    public void run(){
        startGameSounds();
    }
}
