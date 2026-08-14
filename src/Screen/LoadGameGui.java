package Screen;

import FileHandling.LoadGame;
import Game_logic.Rules;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import Timer.GameTimer;

import static Screen.game.gameTimer;
import static Screen.game.timer;

public class LoadGameGui implements Runnable,java.io.Serializable{
    public static boolean clickLoad;
    public static int numberOfGames=0;
    public static enum backFrame{
        home,difficulty,gameUi;
    }

    public static backFrame loadGameBackFrame;
    public static Border mainBorder  = BorderFactory.createLineBorder(Color.red,3,true);
    public static Border mainBorder1  = BorderFactory.createLineBorder(Color.decode("#cbb3b3"),3,true);


    public static MouseListener mouseListener;
    public static JPanel currentClick;
    public static void  initMouseListener(){
            mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                for(JPanel game:gamePanel){
                    if(e.getSource()==game){
                       if(currentClick!=null) currentClick.setBorder(mainBorder);
                        currentClick=game;
                        System.out.println( currentClick.getName());
                        currentClick.setBorder(mainBorder1);
                        loadLabel.setBorder(mainBorder1);
                        loadLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        deleteLabel.setBorder(mainBorder1);
                        deleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }
                }

                if(e.getSource()==deleteLabel&&currentClick!=null){
                 deleteGame(currentClick.getName());
                }

                else if(e.getSource()==loadLabel&&!clickLoad){
                    clickLoad=true;
                    try {
                        timer.stop();
                        if (Home.multiPlayer) {
                            GameTimer.stop = true;
                            game.gameTimer.stop();
                            game.gameTimer1.stop();
                        }
                    } catch (Exception exception) {
//            System.out.println(exception);
                    }
                    try {
                        LoadGame.LoadThisGame(currentClick.getName());
                        game.createAndShowGUI();
                        game.initTime();
                        game.updateGrid();
                        if(!Home.multiPlayer){
                            game.dayTheme();}
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,"Game Load faild","faild",JOptionPane.ERROR_MESSAGE);
                        clickLoad=false;
                    }
                }
                else if(e.getSource()==backLabel&&loadGameBackFrame!=null){
                    switch (loadGameBackFrame) {
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
                    loadGameFrame.setVisible(false);
                }
                else if(e.getSource()==exitLabel){
//                    game.autoSave();
                    System.exit(0);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                for(JPanel game:gamePanel){
                    if(e.getSource()==game){
                        game.setBorder(mainBorder1);
                    }
                }
                if(e.getSource()==backLabel){
                    backLabel.setIcon(Images.backIcon1);
                }
                else if(e.getSource()==exitLabel){
                    exitLabel.setIcon(Images.exitIcon1);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                for(JPanel game:gamePanel){
                    if(e.getSource()==game&&e.getSource()!=currentClick){
                        game.setBorder(mainBorder);
                    }
                }
                if(e.getSource()==backLabel){
                    backLabel.setIcon(Images.backIcon);
                }
                if(e.getSource()==exitLabel){
                    exitLabel.setIcon(Images.exitIcon);
                }
            }
        };
    }
    public static void updateLoadGui(){
        currentClick=null;
        gamesPanel.setVisible(false);
        numberOfGames=0;
        initGamesPanel();
        deleteLabel.setVisible(false);
        loadLabel.setVisible(false);
        backLabel.setVisible(false);
        exitLabel.setVisible(false);
        initBtnLabel();
    }
    public static void deleteGame(String fileName){
        File f = new File(System.getProperty("user.home")+File.separator+"MinesWeeperGame"+File.separator+"Saved Games"+File.separator+"f$G"+fileName+".dat");
        f.delete();
        updateLoadGui();
    }
    public static JFrame loadGameFrame;
    public static JLabel fireLabel;
    public static JLabel loadGameLabel;
    public static JLabel exitLabel;
    public static JLabel backLabel;
    public static JLabel loadLabel;
    public static JLabel deleteLabel;

