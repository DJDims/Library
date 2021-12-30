
package gui;

import classes.Author;
import classes.Book;
import classes.History;
import classes.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import gui.components.ButtonComponent;
import gui.components.LabelComponent;
import gui.components.EditComponent;
import gui.components.ListAuthorsComponent;
import gui.components.ListBooksComponent;
import gui.components.ListReadersComponent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    private LabelComponent takeBookCaption;
    private LabelComponent takeBookInfo;
    
    private EditComponent readerName;
    private EditComponent readerSurename;
    private EditComponent readerPhone;
    private EditComponent addBookTitle;
    private EditComponent count;
    private EditComponent pubslishingYear;
    private EditComponent authorName;
    private EditComponent authorSurename;
    private EditComponent authorBornYear;
    
    private ListAuthorsComponent addBookAuthorsList;
    private ListBooksComponent takeBookBooksList;
    private ListReadersComponent takeBookReadersList;
    
    private ButtonComponent addReaderButton;
    private ButtonComponent addBookButton;
    private ButtonComponent addAuthorButton;
    private ButtonComponent takeBookButton;
    
    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setTitle("Library");
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        tabs.setMinimumSize(tabs.getPreferredSize());
        tabs.setMaximumSize(tabs.getPreferredSize());
        this.add(tabs);
        
        JPanel addReaderPanel = new JPanel();
        tabs.addTab("Добавить читателя", addReaderPanel);
            addReaderCaption = new LabelComponent(WINDOW_WIDTH, 50, "Добавление читателя", 18, 1);
            addReaderPanel.add(addReaderCaption);
            addReaderInfo = new LabelComponent(WINDOW_WIDTH, 30, "Информация о добавлении читателя", 14, 0);
            addReaderPanel.add(addReaderInfo);
            readerName = new EditComponent(250, "Имя читателя", WINDOW_WIDTH, 30);
            addReaderPanel.add(readerName);
            readerSurename = new EditComponent(250, "Фамилия читателя", WINDOW_WIDTH, 30);
            addReaderPanel.add(readerSurename);
            readerPhone = new EditComponent(250, "Телефон читателя", WINDOW_WIDTH, 30);
            addReaderPanel.add(readerPhone);
            addReaderButton = new ButtonComponent("Добавить читателя", WINDOW_WIDTH, 30, 150);
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
            addBookCaption = new LabelComponent(WINDOW_WIDTH, 50, "Добавление книги", 18, 1);
            addBookPanel.add(addBookCaption);
            addBookInfo = new LabelComponent(WINDOW_WIDTH, 30, "Информация о добавлении книги", 14, 0);
            addBookPanel.add(addBookInfo);
            addBookTitle = new EditComponent(250, "Название книги", WINDOW_WIDTH, 30);
            addBookPanel.add(addBookTitle);
            addBookAuthorsList = new ListAuthorsComponent(250, "Список авторов", WINDOW_WIDTH, 150);
            addBookPanel.add(addBookAuthorsList);
            pubslishingYear = new EditComponent(250, "Год публикации", WINDOW_WIDTH, 30);
            addBookPanel.add(pubslishingYear);
            count = new EditComponent(250, "Количество книг", WINDOW_WIDTH, 30);
            addBookPanel.add(count);
            addBookButton = new ButtonComponent("Добавить книгу", WINDOW_WIDTH, 30, 150);
            addBookPanel.add(addBookButton);
            addBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Book book = new Book();
                    
                    if (addBookTitle.getEditor().getText().isEmpty()) {
                        return;
                    }
                    book.setTitle(addBookTitle.getEditor().getText());
                    
                    List<Author> bookAuthors = addBookAuthorsList.getList().getSelectedValuesList();
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
                        addBookAuthorsList.getList().clearSelection();
                        pubslishingYear.getEditor().setText("");
                        count.getEditor().setText("");
                    } catch (Exception e) {
                        return;
                    }
                }
                });
            
        JPanel addAuthorPanel = new JPanel();
        tabs.addTab("Добавить автора", addAuthorPanel);
            addAuthorCaption = new LabelComponent(WINDOW_WIDTH, 50, "Добавление автора", 18, 1);
            addAuthorPanel.add(addAuthorCaption);
            addAuthorInfo = new LabelComponent(WINDOW_WIDTH, 30, "Информация о добавлении автора", 14, 0);
            addAuthorPanel.add(addAuthorInfo);
            authorName = new EditComponent(250, "Имя автора", WINDOW_WIDTH, 30);
            addAuthorPanel.add(authorName);
            authorSurename = new EditComponent(250, "Фамилия автора", WINDOW_WIDTH, 30);
            addAuthorPanel.add(authorSurename);
            authorBornYear = new EditComponent(250, "Год рождения", WINDOW_WIDTH, 30);
            addAuthorPanel.add(authorBornYear);
            addAuthorButton = new ButtonComponent("Добавить автора", WINDOW_WIDTH, 30, 150);
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
            takeBookCaption = new LabelComponent(WINDOW_WIDTH, 30, "Взять книгу", 18, 1);
            takeBookPanel.add(takeBookCaption);
            takeBookInfo = new LabelComponent(WINDOW_WIDTH, 30, "Информация о взятой книги", 14, 0);
            takeBookPanel.add(takeBookInfo);
            takeBookBooksList = new ListBooksComponent(300, "Книги", WINDOW_WIDTH, 200);
            takeBookPanel.add(takeBookBooksList);
            takeBookReadersList = new ListReadersComponent(300, "Читатели", WINDOW_WIDTH, 200);
            takeBookPanel.add(takeBookReadersList);
            takeBookButton = new ButtonComponent("Взять книгу", WINDOW_WIDTH, 30, 150);
            takeBookPanel.add(takeBookButton);
            takeBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    History history = new History();
                    
                    try {
                        history.setBook(takeBookBooksList.getList().getSelectedValue());
                    } catch (Exception e) {
                        return;
                    }
                    try {
                        history.setReader(takeBookReadersList.getList().getSelectedValue());
                    } catch (Exception e) {
                        return;
                    }
                    
                    history.setIssueDate(localdateToDate(LocalDate.now()));
                    history.setReturnDate(localdateToDate(LocalDate.now().plusWeeks(2)));
                    
                    HistoryFacade historyFacade = new HistoryFacade(History.class);
                    try {
                        historyFacade.edit(history);
                        takeBookBooksList.getList().clearSelection();
                        takeBookReadersList.getList().clearSelection();
                    } catch (Exception e) {
                        return;
                    }
                }
                });
            
        JPanel extendBookPanel = new JPanel();
        tabs.addTab("Продлить книгу", extendBookPanel);
            
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
    
    private Date localdateToDate(LocalDate dateToConvert){
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
