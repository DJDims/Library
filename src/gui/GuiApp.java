
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
import gui.components.EditComponent;
import gui.components.LabelComponent;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
//    private LabelComponent changeBookCaption;
//    private LabelComponent changeBookInfo;
    private LabelComponent changeReaderCaption;
    private LabelComponent changeReaderInfo;
    private LabelComponent changeAuthorCaption;
    private LabelComponent changeAuthorInfo;
    
    private EditComponent addReaderName;
    private EditComponent addReaderSurename;
    private EditComponent addReaderPhone;
    private EditComponent addBookTitle;
//    private EditComponent count;
    private EditComponent addBookPublishYear;
    private EditComponent addAuthorName;
    private EditComponent addAuthorSurename;
    private EditComponent addAuthorBornYear;
//    private EditComponent extendBookWeeks;
    private EditComponent changeReaderName;
    private EditComponent changeReaderSurname;
    private EditComponent changeReaderPhone;
    private EditComponent changeAuthorName;
    private EditComponent changeAuthorSurname;
    private EditComponent changeAuthorBornYear;
    
    private ListAuthorsComponent addBookAuthorsList;
    private ListBooksComponent takeBookBooksList;
    private ListReadersComponent takeBookReadersList;
    private ListHistorysComponent extendBookBooksList;
    private ListBooksComponent returnBookBooksList;
    
    private ButtonComponent addReaderButton;
    private ButtonComponent addBookButton;
    private ButtonComponent addAuthorButton;
    private ButtonComponent takeBookButton;
    private ButtonComponent extendBookButton;
    private ButtonComponent returnBookButton;
    private ButtonComponent changeReaderButton;
    private ButtonComponent changeAuthorButton;
    
    private SpinnerComponent addBookCount;
    private SpinnerComponent extendBookWeeks;
    
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
        
//<editor-fold>
        JPanel addReaderPanel = new JPanel();
        tabs.addTab("Добавить читателя", addReaderPanel);
            addReaderCaption = new LabelComponent(30, "Добавление читателя", 18, 1);
            addReaderPanel.add(addReaderCaption);
            addReaderInfo = new LabelComponent(30, "Информация о добавлении читателя", 14, 0);
            addReaderPanel.add(addReaderInfo);
            addReaderName = new EditComponent(250, "Имя читателя", 30);
            addReaderPanel.add(addReaderName);
            addReaderSurename = new EditComponent(250, "Фамилия читателя", 30);
            addReaderPanel.add(addReaderSurename);
            addReaderPhone = new EditComponent(250, "Телефон читателя", 30);
            addReaderPanel.add(addReaderPhone);
            addReaderButton = new ButtonComponent("Добавить читателя", 30, 150);
            addReaderPanel.add(addReaderButton);
            addReaderButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Reader reader = new Reader();
                    
                    if (addReaderName.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите имя читателя", addReaderInfo, Color.red);
                        return;
                    }
                    reader.setFirstname(addReaderName.getEditor().getText().trim());
                    
                    if (addReaderSurename.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию читателя", addReaderInfo, Color.red);
                        return;
                    }
                    reader.setSurename(addReaderSurename.getEditor().getText().trim());
                    
                    if (addReaderPhone.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите телефон читателя", addReaderInfo, Color.red);
                        return;
                    }
                    reader.setPhoneNumber(addReaderPhone.getEditor().getText().trim());
                    
                    ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                    try {
                        readerFacade.create(reader);
                        editLabel("Читатель успешно добавлен", addReaderInfo, Color.green);
                        addReaderName.getEditor().setText("");
                        addReaderSurename.getEditor().setText("");
                        addReaderPhone.getEditor().setText("");
                    } catch (Exception e) {
                    }
                }
            });
        
        JPanel addBookPanel = new JPanel();
        tabs.addTab("Добавить книгу", addBookPanel);
            addBookCaption = new LabelComponent(30, "Добавление книги", 18, 1);
            addBookPanel.add(addBookCaption);
            addBookInfo = new LabelComponent(30, "Информация о добавлении книги", 14, 0);
            addBookPanel.add(addBookInfo);
            addBookTitle = new EditComponent(250, "Название книги", 30);
            addBookPanel.add(addBookTitle);
            addBookAuthorsList = new ListAuthorsComponent(250, "Список авторов", 150);
            addBookPanel.add(addBookAuthorsList);
            addBookPublishYear = new EditComponent(250, "Год публикации", 30);
            addBookPanel.add(addBookPublishYear);
