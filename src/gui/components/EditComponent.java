
package gui.components;

import gui.GuiApp;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditComponent extends JPanel{
    private JLabel label;
    private JTextField editor;
    
    public EditComponent(int widthEditor, String text, int heightPanel) {
        initComponents(widthEditor, text, heightPanel);
    }

    private void initComponents(int widthEditor, String text, int heightPanel) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        label = new JLabel(text);
        label.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH/3, 25));
        label.setMinimumSize(label.getPreferredSize());
        label.setMaximumSize(label.getPreferredSize());
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Tahoma", 0, 12));
        this.add(label);
        
        this.add(Box.createRigidArea(new Dimension(10,0)));
        
        editor = new JTextField();
        editor.setPreferredSize(new Dimension(widthEditor, 25));
        editor.setMinimumSize(editor.getPreferredSize());
        editor.setMaximumSize(editor.getPreferredSize());
        this.add(editor);
    }
    
    public JTextField getEditor(){
        return editor;
    }
    
}
