
package gui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelComponent extends JPanel{
    private JLabel label;
    
    public LabelComponent(int widthWindow, int heightPanel, String text, int fontSize, int fontStyle) {
        initComponents(widthWindow, heightPanel, text, fontSize, fontStyle);
    }

    private void initComponents(int widthWindow, int heightPanel, String text, int fontSize, int fontStyle) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        label = new JLabel(text);
        label.setPreferredSize(new Dimension(widthWindow, heightPanel));
        label.setMinimumSize(label.getPreferredSize());
        label.setMaximumSize(label.getPreferredSize());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Tahoma", fontStyle, fontSize));
        this.add(label);
    }
    
    public JLabel getLabel(){
        return label;
    }
    
}
