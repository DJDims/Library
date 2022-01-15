
package gui.panels;

import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.DoubleListComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListBooksComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;

public class ChangeBookPanel extends JPanel{
    
    private LabelComponent changeBookCaption;
    private LabelComponent changeBookInfo;
    private ListBooksComponent changeBookBooksList;
//------------------------------------------------------------------------------------------------------------------------------------
    private DoubleListComponent doubleListComponent;
//------------------------------------------------------------------------------------------------------------------------------------
    
    public ChangeBookPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        changeBookCaption = new LabelComponent(30, "Изменение книги", 18, 1);
        this.add(changeBookCaption);
        changeBookInfo = new LabelComponent(30, "Данные об изменении книги", 14, 0);
        this.add(changeBookInfo);
        changeBookBooksList = new ListBooksComponent(350, 100);
        this.add(changeBookBooksList);
//------------------------------------------------------------------------------------------------------------------------------------
        doubleListComponent = new DoubleListComponent();
        this.add(doubleListComponent);
//------------------------------------------------------------------------------------------------------------------------------------

    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
    public void update(){
        this.removeAll();
        initComponents();
    }
    
}
