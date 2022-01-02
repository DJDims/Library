
package gui.components;

import gui.GuiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SpinnerComponent extends JPanel{
    JLabel label;
    JSpinner spinner;
    
    public SpinnerComponent(int heightPanel, String text, int widthSpinner, int maxValue) {
        initComponents(heightPanel, text, widthSpinner, maxValue);
    }

    private void initComponents(int heightPanel, String text, int widthSpinner, int maxValue) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Tahoma", 0, 12));
        this.add(label);
        
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        
        spinner = new JSpinner();
        spinner.setPreferredSize(new Dimension(widthSpinner, 30));
        spinner.setMinimumSize(spinner.getPreferredSize());
        spinner.setMaximumSize(spinner.getPreferredSize());
        spinner.setModel(new SpinnerNumberModel(1, 1, maxValue, 1));
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setBackground(Color.white);
        this.add(spinner);
    }
    
    public JSpinner getSpinner(){
        return spinner;
    }
}
