package a38;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AddButton extends JPanel {

    Center center;
    public AddButton(Center center) {

        this.center=center;
        this.setSize(40,40);
        this.setLocation(540,18);
        this.setBackground(center.interf.blue);
        this.setLayout(null);
        ImageIcon image=null;
        try { 
            image = new ImageIcon(ImageIO.read(getClass().getResource("add-icon.png")));
        } catch (Exception e) {}
        JLabel img = new JLabel();
        img.setIcon(image);
        img.setSize(40,40);
        img.setLocation(0,0);
        this.add(img);
        
    }

    public String getType() {
        return center.getCurrentType();
    }

    public A38Object getFilter() {
        return center.getFilter();
    }

}