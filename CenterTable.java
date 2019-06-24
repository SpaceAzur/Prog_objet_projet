package a38;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CenterTable extends JTable {

    String type;
    Interface interf;

    public CenterTable(Object[][] data, String[] cols, Interface interf, String type) {

        super(data, cols);
        this.type=type;
        this.interf=interf;

        class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
 
            public SimpleHeaderRenderer() {
                setFont(interf.headerFont);
                setForeground(Color.WHITE);
                setBackground(interf.blue);
                setOpaque(true);
                setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createMatteBorder(0, 1, 1, 2, interf.yellow));
            }
             
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                setText(value.toString());
    
                return this;
            }
         
        }
        setAutoCreateRowSorter(true);
        setRowHeight(35);
        setGridColor(interf.yellow);
        setFont(interf.tableFont);
        setBackground(interf.lightBlue);
        setSelectionBackground(interf.blue);
        setSelectionForeground(Color.WHITE);
        setFillsViewportHeight(true);
        getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        getTableHeader().setPreferredSize(new Dimension(100, 35));
        //getTableHeader().setReorderingAllowed(false);
        //getTableHeader().setResizingAllowed(false);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    @Override
    public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
        return false;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        JComponent jc = (JComponent) super.prepareRenderer(renderer, row, column);
        jc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(interf.yellow, 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        return jc;
    }

    public String getType() {
        return type;
    }

}