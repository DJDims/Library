
package gui;

import entitys.Author;
import entitys.Book;
import entitys.History;
import entitys.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import gui.components.ButtonComponent;
import gui.components.LabelComponent;
import gui.components.EditComponent;
import gui.components.ListAuthorsComponent;
import gui.components.ListBooksComponent;
import gui.components.ListHistorysComponent;
import gui.components.ListReadersComponent;
import gui.components.SpinnerComponent;
import java.awt.Color;
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
    private LabelComponent extendBookCaption;
    private LabelComponent extendBookInfo;
    private LabelComponent returnBookCaption;
    private LabelComponent returnBookInfo;
    
    private EditComponent readerName;
    private EditComponent readerSurename;
    private EditComponent readerPhone;
    private EditComponent addBookTitle;
//    private EditComponent count;
    private EditComponent pubslishingYear;
    private EditComponent authorName;
    private EditComponent authorSurename;
    private EditComponent authorBornYear;
//    private EditComponent extendBookWeeks;
    
    private ListAuthorsComponent addBookAuthorsList;
    private ListBooksComponent takeBookBooksList;
    private ListReadersComponent takeBookReadersList;
    private ListHistorysComponent extendBookList;
    private ListBooksComponent returnBookBooksList;
    
    private ButtonComponent addReaderButton;
    private ButtonComponent addBookButton;
    private ButtonComponent addAuthorButton;
    private ButtonComponent takeBookButton;
    private ButtonComponent extendBookButton;
    private ButtonComponent returnBookButton;
    
    private SpinnerComponent addBookCount;
    private SpinnerComponent extendBookWeeks;
    
    private ButtonComponent testButton;
    private SpinnerComponent testSpinner;
    
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
        
        JPanel testTab = new JPanel();
        tabs.addTab("Для тестов", testTab);
            testSpinner = new SpinnerComponent(30, "лолкек", 100, 100);
            testTab.add(testSpinner);
            testButton = new ButtonComponent("", 30, 100);
            testTab.add(testButton);
            testButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    System.out.println(testSpinner.getSpinner().getValue());
                    System.out.println(testSpinner.getSpinner().getValue().getClass());
                }
                });
        
