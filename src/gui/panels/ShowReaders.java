
package gui.panels;

import gui.GuiApp;
import gui.components.LabelComponent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ShowReaders extends JPanel{

    LabelComponent caption;
    
    public ShowReaders() {
        initComponents();
    }
    
    private void initComponents() {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, GuiApp.WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        caption = new LabelComponent(30, "Просмотр читателей", 18, 1);
        this.add(caption);
    }
}
