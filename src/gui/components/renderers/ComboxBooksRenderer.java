
package gui.components.renderers;

import entitys.Book;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ComboxBooksRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            Book book = (Book) value;
            label.setText(String.format("%d) %s (%d)",
                    book.getId(),
                    book.getTitle(),
                    book.getPublishYear()
            ));
        }
        return component;
    }
    
}