//<editor-fold>
        JPanel addReaderPanel = new JPanel();
        tabs.addTab("Добавить читателя", addReaderPanel);
            addReaderCaption = new LabelComponent(50, "Добавление читателя", 18, 1);
            addReaderPanel.add(addReaderCaption);
            addReaderInfo = new LabelComponent(30, "Информация о добавлении читателя", 14, 0);
            addReaderPanel.add(addReaderInfo);
            readerName = new EditComponent(250, "Имя читателя", 30);
            addReaderPanel.add(readerName);
            readerSurename = new EditComponent(250, "Фамилия читателя", 30);
            addReaderPanel.add(readerSurename);
            readerPhone = new EditComponent(250, "Телефон читателя", 30);
            addReaderPanel.add(readerPhone);
            addReaderButton = new ButtonComponent("Добавить читателя", 30, 150);
            addReaderPanel.add(addReaderButton);
            addReaderButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Reader reader = new Reader();
                    
                    if (readerName.getEditor().getText().trim().isEmpty()) {
                        System.out.println("Проблема с именем");
                        addReaderInfo.getLabel().setForeground(Color.red);
                        addReaderInfo.getLabel().setText("Введите имя читателя");
                        return;
                    }
                    reader.setFirstname(readerName.getEditor().getText().trim());
                    
                    if (readerSurename.getEditor().getText().trim().isEmpty()) {
                        System.out.println("Проблема с фамилией");
                        addReaderInfo.getLabel().setForeground(Color.red);
                        addReaderInfo.getLabel().setText("Введите фамилию читателя");
                        return;
                    }
                    reader.setSurename(readerSurename.getEditor().getText().trim());
                    
                    if (readerPhone.getEditor().getText().trim().isEmpty()) {
                        System.out.println("Проблема с телефоном");
                        addReaderInfo.getLabel().setForeground(Color.red);
                        addReaderInfo.getLabel().setText("Введите телефон читателя");
                        return;
                    }
                    reader.setPhoneNumber(readerPhone.getEditor().getText().trim());
                    
                    ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                    try {
                        readerFacade.create(reader);
                        addReaderInfo.getLabel().setForeground(Color.green);
                        addReaderInfo.getLabel().setText("Читатель успешно добавлен");
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
            addBookCaption = new LabelComponent(50, "Добавление книги", 18, 1);
            addBookPanel.add(addBookCaption);
            addBookInfo = new LabelComponent(30, "Информация о добавлении книги", 14, 0);
            addBookPanel.add(addBookInfo);
            addBookTitle = new EditComponent(250, "Название книги", 30);
            addBookPanel.add(addBookTitle);
            addBookAuthorsList = new ListAuthorsComponent(250, "Список авторов", 150);
            addBookPanel.add(addBookAuthorsList);
            pubslishingYear = new EditComponent(250, "Год публикации", 30);
            addBookPanel.add(pubslishingYear);
            
//            count = new EditComponent(250, "Количество книг", 30);
//            addBookPanel.add(count);
            addBookCount = new SpinnerComponent(30, "Количество книг", 100, 50);
            addBookPanel.add(addBookCount);
            
            addBookButton = new ButtonComponent("Добавить книгу", 30, 150);
            addBookPanel.add(addBookButton);
            addBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Book book = new Book();
                    
                    if (addBookTitle.getEditor().getText().trim().isEmpty()) {
                        System.out.println("Проблема с именем");
                        addBookInfo.getLabel().setForeground(Color.red);
                        addBookInfo.getLabel().setText("Введите название книги");
                        return;
                    }
                    book.setTitle(addBookTitle.getEditor().getText().trim());
                    
                    List<Author> bookAuthors = addBookAuthorsList.getList().getSelectedValuesList();
                    if (bookAuthors.isEmpty()) {
                        System.out.println("Не выбран автор");
                        addBookInfo.getLabel().setForeground(Color.red);
                        addBookInfo.getLabel().setText("Выберите автора(ов) книги");
                        return;
                    }
                    book.setAuthors(bookAuthors);
                    
                    if (pubslishingYear.getEditor().getText().trim().isEmpty()) {
                        addBookInfo.getLabel().setForeground(Color.red);
                        addBookInfo.getLabel().setText("Введите год публикации книги");
                        return;
                    }
                    
                    try {
                        book.setPublishYear(Integer.parseInt(pubslishingYear.getEditor().getText().trim()));
                    } catch (Exception e) {
                        System.out.println("Проблема с годом");
                        addBookInfo.getLabel().setForeground(Color.red);
                        addBookInfo.getLabel().setText("Введите год публикации книги цифрами");
                        return;
                    }
                    
//                    try {
//                        book.setCount(Integer.parseInt(count.getEditor().getText().trim()));
//                    } catch (Exception e) {
//                        System.out.println("Проблема с количеством");
//                        addBookInfo.getLabel().setForeground(Color.red);
//                        addBookInfo.getLabel().setText("Введите количество книг");
//                        return;
//                    }
                    book.setCount((int) addBookCount.getSpinner().getValue());
                    
                    BookFacade bookFacade = new BookFacade(Book.class);
                    try {
                        bookFacade.create(book);
                        addBookInfo.getLabel().setForeground(Color.green);
                        addBookInfo.getLabel().setText("Книга успешно добавлена");
                        addBookTitle.getEditor().setText("");
                        addBookAuthorsList.getList().clearSelection();
                        pubslishingYear.getEditor().setText("");
//                        count.getEditor().setText("");
                    } catch (Exception e) {
                        return;
                    }
                }
                });
            
        JPanel addAuthorPanel = new JPanel();
        tabs.addTab("Добавить автора", addAuthorPanel);
            addAuthorCaption = new LabelComponent(50, "Добавление автора", 18, 1);
            addAuthorPanel.add(addAuthorCaption);
            addAuthorInfo = new LabelComponent(30, "Информация о добавлении автора", 14, 0);
            addAuthorPanel.add(addAuthorInfo);
            authorName = new EditComponent(250, "Имя автора", 30);
            addAuthorPanel.add(authorName);
            authorSurename = new EditComponent(250, "Фамилия автора", 30);
            addAuthorPanel.add(authorSurename);
            authorBornYear = new EditComponent(250, "Год рождения", 30);
            addAuthorPanel.add(authorBornYear);
            addAuthorButton = new ButtonComponent("Добавить автора", 30, 150);
            addAuthorPanel.add(addAuthorButton);
            addAuthorButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Author author = new Author();
                
                if (authorName.getEditor().getText().trim().isEmpty()) {
                    System.out.println("Проблема с именем");
                    addAuthorInfo.getLabel().setForeground(Color.red);
                    addAuthorInfo.getLabel().setText("Введите имя автора");
                    return;
                }
                author.setName(authorName.getEditor().getText().trim());
                
                if (authorSurename.getEditor().getText().trim().isEmpty()) {
                    System.out.println("Проблема с фамилией");
                    addAuthorInfo.getLabel().setForeground(Color.red);
                    addAuthorInfo.getLabel().setText("Введите фамилию автора");
                    return;
                }
                author.setSurename(authorSurename.getEditor().getText().trim());
                
                try {
                    author.setBornYear(Integer.parseInt(authorBornYear.getEditor().getText().trim()));
                } catch (Exception e) {
                    System.out.println("Проблема с годом");
                    addAuthorInfo.getLabel().setForeground(Color.red);
                    addAuthorInfo.getLabel().setText("Введите год рождения автора");
                    return;
                }
                
                AuthorFacade authorFacade = new AuthorFacade(Author.class);
                try {
                    authorFacade.create(author);
                    addAuthorInfo.getLabel().setForeground(Color.green);
                    addAuthorInfo.getLabel().setText("Автор успешно добавлен");
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
            takeBookCaption = new LabelComponent(30, "Взять книгу", 18, 1);
            takeBookPanel.add(takeBookCaption);
            takeBookInfo = new LabelComponent(30, "Информация о взятой книги", 14, 0);
            takeBookPanel.add(takeBookInfo);
            takeBookBooksList = new ListBooksComponent(300, "Книги", 200);
            takeBookPanel.add(takeBookBooksList);
            takeBookReadersList = new ListReadersComponent(300, "Читатели", 200);
            takeBookPanel.add(takeBookReadersList);
            takeBookButton = new ButtonComponent("Взять книгу", 30, 150);
            takeBookPanel.add(takeBookButton);
            takeBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    History history = new History();
                    
                    if (takeBookBooksList.getList().isSelectionEmpty()) {
                        System.out.println("Не выбрана книга");
                        takeBookInfo.getLabel().setForeground(Color.red);
                        takeBookInfo.getLabel().setText("Выберите книгу");
                        return;
                    }
                    history.setBook(takeBookBooksList.getList().getSelectedValue());

                    if (takeBookReadersList.getList().isSelectionEmpty()) {
                        System.out.println("Не выбран читатель");
                        takeBookInfo.getLabel().setForeground(Color.red);
                        takeBookInfo.getLabel().setText("Выберите читателя");
                        return;
                    }
                    history.setReader(takeBookReadersList.getList().getSelectedValue());
                    
                    history.setIssueDate(localdateToDate(LocalDate.now()));
                    history.setReturnDate(localdateToDate(LocalDate.now().plusWeeks(2)));
                    
                    history.getBook().takeBook();
                    
                    HistoryFacade historyFacade = new HistoryFacade(History.class);
                    BookFacade bookFacade = new BookFacade(Book.class);
                    
                    try {
                        historyFacade.edit(history);
                        bookFacade.edit(history.getBook());
                        takeBookInfo.getLabel().setForeground(Color.green);
                        takeBookInfo.getLabel().setText("Информация о взятой книге успешно добавлена");
                        takeBookBooksList.getList().clearSelection();
                        takeBookReadersList.getList().clearSelection();
                    } catch (Exception e) {
                        System.out.println("Что-то пошло не так");
                        return;
                    }
                }
                });
            
        JPanel extendBookPanel = new JPanel();
        tabs.addTab("Продлить книгу", extendBookPanel);
            extendBookCaption = new LabelComponent(30, "Продлить срок даты сдачи книги", 18, 1);
            extendBookPanel.add(extendBookCaption);
            extendBookInfo = new LabelComponent(30, "Информация о продлении книги", 14, 0);
            extendBookPanel.add(extendBookInfo);
            extendBookList = new ListHistorysComponent(350, "Взятые книги", 150);
            extendBookPanel.add(extendBookList);
            
