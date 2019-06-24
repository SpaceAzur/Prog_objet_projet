package a38;

import javax.swing.JComboBox;

public class SideComboBox extends JComboBox<String> {

    private String key;

    public SideComboBox(String key, String[] vals) {
        super(vals);
        this.key=key;
    }

    public String getKey() {
        return this.key;
    }

}