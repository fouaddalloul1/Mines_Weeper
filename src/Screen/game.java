package Screen;
import FileHandling.AutoSave;
import Game_logic.*;
import Sound.GameSounds;
import Sound.GameSounds1;
import Sound.SoundEffect;
import Timer.*;
import FileHandling.LoadGame;
import FileHandling.SaveGame;
import buttons.ButtonGradient;
import buttons.ColorToggleButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;

public class game implements java.io.Serializable {
    static JFrame mainFrame;
    static JPanel MainJPanel;
    static GridLayout GridGame;
    public static JPanel GridJPanel;
    public static JPanel TopJPanel;
    public static JLabel bombNum;
    public static ColorToggleButton[][] btn;
    public static int bombOpenNum;
    //////////////////////////////////////////Images



    public static Border cellBorder(Color color) {
        return BorderFactory.createLineBorder(color, 3, true);
    }

    public static void timeSus() {
        try {
            gameTime.mySuspend();
        } catch (Exception e) {
        }
        try {
            gameTimer1.mySuspend();
        } catch (Exception ex) {
        }
    }

    public static void timeRes() {
        try {
            gameTime.myResume();
        } catch (Exception e) {
        }
        try {
            gameTimer1.myResume();
        } catch (Exception ex) {
        }
    }
    public static ImageIcon cellBombIcon;

