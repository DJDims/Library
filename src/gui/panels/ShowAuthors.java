
package gui.panels;

import gui.GuiApp;
import gui.components.LabelComponent;
import gui.components.lists.ListAuthorsComponent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ShowAuthors extends JPanel{

    LabelComponent caption;
    ListAuthorsComponent listAuthors;
    
    public ShowAuthors() {
        initComponents();
    }
    
    private void initComponents() {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, GuiApp.WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        caption = new LabelComponent(30, "Просмотр авторов", 18, 1);
        this.add(caption);
        
        listAuthors = new ListAuthorsComponent(300, 200);
        this.add(listAuthors);
    }
}
