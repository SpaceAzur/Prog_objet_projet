import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MaterielComboControl implements ItemListener {

    Side side;

    public MaterielComboControl(Side side) {
        this.side = side;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object item = event.getItem();
            if (item instanceof String) {
                String s = (String) item;
                if (s.equals("terminaux")) {
                    side.showTerminalOptions(null);
                }
            }
        }
    }

}