//            extendBookWeeks = new EditComponent(350, "Количество недель", 30);
//            extendBookPanel.add(extendBookWeeks);
            extendBookWeeks = new SpinnerComponent(30, "Количество недель", 100, 4);
            extendBookPanel.add(extendBookWeeks);
            
            extendBookButton = new ButtonComponent("Продлить книгу", 30, 200);
            extendBookPanel.add(extendBookButton);
            extendBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    History history;
                    int countWeeks;
                    
                    if (extendBookList.getList().isSelectionEmpty()) {
                        System.out.println("Не выбрана запись");
                        extendBookInfo.getLabel().setForeground(Color.red);
                        extendBookInfo.getLabel().setText("Выберите запись");
                        return;
                    }
                    history = extendBookList.getList().getSelectedValue();
                    
//                    try {
//                        countWeeks = Math.abs(Integer.parseInt(extendBookWeeks.getEditor().getText().trim()));
//                    } catch (Exception e) {
//                        System.out.println("Проблема с неделями");
//                        extendBookInfo.getLabel().setForeground(Color.red);
//                        extendBookInfo.getLabel().setText("Введите срок продления");
//                        return;
//                    }
                    countWeeks = (int) extendBookWeeks.getSpinner().getValue();

                    history.setReturnDate(localdateToDate(dateToLocaldate(history.getReturnDate()).plusWeeks(countWeeks)));
                    
                    HistoryFacade historyFacade = new HistoryFacade(History.class);
                    try {
                        historyFacade.edit(history);
                        extendBookInfo.getLabel().setForeground(Color.green);
                        extendBookInfo.getLabel().setText("Срок сдачи книги успешно продлен");
                        extendBookList.getList().clearSelection();
//                        extendBookWeeks.getEditor().setText("");
                    } catch (Exception e) {
                        System.out.println("Что-то пошло не так");
                    }
                }
                });
//</editor-fold>
            
        JPanel returnBookPanel = new JPanel();
        tabs.addTab("Вернуть книгу", returnBookPanel);
            returnBookCaption = new LabelComponent(30, "Вернуть книгу", 18, 1);
            returnBookPanel.add(returnBookCaption);
            returnBookInfo = new LabelComponent(30, "Информация о возвращении книги", 14, 0);
            returnBookPanel.add(returnBookInfo);
            returnBookBooksList = new ListBooksComponent(300, "Книги", 200);
            returnBookPanel.add(returnBookBooksList);
            returnBookButton = new ButtonComponent("Вернуть книгу", 30, 150);
            returnBookPanel.add(returnBookButton);
            returnBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    
                }
                });
            
//        JPanel changeBookPanel = new JPanel();
//        tabs.addTab("Изменить книгу", changeBookPanel);
//            //code...
//            
//        JPanel changeReaderPanel = new JPanel();
//        tabs.addTab("Изменить читателя", changeReaderPanel);
//            //code...
//            
//        JPanel changeAuthorPanel = new JPanel();
//        tabs.addTab("Изменить автора", changeAuthorPanel);
//            //code...
            
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
    
    private LocalDate dateToLocaldate(Date dateToConvert){
	return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