//------------------------------------------------------------------------------------------------------------------------------------
//            count = new EditComponent(250, "Количество книг", 30);
//            addBookPanel.add(count);
            addBookCount = new SpinnerComponent(30, "Количество книг", 100, 50);
            addBookPanel.add(addBookCount);
//------------------------------------------------------------------------------------------------------------------------------------
            addBookButton = new ButtonComponent("Добавить книгу", 30, 150);
            addBookPanel.add(addBookButton);
            addBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Book book = new Book();
                    
                    if (addBookTitle.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите название книги", addBookInfo, Color.red);
                        return;
                    }
                    book.setTitle(addBookTitle.getEditor().getText().trim());
                    
                    List<Author> bookAuthors = addBookAuthorsList.getList().getSelectedValuesList();
                    if (bookAuthors.isEmpty()) {
                        editLabel("Выберите автора(ов) книги", addBookInfo, Color.red);
                        return;
                    }
                    book.setAuthors(bookAuthors);
                    
                    if (addBookPublishYear.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите год публикации книги", addBookInfo, Color.red);
                        return;
                    }
                    
                    try {
                        book.setPublishYear(Integer.parseInt(addBookPublishYear.getEditor().getText().trim()));
                    } catch (Exception e) {
                        editLabel("Введите год публикации книги цифрами", addBookInfo, Color.red);
                        return;
                    }
                    
