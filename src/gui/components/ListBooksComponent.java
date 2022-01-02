
package gui.components;

import entitys.Book;
import facade.BookFacade;
import gui.GuiApp;
import gui.components.renderers.ListBooksCellRenderer;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class ListBooksComponent extends JPanel{
    private JLabel title;
    private JList<Book> list;
    
    public ListBooksComponent(int widthList, String text, int heightPanel) {
        initComponents(widthList, text, heightPanel);
    }

    private void initComponents(int widthList, String text, int heightPanel) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        title = new JLabel(text);
        title.setAlignmentY(TOP_ALIGNMENT);
        title.setFont(new Font("Tahoma", 0, 12));
        this.add(title);
        
        this.add(Box.createRigidArea(new Dimension(10,0)));
        
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(new ListBooksCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthList, heightPanel));
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    private ListModel<Book> getListModel() {
        BookFacade bookFacade = new BookFacade(Book.class);
        List<Book> booksArray = bookFacade.findAll();
        DefaultListModel<Book> defaultListModel = new DefaultListModel<>();
        for (Book booksArray1 : booksArray) {
            defaultListModel.addElement(booksArray1);
        }
        return defaultListModel;
    }
    
    public JList<Book> getList() {
        return list;
    }
}
