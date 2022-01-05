
package gui.components.renderers;

import entitys.Reader;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ComboxReadersRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            Reader reader = (Reader) value;
            label.setText(String.format("%d) %s %s",
                    reader.getId(),
                    reader.getFirstname(),
                    reader.getSurename()
            ));
        }
        return component;
    }
    
}
