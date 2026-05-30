package FileHandling;
import Timer.*;
import Game_logic.*;
import buttons.ColorToggleButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameData implements java.io.Serializable{
    //Logic.Game Data
    public ArrayList<Player> players;
    public Player currentPlayer;
    public int numberOfPlayers;


    //GridData
    public Cell[][] cells;
    public int gridRows;
    public int gridCols;
    public int mines;
    public static ArrayList<Cell> minedCells;
    public static ArrayList<Cell> flagedCells;

    //Rules Data
    public boolean gameOver;
    public Color[] playersColor;


    //GUI Data
    public ColorToggleButton[][] btn;
    JPanel gridPanel;
    JLabel timeLabel;
    boolean first;
    int dig;
    boolean multiPlayer;

    //GUI Time
    int timeNumber;
    int sec;
    int maxSec;
    int minute;
    boolean timeNewGame;

    int maxMinute;
    //gameTimer
    int currentGameTimer;
    JLabel currentTimeLabel;
}
