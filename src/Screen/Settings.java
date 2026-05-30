package Screen;

import Game_logic.Main;
import Sound.GameSounds;
import Sound.SoundEffect;
import buttons.Slider.JsliderCustom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Settings  implements Runnable{
    public static enum backFrame{
        home,difficulty,gameUi;
    }

    public static backFrame settingsBackFrame=backFrame.home;
    public static Border mainBorder  = BorderFactory.createLineBorder(Color.red,3,true);
    public static Border chosenBorder =BorderFactory.createLineBorder(Color.lightGray,3,true);
    public  static JFrame settingsFrame;
    public static void  initTopBar(){
        JPanel topPanel =new JPanel(null);
        topPanel.setBounds(0,0,1000,40);
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(mainBorder);
        JLabel settingsLabel  = new JLabel();
        settingsLabel.setText("Settings");
        settingsLabel.setBackground(null);
        settingsLabel.setForeground(Color.lightGray);
        settingsLabel.setBounds(10,0,100,35);
        settingsLabel.setFont(new Font("Serif",Font.BOLD,25));
        topPanel.add(settingsLabel);
        mainJPanel.add(topPanel);
    }
public static void initSideBar(){

}
public static JLabel backLabel;
    public static void initBtn(){
        backLabel= new JLabel(Images.backIcon);
        backLabel.setBounds(30,40,64,64);
        backLabel.addMouseListener(mouseListener);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentJPanel.add(backLabel);
    }
    public static GridLayout contentGridLayout;
    public static JPanel contentJPanel;
    public static JsliderCustom musicVolumeSlider;
    public static JLabel musicVolumeLabel;
    public static JCheckBox musicVolumeCheck;
    public static void initMusicVolume(){
        musicVolumeCheck = new JCheckBox();
        musicVolumeCheck.setBounds(737,107,200,40);
        musicVolumeCheck.setIcon(Images.checkIcon);
        musicVolumeCheck.setBackground(null);
        musicVolumeCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//        musicVolumeCheck.setSelected(true);
        musicVolumeCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(musicVolumeCheck.isSelected())musicVolumeCheck.setIcon(Images.crossIcon);
                else musicVolumeCheck.setIcon(Images.checkIcon);
                GameSounds.musicGame.mute();

            }
        });
        musicVolumeLabel =new JLabel("Music");
        musicVolumeLabel.setFont(font);
        musicVolumeLabel.setForeground(Color.lightGray);
        musicVolumeLabel.setBounds(385,100,200,40);
        musicVolumeSlider = new JsliderCustom();
        musicVolumeSlider.setBounds(530,120,200,20);
        musicVolumeSlider.setMinimum(-55);
        musicVolumeSlider.setMaximum(6);
        musicVolumeSlider.setValue(-80);//-12
        musicVolumeSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        musicVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GameSounds.musicGame.control((float) musicVolumeSlider.getValue());
                if(musicVolumeSlider.getValue()==-55)GameSounds.musicGame.control(-80.0f);
            }
        });
        GameSounds.musicGame.control((float) musicVolumeSlider.getValue());
        contentJPanel.add(musicVolumeCheck);
        contentJPanel.add(musicVolumeLabel);
        contentJPanel.add(musicVolumeSlider);

    }
    public static JsliderCustom systemVolumeSlider;
    public static JLabel systemVolumeLabel;
    public static JCheckBox systemVolumeCheck;
    public static void initSystemEffect(){
        systemVolumeCheck = new JCheckBox();
        systemVolumeCheck.setBounds(737,207,200,40);
        systemVolumeCheck.setIcon(Images.checkIcon);
        systemVolumeCheck.setBackground(null);
        systemVolumeCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        systemVolumeCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(systemVolumeCheck.isSelected())systemVolumeCheck.setIcon(Images.crossIcon);
                else systemVolumeCheck.setIcon(Images.checkIcon);
                GameSounds.systemSe.mute();
            }
        });
        systemVolumeLabel =new JLabel("System");
        systemVolumeLabel.setFont(font);
        systemVolumeLabel.setForeground(Color.lightGray);
        systemVolumeLabel.setBounds(385,200,200,40);
        systemVolumeSlider = new JsliderCustom();
        systemVolumeSlider.setBounds(530,220,200,20);
        systemVolumeSlider.setMinimum(-55);
        systemVolumeSlider.setMaximum(6);
        systemVolumeSlider.setValue(-21);
        systemVolumeSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        systemVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GameSounds.systemSe.control((float) systemVolumeSlider.getValue());
                if(systemVolumeSlider.getValue()==-55)GameSounds.systemSe.control(-80.0f);
            }
        });
        GameSounds.systemSe.control((float) systemVolumeSlider.getValue());
        contentJPanel.add(systemVolumeCheck);
        contentJPanel.add(systemVolumeLabel);
        contentJPanel.add(systemVolumeSlider);
    }
    public static JsliderCustom gameVolumeSlider;
    public static JLabel gameVolumeLabel;
    public static JCheckBox gameVolumeCheck;
    public static void initGameVolume(){
        gameVolumeCheck = new JCheckBox();
        gameVolumeCheck.setBounds(737,307,200,40);
        gameVolumeCheck.setIcon(Images.checkIcon);
        gameVolumeCheck.setBackground(null);
        gameVolumeCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//        musicVolumeCheck.setSelected(true);
        gameVolumeCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameVolumeCheck.isSelected())gameVolumeCheck.setIcon(Images.crossIcon);
                else gameVolumeCheck.setIcon(Images.checkIcon);
                GameSounds.gameEffect.mute();

            }
        });
        gameVolumeLabel =new JLabel("SEffects");
        gameVolumeLabel.setFont(font);
        gameVolumeLabel.setForeground(Color.lightGray);
        gameVolumeLabel.setBounds(385,300,210,40);
        gameVolumeSlider = new JsliderCustom();
        gameVolumeSlider.setBounds(530,320,200,20);
        gameVolumeSlider.setMinimum(-55);
        gameVolumeSlider.setMaximum(6);
        gameVolumeSlider.setValue(2);
        gameVolumeSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gameVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GameSounds.gameEffect.control((float) gameVolumeSlider.getValue());
                if(gameVolumeSlider.getValue()==-55)GameSounds.gameEffect.control(-80.0f);
            }
        });
        GameSounds.gameEffect.control((float) gameVolumeSlider.getValue());//change
        contentJPanel.add(gameVolumeCheck);
        contentJPanel.add(gameVolumeLabel);
        contentJPanel.add(gameVolumeSlider);

    }
        public static JLabel audioLabel;
    public static void initAudioSettings(){
        audioLabel = new JLabel("Audio Settings :");
        audioLabel.setFont(font);
        audioLabel.setForeground(Color.lightGray);
        audioLabel.setBounds(160,67,300,45);
        mainJPanel.add(audioLabel);
        initMusicVolume();
        initSystemEffect();
        initGameVolume();
    }
    public static enum BombTheme{
        normal,skull,explode,grayMine;
    }
    public static BombTheme currentBombTheme;
    public static JLabel currentBombLabel ;
    public static JLabel explodeLabel;
    public static JLabel skullLabel;
    public static JLabel normalLabel;
    public static JLabel grayMineLabel;

    public static void initBombTheme(){
        currentBombTheme = BombTheme.normal;
//        currentBombLabel = new JLabel();
        JLabel bombLabel = new JLabel("BombTheme :");
        bombLabel.setForeground(Color.lightGray);
        bombLabel.setFont(font);
        bombLabel.setBounds(160,410,250,40);
        //explode
        explodeLabel =new JLabel(Images.explodeIcon64);
        explodeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        explodeLabel.setBounds(385,477,64,64);
        explodeLabel.setBorder(mainBorder);
        explodeLabel.addMouseListener(mouseListener);
        if(currentBombTheme==BombTheme.explode)normalLabel.setBorder(chosenBorder);
        //skull
        skullLabel = new JLabel(Images.skullIcon64);
        skullLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        skullLabel.setBounds(456,477,64,64);
        skullLabel.setBorder(mainBorder);
        skullLabel.addMouseListener(mouseListener);
        if(currentBombTheme==BombTheme.skull)normalLabel.setBorder(chosenBorder);
        //normal
        normalLabel = new JLabel(Images.normalIcon64);
        normalLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        normalLabel.setBounds(527,477,64,64);
        normalLabel.setBorder(mainBorder);
        normalLabel.addMouseListener(mouseListener);
        if(currentBombTheme==BombTheme.normal)normalLabel.setBorder(chosenBorder);
        //grayMine
        grayMineLabel = new JLabel(Images.grayMineIcon64);
        grayMineLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        grayMineLabel.setBounds(598,477,64,64);
        grayMineLabel.setBorder(mainBorder);
        grayMineLabel.addMouseListener(mouseListener);
        if(currentBombTheme==BombTheme.grayMine)normalLabel.setBorder(chosenBorder);

        //
        mainJPanel.add(bombLabel);
        mainJPanel.add(grayMineLabel);
        mainJPanel.add(explodeLabel);
        mainJPanel.add(skullLabel);
        mainJPanel.add(normalLabel);


        currentBombLabel = normalLabel;

//        gameVolumeLabel.setBounds(385,350,210,40);
    }
    public static Font font;
    public static void initContentSettings(){

    font =new Font("Serif",Font.BOLD,40);
    contentGridLayout = new GridLayout(8,1) ;
    contentJPanel = new JPanel(null);
    initBtn();
    initAudioSettings();
    initBombTheme();
    contentJPanel.setBounds(0,40,1000,790);
    contentJPanel.setBorder(mainBorder);
    contentJPanel.setBackground(null);
    //return btn

    mainJPanel.add(contentJPanel);

}



    public static JPanel mainJPanel;
    public static void initMainJPanel(){
    mainJPanel =new JPanel(null );
    mainJPanel.setBackground(new Color(40, 48, 61));
    mainJPanel.setSize(1000, 830);
    initContentSettings();
    initTopBar();
    settingsFrame.add(mainJPanel);

}

    public static void startSettings(){
        settingsFrame =new JFrame("MinesWeeper");
        initSound();

        initMainJPanel();
        settingsFrame.setIconImage(Images.bombIcon.getImage());
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setUndecorated(true);
        settingsFrame.setSize(1000, 830);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setLayout(null);
        settingsFrame.setResizable(false);
//        settingsFrame.setVisible(true);
    }
    public void run(){
        startSettings();
    }
    public static SoundEffect settingsSound;

    public static void initSound(){
        settingsSound =new SoundEffect();
        initMouseListener();
    }
    public static MouseListener mouseListener;
    public static void  initMouseListener(){
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource()==backLabel){
                    switch (settingsBackFrame) {
                        case home:{Home.homeFrame.setVisible(true);break;}
                        case difficulty:{DIfficulty.difficultyFrame.setVisible(true);break;}
                        case gameUi:{
                            game.initCellBombImage();
                            if(Home.multiPlayer)
                            game.updateGrid();
                            game.mainFrame.setVisible(true);
                            game.timeRes();
                            break;}
                    }
                    settingsFrame.setVisible(false);
                }
                else if(e.getSource()==explodeLabel){//now
                    currentBombTheme = BombTheme.explode;
                    currentBombLabel.setBorder(mainBorder);
                    currentBombLabel = explodeLabel;
                    currentBombLabel.setBorder(chosenBorder);
                }
                else if(e.getSource()==skullLabel){
                    currentBombTheme =BombTheme.skull;
                    currentBombLabel.setBorder(mainBorder);
                    currentBombLabel = skullLabel;
                    currentBombLabel.setBorder(chosenBorder);
                }
                else if(e.getSource()==normalLabel){
                    currentBombTheme =BombTheme.normal;
                    currentBombLabel.setBorder(mainBorder);
                    currentBombLabel = normalLabel;
                    currentBombLabel.setBorder(chosenBorder);
                }
                else if(e.getSource()==grayMineLabel){
                    currentBombTheme = BombTheme.grayMine;
                    currentBombLabel.setBorder(mainBorder);
                    currentBombLabel = grayMineLabel;
                    currentBombLabel.setBorder(chosenBorder);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                GameSounds.systemSe.setFile("Sound/Sounds/Pop2.wav");
                GameSounds.systemSe.control((float)Settings.systemVolumeSlider.getValue());
                if(!Settings.systemVolumeCheck.isSelected())
                    GameSounds.systemSe.play();
                if(e.getSource()==backLabel){
                    backLabel.setIcon(Images.backIcon1);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource()==backLabel){
                    backLabel.setIcon(Images.backIcon);
                }
            }
        };

    }

}
