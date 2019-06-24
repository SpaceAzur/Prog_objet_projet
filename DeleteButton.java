package a38;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DeleteButton extends JPanel {

    private A38Object item;

    public DeleteButton(Side side, A38Object o) {

        this.setSize(40,40);
        this.setLocation(350,10);
        this.setBackground(side.interf.blue);

        this.setLayout(null);
        ImageIcon image=null;
        try { 
            image = new ImageIcon(ImageIO.read(getClass().getResource("delete-icon.png")));
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