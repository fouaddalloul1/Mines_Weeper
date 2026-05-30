package FileHandling;

import Game_logic.Grid;
import Screen.Home;
import Game_logic.Rules;
import Screen.game;
import Timer.GameTimer;
import Timer.Time;
import buttons.ColorToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AutoSave extends Thread implements java.io.Serializable{
    static GameData gameData;
    static String fileName="autoSave";
    @Override
    public void run(){

        gameData = new GameData();
        SaveGame.fileName = fileName;
        //game
        gameData.numberOfPlayers= Home.numberOfPlayers;
//        gameData.playersColor = Rules.playersColor;
        //game grid
        gameData.cells = Grid.cells;
        gameData.gridRows = Grid.numOfRows;
        gameData.gridCols = Grid.numOfCol;
        gameData.mines = Grid.noOfBombs;
        GameData.minedCells = Grid.minedCells;
        gameData.flagedCells =Grid.flagedCells;


        //game rules
        gameData.players = Rules.players;
        gameData.currentPlayer = Rules.currentPlayer;
        gameData.gameOver = Rules.gameOver;
        gameData.playersColor = Rules.playersColor;
        gameData.multiPlayer=Home.multiPlayer;
        // game Gui
//        synchronized (this){gameData.btn  = game.btn;}
        gameData.gridPanel = game.GridJPanel;
        gameData.first = game.firstClick;
        gameData.timeLabel= Time.timeLabel;
        gameData.dig = ColorToggleButton.dig;//color button shadow digree
        //Gui Time
        gameData.timeNumber = Time.number;
        gameData.sec = Time.tenSec;
        gameData.maxSec =Time.maxSec;
        gameData.minute= Time.minute;
        gameData.maxMinute = Time.maxMin;

        //Game Time
        gameData.currentGameTimer= GameTimer.curTime;
        gameData.currentTimeLabel=GameTimer.timeLabel;
        try {
            serialize(gameData,fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
    public void serialize(GameData gameData, String fileName) throws IOException {
        File minesWeeperDir = new File(System.getProperty("user.home") + File.separator + "MinesWeeperGame" + File.separator + "Saved Games");
        minesWeeperDir.mkdirs();
        FileOutputStream fileOut = new FileOutputStream(minesWeeperDir.getAbsolutePath()+File.separator+fileName+".dat");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);

        synchronized (this){objectOutputStream.writeObject(gameData);}

        fileOut.close();
        objectOutputStream.close();

    }
    }