    public static void initCellBombImage(){
        if (DIfficulty.currentDif==DIfficulty.Dif.expert) try {
            if(Settings.currentBombTheme== Settings.BombTheme.explode) {cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/explodeIcon16.png"));}
            else if(Settings.currentBombTheme== Settings.BombTheme.normal)
            cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/bomb16.png"));
            else if(Settings.currentBombTheme== Settings.BombTheme.skull)
                cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/skull16.png"));
            else if(Settings.currentBombTheme==Settings.BombTheme.grayMine)
                cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/mine16.png"));

        } catch (Exception e) {
        }
        else
            try {if(Settings.currentBombTheme== Settings.BombTheme.explode)
                cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/explodeIcon24.png"));
            else if(Settings.currentBombTheme== Settings.BombTheme.normal)
                cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/bomb24.png"));
            else if(Settings.currentBombTheme==Settings.BombTheme.skull)
                cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/skull24.png"));
            else if(Settings.currentBombTheme==Settings.BombTheme.grayMine)
                cellBombIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/mine24.png"));

            } catch (Exception e) {
            }
    }


    //////////////////////////////////////////////// Border
    static Border mainBorder = BorderFactory.createLineBorder(new Color(55, 20, 104), 3, true);
    public static boolean firstClick;//for first btn
    public static int startx;
    public static int starty;
    public static boolean swapPlayer;
    public static MouseListener mouseListener;

    static public void setBtnNumColor(ColorToggleButton btn, Cell cell) {

        int number = cell.getValue() - '0';
        if (number == 1)
//            ColorToggleButton.disabledTextColor = new Color(213, 124, 8);
            btn.setDisabledTextColor(new Color(204, 98, 98));
        else if (number == 2)
            btn.setDisabledTextColor(Color.blue);
        else if (number == 3)
            btn.setDisabledTextColor(new Color(167, 129, 241));
        else if (number == 4)
            btn.setDisabledTextColor(new Color(241, 9, 195, 255));
        else if (number == 5)
            btn.setDisabledTextColor(Color.magenta);
        else if (number == 6)
            btn.setDisabledTextColor(new Color(241, 9, 195));
        else if (number == 7)
            btn.setDisabledTextColor(Color.orange);
        else if (number == 8)
            btn.setDisabledTextColor(new Color(159, 88, 143));
    }

    public static boolean timeNewGame = false;
    public static Border themeBtnBorder;
    public static GameTimer gameTimer1;
    public static Thread gameTimer;

    static public void initGameTimer() {
        if (!Home.multiPlayer) {
            try {
                GameTimer.timeLabel.setVisible(false);
            } catch (Exception e) {
            }
            return;
        }
        gameTimer1 = new GameTimer();
        gameTimer = new Thread(gameTimer1);
        GameTimer.timeLabel.setFont(new Font("arial", Font.BOLD, 26));
        GameTimer.timeLabel.setBounds(484, 66, 200, 40);
        GameTimer.setMaxTime(45);
        GameTimer.timeLabel.setForeground(new Color(0xFFFFFF));
        GameTimer.resetTime();
        GameTimer.timeLabel.setVisible(true);
//        MainJPanel.add(GameTimer.timeLabel);
        gameTimer.start();
    }

    public static Thread timer;
    public static Time gameTime;

    static public void initTime() {
//        if(!firstTime)
        gameTime = new Time();
        timer = new Thread(gameTime);//just we will do this first turn on of program on main and when we will stop time like when game is over
        Time.timeLabel.setFont(new Font("arial", Font.BOLD, 20));
        Time.timeLabel.setBounds(850, 10, 200, 40);
        Time.timeLabel.setIcon(Images.clockIcon);
        Time.timeLabel.setForeground(new Color(0xC3C3D2));

        if (timeNewGame) {
            System.out.println("tiem new game done");
            //for new game// because when we load game we don't need to reset Time
            Time.resetTime();//for reset game when i call this function again like startNewGame
            timeNewGame = false;
        }
        Time.timeLabel.setVisible(true);
        TopJPanel.add(Time.timeLabel);//now for timer
        timer.start();


    }

    public static void updateGrid() {
//        System.out.println("update grid");
        for (int i = 0; i < Grid.numOfRows; i++) {
            for (int j = 0; j < Grid.numOfCol; j++) {
                if (Grid.cells[i][j].isFlag()) {
                    game.btn[i][j].setIcon(Images.flagIcon);
                    game.btn[i][j].setBorder(cellBorder(Grid.cells[i][j].getCellColor()));
                } else if (Grid.cells[i][j].isVisible()) {
                    btn[i][j].setOpen(true);
                    if (Grid.cells[i][j].getValue() == 'B') {
                        game.btn[i][j].setIcon(cellBombIcon);
                        game.btn[i][j].setFocusPainted(false);
                        game.btn[i][j].setContentAreaFilled(false);
                        game.btn[i][j].setBorder(cellBorder(Grid.cells[i][j].getCellColor()));
                    } else {
                        game.btn[i][j].setText(Grid.cells[i][j].getValue() + "");
                        setBtnNumColor(game.btn[i][j], Grid.cells[i][j]);
                        game.btn[i][j].setIcon(null);
                        game.btn[i][j].setFocusPainted(false);
                        game.btn[i][j].setEnabled(false);
                        game.btn[i][j].setBorder(cellBorder(Grid.cells[i][j].getCellColor()));

                    }
                }
            }
        }

    }

    //input Message for load and save
    static public String inputMessage(String title, String message) {
        String InputText = "";
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message);
        JTextField inputField = new JTextField();
        inputPanel.add(label, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.SOUTH);
        int result = JOptionPane.showConfirmDialog(null, inputPanel, title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // user clicked OK, get the value of the input field
            InputText = inputField.getText();
            // do something with the input
        }
        return InputText;
    }