//                    try {
//                        book.setCount(Integer.parseInt(count.getEditor().getText().trim()));
//                    } catch (Exception e) {
//                        System.out.println("Проблема с количеством");
//                        editLabel("Введите количество книг", addBookInfo, Color.red);
//                        return;
//                    }
                    book.setCount((int) addBookCount.getSpinner().getValue());
                    
                    BookFacade bookFacade = new BookFacade(Book.class);
                    try {
                        bookFacade.create(book);
                        editLabel("Книга успешно добавлена", addBookInfo, Color.green);
                        addBookTitle.getEditor().setText("");
                        addBookAuthorsList.getList().clearSelection();
                        addBookPublishYear.getEditor().setText("");
//                        count.getEditor().setText("");
                    } catch (Exception e) {
                    }
                }
            });
            
        JPanel addAuthorPanel = new JPanel();
        tabs.addTab("Добавить автора", addAuthorPanel);
            addAuthorCaption = new LabelComponent(30, "Добавление автора", 18, 1);
            addAuthorPanel.add(addAuthorCaption);
            addAuthorInfo = new LabelComponent(30, "Информация о добавлении автора", 14, 0);
            addAuthorPanel.add(addAuthorInfo);
            addAuthorName = new EditComponent(250, "Имя автора", 30);
            addAuthorPanel.add(addAuthorName);
            addAuthorSurename = new EditComponent(250, "Фамилия автора", 30);
            addAuthorPanel.add(addAuthorSurename);
            addAuthorBornYear = new EditComponent(250, "Год рождения", 30);
            addAuthorPanel.add(addAuthorBornYear);
            addAuthorButton = new ButtonComponent("Добавить автора", 30, 150);
            addAuthorPanel.add(addAuthorButton);
            addAuthorButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Author author = new Author();

                    if (addAuthorName.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите имя автора", addAuthorInfo, Color.red);
                        return;
                    }
                    author.setName(addAuthorName.getEditor().getText().trim());

                    if (addAuthorSurename.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию автора", addAuthorInfo, Color.red);
                        return;
                    }
                    author.setSurename(addAuthorSurename.getEditor().getText().trim());

                    try {
                        author.setBornYear(Integer.parseInt(addAuthorBornYear.getEditor().getText().trim()));
                    } catch (Exception e) {
                        editLabel("Введите год рождения автора", addAuthorInfo, Color.red);
                        return;
                    }

                    AuthorFacade authorFacade = new AuthorFacade(Author.class);
                    try {
                        authorFacade.create(author);
                        editLabel("Автор успешно добавлен", addAuthorInfo, Color.green);
                        addAuthorName.getEditor().setText("");
                        addAuthorSurename.getEditor().setText("");
                        addAuthorBornYear.getEditor().setText("");
                    } catch (Exception e) {
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
                        editLabel("Выберите книгу", takeBookInfo, Color.red);
                        return;
                    }
                    history.setBook(takeBookBooksList.getList().getSelectedValue());

                    if (takeBookReadersList.getList().isSelectionEmpty()) {
                        editLabel("Выберите читателя", takeBookInfo, Color.red);
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
                        editLabel("Информация о взятой книге успешно добавлена", takeBookInfo, Color.green);
                        takeBookBooksList.getList().clearSelection();
                        takeBookReadersList.getList().clearSelection();
                    } catch (Exception e) {
                    }
                }
            });
            
        JPanel extendBookPanel = new JPanel();
        tabs.addTab("Продлить книгу", extendBookPanel);
            extendBookCaption = new LabelComponent(30, "Продлить срок даты сдачи книги", 18, 1);
            extendBookPanel.add(extendBookCaption);
            extendBookInfo = new LabelComponent(30, "Информация о продлении книги", 14, 0);
            extendBookPanel.add(extendBookInfo);
            extendBookBooksList = new ListHistorysComponent(350, "Взятые книги", 150);
            extendBookPanel.add(extendBookBooksList);
            
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
                    
                    if (extendBookBooksList.getList().isSelectionEmpty()) {
                        System.out.println("Не выбрана запись");
                        editLabel("Выберите запись", extendBookInfo, Color.red);
                        return;
                    }
                    history = extendBookBooksList.getList().getSelectedValue();
                    
//                    try {
//                        countWeeks = Math.abs(Integer.parseInt(extendBookWeeks.getEditor().getText().trim()));
//                    } catch (Exception e) {
//                        editLabel("Введите срок продления", extendBookInfo, Color.red);
//                        return;
//                    }
                    countWeeks = (int) extendBookWeeks.getSpinner().getValue();

                    history.setReturnDate(localdateToDate(dateToLocaldate(history.getReturnDate()).plusWeeks(countWeeks)));
                    
                    HistoryFacade historyFacade = new HistoryFacade(History.class);
                    try {
                        historyFacade.edit(history);
                        editLabel("Срок сдачи книги успешно продлен", extendBookInfo, Color.green);
                        extendBookBooksList.getList().clearSelection();
//                        extendBookWeeks.getEditor().setText("");
                    } catch (Exception e) {
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
        JPanel changeReaderPanel = new JPanel();
        tabs.addTab("Изменить читателя", changeReaderPanel);
            changeReaderCaption = new LabelComponent(30, "Изменить читателя", 18, 1);
            changeReaderPanel.add(changeReaderCaption);
            changeReaderInfo = new LabelComponent(30, "Данные о изменении читателя", 14, 0);
            changeReaderPanel.add(changeReaderInfo);
            changeReaderName = new EditComponent(100, "Имя", 30);
            changeReaderPanel.add(changeReaderName);
            changeReaderSurname = new EditComponent(100, "Фамилия", 30);
            changeReaderPanel.add(changeReaderSurname);
            changeReaderPhone = new EditComponent(100, "Телефон", 30);
            changeReaderPanel.add(changeReaderPhone);
            changeReaderButton = new ButtonComponent("Обновить данные", 30, 150);
            changeReaderPanel.add(changeReaderButton);
            changeReaderButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (changeReaderName.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите имя читателя", changeReaderInfo, Color.red);
                        return;
                    }
//                    reader.setFirstname(changeReaderName.getEditor().getText().trim());
                    
                    if (changeReaderSurname.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию читателя", changeReaderInfo, Color.red);
                        return;
                    }
//                    reader.setSurename(changeReaderSurname.getEditor().getText().trim());
                    
                    if (changeReaderPhone.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите телефон читателя", changeReaderInfo, Color.red);
                        return;
                    }
//                    reader.setPhoneNumber(changeReaderPhone.getEditor().getText().trim());
                    
                    ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                    try {
//                        readerFacade.edit(reader);
                        editLabel("Читатель успешно обновлен", changeReaderInfo, Color.green);
                        changeReaderName.getEditor().setText("");
                        changeReaderSurname.getEditor().setText("");
                        changeReaderPhone.getEditor().setText("");
                    } catch (Exception e) {
                    }
                }
            });
            
