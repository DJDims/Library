
package gui;

import classes.Author;
import classes.Book;
import classes.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.ReaderFacade;
import gui.components.ButtonComponent;
import gui.components.LabelComponent;
import gui.components.EditComponent;
import gui.components.ListAuthorsComponent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GuiApp extends JFrame{
    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 700;
    
    private LabelComponent addReaderCaption;
    private LabelComponent addReaderInfo;
    private LabelComponent addBookCaption;
    private LabelComponent addBookInfo;
    private LabelComponent addAuthorCaption;
    private LabelComponent addAuthorInfo;
    
    private EditComponent readerName;
    private EditComponent readerSurename;
    private EditComponent readerPhone;
    private EditComponent addBookTitle;
    private EditComponent count;
    private EditComponent pubslishingYear;
    private EditComponent authorName;
    private EditComponent authorSurename;
    private EditComponent authorBornYear;
    
    private ListAuthorsComponent listAuthors;
    
    private ButtonComponent addReaderButton;
    private ButtonComponent addBookButton;
    private ButtonComponent addAuthorButton;
    
    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        tabs.setMinimumSize(tabs.getPreferredSize());
        tabs.setMaximumSize(tabs.getPreferredSize());
        this.add(tabs);
        
        JPanel addReaderPanel = new JPanel();
        tabs.addTab("Добавить читателя", addReaderPanel);
            addReaderCaption = new LabelComponent(this.getWidth(), 50, "Добавление читателя", 18, 1);
            addReaderPanel.add(addReaderCaption);
            addReaderInfo = new LabelComponent(this.getWidth(), 30, "Информация о добавлении читателя", 14, 0);
            addReaderPanel.add(addReaderInfo);
            readerName = new EditComponent(250, "Имя читателя", this.getWidth(), 30);
            addReaderPanel.add(readerName);
            readerSurename = new EditComponent(250, "Фамилия читателя", this.getWidth(), 30);
            addReaderPanel.add(readerSurename);
            readerPhone = new EditComponent(250, "Телефон читателя", this.getWidth(), 30);
            addReaderPanel.add(readerPhone);
            addReaderButton = new ButtonComponent("Добавить читателя", WINDOW_WIDTH, 50, 200, 150);
            addReaderPanel.add(addReaderButton);
            addReaderButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Reader reader = new Reader();
                    
                    if (readerName.getEditor().getText().isEmpty()) {
                        return;
                    }
                    reader.setFirstname(readerName.getEditor().getText());
                    
                    if (readerSurename.getEditor().getText().isEmpty()) {
                        return;
                    }
                    reader.setSurename(readerSurename.getEditor().getText());
                    
                    if (readerPhone.getEditor().getText().isEmpty()) {
                        return;
                    }
                    reader.setPhoneNumber(readerPhone.getEditor().getText());
                    
                    ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                    try {
                        readerFacade.create(reader);
                        readerName.getEditor().setText("");
                        readerSurename.getEditor().setText("");
                        readerPhone.getEditor().setText("");
                    } catch (Exception e) {
                        return;
                    }
                }
                });
        
        JPanel addBookPanel = new JPanel();
        tabs.addTab("Добавить книгу", addBookPanel);
            addBookCaption = new LabelComponent(this.getWidth(), 50, "Добавление книги", 18, 1);
            addBookPanel.add(addBookCaption);
            addBookInfo = new LabelComponent(this.getWidth(), 30, "Информация о добавлении книги", 14, 0);
            addBookPanel.add(addBookInfo);
            addBookTitle = new EditComponent(250, "Название книги", this.getWidth(), 30);
            addBookPanel.add(addBookTitle);
            listAuthors = new ListAuthorsComponent(250, "Список авторов", this.getWidth(), 120);
            addBookPanel.add(listAuthors);
            pubslishingYear = new EditComponent(250, "Год публикации", this.getWidth(), 30);
            addBookPanel.add(pubslishingYear);
            count = new EditComponent(250, "Количество книг", this.getWidth(), 30);
            addBookPanel.add(count);
            addBookButton = new ButtonComponent("Добавить книгу", WINDOW_WIDTH, 50, 200, 150);
            addBookPanel.add(addBookButton);
            addBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Book book = new Book();
                    
                    if (addBookTitle.getEditor().getText().isEmpty()) {
                        return;
                    }
                    book.setTitle(addBookTitle.getEditor().getText());
                    
                    List<Author> bookAuthors = listAuthors.getList().getSelectedValuesList();
                    if (bookAuthors.isEmpty()) {
                        return;
                    }
                    book.setAuthors(bookAuthors);
                    
                    try {
                        book.setPublishYear(Integer.parseInt(pubslishingYear.getEditor().getText()));
                    } catch (Exception e) {
                        System.out.println("Проблема с годом");
                        return;
                    }
                    
                    try {
                        book.setCount(Integer.parseInt(count.getEditor().getText()));
                    } catch (Exception e) {
                        System.out.println("Проблема с количеством");
                        return;
                    }
                    
                    BookFacade bookFacade = new BookFacade(Book.class);
                    try {
                        bookFacade.create(book);
                        addBookTitle.getEditor().setText("");
                        listAuthors.getList().clearSelection();
                        pubslishingYear.getEditor().setText("");
                        count.getEditor().setText("");
                    } catch (Exception e) {
                        return;
                    }
                }
                });
            
        JPanel addAuthorPanel = new JPanel();
        tabs.addTab("Добавить автора", addAuthorPanel);
            addAuthorCaption = new LabelComponent(this.getWidth(), 50, "Добавление автора", 18, 1);
            addAuthorPanel.add(addAuthorCaption);
            addAuthorInfo = new LabelComponent(this.getWidth(), 30, "Информация о добавлении автора", 14, 0);
            addAuthorPanel.add(addAuthorInfo);
            authorName = new EditComponent(250, "Имя автора", this.getWidth(), 30);
            addAuthorPanel.add(authorName);
            authorSurename = new EditComponent(250, "Фамилия автора", this.getWidth(), 30);
            addAuthorPanel.add(authorSurename);
            authorBornYear = new EditComponent(250, "Год рождения", this.getWidth(), 30);
            addAuthorPanel.add(authorBornYear);
            addAuthorButton = new ButtonComponent("Добавить автора", WINDOW_WIDTH, 50, 200, 150);
            addAuthorPanel.add(addAuthorButton);
            addAuthorButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Author author = new Author();
                
                if (authorName.getEditor().getText().isEmpty()) {
                    return;
                }
                author.setName(authorName.getEditor().getText());
                
                if (authorSurename.getEditor().getText().isEmpty()) {
                    return;
                }
                author.setSurename(authorSurename.getEditor().getText());
                
                try {
                    author.setBornYear(Integer.parseInt(authorBornYear.getEditor().getText()));
                } catch (Exception e) {
                    return;
                }
                
                AuthorFacade authorFacade = new AuthorFacade(Author.class);
                try {
                    authorFacade.create(author);
                    authorName.getEditor().setText("");
                    authorSurename.getEditor().setText("");
                    authorBornYear.getEditor().setText("");
                } catch (Exception e) {
                    return;
                }
            }
            });
                
        JPanel takeBookPanel = new JPanel();
        tabs.addTab("Взять книгу", takeBookPanel);
            //code...
            
        JPanel extendBookPanel = new JPanel();
        tabs.addTab("Продлить книгу", extendBookPanel);
            //code...
            
        JPanel returnBookPanel = new JPanel();
        tabs.addTab("Вернуть книгу", returnBookPanel);
            //code...
            
        JPanel changeBookPanel = new JPanel();
        tabs.addTab("Изменить книгу", changeBookPanel);
            //code...
            
        JPanel changeReaderPanel = new JPanel();
        tabs.addTab("Изменить читателя", changeReaderPanel);
            //code...
            
        JPanel changeAuthorPanel = new JPanel();
        tabs.addTab("Изменить автора", changeAuthorPanel);
            //code...
            
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GuiApp().setVisible(true);
            }
        });
    }
}
