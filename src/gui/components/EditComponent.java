
package gui.components;

import gui.GuiApp;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditComponent extends JPanel{
    private JLabel title;
    private JTextField editor;
    
    public EditComponent(int widthEditor, String text, int heightPanel) {
        initComponents(widthEditor, text, heightPanel);
    }

    private void initComponents(int widthEditor, String text, int heightPanel) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        title = new JLabel(text);
        title.setFont(new Font("Tahoma", 0, 12));
        this.add(title);
        
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
