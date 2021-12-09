
package gui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoComponent extends JPanel{
    private JLabel caption;
    
    public InfoComponent(int widthWindow, int heightPanel, String text) {
        initComponents(widthWindow, heightPanel, text);
    }

    private void initComponents(int widthWindow, int heightPanel, String text) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthWindow, heightPanel));
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setMaximumSize(caption.getPreferredSize());
        caption.setHorizontalAlignment(JLabel.CENTER);
        caption.setFont(new Font("Tahoma", 0, 14));
        this.add(caption);
    }
    
}