    public static void  initBtnLabel(){
        //initExitLabel
        exitLabel = new JLabel(Images.exitIcon );
        exitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitLabel.setBounds(965,10,24,24);
        exitLabel.addMouseListener(mouseListener);
        mainJPanel.add(exitLabel);
        //initBackLabel
        backLabel= new JLabel(Images.backIcon);
        backLabel.setBounds(30,40,64,64);
        backLabel.addMouseListener(mouseListener);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainJPanel.add(backLabel);
        //initLoadlabel
        loadLabel= new JLabel(" Load");
        loadLabel.setBorder(mainBorder);
        loadLabel.setBounds(729,750,143,70);
        loadLabel.setForeground(Color.decode("#cbb3b3"));
        loadLabel.setFont(new Font("Serif",Font.BOLD,50));
        loadLabel.addMouseListener(mouseListener);
        mainJPanel.add(loadLabel);
        //initDeleteLabel
        deleteLabel= new JLabel(" Delete");
        deleteLabel.setBorder(mainBorder);
        deleteLabel.setBounds(552,750,170,70);
        deleteLabel.setForeground(Color.decode("#cbb3b3"));
        deleteLabel.setFont(new Font("Serif",Font.BOLD,50));
        deleteLabel.addMouseListener(mouseListener);
        mainJPanel.add(deleteLabel);
    }
    public static void  initFireLabel(){
        //load label
        loadGameLabel = new JLabel(Images.loadGameLabelImage);
        loadGameLabel.setBounds(150,0,700,270);
        mainJPanel.add(loadGameLabel);
        //fire image
        fireLabel = new JLabel(Images.fireImage);
        fireLabel.setBounds(143,-10,700,270);
        mainJPanel.add(fireLabel);
    }
    public static JPanel gamePanel[];
    public static void showGames(){
        numberOfGames=0;
        gamePanel = new JPanel[10];
        JLabel gameNames[] =new JLabel[10];
        JLabel gameTimes[] =new JLabel[10];
        File dir = new File(System.getProperty("user.home")+File.separator+"MinesWeeperGame"+File.separator+"Saved Games");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File elements[] = dir.listFiles();

        if (elements == null) {
            return;
        }

        for (File element:elements){
            String fileName = element.getName();
            String fileDomain = fileName.substring(fileName.length()-4,fileName.length());
            String fileSave  = fileName.substring(0,3);
            String finalFileName = fileName.substring(3, fileName.length()-4);
            if(fileDomain.equals(".dat")&&fileSave.equals("f$G")&&!finalFileName.equals("autoSave")&&numberOfGames<10)//
            {
                //initGamePanel
                gamePanel[numberOfGames] = new JPanel(null);
                gamePanel[numberOfGames].setSize(720,480);
                gamePanel[numberOfGames].setBackground(null);
                gamePanel[numberOfGames].setBorder(mainBorder);
                gamePanel[numberOfGames].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                gamePanel[numberOfGames].addMouseListener(mouseListener);
                gamePanel[numberOfGames].setName(finalFileName);
                //gameName
                gameNames[numberOfGames] = new JLabel(finalFileName);
                gameNames[numberOfGames].setForeground(Color.decode("#cbb3b3"));
                gameNames[numberOfGames].setBounds(20,0,380,40);
                gameNames[numberOfGames].setFont(new Font("Serif",Font.BOLD,30));
                gamePanel[numberOfGames].add(gameNames[numberOfGames]);
                //gameTime
                long lm = element.lastModified();
                Date date = new Date(lm);
                SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd    hh:mm:ss");
                System.out.println(sdf.format(date));
                gameTimes[numberOfGames] = new JLabel(sdf.format(date));
                gameTimes[numberOfGames].setBounds(410,0,350,40);
                gameTimes[numberOfGames].setForeground(Color.decode("#cbb3b3"));
                gameTimes[numberOfGames].setFont(new Font("Serif",Font.BOLD,30));
                gamePanel[numberOfGames].add(gameTimes[numberOfGames]);
                gamesPanel.add(gamePanel[numberOfGames]);
                numberOfGames++;
            }
        }
    }
    public static JPanel gamesPanel;
    public static JScrollPane jScrollPane;
    public static void initGamesPanel(){
//
//        JLabel label= new JLabel("fouad dalloul");
//        label.setForeground(Color.white);

        GridLayout gridLayout = new GridLayout(10,1);
        gamesPanel = new JPanel(gridLayout);
        showGames();
//        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        gamesPanel.setBorder(mainBorder);
        gamesPanel.setBackground(null);
        gamesPanel.setBounds(150,260,720,480);
        gamesPanel.setVisible(true);
//        gamesPanel.add(label);
        mainJPanel.add(gamesPanel);

    }
    public static  JPanel mainJPanel;
    public static void  initMainJPanel(){
        mainJPanel = new JPanel(null);
        initMouseListener();
        initGamesPanel();
        initBtnLabel();
        initFireLabel();
        mainJPanel.setBorder(mainBorder);
        mainJPanel.setBackground(new Color(40, 48, 61));
        mainJPanel.setBounds(0,0,100,830);
        loadGameFrame.add(mainJPanel);
        mainJPanel.setVisible(true);
    }
    public void startLoadGameGui(){
        clickLoad=false;
        loadGameFrame =new JFrame("MinesWeeper");
        loadGameFrame.setUndecorated(true);
        loadGameFrame.setVisible(false);
        initImage();
       initMainJPanel();
//        loadGameFrame.setIconImage(bombIcon.getImage());
//        loadGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        loadGameFrame.setUndecorated(true);
        loadGameFrame.setSize(1000, 830);
        loadGameFrame.setLocationRelativeTo(null);
//        loadGameFrame.setLayout(null);
//        loadGameFrame.setResizable(false);

    }

    public  void initImage(){

    }
    public void run(){

       startLoadGameGui();

    }
}
