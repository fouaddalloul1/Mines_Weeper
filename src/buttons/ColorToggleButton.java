package buttons;

import javax.swing.*;
import java.awt.*;


public class ColorToggleButton extends JButton {
     Color disabledTextColor ;
     public static int dig=5;
     public boolean isOpen;

     public  boolean isOpen(){
         return isOpen;
     }

     public void setOpen(boolean isOpen){
         this.isOpen=isOpen;
     }
    public Color getDisabledTextColor() {
        return disabledTextColor;
    }


    public  void setDisabledTextColor(Color disabledTextColor) {
        this.disabledTextColor = disabledTextColor;
    }

    public ColorToggleButton(){
        theme();
    }

     public void theme(){
        synchronized (this){
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
            catch (UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException |
                   ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null,"there is problem with button Gui, Sorry");
            }}

    }



   synchronized public void paintComponent(Graphics g){
       try{ super.paintComponent(g);}catch (Exception e){}
        try {
            if (!isEnabled()) {
                g.setColor(disabledTextColor);
                g.drawString(getText(), getWidth() / 2 - g.getFontMetrics().stringWidth(getText()) / 2, getHeight() / 2 + g.getFontMetrics().getHeight() / 2 - dig);
            }
        }catch (Exception e){}
    }
}
