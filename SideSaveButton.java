import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SideSaveButton extends JPanel {

    A38Object obj;
    ArrayList<SideTextField> fields;
    String type;

    public SideSaveButton(Side side, A38Object obj, ArrayList<SideTextField> fields) {
        
        this.setSize(125,65);
        this.setLocation(50, 580);
        this.setBackground(Color.GREEN);
        this.setLayout(null);
        this.obj=obj;
        this.fields=fields;
        this.type=side.interf.center.getCurrentType();

        JLabel label = new JLabel("Valider");
        label.setFont(side.interf.sideFont);
        label.setForeground(Color.WHITE);
        label.setSize(label.getPreferredSize());
        label.setLocation(this.getWidth() / 2 - label.getWidth() / 2,
        this.getHeight() / 2 - label.getHeight() / 2);

        this.add(label);
        this.addMouseListener(side.sctrl);

        side.add(this);

    }

    public A38Object getObj() {
        return obj;
    }

    public Map<String, String> getValues() {

        Map<String, String> values = new HashMap<>();

        for (int i=0 ; i<fields.size() ; i++) {
            values.put(fields.get(i).getKey(), fields.get(i).getVal());
        }

        return values;

    }

    public String getType() {
        return this.type;
    }

}