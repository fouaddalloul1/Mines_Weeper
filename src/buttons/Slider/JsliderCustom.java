package buttons.Slider;

import java.awt.Color;
import javax.swing.JSlider;

public class JsliderCustom extends JSlider {

    public JsliderCustom() {
        setOpaque(false);
        setBackground(new Color(255, 0, 0));
        setForeground(new Color(19, 19, 23));
        setUI(new JSliderUI(this));
    }
}