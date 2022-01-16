
package gui.panels;

import gui.GuiApp;
import gui.components.LabelComponent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ShowAuthors extends JPanel{

    LabelComponent caption;
    
    public ShowAuthors() {
        initComponents();
    }
    
    private void initComponents() {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, GuiApp.WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        caption = new LabelComponent(30, "Просмотр авторов", 18, 1);
        this.add(caption);
    }
}
