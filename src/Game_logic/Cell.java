package Game_logic;

import javax.swing.*;
import java.awt.*;

public class Cell  implements java.io.Serializable{

   private int x;
   private int y;
   private char value;
   private boolean isVisible;
  private   boolean bomb;
  //for return it when
  private Color cellColor;
   public  boolean isBomb(){return bomb;}
    public void setBomb(boolean bombb){
      this.bomb = bombb;
    }
//   static  Color disabledTextColor;
//
//    public static Color getDisabledTextColor() {
//        return disabledTextColor;
//    }
//
//    public static void setDisabledTextColor(Color disabledTextColor) {
//        Cell.disabledTextColor = disabledTextColor;
//    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    private boolean isFlag;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = ' ';
        this.isVisible=false;
        this.bomb=false;
    }

    public Color getCellColor() {
        return cellColor;
    }

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

//    @Override
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        if(!isEnabled()){
//            g.setColor(disabledTextColor);
//            g.drawString(getText(),getWidth()/2-g.getFontMetrics().stringWidth(getText())/2,getHeight()/2+g.getFontMetrics().getHeight()/2-3);
//        }
//    }
}
