package Screen;

import FileHandling.LoadGame;
import FileHandling.SaveGame;
import buttons.ButtonGradient;
import buttons.ColorToggleButton;
import buttons.MyTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Screen.game.timer;

public class SaveScreen {
    Font mainFont =new Font("Harrington",Font.BOLD,25 );
    public SaveScreen(){
        startScreen();
    }
    JLabel saveGameLabel;
    JLabel fireExplodeLabel;
    public void initFireExplodeLabel(){
        //initSaveGameLabel
        saveGameLabel = new JLabel(saveGameLabelImage);
        saveGameLabel.setBounds(25,15,359,150);
        mainJPanel.add(saveGameLabel);
        //initFireExplodeLabel
        fireExplodeLabel = new JLabel(fireExplode_350_150);
        fireExplodeLabel.setBounds(25,15,350,150);
        mainJPanel.add(fireExplodeLabel);

    }
    ImageIcon fireExplode_350_150;
    ImageIcon saveGameLabelImage;
    public MyTextField saveTextField;
    public void initImages(){
        try {
            fireExplode_350_150 = new ImageIcon(SaveScreen.class.getClassLoader().getResource("Images/fire_explode_350_150.png"));
        }catch (Exception e){}
        try {
            saveGameLabelImage = new ImageIcon(SaveScreen.class.getClassLoader().getResource("Images/saveLabel.png"));
        }catch (Exception e){}
    }

    JLabel fileNameLabel;
    public void initTextField(){
        //initFileNameLabel
        fileNameLabel = new JLabel("Game Name :");
        fileNameLabel.setFont(mainFont);
        fileNameLabel.setForeground(Color.decode("#cbb3b3"));
        fileNameLabel.setBounds(50,170,300,32);
        mainJPanel.add(fileNameLabel);
        //initTextLabel
        saveTextField = new MyTextField();
        saveTextField.setBounds(50,215,300,75);
        saveTextField.setFont(mainFont);
        saveTextField.setText("fouad");
        saveTextField.setShadowColor(Color.red);
        mainJPanel.add(saveTextField);
    }
    public JPanel mainJPanel;
    public void initMainJPanel(){
        mainJPanel = new JPanel(null);
        mainJPanel.setBounds(saveFrame.getBounds());
        initMouseListener();
        initImages();
        initFireExplodeLabel();
        initTextField();
        initBtnLabel();
        initSuccessfullyDialog();
        mainJPanel.setBackground(new Color(40, 48, 61));
        mainJPanel.setBorder(Settings.mainBorder);
        saveFrame.add(mainJPanel);
    }
    public  JDialog saveFrame;
    JLabel exitLabel;
    ButtonGradient saveBtn;
    ButtonGradient cancelBtn;
    public static JLabel loadLabel;
    public static JLabel deleteLabel;

    public void  initBtnLabel(){
        //initExitLabel
        exitLabel = new JLabel(Images.exitIcon);
        exitLabel.setBounds(368,8,24,24);
        exitLabel.addMouseListener(mouseListener);
        mainJPanel.add(exitLabel);
        //initSaveBtn
        saveBtn =new ButtonGradient();
        saveBtn.setText("Save");
        saveBtn.setForeground(Color.decode("#cbb3b3"));
        saveBtn.setFont(mainFont);
        saveBtn.setColor1(new Color(101, 77, 77));
        saveBtn.setColor2(new Color(101, 77, 77));
        saveBtn.setBounds(250,300,100,50);
        saveBtn.setPressedColor( new Color(101, 77, 77));
        saveBtn.addMouseListener(mouseListener);
        mainJPanel.add(saveBtn);
        //initCancelBtn
        cancelBtn =new ButtonGradient();
        cancelBtn.setText("Cancel");
        cancelBtn.setForeground(Color.decode("#cbb3b3"));
        cancelBtn.setFont(mainFont);
        cancelBtn.setColor1(new Color(101, 77, 77));
        cancelBtn.setColor2(new Color(101, 77, 77));
        cancelBtn.setBounds(120,300,120,50);
        cancelBtn.setPressedColor(new Color(101, 77, 77));
        cancelBtn.addMouseListener(mouseListener);
        mainJPanel.add(cancelBtn);
    }
    MouseListener mouseListener ;
    ButtonGradient okBtn;
    JDialog successfullyDialog;
    public void initSuccessfullyDialog(){
//        successfullyDialog.setVisible(false);
        //initSuccessfullyDialog
        successfullyDialog = new JDialog();
        successfullyDialog.setUndecorated(true);
        successfullyDialog.setSize(310,130);
        successfullyDialog.setModal(true);
        successfullyDialog.setLocation(620,370);
        //initSuccessfullyPanel
        JPanel successfullyPanel =new JPanel(null);
        successfullyPanel.setBackground(new Color(40, 48, 61));
        successfullyPanel.setBorder(Settings.mainBorder);
        successfullyPanel.setBounds(successfullyDialog.getBounds());
        //initOkBtn
        okBtn= new ButtonGradient();
        okBtn.setText("OK");
        okBtn.setBounds(105,80,100,40);
        okBtn.setForeground(Color.decode("#cbb3b3"));
        okBtn.setFont(mainFont);
        okBtn.setColor1(new Color(101, 77, 77));
        okBtn.setColor2(new Color(101, 77, 77));
        okBtn.setPressedColor(new Color(101, 77, 77));
        okBtn.addMouseListener(mouseListener);
        successfullyPanel.add(okBtn);
        //initSaveDoneLabel
        JLabel saveDoneLabel = new JLabel("Game Saved Successfully");
        saveDoneLabel.setForeground(Color.decode("#cbb3b3"));
        saveDoneLabel.setBounds(10,20,300,40);
        saveDoneLabel.setFont(mainFont);
        successfullyPanel.add(saveDoneLabel);

        successfullyDialog.add(successfullyPanel);

    }


    public void initMouseListener(){
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                    if(e.getSource()==exitLabel)
                        saveFrame.setVisible(false);
                    else if(e.getSource()==saveBtn){
                        if(!saveTextField.getText().equals("")){
                         try{
                        SaveGame.SaveThisGame(saveTextField.getText());
                        saveFrame.setVisible(false);
                            if(LoadGameGui.numberOfGames<10)
                                successfullyDialog.setVisible(true);
                             }catch(Exception exception){
                             game.showErrorMessage("Game Save", exception.getMessage());
                         }
                        }
                    }
                    else if(e.getSource()==cancelBtn||e.getSource()==okBtn||e.getSource()==exitLabel){
                        System.out.println("ok btn mouse listener");
                        if(e.getSource()==okBtn)successfullyDialog.setVisible(false);
                        saveFrame.setVisible(false);
                    }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                 if(e.getSource()==exitLabel){
                    exitLabel.setIcon(Images.exitIcon1);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource()==exitLabel){
                    exitLabel.setIcon(Images.exitIcon);
                }
            }
        };
    }
    public void startScreen(){
        saveFrame = new JDialog();
        saveFrame.setUndecorated(true);
        initMainJPanel();
        saveFrame.setSize(new Dimension(400,370));
        saveFrame.setModal(true);
        saveFrame.setLocation(570,240);
        saveFrame.setVisible(true);
    }


}
