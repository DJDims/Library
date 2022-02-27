
package gui.components.lists;

import entitys.Book;
import entitys.User;
import facade.BookFacade;
import facade.UserFacade;
import gui.GuiApp;
import gui.components.renderers.ListBooksCellRenderer;
import gui.components.renderers.ListUsersCellRenderer;
import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class ListUsersComponent extends JPanel{
    private JLabel label;
    private JList<User> list;
    
    public ListUsersComponent(int widthList, int heightPanel) {
        initComponents(widthList, heightPanel);
    }

    private void initComponents(int widthList, int heightPanel) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        label = new JLabel("Список пользователей");
        label.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH/3, 25));
        label.setMinimumSize(label.getPreferredSize());
        label.setMaximumSize(label.getPreferredSize());
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setAlignmentY(TOP_ALIGNMENT);
        label.setFont(new Font("Tahoma", 0, 12));
        
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(new ListUsersCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthList, heightPanel));
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(
                this.getPreferredSize().width/2 - 
                        scrollPane.getPreferredSize().width/2 - 
                        label.getPreferredSize().width - 
                        10
                , 0)));
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(scrollPane);
    }

    private ListModel<User> getListModel() {
        UserFacade userFacade = new UserFacade();
        List<User> usersArray = userFacade.findAll();
        DefaultListModel<User> defaultListModel = new DefaultListModel<>();
        for (User usersArray1 : usersArray) {
            defaultListModel.addElement(usersArray1);
        }
        return defaultListModel;
    }
    
}
