package Game_logic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import Screen.Home;
import Screen.Settings;
import Screen.game;
import Sound.GameSounds;
import Timer.*;

import javax.swing.*;

public class Rules extends Thread implements java.io.Serializable{
    public static boolean gameOver;
    public static boolean win;
    public static boolean flagRole;
    public static Color[]  playersColor ;
    public static int rulesRows;
    public static int rulesCols;
    public  static int rulesBomb;
    public static ArrayList<Player> players;
    public static Player currentPlayer;
    public static boolean multiPlayer = Home.multiPlayer;

    public Rules(int rulesRows,int rulesCols,int rulesBomb){
        this.rulesRows = rulesRows;
        this.rulesCols = rulesCols;
        this.rulesBomb = rulesBomb;
        gameOver=false;
    }
    public static void startNewGame() {
        currentPlayer=players.get(0);
        gameOver=false;
        win =true;
        flagRole =false;
        game.firstClick=false;
        Grid.init(rulesRows,rulesCols,rulesBomb);
        game.createAndShowGUI();
    }
    @Override
    public void run(){
        startNewGame();
    }

    public static void addPlayers(String name,int index){
        if (index==0){initPlayerColor();
        players =new ArrayList<Player>();
        }

        players.add(new Player(name,playersColor[index]));
            if(index==0){players.get(0).setTurn(true);
            currentPlayer=players.get(0);}
            else players.get(index).setTurn(false);
    }
    public static void initPlayerColor(){
        playersColor = new Color[8];
        playersColor[0]=new Color(255, 0, 0);
        playersColor[4]=new Color(0, 140, 255);
        playersColor[2]=Color.orange;
        playersColor[3]=new Color(255, 0, 189, 255);
        playersColor[1]=new Color(97, 164, 18);
        playersColor[5]=new Color(124, 131, 134);
        playersColor[6]= new Color(5, 7, 229);
        playersColor[7]=new Color(0, 0, 0);
    }
    public static void swapPlayer(){
        for (int i = 0; i < Home.numberOfPlayers; i++) {
                if(players.get(i).isTurn()){
                    players.get(i).setTurn(false);
                    try{players.get((i+1)%Home.numberOfPlayers).setTurn(true);}catch (Exception e){}
                    currentPlayer=players.get((i+1)% Home.numberOfPlayers);
                    break;
                }
        }
        game.currentPlayerBtn.setText(currentPlayer.getName());
        game.currentPlayerBtn.setPressedColor(Rules.currentPlayer.getColor());
        if (Home.multiPlayer)
        {
            game.currentPlayerBtn.setColor2(Rules.currentPlayer.getColor());
            game.currentPlayerBtn.setColor1(Rules.currentPlayer.getColor());

        }
    }
    public static void addScore(int score){
        Rules.currentPlayer.setCurrentScore(Rules.currentPlayer.getCurrentScore()+score);
    }
    public static void countScore(Cell cell, MouseEvent e){
        if (SwingUtilities.isRightMouseButton(e)&& !cell.isVisible()) {

            if (!cell.isFlag() ) {
                if (cell.getValue() == 'B')
                    addScore(12);
                else
                    addScore(-14);
            }
            else if (cell.isFlag() ) {
                if(cell.getValue()=='B')addScore(-14);
                else addScore(+14);}
        }
        else if (SwingUtilities.isLeftMouseButton(e)&& !cell.isVisible()&&!cell.isFlag()){
            if (cell.getValue() == 'B'){ //if it;s bomb
                if(Home.multiPlayer)addScore(-35);
                //else single player are lose
                //else addScore(Grid.unCheckedCells()*25*DIfficulty.dif) for single player won
            }

        }
    }
    public static void floodFillScore(Cell cell){
        if(Grid.score==1)addScore((cell.getValue()-48)*2);
            //اذا كانت رقم بضيف نقاط الرقم
        else addScore(Grid.score);
        Grid.score=0;
        // غير هيك حيضيف واحد لكل خانة انفتحت
    }
    public static boolean isWon(){
        if(!Home.multiPlayer){
            if(Grid.unCheckedCells() == Grid.noOfBombs){
                GameSounds.wonEffect.setFile("Sound/Sounds/wonEffect.wav");
                GameSounds.wonEffect.control((float) Settings.gameVolumeSlider.getValue());
                if(!Settings.gameVolumeCheck.isSelected())
                    GameSounds.wonEffect.play();
                return true;}}
        else if(Grid.visableCells()== (Grid.numOfRows*Grid.numOfCol)-Grid.noOfBombs){
            GameSounds.wonEffect.setFile("Sound/Sounds/wonEffect.wav");
            GameSounds.wonEffect.control((float) Settings.gameVolumeSlider.getValue());
            if(!Settings.gameVolumeCheck.isSelected())
            GameSounds.wonEffect.play();
            return true;
        }
        return false;
    }

    public static void gameOver(){
        gameOver=true;
        try{
            Time.stop=true;
            game.gameTimer.stop();
            game.gameTimer1.stop();}catch (Exception exception){}

        //JOption
        JPanel PlayersColorLabel = new JPanel(new GridLayout(players.size(), 1));
        for (int i = 0; i < Home.numberOfPlayers; i++) {
           // Set player name text color to default
            JLabel colorLabel = new JLabel(players.get(i).getName() + " : "+players.get(i).getCurrentScore());
            colorLabel.setForeground(Rules.players.get(i).getColor());  // Set color name text color to player color
            // Add  labels to the panel
            JPanel playerPanel = new JPanel();
            playerPanel.add(colorLabel);
            PlayersColorLabel.add(playerPanel);
        }

        // Show the player color dialog

        File file =new File(System.getProperty("user.home") + File.separator + "MinesWeeperGame" + File.separator + "Saved Games"+File.separator+"f$GautoSave"+".dat");
        file.delete();
        JOptionPane.showMessageDialog(null, PlayersColorLabel, isWon()? "Congratulations, you won!":"you lose :( , But you can play again ;) ", JOptionPane.PLAIN_MESSAGE);
        GameSounds.musicGame.resume();
        game.returnHome();
//        file.renameTo(new File(System.getProperty("user.home") + File.separator + "MinesWeeperGame" + File.separator + "Saved Games"+File.separator+"autoSaveDeleted.dat"));
    }

}

