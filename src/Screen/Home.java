package Screen;

import FileHandling.LoadGame;
import Game_logic.Main;
import Game_logic.Rules;
import Sound.GameSounds;
import buttons.ButtonGradient;
import Sound.SoundEffect;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

public class Home extends Thread implements java.io.Serializable{
   public static JFrame homeFrame;
   public static boolean multiPlayer;
   public static JLabel backGroundLabel;
   public static  ImageIcon backGroundImage;
   public static JMenuBar menuBar ;
   public static JMenu file;
   public static JMenu help;

   public ImageIcon minesWeeper;

   public static ButtonGradient singleBtn;
   public static ButtonGradient multiPlayerBtn;
    public static ButtonGradient loadBtn;
    public static ButtonGradient getMultiPlayerBtn;
   public static ActionListener actionListener;

   public static ImageIcon bombIcon;
    public static ImageIcon exitIcon;
    public static ImageIcon aboutIcon;
    public static ImageIcon howToPlayIcon;
    public static JMenuItem exit;
    static public void showInfoMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
   public static void initMenuBar(){
       menuBar = new JMenuBar();
       file = new JMenu("File");
       exit = new JMenuItem(new AbstractAction("Exit") {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });
       exit.setIcon(exitIcon);
       file.add(exit);
       JMenuItem howToPlay = new JMenuItem(new AbstractAction("How To Play") {
           public void actionPerformed(ActionEvent e) {
               String message = "The game is played on a grid of cells, where some of the cells contain mines.\n The goal of the game is to reveal all the cells that do not contain mines, without revealing any mines.\n\nThe player can left-click on a cell to reveal it. If the revealed cell contains a mine,\n the player loses the game. If it does not contain a mine \n the cell will show a number indicating the number of mines in the surrounding cells.\n\nThe player can right-click on a cell to mark it as potentially containing a mine.\n This can be helpful in keeping track of which cells the player thinks might contain mines.\n\nThe player can left-click on a cell that has already been revealed to reveal all\n the surrounding cells that have not been marked as potentially containing mines.\n This can be useful if the player is confident that all the surrounding cells do not contain mines.\n\nThe game is won when all the cells that do not contain mines have been revealed.\n\nThe game has four difficulty levels: easy, medium, hard, and expert.\n The easy level has a smaller grid with fewer mines, while the expert level has a larger grid with more mines.\n\nThe player can choose a difficulty level by clicking on the corresponding button in the \"Choose Difficulty\" dialog.\n Once a difficulty level has been selected, a new game will start with the chosen settings.";
               showInfoMessage("How To Play", message);
           }
       });
       JMenuItem about = new JMenuItem(new AbstractAction("About") {
           public void actionPerformed(ActionEvent e) {
               String message = "MINESWEEPER GAME v1.0 \nAll Rights Are Reserved \nBY Fouad_dalloul ❤";
               showInfoMessage("About", message);
           }
       });
       help = new JMenu("Help");
       howToPlay.setIcon(howToPlayIcon);
       about.setIcon(aboutIcon);
       help.add(howToPlay);
       help.add(about);
       menuBar.add(file);
       menuBar.add(help);
       homeFrame.add(menuBar);
       homeFrame.setJMenuBar(menuBar);
//       menuBar.setForeground(Color.red);
       menuBar.setBackground(new Color(162, 131, 131));
   }
    public static ImageIcon settingsIcon;
    public static ImageIcon settingsIcon1;
    public static void  initImage(){
        bombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/bomb.png"));
        try{exitIcon = new ImageIcon(Home.class.getClassLoader().getResource("Images/door.png"));}catch (Exception e){}
        try { aboutIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/about.png"));}catch (Exception e){}
        try { howToPlayIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/howToPlay.png"));}catch (Exception e){}
        try { aboutIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/about.png"));}catch (Exception e){}
        try { howToPlayIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/howToPlay.png"));}catch (Exception e){}
        try { settingsIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/settings.png"));}catch (Exception e){}
        try { settingsIcon1 = new ImageIcon(game.class.getClassLoader().getResource("Images/settings1.png"));}catch (Exception e){}
    }
    public static JLabel settingsLabel;
    public static void initBtn(){
       //Single Button
       singleBtn = new ButtonGradient();
       singleBtn.setBounds(200,540,230,230);
       singleBtn.setFont(new Font("Serif",Font.BOLD,40));
       singleBtn.setText("SINGLE");
       singleBtn.addActionListener(actionListener);
       singleBtn.addMouseListener(bHandler);
       backGroundLabel.add(singleBtn);
       /////////////
       //multiPlayer button
       multiPlayerBtn = new ButtonGradient();
       multiPlayerBtn.setBounds(600,540,230,230);
       multiPlayerBtn.setFont(new Font("Serif",Font.BOLD,35));
       multiPlayerBtn.setText("MultiPlayer");
       multiPlayerBtn.addActionListener(actionListener);
       multiPlayerBtn.addMouseListener(bHandler);
       backGroundLabel.add(multiPlayerBtn);
/////loadBtn
        loadBtn = new ButtonGradient();
       loadBtn.setText("Continue");
       loadBtn.setBounds(270,400,500,100);
       loadBtn.setFont(new Font("Serif",Font.BOLD,35));
       loadBtn.addActionListener(actionListener);
       loadBtn.addMouseListener(bHandler);
       File minesweeperDir = new File(System.getProperty("user.home")+File.separator+"MinesWeeperGame"+File.separator+"Saved Games"+ File.separator + "f$GautoSave.dat");
       if (minesweeperDir.exists()) {
           backGroundLabel.add(loadBtn);
       }

       //Settings button
       ///now

        settingsLabel= new JLabel(settingsIcon);
        settingsLabel.setVisible(true);
        settingsLabel.setBounds(900,60,64,64);
        settingsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settingsLabel.addMouseListener(bHandler);
        backGroundLabel.add(settingsLabel);
    }
   public static int numberOfPlayers;
   public static boolean loadLastGame;
   public static void actionListener(){
       actionListener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                if(e.getSource()==multiPlayerBtn||e.getSource()==singleBtn){
//                    game.gameMusic.musicStop();//now
                    loadLastGame=false;
                            game.timeNewGame =true;//for resetTime
                    if(e.getSource()==multiPlayerBtn)multiPlayer=true;
                   else multiPlayer=false;//if it's Single
                    JSlider slider = new JSlider(JSlider.HORIZONTAL, 2, 8, 2);
                    slider.setMinimum(2);slider.setMaximum(8);slider.setValue(2);
                    slider.setMajorTickSpacing(1);
                    slider.setPaintTicks(true);
                    slider.setPaintLabels(true);
                    slider.setBounds(40, 80, 200, 50);
                    slider.setForeground(Color.red);
                    int numOfPlayerOption;
                    if(multiPlayer) { numOfPlayerOption = JOptionPane.showConfirmDialog(null,slider," Select number of Players",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if (numOfPlayerOption==JOptionPane.CANCEL_OPTION||numOfPlayerOption==JOptionPane.CLOSED_OPTION)return;
                    }
                    numberOfPlayers=1;//for if it's single
try{
                    if(multiPlayer)numberOfPlayers= slider.getValue();
                       if(e.getSource()==multiPlayerBtn&&numberOfPlayers<2||numberOfPlayers>8)JOptionPane.showMessageDialog(null,"Players should be more than 1 and less than 8");
                       else{
                           JPanel InputsPanel = new JPanel(new GridLayout(numberOfPlayers, 1));
                           JTextField[] playersNamesFields = new JTextField[numberOfPlayers];
                           for (int i = 0; i < numberOfPlayers; i++) {
                               if(multiPlayer)
                               InputsPanel.add(new JLabel("Player " + (i + 1) + ":"));
                               else
                                   InputsPanel.add(new JLabel("Your Name :"));
                               playersNamesFields[i] = new JTextField("");
                               InputsPanel.add(playersNamesFields[i]);
                           }
                           // Show the player name dialog//now
                           int result = JOptionPane.showConfirmDialog(null, InputsPanel, (multiPlayer)?"Enter Players Name :":"Enter your name :", JOptionPane.OK_CANCEL_OPTION);
                           if (result==JOptionPane.CANCEL_OPTION||result==JOptionPane.CLOSED_OPTION)
                               return;
                           for (int i = 0; i < numberOfPlayers; i++) {
                              if(playersNamesFields[i].getText().equals("")){
                                  JOptionPane.showConfirmDialog(null,"Please fill in all names","Error",JOptionPane.ERROR_MESSAGE,JOptionPane.OK_OPTION);
                              numberOfPlayers=0;
                              return;
                              }
                           }
                           for (int i = 0; i < numberOfPlayers; i++) {
                               Rules.addPlayers(playersNamesFields[i].getText(),i);
                           }
                           JPanel PlayersColorLabel = new JPanel(new GridLayout(Rules.players.size(), 1));
                           for (int i = 0; i < Home.numberOfPlayers; i++) {
                               JLabel label = new JLabel("Player " + (i + 1) + " : ");
                               label.setForeground(Color.BLACK);  // Set player name text color to default
                               JLabel colorLabel = new JLabel(Rules.players.get(i).getName());
                               colorLabel.setForeground(Rules.players.get(i).getColor());  // Set color name text color to player color
                               // Add the labels to a panel
                               JPanel playerPanel = new JPanel();
                               playerPanel.add(label);
                               playerPanel.add(colorLabel);
                               PlayersColorLabel.add(playerPanel);
                           }

                           // Show the player color dialog
                           JOptionPane.showMessageDialog(null, PlayersColorLabel, "Players Colors", JOptionPane.INFORMATION_MESSAGE);
                           DIfficulty d = new DIfficulty();
                           d.start();
                       }

                   }catch (Exception ex){
//                       throw new RuntimeException(ex);
                        System.out.println(ex.getCause());
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null,"An Error Occurred. Pleas Try Again !","Error",JOptionPane.ERROR_MESSAGE);

                   }

               }
                else if(e.getSource()==loadBtn){

                        try {
                    LoadGame.LoadThisGame("autoSave");
                    loadLastGame=true;
                    game.createAndShowGUI();
                    game.initTime();
//                    game.initGameTimer();
                    game.updateGrid();
                    if(!Home.multiPlayer){
                        game.dayTheme();}
//                    game.initGameTimer();
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null,"Game Load faild","faild",JOptionPane.ERROR_MESSAGE);
                        }
                }
           }
       };
   }
   public static void initBackGround(){
       backGroundImage = new ImageIcon(Home.class.getClassLoader().getResource("Images/minesWeeperBack3.png"));
       ///////////////
       backGroundLabel = new JLabel(backGroundImage);
       backGroundLabel.setVisible(true);
       backGroundLabel.setBounds(0,-30,1000,830);
   }
    public static void startHome(){
        initImage();
        initSound();
        multiPlayer=false;
        homeFrame =new JFrame("MinesWeeper");
        initBackGround();
        initMenuBar();
        actionListener();
        initBtn();

//        homeFrame.pack();
        homeFrame.setIconImage(bombIcon.getImage());
        homeFrame.add(backGroundLabel);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(1000, 830);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setLayout(null);
        homeFrame.setResizable(false);
        homeFrame.setVisible(true);
    }
@Override
public void run(){
    startHome();
}



//// Sound.Sound
    public static ButtonHandler bHandler ;
    public static void initSound(){
        bHandler = new ButtonHandler();
    }


    public static class ButtonHandler implements MouseListener{


        @Override
        public void mouseEntered(MouseEvent e) {
            GameSounds.systemSe.setFile("Sound/Sounds/Pop2.wav");
            GameSounds.systemSe.control((float)Settings.systemVolumeSlider.getValue());
            if(!Settings.systemVolumeCheck.isSelected())
            GameSounds.systemSe.play();
            if(e.getSource()==settingsLabel){
                settingsLabel.setIcon(settingsIcon1);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==settingsLabel)
            { Settings.settingsFrame.setVisible(true);
                Settings.settingsBackFrame = Settings.backFrame.home;
            homeFrame.setVisible(false);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }


        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==settingsLabel){
                settingsLabel.setIcon(settingsIcon);
            }
        }
    }


}