    static public void showInfoMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    static public void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void returnHome() {

        try {
            timer.stop();
            if (Home.multiPlayer) {
                GameTimer.stop = true;
                gameTimer.stop();
                gameTimer1.stop();
            }
        } catch (Exception exception) {
//            System.out.println(exception);
        }
        Home home = new Home();
        home.start();
        try {
            mainFrame.setVisible(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        Time.timeLabel.setVisible(false);
    }

    public static JMenuBar menuBar;
    static public void initMenuBar() {
         menuBar = new JMenuBar();
         menuBar.setLayout(null);
        JMenu file = new JMenu("File");
        JMenuItem home = new JMenuItem(new AbstractAction("Home") {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoSave();
                returnHome();
            }
        });
        JMenuItem save = new JMenuItem(new AbstractAction("Save game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeSus();
                SaveScreen saveScreen = new SaveScreen();


/*                try {
                    String saveString = inputMessage("Game Save", "Enter Save File Name :");
                    if (saveString != "") {
                        SaveGame.SaveThisGame(saveString);
                        if(LoadGameGui.numberOfGames<10)
                        showInfoMessage("Game Save", "Data Has Been Saved Successfully");
                    }

                } catch (Exception exception) {
                    showErrorMessage("Game Save", exception.getMessage());
                }*/
                timeRes();
            }
        });
        JMenuItem load = new JMenuItem(new AbstractAction("Load game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeSus();
                LoadGameGui.updateLoadGui();
                LoadGameGui.loadGameFrame.setVisible(true);
                LoadGameGui.loadGameBackFrame = LoadGameGui.backFrame.gameUi;
                mainFrame.setVisible(false);
            }
        });
        JMenuItem newGame = new JMenuItem(new AbstractAction("Start New Game") {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.setVisible(false);
                Time.timeLabel.setVisible(false);
                timeNewGame = true;
                try {
                    timer.stop();
                    gameTimer.stop();
                    gameTimer1.stop();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                Rules.startNewGame();
            }
        });
        JMenuItem themeMenuItem = new JMenuItem(new AbstractAction("Theme") {
            @Override
            public void actionPerformed(ActionEvent e) {
                theme();
            }
        });
        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoSave();
                System.exit(0);
            }
        });
        JMenu help = new JMenu("Help");
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

        //now
        JMenuItem settings = new JMenuItem(new AbstractAction("Settings") {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeSus();
                Settings.settingsFrame.setVisible(true);
                    Settings.settingsBackFrame = Settings.backFrame.gameUi;
                    mainFrame.setVisible(false);

            }
        });
        settings.setIcon(Images.settingsIcon);
        help.add(howToPlay);
        help.add(about);
        howToPlay.setIcon(Images.howToPlayIcon);
        about.setIcon(Images.aboutIcon);

        file.add(home);
        file.add(themeMenuItem);
        file.add(settings);
        if (!Home.loadLastGame) file.add(newGame);
        file.add(save);
        file.add(load);
        file.add(exit);
        menuBar.add(file);
        menuBar.add(help);

        //menu bar icons
