
package gui.components;

import entitys.Author;
import facade.AuthorFacade;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.renderers.ListAuthorsCellRenderer;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class DoubleListComponent extends JPanel{
    private JList authorsBase;
    private JList authorsThis;
    private JButton addButton;
    private JButton removeButton;
    private JPanel buttonsPanel;
    
    public DoubleListComponent() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        authorsBase = new JList();
        authorsBase.setPreferredSize(new Dimension(250, 150));
        authorsBase.setMinimumSize(authorsBase.getPreferredSize());
        authorsBase.setMaximumSize(authorsBase.getPreferredSize());
        authorsBase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        authorsBase.setCellRenderer(new ListAuthorsCellRenderer());
        
        authorsThis = new JList();
        authorsThis.setPreferredSize(new Dimension(250, 150));
        authorsThis.setMinimumSize(authorsThis.getPreferredSize());
        authorsThis.setMaximumSize(authorsThis.getPreferredSize());
        authorsThis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        authorsThis.setCellRenderer(new ListAuthorsCellRenderer());
        
        addButton = new JButton();
        addButton.setPreferredSize(new Dimension(130, 20));
        addButton.setMinimumSize(addButton.getPreferredSize());
        addButton.setMaximumSize(addButton.getPreferredSize());
        addButton.setText("Добавить -->");
        
        removeButton = new JButton();
        removeButton.setPreferredSize(new Dimension(130, 20));
        removeButton.setMinimumSize(removeButton.getPreferredSize());
        removeButton.setMaximumSize(removeButton.getPreferredSize());
        removeButton.setText("<-- Удалить");
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(removeButton);
        
        this.add(authorsBase);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(buttonsPanel);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(authorsThis);

    }
    
    private ListModel<Author> getListBaseModel() {
        AuthorFacade authorFacade = new AuthorFacade();
        List<Author> authorsArray = authorFacade.findAll();
        DefaultListModel<Author> defaultListModel = new DefaultListModel<>();
        for (Author authorsArray1 : authorsArray) {
            defaultListModel.addElement(authorsArray1);
        }
        return defaultListModel;
    }
    
    private ListModel<Author> getListThisModel() {
        AuthorFacade authorFacade = new AuthorFacade();
        List<Author> authorsArray = authorFacade.findAll();
        DefaultListModel<Author> defaultListModel = new DefaultListModel<>();
        for (Author authorsArray1 : authorsArray) {
            defaultListModel.addElement(authorsArray1);
        }
        return defaultListModel;
    }
    
}
