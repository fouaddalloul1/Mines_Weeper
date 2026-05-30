package Screen;

import Game_logic.Rules;
import Screen.Home;
import Screen.game;
import Sound.GameSounds;
import buttons.ButtonGradient;
import buttons.ColorToggleButton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Scanner;

public class DIfficulty extends Thread implements java.io.Serializable {
    static Scanner cin = new Scanner(System.in);
    public static JFrame difficultyFrame;
    public static boolean multiPlayer;
    public static JLabel backGroundLabel;
    public static  ImageIcon backGround;
    public static JMenuBar menuBar ;
    public static JMenu file;
    public static JMenu help;
    public static ImageIcon bombIcon;
    public static ImageIcon homeIcon;
    public static ImageIcon homeIcon1;
    public static ImageIcon exitIcon;
    public static ImageIcon aboutIcon;
    public static ImageIcon howToPlayIcon;
    public static ImageIcon settingsIcon;
    public static ImageIcon settingsIcon1;
    public static JMenuItem exit;
    public static JMenuItem howToPlay;
    public static JMenuItem about;

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
        file.add(exit);
        exit.setIcon(exitIcon);
         howToPlay = new JMenuItem(new AbstractAction("How To Play") {
            public void actionPerformed(ActionEvent e) {
                String message = "The game is played on a grid of cells, where some of the cells contain mines.\n The goal of the game is to reveal all the cells that do not contain mines, without revealing any mines.\n\nThe player can left-click on a cell to reveal it. If the revealed cell contains a mine,\n the player loses the game. If it does not contain a mine \n the cell will show a number indicating the number of mines in the surrounding cells.\n\nThe player can right-click on a cell to mark it as potentially containing a mine.\n This can be helpful in keeping track of which cells the player thinks might contain mines.\n\nThe player can left-click on a cell that has already been revealed to reveal all\n the surrounding cells that have not been marked as potentially containing mines.\n This can be useful if the player is confident that all the surrounding cells do not contain mines.\n\nThe game is won when all the cells that do not contain mines have been revealed.\n\nThe game has four difficulty levels: easy, medium, hard, and expert.\n The easy level has a smaller grid with fewer mines, while the expert level has a larger grid with more mines.\n\nThe player can choose a difficulty level by clicking on the corresponding button in the \"Choose Difficulty\" dialog.\n Once a difficulty level has been selected, a new game will start with the chosen settings.";
                showInfoMessage("How To Play", message);
            }
        });
        about = new JMenuItem(new AbstractAction("About") {
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
        difficultyFrame.add(menuBar);
        difficultyFrame.setJMenuBar(menuBar);
       menuBar.setForeground(Color.red);
        menuBar.setBackground(new Color(162, 131, 131));
    }
    public static void  initImage(){
        try { settingsIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/settings.png"));}catch (Exception e){}
        try { settingsIcon1 = new ImageIcon(game.class.getClassLoader().getResource("Images/settings1.png"));}catch (Exception e){}
        try{bombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/bomb.png"));}catch (Exception e){}
        try{backGround = new ImageIcon(Home.class.getClassLoader().getResource("Images/minesWeeperBack3.png"));}catch (Exception e){}
        try{homeIcon = new ImageIcon(Home.class.getClassLoader().getResource("Images/home.png"));}catch (Exception e){}
        try{homeIcon1 = new ImageIcon(Home.class.getClassLoader().getResource("Images/home1.png"));}catch (Exception e){}
        try{exitIcon = new ImageIcon(Home.class.getClassLoader().getResource("Images/door.png"));}catch (Exception e){}
        try { aboutIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/about.png"));}catch (Exception e){}
        try { howToPlayIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/howToPlay.png"));}catch (Exception e){}
    }
    public static MouseListener mouseListener;
    public static ActionListener actionListener;
    public static ButtonGradient easyBtn;
    public static ButtonGradient mediumBtn;
    public static ButtonGradient hardBtn;
    public static ButtonGradient expertBtn;
    public static JLabel homeLabel;

    public static JLabel settingsLabel;
    public static void initBtn(){
        //easy Button
        try{easyBtn = new ButtonGradient();}catch (Exception e){e.getStackTrace();}
        easyBtn.setBounds(60,540,170,170);
        easyBtn.setFont(new Font("Serif",Font.BOLD,40));
        easyBtn.setText("Easy");
        easyBtn.addActionListener(actionListener);
        easyBtn.addMouseListener(bHandler);
        backGroundLabel.add(easyBtn);
        /////////////
        //medium button
        mediumBtn = new ButtonGradient();
        mediumBtn.setBounds(285,540,170,170);
        mediumBtn.setFont(new Font("Serif",Font.BOLD,35));
        mediumBtn.setText("Medium");
        mediumBtn.setColor2(Color.ORANGE);
        mediumBtn.setForeground(new Color(62, 68, 87));
        mediumBtn.addMouseListener(bHandler);
        mediumBtn.addActionListener(actionListener);
        backGroundLabel.add(mediumBtn);
        ///////
        //Hard button
        hardBtn = new ButtonGradient();
        hardBtn.setBounds(525,540,170,170);
        hardBtn.setFont(new Font("Serif",Font.BOLD,35));
        hardBtn.setText("Hard");
        hardBtn.setColor1(Color.orange);
        hardBtn.setColor2(Color.black);
        hardBtn.setForeground(new Color(62, 68, 87));
        hardBtn.addMouseListener(bHandler);
        hardBtn.addActionListener(actionListener);
        backGroundLabel.add(hardBtn);
        //Expert button
        expertBtn = new ButtonGradient();
        expertBtn.setBounds(750,540,170,170);
        expertBtn.setFont(new Font("Serif",Font.BOLD,35));
        expertBtn.setText("Expert");
        expertBtn.addMouseListener(bHandler);
        expertBtn.addActionListener(actionListener);
        backGroundLabel.add(expertBtn);

        //Home Btn
         homeLabel= new JLabel(homeIcon);
        homeLabel.setVisible(true);
        homeLabel.setBounds(30,60,64,64);
        homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeLabel.addMouseListener(bHandler);
        backGroundLabel.add(homeLabel);

        //settings label
        settingsLabel= new JLabel(settingsIcon);
        settingsLabel.setVisible(true);
        settingsLabel.setBounds(900,60,64,64);
        settingsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settingsLabel.addMouseListener(bHandler);
        backGroundLabel.add(settingsLabel);
    }
    public static enum Dif{
        easy(1),medium(2),hard(3),expert(4);
         int dif;
        Dif(int dif){
            this.dif = dif;
        }
    }
    public static Dif currentDif;
    public static int dif;
    public static void initActionListener(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==easyBtn){
                    currentDif =Dif.easy;
                    multiPlayer =false;
                    ColorToggleButton.dig=5;
                    Rules r = new Rules(10,10,13);//13
                    r.start();
                }
                else if(e.getSource()==mediumBtn){
                    currentDif =Dif.medium;
                    multiPlayer =false;
                    ColorToggleButton.dig=5;
                    Rules r = new Rules(15,15,33);//33
                    r.start();
                }
                else if(e.getSource()==hardBtn){
                    currentDif = Dif.hard;
                    multiPlayer =false;
                    Rules r = new Rules(20,20,70);//70
                    ColorToggleButton.dig=3; // for cells numbers shadow
                    r.start();
                }
                else if(e.getSource()==expertBtn){
                    currentDif = Dif.expert;
                    ColorToggleButton.dig=3;//for cells numbers shadow
                    multiPlayer =false;
                    Rules r = new Rules(24,24,105);
                    r.start();
                }
            }
        };
    }
    /////////////////////////////////sounds


    public static void initBackGround(){
        ///////////////
        backGroundLabel = new JLabel(backGround);
        backGroundLabel.setVisible(true);
        backGroundLabel.setBounds(0,-30,1000,830);
    }
    ///sounds

    public  static void init(){
        initSound();
//        clickSound = "C:\\Users\\Fouad dalloul\\Downloads\\Pop.wav";
        difficultyFrame =new JFrame("MinesWeeper");
        initImage();
        initActionListener();
        initBackGround();
        initMenuBar();
        initBtn();

        difficultyFrame.setIconImage(bombIcon.getImage());
        difficultyFrame.add(backGroundLabel);
        difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        difficultyFrame.setSize(1000, 830);
        difficultyFrame.setLocationRelativeTo(null);
        difficultyFrame.setLayout(null);
        difficultyFrame.setVisible(true);
        difficultyFrame.setResizable(false);
        Home.homeFrame.setVisible(false);
    }
    @Override
    public void run(){
        init();
    }
    /////////////////inneer class for sounds // init sound
    public static String clickSound;
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
            if(e.getSource()==homeLabel){
                homeLabel.setIcon(homeIcon1);
            }
            else if(e.getSource()==settingsLabel){
                settingsLabel.setIcon(settingsIcon1);
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==homeLabel){
                Home home = new Home();
                home.start();
                difficultyFrame.setVisible(false);
            }
           else if(e.getSource()==settingsLabel)
            {
                Settings.settingsFrame.setVisible(true);
                Settings.settingsBackFrame = Settings.backFrame.difficulty;
                difficultyFrame.setVisible(false);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }


        @Override
        public void mouseExited(MouseEvent e) {

            if(e.getSource()==homeLabel){
                homeLabel.setIcon(homeIcon);
            }
            else if(e.getSource()==settingsLabel){
                settingsLabel.setIcon(settingsIcon);
            }

        }
    }

}
