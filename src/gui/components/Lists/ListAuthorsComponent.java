
package gui.components.Lists;

import entitys.Author;
import facade.AuthorFacade;
import gui.GuiApp;
import gui.components.renderers.ListAuthorsCellRenderer;
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

public class ListAuthorsComponent extends JPanel{
    private JLabel title;
    private JList<Author> list;
    
    public ListAuthorsComponent(int widthList, int heightPanel) {
        initComponents(widthList, heightPanel);
    }

    private void initComponents(int widthList, int heightPanel) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        title = new JLabel("Список авторов");
        title.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH/3, 25));
        title.setMinimumSize(title.getPreferredSize());
        title.setMaximumSize(title.getPreferredSize());
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setAlignmentY(TOP_ALIGNMENT);
        title.setFont(new Font("Tahoma", 0, 12));
        this.add(title);
        
        this.add(Box.createRigidArea(new Dimension(10,0)));
        
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(new ListAuthorsCellRenderer());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthList, heightPanel));
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    private ListModel<Author> getListModel() {
        AuthorFacade authorFacade = new AuthorFacade(Author.class);
        List<Author> authorsArray = authorFacade.findAll();
        DefaultListModel<Author> defaultListModel = new DefaultListModel<>();
        for (Author authorsArray1 : authorsArray) {
            defaultListModel.addElement(authorsArray1);
        }
        return defaultListModel;
    }
    
    public JList<Author> getList() {
        return list;
    }
}
