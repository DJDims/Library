
package gui.panels;

import entitys.Author;
import entitys.Book;
import facade.AuthorFacade;
import facade.BookFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import gui.components.lists.ListBooksComponent;
import gui.components.renderers.ListAuthorsCellRenderer;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChangeBookPanel extends JPanel{
    
    private LabelComponent changeBookCaption;
    private LabelComponent changeBookInfo;
    private ListBooksComponent changeBookBooksList;
    private ButtonComponent updateButton;
    private EditComponent bookTitle;
    private EditComponent bookYear;
    
    private JPanel doubleList;
    private JList authorsBase;
    private JList authorsThis;
    private JButton addButton;
    private JButton removeButton;
    private JPanel buttonsPanel;
    
    AuthorFacade authorFacade = new AuthorFacade();
    BookFacade bookFacade = new BookFacade();
    
    List<Author> base = authorFacade.findAll();
    List<Author> thisBook = new ArrayList<>();
    
    Book book;
    
    public ChangeBookPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        changeBookCaption = new LabelComponent(30, "Изменение книги", 18, 1);
        changeBookInfo = new LabelComponent(30, "Данные об изменении книги", 14, 0);
        changeBookBooksList = new ListBooksComponent(350, 150);
        bookTitle = new EditComponent(300, "Название книги", 30);
        bookYear = new EditComponent(300, "Год публикации", 30);
        
        doubleList = new JPanel();
        
            JPanel authorBasePanel = new JPanel();
            authorBasePanel.setLayout(new BoxLayout(authorBasePanel, BoxLayout.Y_AXIS));

                JLabel authorBaseLabel = new JLabel();
                authorBaseLabel.setText("База авторов");
                authorBaseLabel.setFont(new Font("Tahoma", 0, 12));

                authorsBase = new JList();
                authorsBase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                authorsBase.setCellRenderer(new ListAuthorsCellRenderer());
                authorsBase.setModel(getListBaseModel());

                JScrollPane scrollBase = new JScrollPane(authorsBase);
                scrollBase.setPreferredSize(new Dimension(250, 150));
                scrollBase.setMinimumSize(scrollBase.getPreferredSize());
                scrollBase.setMaximumSize(scrollBase.getPreferredSize());
                scrollBase.setAlignmentX(LEFT_ALIGNMENT);
                scrollBase.setAlignmentY(TOP_ALIGNMENT);

            authorBasePanel.add(authorBaseLabel);
            authorBasePanel.add(scrollBase);

            JPanel authorThisPanel = new JPanel();
            authorThisPanel.setLayout(new BoxLayout(authorThisPanel, BoxLayout.Y_AXIS));

                JLabel authorThisLabel = new JLabel();
                authorThisLabel.setText("Авторы этой книги");
                authorThisLabel.setFont(new Font("Tahoma", 0, 12));

                authorsThis = new JList();
                authorsThis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                authorsThis.setCellRenderer(new ListAuthorsCellRenderer());

                JScrollPane scrollThis = new JScrollPane(authorsThis);
                scrollThis.setPreferredSize(new Dimension(250, 150));
                scrollThis.setMinimumSize(scrollThis.getPreferredSize());
                scrollThis.setMaximumSize(scrollThis.getPreferredSize());
                scrollThis.setAlignmentX(LEFT_ALIGNMENT);
                scrollThis.setAlignmentY(TOP_ALIGNMENT);

            authorThisPanel.add(authorThisLabel);
            authorThisPanel.add(scrollThis);

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

            doubleList.add(authorBasePanel);
            doubleList.add(buttonsPanel);
            doubleList.add(authorThisPanel);
        
        updateButton = new ButtonComponent("Обновить данные", 30, 180);
        
        this.add(changeBookCaption);
        this.add(changeBookInfo);
        this.add(changeBookBooksList);
        this.add(bookTitle);
        this.add(bookYear);
        this.add(doubleList);
        this.add(updateButton);
        
        changeBookBooksList.getList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                book = changeBookBooksList.getList().getSelectedValue();
                thisBook = book.getAuthors();
                base = authorFacade.findAll();
                for (Author author : thisBook) {
                    base.remove(author);
                }
                updateLists();
                
                bookTitle.getEditor().setText(book.getTitle());
                bookYear.getEditor().setText(Integer.toString(book.getPublishYear()));
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<Author> authorsToEdit = authorsBase.getSelectedValuesList();
                
                if (authorsToEdit.isEmpty()) {
                    editLabel("Не выбраны авторы", changeBookInfo, Color.red);
                    return;
                }
                
                for (Author author : authorsToEdit) {
                    base.remove(author);
                    thisBook.add(author);
                }
                updateLists();
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<Author> authorsToEdit = authorsThis.getSelectedValuesList();
                
                if (authorsToEdit.isEmpty()) {
                    editLabel("Не выбраны авторы", changeBookInfo, Color.red);
                    return;
                }
                
                for (Author author : authorsToEdit) {
                    base.add(author);
                    thisBook.remove(author);
                }
                updateLists();
            }
        });
        
        updateButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (bookTitle.getText().isEmpty()) {
                    editLabel("Некорректное название", changeBookInfo, Color.red);
                    return;
                }
                book.setTitle(bookTitle.getText());
                
                try {
                    book.setPublishYear(Integer.parseInt(bookYear.getText()));
                } catch (Exception e) {
                    editLabel("Некорректный год", changeBookInfo, Color.red);
                    return;
                }
                
                if (thisBook.isEmpty()) {
                    editLabel("Добавте хотя бы одного автора", changeBookInfo, Color.red);
                    return;
                }
                changeBookBooksList.getList().getSelectedValue().setAuthors(thisBook);
                try {
                    bookFacade.edit(book);
                    editLabel("Данные книги успешно обновлены", changeBookInfo, Color.green);
                    bookTitle.clear();
                    bookYear.clear();
                    authorsBase.clearSelection();
                    authorsThis.clearSelection();
                    
                } catch (Exception e) {
                    editLabel("Что-то пошло не так", changeBookInfo, Color.red);
                }
            }
        });
    }
    
    private ListModel<Author> getListBaseModel() {
        DefaultListModel<Author> defaultListModel = new DefaultListModel<>();
        for (Author author : base) {
            defaultListModel.addElement(author);
        }
        return defaultListModel;
    }
    
    private ListModel<Author> getListThisModel() {
        DefaultListModel<Author> defaultListModel = new DefaultListModel<>();
        for (Author author : thisBook) {
            defaultListModel.addElement(author);
        }
        return defaultListModel;
    }
    
    private void updateLists() {
        authorsThis.setModel(getListThisModel());
        authorsBase.setModel(getListBaseModel());
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
    public void update(){
        this.removeAll();
        initComponents();
    }
    
}
