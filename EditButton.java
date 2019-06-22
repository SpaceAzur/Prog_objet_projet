import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class EditButton extends JPanel {

    private A38Object item;

    public EditButton(Side side, A38Object o) {

        this.setSize(40,40);
        this.setLocation(10,10);
        this.setBackground(side.interf.blue);
        this.setLayout(null);
        ImageIcon image=null;
        try { 
            image = new ImageIcon(ImageIO.read(getClass().getResource("edit-icon.png")));
        } catch (Exception e) {}
        JLabel img = new JLabel();
        img.setIcon(image);
        img.setSize(40,40);
        img.setLocation(0,0);
        this.add(img);

        this.item=o;
        this.addMouseListener(side.sctrl);
        
    }

    public A38Object getItem() {
        return this.item;
    }

}