//        menuBar.setBackground(new Color(162, 131, 131));
        home.setIcon(Images.homeIcon);
        save.setIcon(Images.saveIcon);
        load.setIcon(Images.loadIcon);
        newGame.setIcon(Images.newGameIcon);
        themeMenuItem.setIcon(Images.themeIcon);
        exit.setIcon(Images.door);
        mainFrame.add(menuBar);
       try{ mainFrame.setJMenuBar(menuBar);}catch (Exception e){e.getStackTrace();}
    }

    public static void openBombClicked() {
        for (int k = 0; k < Grid.numOfRows; k++) {
            for (int l = 0; l < Grid.numOfCol; l++) {
                if (Grid.cells[k][l].isVisible()) {
                    if (Grid.cells[k][l].getValue() == 'B') {

                        btn[k][l].setIcon(cellBombIcon);
//                                                        btn[k][l].setBorder(btnBorderMines);
                        btn[k][l].setContentAreaFilled(false);
                        if (btn[k][l].isOpen && !Grid.cells[k][l].isBomb()) {//is open for multi player if i delete it on single bombs will get border
                            btn[k][l].setBorder(cellBorder(Rules.currentPlayer.getColor()));
                            Grid.cells[k][l].setCellColor(Rules.currentPlayer.getColor());
                            Grid.cells[k][l].setBomb(true);
                        }
                    }
                }
            }
        }
    }

    public static void openButtonsClicked() {
        for (int k = 0; k < Grid.numOfRows; k++) {
            for (int l = 0; l < Grid.numOfCol; l++) {
                if (Grid.cells[k][l].isVisible()) {
                    if (btn[k][l].isEnabled() && Grid.cells[k][l].getValue() != 'B') {
                        btn[k][l].setIcon(null);
                        String value = Grid.cells[k][l].getValue() + "";
                        setBtnNumColor(btn[k][l], Grid.cells[k][l]);
                        btn[k][l].setText(value);
//                         btn[k][l].setBackground(Color.red);
                        btn[k][l].setEnabled(false);
                        if (Home.multiPlayer) {
                            btn[k][l].setBorder(cellBorder(Rules.currentPlayer.getColor()));
                            Grid.cells[k][l].setCellColor(Rules.currentPlayer.getColor());//for update take opens cells there color
                        } else
                            btn[k][l].setBorder(themeBtnBorder);
                    }
                }
            }
        }
    }

    public static boolean one=false;
    public static void bombSoundPlay(ColorToggleButton btn){
        if (Home.multiPlayer&&one) {
            GameSounds1.gameEffect1.stop();
            one=false;
            GameSounds.gameEffect.setFile("Sound/sounds/BombEffect.wav");
            GameSounds.gameEffect.control((float)Settings.gameVolumeSlider.getValue());
            if(!Settings.gameVolumeCheck.isSelected())
            {
                GameSounds.gameEffect.play();
            }
           btn.setOpen(true);
        }
        if (Home.multiPlayer&&!one) {
            GameSounds.gameEffect.stop();
            one=true;
            GameSounds1.gameEffect1.setFile("Sound/sounds/BombEffect.wav");
            GameSounds1.gameEffect1.control((float)Settings.gameVolumeSlider.getValue());
            if(!Settings.gameVolumeCheck.isSelected())
            {
                GameSounds1.gameEffect1.play();
            }
           btn.setOpen(true);
        }
    }

    public static void loseEffect(){
       GameSounds.loseEffect.setFile("Sound/sounds/gameOverEffect.wav");
            GameSounds.loseEffect.control((float)Settings.gameVolumeSlider.getValue());
            GameSounds.musicGame.pause();
            if(!Settings.gameVolumeCheck.isSelected())
                GameSounds.loseEffect.play();
    }
    public static void initMouseListener() {

        mouseListener = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < Grid.numOfRows; i++) {
                    for (int j = 0; j < Grid.numOfCol; j++) {
                        if (e.getSource() == btn[i][j]) {
                            Rules.countScore(Grid.cells[i][j], e);
                            if (SwingUtilities.isRightMouseButton(e) && !Grid.cells[i][j].isVisible()) {
                                if (!Grid.cells[i][j].isFlag()) {
                                    btn[i][j].setIcon(Images.flagIcon);
                                    Grid.cells[i][j].setFlag(true);
                                    if (!Home.multiPlayer) {
                                        btn[i][j].setBorder(themeBtnBorder);
                                    } else {
                                        btn[i][j].setBorder(cellBorder(Rules.currentPlayer.getColor()));
                                        Grid.cells[i][j].setCellColor(Rules.currentPlayer.getColor());//for update take open cells there color}
                                        swapPlayer = true;
                                    }
                                } else if (Grid.cells[i][j].isFlag()) {
                                    btn[i][j].setIcon(null);
                                    Grid.cells[i][j].setFlag(false);
                                    btn[i][j].setBorder(null);
                                    swapPlayer = true;
                                }
                            } else if (SwingUtilities.isLeftMouseButton(e) && !Grid.cells[i][j].isVisible() && !Grid.cells[i][j].isFlag()) {

                                //for first choice
                                if (!firstClick) {
                                    initTime();
                                    game.startx = i;
                                    game.starty = j;
                                    Grid.bombInit();
                                    firstClick = true;
                                }
                                if (Grid.cells[i][j].getValue() == 'B') {//if it;s bomb
                                    if (!Home.multiPlayer) {
                                        Grid.visibleAll();
                                        openBombClicked();
                                        loseEffect();
                                        Rules.gameOver();
                                    } else {
                                        swapPlayer = true;
                                        bombOpenNum++;
                                        if (  !btn[i][j].isOpen()) {//e.getSource() == btn[i][j] &&Grid.cells[i][j].getValue() == 'B' &&&&!Grid.cells[i][j].isFlag()

                                            bombSoundPlay(btn[i][j]);


                                        }
                                        Grid.cells[i][j].setVisible(true);
//                                            btn[i][j].setOpen(true);
                                        openBombClicked();
                                    }
                                } else//if it's no bomb
                                {
                                    Grid.floodFill(i, j);
                                    Rules.floodFillScore(Grid.cells[i][j]);
                                    openButtonsClicked();
                                    swapPlayer = true;
                                }
                                if (Rules.isWon()) {
//                                    Rules.addScore(Grid.unCheckedCells() * 25 * DIfficulty.currentDif.dif);
                                    Rules.gameOver();

                                }//won condation


                            }
                            if (!Rules.gameOver)
//                                autoSave();
                                if (swapPlayer) {
                                    GameTimer.timeLabel.setText(Integer.toString(GameTimer.getMaxTime()));
                                    GameTimer.resetTime();
                                    swapPlayer = false;
                                    Rules.swapPlayer();
                                }
                        }


                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

    }

    public static void initGridJPanel() {
        initMouseListener();
        GridGame = new GridLayout(Grid.numOfRows, Grid.numOfCol);
        GridGame.setVgap(2);
        GridGame.setHgap(2);
        GridJPanel = new JPanel(GridGame);
        GridJPanel.setBackground(new Color(116, 200, 244));
        GridJPanel.setBounds(4, 95, 980, 670);
        GridJPanel.setBorder(mainBorder);

        MainJPanel.add(GridJPanel);
        // لجعل محتوى االنافذة يظهر بشكل أجمل Nimbus إلى الـ UIManager هنا قمنا بتغير الـ

        btn = new ColorToggleButton[Grid.numOfRows][Grid.numOfCol];
        for (int i = 0; i < Grid.numOfRows; i++) {
            for (int j = 0; j < Grid.numOfCol; j++) {
                btn[i][j] = new ColorToggleButton();
//                btn[i][j].addMouseListener(soundMouseListener);
                btn[i][j].addMouseListener(mouseListener);
                if (Grid.numOfRows < 11)
                    btn[i][j].setFont(new Font(null, Font.BOLD, 25));
                else if (Grid.numOfRows < 20)
                    btn[i][j].setFont(new Font(null, Font.BOLD, 20));
                else
                    btn[i][j].setFont(new Font(null, Font.BOLD, 15));
                GridJPanel.add(btn[i][j]);
                btn[i][j].setFocusPainted(false);
            }
        }
    }

    public static ButtonGradient currentPlayerBtn;

    public static void initTopJPanel() {
        TopJPanel = new JPanel(null);
        TopJPanel.setBounds(24, 20, 940, 60);
        TopJPanel.setBorder(mainBorder);
        TopJPanel.setBackground(new Color(16, 97, 138));
        bombNum = new JLabel(Integer.toString(Grid.noOfBombs));
        bombNum.setIcon(Images.bombIcon);
        bombNum.setFont(new Font("Serif", Font.BOLD, 30));
        bombNum.setBounds(10, 10, 200, 40);
        bombNum.setForeground(new Color(0xC3C3D2));
        TopJPanel.add(bombNum);

        //current player

    }

    public static void autoSave() {
        try {
//            with auto save for thread and i should delete my theme to run without problems
//            AutoSave quickSave = new AutoSave();
//            quickSave.start();
            SaveGame.SaveThisGame("autoSave");
        } catch (Exception ex) {
            game.showErrorMessage("Game Save", ex.getMessage());
        }
    }

    public static void closeAutoSave() {
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    SaveGame.SaveThisGame("autoSave");
                } catch (Exception ex) {
                    game.showErrorMessage("Game Save", ex.getMessage());
                }
            }
        });
    }

    public static boolean resume;

    public static void pauseMode() {
        JDialog shadowDialog = new JDialog();
        shadowDialog.setUndecorated(true);
        shadowDialog.setTitle("PAUSE MODE");
        shadowDialog.setBackground(new Color(10,20,30,70));
        shadowDialog.setBackground(new Color(0x81594242, true));
        shadowDialog.setSize(new Dimension(1000,830));
        shadowDialog.setSize(new Dimension(975, 665));
        shadowDialog.setLocation(282,152);
//        initResumeBtnDialog
        JDialog resumeBtnDialog = new JDialog();
        resumeBtnDialog.setUndecorated(true);
        resumeBtnDialog.setTitle("PAUSE MODE");
        resumeBtnDialog.setModal(true);
        resumeBtnDialog.setBackground(Color.red);
        resumeBtnDialog.setLocationRelativeTo(null);
        resumeBtnDialog.setSize(new Dimension(500,500));
        resumeBtnDialog.setLocation(520,250);
        //initResumeBtn
        ButtonGradient resumeBtn = new ButtonGradient();
        resumeBtn.setText("RESUME");
        resumeBtn.setFont(new Font("Serif", Font.BOLD, 100));
        resumeBtn.setSize(500, 500);
        resumeBtn.setEnteredColor1(new Color(0,0,0,0));
        resumeBtn.setEnteredColor1(new Color(0,0,0,0));
        resumeBtn.setForeground(Color.white);
        //resume panel
        JPanel resumePanel = new JPanel(null);
        resumePanel.setBackground(new Color(0,0,0,0));
        resumeBtnDialog.setBackground(new Color(0,0,0,0));
        resumePanel.setBounds(resumeBtnDialog.getBounds());
        game.currentPlayerBtn.setPressedColor(Rules.currentPlayer.getColor());
        resumePanel.add(resumeBtn);
        resumeBtnDialog.add(resumePanel);

        resumeBtnDialog.setUndecorated(true);
        MouseListener playerBtnActionListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getSource() == currentPlayerBtn) {

                    resumeBtn.setColor1(Rules.currentPlayer.getColor());
                    resumeBtn.setColor2(Rules.currentPlayer.getColor());
                    resume = false;
                    timeSus();
                    shadowDialog.setBackground(new Color(Rules.currentPlayer.getColor().getRed(),Rules.currentPlayer.getColor().getGreen(),Rules.currentPlayer.getColor().getBlue(),100));
                    shadowDialog.setVisible(true);
                    resumeBtnDialog.setVisible(true);
                    try {
//                        if (!resume) timeRes();
                    } catch (Exception exception) {
                    }

                } else if (e.getSource() == resumeBtn) {
                    resumeBtnDialog.setVisible(false);
                    shadowDialog.setVisible(false);
//                    System.out.println("resumebtn");
                    resume = true;
                    timeRes();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (e.getSource() == resumeBtn && resume) {currentPlayerBtn.setText(Rules.currentPlayer.getName());resume=false;}
                else if(e.getSource()==resumeBtn){
//                    currentPlayerBtn.setText("RESUME");
//                    currentPlayerBtn.setFont(new Font("Serif",Font.BOLD,37));
                }
                else if (e.getSource() == currentPlayerBtn) currentPlayerBtn.setText("PAUSE");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == currentPlayerBtn) currentPlayerBtn.setText(Rules.currentPlayer.getName());
                else currentPlayerBtn.setText("PAUSE");
                currentPlayerBtn.setFont(new Font("Serif", Font.BOLD, 40));

            }
        };
        resumeBtn.addMouseListener(playerBtnActionListener);
        currentPlayerBtn.addMouseListener(playerBtnActionListener);
    }

    public static void initMainJPanel() {
        MainJPanel = new JPanel(null);
        MainJPanel.setBackground(new Color(76, 173, 205));
        initGridJPanel();
        initTopJPanel();
//        MainJPanel.setPreferredSize(new Dimension(1000,830));
        MainJPanel.setBorder(mainBorder);
        MainJPanel.setBounds(0, 0, 1000, 830);
        MainJPanel.add(MainJPanel.add(GameTimer.timeLabel));
        currentPlayerBtn = new ButtonGradient();
        currentPlayerBtn.setText(Rules.currentPlayer.getName());
        currentPlayerBtn.setColor2(Rules.currentPlayer.getColor());
        currentPlayerBtn.setColor1(Rules.currentPlayer.getColor());
        currentPlayerBtn.setBounds(395, 1, 200, 100);
        currentPlayerBtn.setFont(new Font("Serif", Font.BOLD, 40));
        currentPlayerBtn.setForeground(Color.white);
        currentPlayerBtn.setVerticalTextPosition(SwingConstants.TOP);

        pauseMode();
        MainJPanel.add(currentPlayerBtn);
        MainJPanel.add(TopJPanel);
        if (!Home.multiPlayer) {
            dayTheme();
        }
    }

    static public void createAndShowGUI() {//noww
        try {
            mainFrame = new JFrame("MinesWeeper");
        } catch (Exception e) {
        }
        try {
            initSound();
        } catch (Exception e) {
            e.getStackTrace();
        }
        initGameTimer();
        initCellBombImage();
        try {
            initMainJPanel();
        } catch (Exception e) {
            e.getStackTrace();
        }
        initMenuBar();
        ///////////////////////////////////// create MenuBar
        closeAutoSave();
//        mainFrame.pack();
        mainFrame.add(MainJPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("MinesWeeper");
        mainFrame.setSize(1000, 830);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        try {
            DIfficulty.difficultyFrame.setVisible(false);
        } catch (Exception e) {
        }
        try {
            Home.homeFrame.setVisible(false);
        } catch (Exception e) {
        }
        try {
            LoadGameGui.loadGameFrame.setVisible(false);
            LoadGameGui.clickLoad=false;
            timeRes();
        } catch (Exception e) {
        }
        mainFrame.setIconImage(Images.bombIcon.getImage());
        mainFrame.setVisible(true);

    }

    public game() {
        createAndShowGUI();
    }

    public static void theme() {
        if (Home.multiPlayer) {
            showErrorMessage("Sorry", " this feature is not available in multiplayer mode yet");
            return;
        }
        JButton dayThemeBtn = new JButton("DAY THEME");
        JButton darkulaThemeBtn = new JButton("DARKULA THEME");
        ActionListener themeActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == dayThemeBtn)
                    dayTheme();
                else if (e.getSource() == darkulaThemeBtn) darkulaTheme();
            }
        };
        JPanel themeLabel = new JPanel(new GridLayout(2, 1));
        dayThemeBtn.addActionListener(themeActionListener);
        darkulaThemeBtn.addActionListener(themeActionListener);
        themeLabel.add(dayThemeBtn);
        themeLabel.add(darkulaThemeBtn);
        JOptionPane.showConfirmDialog(null, themeLabel, "Chose Theme :", JOptionPane.OK_CANCEL_OPTION);
    }

    public static void dayTheme() {
        themeBtnBorder = BorderFactory.createLineBorder(new Color(255, 0, 0), 3, false);
        for (int i = 0; i < Grid.numOfRows; i++) {
            for (int j = 0; j < Grid.numOfCol; j++) {
                if (btn[i][j].isOpen && (Grid.cells[i][j].isVisible()) || Grid.cells[i][j].isFlag())
                    btn[i][j].setBorder(themeBtnBorder);
            }
        }
        TopJPanel.setBackground(new Color(16, 97, 138));
        MainJPanel.setBackground(new Color(76, 173, 205));
        GridJPanel.setBackground(new Color(116, 200, 244));

    }

    public static boolean darkulaTheme;

    public static void darkulaTheme() {
        themeBtnBorder = BorderFactory.createLineBorder(new Color(105, 143, 229), 3, false);
        darkulaTheme = true;
//        updateGrid();
        for (int i = 0; i < Grid.numOfRows; i++) {
            for (int j = 0; j < Grid.numOfCol; j++) {
                if ((btn[i][j].isOpen && Grid.cells[i][j].isVisible()) || Grid.cells[i][j].isFlag())
                    btn[i][j].setBorder(themeBtnBorder);
            }
        }
        Time.timeLabel.setBackground(new Color(229, 10, 51, 176));
        TopJPanel.setBackground(new Color(224, 19, 58, 255));
        MainJPanel.setBackground(Color.gray);
        GridJPanel.setBackground(new Color(255, 0, 0));
    }


    public static void initSound() {
        initSoundMouseListener();
    }
    public static MouseListener soundMouseListener;

    public static void initSoundMouseListener() {

        soundMouseListener= new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e))
                    for (int i = 0; i < Grid.numOfRows; i++) {
                        for (int j = 0; j < Grid.numOfCol; j++) {

                        }

                    }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }


        };
    }
}
