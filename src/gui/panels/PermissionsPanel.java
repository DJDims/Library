
package gui.panels;

import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.LabelComponent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class PermissionsPanel extends JPanel{
    
    private LabelComponent permissionCaption;
    private LabelComponent permissionInfo;
    
//    private JList permissions;
    
    public PermissionsPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        permissionCaption = new LabelComponent(30, "Назначение прав пользователям", 18, 1);
        this.add(permissionCaption);
        permissionInfo = new LabelComponent(30, "Информация о назначении прав пользователям", 14, 0);
        this.add(permissionInfo);
    }
    
}