//            
        JPanel changeAuthorPanel = new JPanel();
        tabs.addTab("Изменить автора", changeAuthorPanel);
            changeAuthorCaption = new LabelComponent(30, "Изменить автора", 18, 1);
            changeAuthorPanel.add(changeAuthorCaption);
            changeAuthorInfo = new LabelComponent(30, "Данные о изменении автора", 14, 0);
            changeAuthorPanel.add(changeAuthorInfo);
            changeAuthorName = new EditComponent(100, "Имя", 30);
            changeAuthorPanel.add(changeAuthorName);
            changeAuthorSurname = new EditComponent(100, "Фамилия", 30);
            changeAuthorPanel.add(changeAuthorSurname);
            changeAuthorBornYear = new EditComponent(100, "Год рождения", 30);
            changeAuthorPanel.add(changeAuthorBornYear);
            changeAuthorButton = new ButtonComponent("Обновить данные", 30, 150);
            changeAuthorPanel.add(changeAuthorButton);
            changeAuthorButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (changeAuthorName.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите имя читателя", changeAuthorInfo, Color.red);
                        return;
                    }
//                    reader.setFirstname(changeAuthorName.getEditor().getText().trim());
                    
                    if (changeAuthorSurname.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию читателя", changeAuthorInfo, Color.red);
                        return;
                    }
//                    reader.setSurename(changeAuthorSurname.getEditor().getText().trim());
                    
                    if (changeAuthorBornYear.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите телефон читателя", changeAuthorInfo, Color.red);
                        return;
                    }
//                    reader.setPhoneNumber(changeAuthorBornYear.getEditor().getText().trim());
                    
                    ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                    try {
//                        readerFacade.edit(reader);
                        editLabel("Читатель успешно обновлен", changeAuthorInfo, Color.green);
                        changeAuthorName.getEditor().setText("");
                        changeAuthorSurname.getEditor().setText("");
                        changeAuthorBornYear.getEditor().setText("");
                    } catch (Exception e) {
                    }
                }
            });

        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                editLabel("Информация о добавлении читателя", addReaderInfo, Color.black);
                editLabel("Информация о добавлении книги", addBookInfo, Color.black);
                editLabel("Информация о добавлении автора", addAuthorInfo, Color.black);
                editLabel("Информация о взятой книги", takeBookInfo, Color.black);
                editLabel("Информация о продлении книги", extendBookInfo, Color.black);
                editLabel("Информация о возвращении книги", returnBookInfo, Color.black);
                editLabel("Информация о изменении читателя", changeReaderInfo, Color.black);
                editLabel("Информация о изменении автора", changeAuthorInfo, Color.black);
            }
        });
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
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
}
