package FileHandling;

import Game_logic.*;
import Screen.Home;
import Screen.LoadGameGui;
import Screen.game;
import Timer.GameTimer;
import Timer.Time;
import buttons.ColorToggleButton;

import javax.swing.*;
import java.io.*;

public class SaveGame  {
    static GameData gameData;
    static String fileName;
    static public void SaveThisGame(String fileName)  {
        LoadGameGui.showGames();
        if(LoadGameGui.numberOfGames>=10)
        {
            JOptionPane.showMessageDialog(null,"sorry maximum games you can save is 10");
        return;}
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
        gameData.btn  = game.btn;
        gameData.gridPanel = game.GridJPanel;
        gameData.first = game.firstClick;
        gameData.timeLabel= Time.timeLabel;
        gameData.dig = ColorToggleButton.dig;//color button shadow digree
        //Gui Time
        gameData.timeNewGame = game.timeNewGame;
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
    public static void serialize(GameData gameData , String fileName) throws IOException {

        //c:users/fouad dalloul/Minesweepergame/Saved Games

        File minesWeeperDir = new File(System.getProperty("user.home") + "/" + "MinesWeeperGame" + File.separator + "Saved Games");
        minesWeeperDir.mkdirs();
        FileOutputStream fileOut = new FileOutputStream(minesWeeperDir.getAbsolutePath()+File.separator+"f$G"+fileName+".dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);

        objectOutputStream.writeObject(gameData);

        fileOut.close();
        objectOutputStream.close();
    }

}
