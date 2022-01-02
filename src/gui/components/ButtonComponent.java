
package gui.components;

import gui.GuiApp;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonComponent extends JPanel{
    private JButton button;
    
    public ButtonComponent(String text, int heightPanel, int widthButton) {
        initComponents(text, heightPanel, widthButton);
    }

    private void initComponents(String text, int heightPanel, int widthButton) {
       this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
       this.setMinimumSize(this.getPreferredSize());
       this.setMaximumSize(this.getPreferredSize());
       this.setLayout(new FlowLayout(FlowLayout.CENTER));
       
//       this.add(Box.createRigidArea(new Dimension((GuiApp.WINDOW_WIDTH/2) - (widthButton/2), 0)));
       
       button = new JButton(text);
       button.setPreferredSize(new Dimension(widthButton, 25));
       button.setMinimumSize(button.getPreferredSize());
       button.setMaximumSize(button.getPreferredSize());
       this.add(button);
    }

    public JButton getButton() {
        return button;
    }
    
}
