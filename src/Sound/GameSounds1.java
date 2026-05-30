package Sound;

public class GameSounds1 implements Runnable{

    public static SoundEffect gameEffect1;

    public static void startGameSounds(){
        initGameEffect1();
    }
    private static void initGameEffect1(){

        gameEffect1 = new SoundEffect();
        gameEffect1.setFile("Sound/Sounds/BombEffect.wav");//change
    }
    public void run(){
        startGameSounds();
    }

}
