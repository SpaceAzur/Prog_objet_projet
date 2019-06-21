import java.awt.Color;

import javax.swing.*;

public class AddButton extends JPanel {

    Center center;

    public AddButton(Center center) {

        this.center=center;
        this.setSize(40,40);
        this.setLocation(550,10);
        this.setBackground(Color.GREEN);
        
    }

    public String getType() {
        return center.getCurrentType();
    }

}