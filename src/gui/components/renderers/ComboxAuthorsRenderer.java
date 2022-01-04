
package gui.components.renderers;

import entitys.Author;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ComboxAuthorsRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            Author author = (Author) value;
            label.setText(String.format("%d) %s %s %d",
                    author.getId(),
                    author.getName(),
                    author.getSurename(),
                    author.getBornYear()
            ));
        }
        return component;
    }
    
}
