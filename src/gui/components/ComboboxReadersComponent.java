
package gui.components;

import entitys.Reader;
import facade.ReaderFacade;
import gui.GuiApp;
import gui.components.renderers.ComboxReadersRenderer;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboboxReadersComponent extends JPanel{
    JLabel label;
    JComboBox combobox;
    
    public ComboboxReadersComponent(int heightPanel, int comboboxWidth) {
        initComponents(heightPanel, comboboxWidth);
    }
    
    private void initComponents(int heightPanel, int comboboxWidth){
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        label = new JLabel("Список читателей");
        label.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH/3, heightPanel));
        label.setMinimumSize(label.getPreferredSize());
        label.setMaximumSize(label.getPreferredSize());
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Tahoma", 0, 12));
        this.add(label);
        
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        
        combobox = new JComboBox();
        combobox.setPreferredSize(new Dimension(comboboxWidth, heightPanel));
        combobox.setMinimumSize(combobox.getPreferredSize());
        combobox.setMaximumSize(combobox.getPreferredSize());
        combobox.setModel(getComboboxModel());
        combobox.setRenderer(new ComboxReadersRenderer());
        this.add(combobox);
    }

    private ComboBoxModel<Reader> getComboboxModel() {
        ReaderFacade readerFacade = new ReaderFacade();
        List<Reader> readersArray = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader readersArray1 : readersArray) {
            defaultComboBoxModel.addElement(readersArray1);
        }
        return defaultComboBoxModel;
    }
    
    public JComboBox getCombobox() {
        return combobox;
    }

}
