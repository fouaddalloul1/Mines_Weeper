
package Game_logic;
import Screen.*;
import Sound.GameSounds;
import Sound.GameSounds1;
import Sound.SoundEffect;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Game_logic.*;
public class Main{
    public static boolean console;
    public static SoundEffect musicGame;
    public static Settings settings;
    public static void initSounds(){
        //            initSound();
        GameSounds1 gs1 =new GameSounds1();
        GameSounds gs = new GameSounds();
        Thread tgs = new Thread(gs);
        Thread tgs1 = new Thread(gs1);
        tgs.start();
        tgs1.start();
        try {
            tgs.join();
            tgs1.join();
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
    public static void initSettings(){
        settings =new Settings();
        Thread settingsTh = new Thread(settings);
        settingsTh.start();
    }
    public static void main(String args[]) throws IOException {
        Scanner cin = new Scanner(System.in);
        {
            Images.initImages();
            initSounds();
            initSettings();
        LoadGameGui loadGameGui= new LoadGameGui();
            Thread loadGameGuiTh = new Thread(loadGameGui);
            loadGameGuiTh.start();
        Home home = new Home();
        home.start();
        }
    }
}
