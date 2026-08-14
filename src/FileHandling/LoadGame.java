package FileHandling;

import Game_logic.*;
import Screen.Home;
import Screen.game;
import Timer.GameTimer;
import Timer.Time;
import buttons.ColorToggleButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadGame {
     static GameData loadGame;
     static String fileName;
    public static void LoadThisGame(String filename)throws IOException,ClassNotFoundException{
        fileName = filename;
            loadGame = desrialize(filename);

        try{Grid.cells = loadGame.cells;}catch (Exception e){
            System.out.printf(e.getMessage());
            System.out.printf(String.valueOf(e.getStackTrace()));
        }


        Home.numberOfPlayers= loadGame.numberOfPlayers;
        Grid.numOfRows = loadGame.gridRows;
        Grid.numOfCol = loadGame.gridCols;
        Grid.noOfBombs = loadGame.mines;
        Grid.minedCells = loadGame.minedCells;
        Grid.flagedCells =loadGame.flagedCells;



        //game rules
        Rules.gameOver = loadGame.gameOver;
        Rules.playersColor = loadGame.playersColor;
        Home.multiPlayer=loadGame.multiPlayer;
        Rules.multiPlayer=loadGame.multiPlayer;
        Rules.players=loadGame.players;
        Rules.currentPlayer=loadGame.currentPlayer;
        //game Gui
        game.btn = loadGame.btn;
        game.GridJPanel = loadGame.gridPanel;
//        game.firstTime = false;
        game.firstClick = loadGame.first;
        ColorToggleButton.dig = loadGame.dig;
        //Gui Time
        Time.number = loadGame.timeNumber;
        Time.tenSec = loadGame.sec;
        Time.maxSec = loadGame.maxSec;
        Time.minute = loadGame.minute;
        Time.maxMin = loadGame.maxMinute;
        Time.timeLabel= loadGame.timeLabel;
        game.timeNewGame = loadGame.timeNewGame;
        GameTimer.curTime=loadGame.currentGameTimer;
        GameTimer.timeLabel=loadGame.currentTimeLabel;

    }
    public static void listDir(File dir){
        File elements[] = dir.listFiles();
        for (File elemnt:elements){
            System.out.println(elemnt.getName());
        }

    }

    public static GameData desrialize(String fileName) throws IOException, ClassNotFoundException {
        File minesWeeperDir = new File(System.getProperty("user.home")+File.separator+"MinesWeeperGame"+File.separator+"Saved Games");
        FileInputStream fileIn = new FileInputStream(minesWeeperDir.getAbsolutePath()+File.separator+"f$G"+fileName+".dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        loadGame = (GameData) objectInputStream.readObject();
        objectInputStream.close();
        fileIn.close();
        return loadGame;
    }
}
