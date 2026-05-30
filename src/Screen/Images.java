package Screen;

import javax.swing.*;

public class Images {
    public  static ImageIcon bombIcon;
    public static ImageIcon fireImage;
    public static ImageIcon loadGameLabelImage;
    public static ImageIcon exitIcon;
    public static ImageIcon exitIcon1;
    public static ImageIcon backIcon;
    public static ImageIcon backIcon1;

    //settings images
    public static ImageIcon checkIcon;
    public static ImageIcon crossIcon;
    public static ImageIcon explodeIcon64;
    public static ImageIcon skullIcon64;
    public static ImageIcon normalIcon64;
    public static ImageIcon grayMineIcon64;

    //game images

    public static ImageIcon flagIcon;

    public static ImageIcon loadIcon;
    public static ImageIcon saveIcon;
    public static ImageIcon newGameIcon;
    public static ImageIcon clockIcon;

    public static ImageIcon homeIcon;
    public static ImageIcon themeIcon;
    public static ImageIcon aboutIcon;
    public static ImageIcon howToPlayIcon;
    public static ImageIcon door;
    public static ImageIcon  settingsIcon;
    public static void initImages(){
        try {
            backIcon1 = new ImageIcon(game.class.getClassLoader().getResource("Images/back1.png"));
        } catch (Exception e) {}
        try {
            backIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/back.png"));
        } catch (Exception e) {}
        try{
            fireImage = new ImageIcon(LoadGameGui.class.getClassLoader().getResource("Images/fire_explode_700.png"));
        }catch (Exception e){}
        try{
            loadGameLabelImage = new ImageIcon(LoadGameGui.class.getClassLoader().getResource("Images/loadGameLabel.png"));
        }catch (Exception e){}
        try {
            bombIcon = new ImageIcon(LoadGameGui.class.getClassLoader().getResource("Images/bomb.png"));
        } catch (Exception e) {}
        try{
            exitIcon = new ImageIcon(LoadGameGui.class.getClassLoader().getResource("Images/exit.png"));
        }catch (Exception e){
        }
        try{
            exitIcon1 = new ImageIcon(LoadGameGui.class.getClassLoader().getResource("Images/exit1.png"));
        }catch (Exception e){
        }
//Settings image
        try {
            grayMineIcon64 = new ImageIcon(game.class.getClassLoader().getResource("Images/mine64.png"));
        } catch (Exception e) {}
        try {
            normalIcon64 = new ImageIcon(game.class.getClassLoader().getResource("Images/bomb64.png"));
        } catch (Exception e) {}
        try {
            skullIcon64 = new ImageIcon(game.class.getClassLoader().getResource("Images/skull64.png"));
        } catch (Exception e) {}
        try {
            explodeIcon64 = new ImageIcon(game.class.getClassLoader().getResource("Images/explodeIcon64.png"));
        } catch (Exception e) {}
        try {
            checkIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/check.png"));
        } catch (Exception e) {}
        try {
            crossIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/cross.png"));
        } catch (Exception e) {}

        //game images
        try {
            flagIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/flag.png"));
        } catch (Exception e) {
        }

        try {
            loadIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/folder.png"));
        } catch (Exception e) {
        }
        try {
            saveIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/floppy-disk.png"));
        } catch (Exception e) {
        }
        try {
            newGameIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/bomb.png"));
        } catch (Exception e) {
        }
        try {
            clockIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/clock.png"));
        } catch (Exception e) {
        }
        try {
            door = new ImageIcon(game.class.getClassLoader().getResource("Images/door.png"));
        } catch (Exception e) {
        }
        try {
            homeIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/house.png"));
        } catch (Exception e) {
        }
        try {
            themeIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/theme.png"));
        } catch (Exception e) {
        }
        try {
            aboutIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/about.png"));
        } catch (Exception e) {
        }
        try {
            howToPlayIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/howToPlay.png"));
        } catch (Exception e) {
        }
        try {
            settingsIcon = new ImageIcon(game.class.getClassLoader().getResource("Images/setting.png"));
        } catch (Exception e) {
        }

    }
}
