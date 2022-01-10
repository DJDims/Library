
package gui;

import entitys.Author;
import entitys.Book;
import entitys.History;
import entitys.Reader;
import entitys.Role;
import entitys.User;
import entitys.UserRoles;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
import gui.components.ButtonComponent;
import gui.components.DoubleListComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListAuthorsComponent;
import gui.components.Lists.ListBooksComponent;
import gui.components.Lists.ListHistorysComponent;
import gui.components.Lists.ListReadersComponent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private LabelComponent changeBookCaption;
    private LabelComponent changeBookInfo;
    private LabelComponent changeReaderCaption;
    private LabelComponent changeReaderInfo;
    private LabelComponent changeAuthorCaption;
    private LabelComponent changeAuthorInfo;
    
    private EditComponent addReaderName;
    private EditComponent addReaderSurename;
    private EditComponent addReaderPhone;
    private EditComponent addBookTitle;
    private EditComponent addBookPublishYear;
    private EditComponent addAuthorName;
    private EditComponent addAuthorSurename;
    private EditComponent addAuthorBornYear;
    private EditComponent changeReaderName;
    private EditComponent changeReaderSurname;
    private EditComponent changeReaderPhone;
    private EditComponent changeAuthorName;
    private EditComponent changeAuthorSurname;
    private EditComponent changeAuthorBornYear;
    
    private ButtonComponent addReaderButton;
    private ButtonComponent addBookButton;
    private ButtonComponent addAuthorButton;
    private ButtonComponent takeBookButton;
    private ButtonComponent extendBookButton;
    private ButtonComponent returnBookButton;
    private ButtonComponent changeReaderButton;
    private ButtonComponent changeAuthorButton;
    
    private ListAuthorsComponent addBookAuthorsList;
    private ListAuthorsComponent changeAuthorAuthorsList;
    private ListBooksComponent takeBookBooksList;
    private ListBooksComponent changeBookBooksList;
    private ListReadersComponent takeBookReadersList;
    private ListReadersComponent changeReaderReadersList;
    private ListHistorysComponent extendBookBooksList;
    private ListHistorysComponent extendBookHistorysList;
    
    
    private SpinnerComponent addBookCount;
    private SpinnerComponent extendBookWeeks;
    
    private UserFacade userFacade = new UserFacade();
    private RoleFacade roleFacade = new RoleFacade();
    private UserRolesFacade userRolesFacade = new UserRolesFacade();
    private ReaderFacade readerFacade = new ReaderFacade();
    
//------------------------------------------------------------------------------------------------------------------------------------
    private DoubleListComponent doubleListComponent;
//------------------------------------------------------------------------------------------------------------------------------------
    
    public GuiApp() {
        superAdmin();
        this.setTitle("Library");
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
        
        JPanel login = new JPanel();
        tabs.addTab("Вход", login);
        
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
                    if (addReaderSurename.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию читателя", addReaderInfo, Color.red);
                        return;
                    }
                    if (addReaderPhone.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите телефон читателя", addReaderInfo, Color.red);
                        return;
                    }
                    
                    reader.setFirstname(addReaderName.getEditor().getText().trim());
                    reader.setSurename(addReaderSurename.getEditor().getText().trim());
                    reader.setPhoneNumber(addReaderPhone.getEditor().getText().trim());
                    
                    ReaderFacade readerFacade = new ReaderFacade();
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
            addBookAuthorsList = new ListAuthorsComponent(250, 150);
            addBookPanel.add(addBookAuthorsList);
            addBookPublishYear = new EditComponent(250, "Год публикации", 30);
            addBookPanel.add(addBookPublishYear);
            addBookCount = new SpinnerComponent(30, "Количество книг", 250, 50);
            addBookPanel.add(addBookCount);
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
                    List<Author> bookAuthors = addBookAuthorsList.getList().getSelectedValuesList();
                    if (bookAuthors.isEmpty()) {
                        editLabel("Выберите автора(ов) книги", addBookInfo, Color.red);
                        return;
                    }
                    if (addBookPublishYear.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите год публикации книги", addBookInfo, Color.red);
                        return;
                    }
                    
                    book.setTitle(addBookTitle.getEditor().getText().trim());
                    book.setAuthors(bookAuthors);
                    
                    try {
                        book.setPublishYear(Integer.parseInt(addBookPublishYear.getEditor().getText().trim()));
                    } catch (Exception e) {
                        editLabel("Введите год публикации книги цифрами", addBookInfo, Color.red);
                        return;
                    }

                    book.setQuantity((int) addBookCount.getSpinner().getValue());
                    book.setCount(book.getQuantity());
                    
                    BookFacade bookFacade = new BookFacade();
                    try {
                        bookFacade.create(book);
                        editLabel("Книга успешно добавлена", addBookInfo, Color.green);
                        addBookTitle.getEditor().setText("");
                        addBookAuthorsList.getList().clearSelection();
                        addBookPublishYear.getEditor().setText("");
                        addBookCount.getSpinner().setValue(1);
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
                    if (addAuthorSurename.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию автора", addAuthorInfo, Color.red);
                        return;
                    }
                    author.setName(addAuthorName.getEditor().getText().trim());
                    author.setSurename(addAuthorSurename.getEditor().getText().trim());

                    try {
                        author.setBornYear(Integer.parseInt(addAuthorBornYear.getEditor().getText().trim()));
                    } catch (Exception e) {
                        editLabel("Введите год рождения автора", addAuthorInfo, Color.red);
                        return;
                    }

                    AuthorFacade authorFacade = new AuthorFacade();
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
            takeBookBooksList = new ListBooksComponent(300, 200);
            takeBookPanel.add(takeBookBooksList);
            takeBookReadersList = new ListReadersComponent(300, 200);
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
                    if (takeBookReadersList.getList().isSelectionEmpty()) {
                        editLabel("Выберите читателя", takeBookInfo, Color.red);
                        return;
                    }
                    history.setBook(takeBookBooksList.getList().getSelectedValue());
                    history.setReader(takeBookReadersList.getList().getSelectedValue());
                    
                    history.setIssueDate(localdateToDate(LocalDate.now()));
                    history.setReturnDate(localdateToDate(LocalDate.now().plusWeeks(2)));
                    
                    if (history.getBook().getCount() == 0) {
                        editLabel("Экземпляры книги закончились", takeBookInfo, Color.red);
                        return;
                    }
                    history.getBook().takeBook();
                    
                    HistoryFacade historyFacade = new HistoryFacade();
                    BookFacade bookFacade = new BookFacade();
                    
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
            extendBookBooksList = new ListHistorysComponent(350, 150);
            extendBookPanel.add(extendBookBooksList);
            
            extendBookWeeks = new SpinnerComponent(30, "Количество недель", 350, 4);
            extendBookPanel.add(extendBookWeeks);
            
            extendBookButton = new ButtonComponent("Продлить книгу", 30, 200);
            extendBookPanel.add(extendBookButton);
            extendBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    History history;
                    
                    if (extendBookBooksList.getList().isSelectionEmpty()) {
                        editLabel("Выберите запись", extendBookInfo, Color.red);
                        return;
                    }
                    history = extendBookBooksList.getList().getSelectedValue();

                    int countWeeks = (int) extendBookWeeks.getSpinner().getValue();

                    history.setReturnDate(localdateToDate(dateToLocaldate(history.getReturnDate()).plusWeeks(countWeeks)));
                    
                    HistoryFacade historyFacade = new HistoryFacade();
                    try {
                        historyFacade.edit(history);
                        editLabel("Срок сдачи книги успешно продлен", extendBookInfo, Color.green);
                        extendBookBooksList.getList().clearSelection();
                    } catch (Exception e) {
                    }
                }
            });
            
        JPanel returnBookPanel = new JPanel();
        tabs.addTab("Вернуть книгу", returnBookPanel);
            returnBookCaption = new LabelComponent(30, "Вернуть книгу", 18, 1);
            returnBookPanel.add(returnBookCaption);
            returnBookInfo = new LabelComponent(30, "Информация о возвращении книги", 14, 0);
            returnBookPanel.add(returnBookInfo);
            extendBookHistorysList = new ListHistorysComponent(300, 200);
            returnBookPanel.add(extendBookHistorysList);
            returnBookButton = new ButtonComponent("Вернуть книгу", 30, 150);
            returnBookPanel.add(returnBookButton);
            returnBookButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (extendBookHistorysList.getList().isSelectionEmpty()) {
                        editLabel("Выберите запись для возвращения", returnBookInfo, Color.red);
                        return;
                    }
                    History history = extendBookHistorysList.getList().getSelectedValue();
                    history.setReturnedDate(localdateToDate(LocalDate.now()));
                    Book book = history.getBook();
                    book.returnBook();
                    
                    HistoryFacade historyFacade = new HistoryFacade();
                    BookFacade bookFacade = new BookFacade();
                    try {
                        historyFacade.edit(history);
                        bookFacade.edit(book);
//                        extendBookHistorysList.getList().;
                        extendBookHistorysList.getList().clearSelection();
                        editLabel("Книга успешно возвращена", returnBookInfo, Color.green);
                    } catch (Exception e) {
                    }
                }
            });
            
        JPanel changeBookPanel = new JPanel();
        tabs.addTab("Изменить книгу", changeBookPanel);
            changeBookCaption = new LabelComponent(30, "Изменение книги", 18, 1);
            changeBookPanel.add(changeBookCaption);
            changeBookInfo = new LabelComponent(30, "Данные об изменении книги", 14, 0);
            changeBookPanel.add(changeBookInfo);
            changeBookBooksList = new ListBooksComponent(350, 100);
            changeBookPanel.add(changeBookBooksList);
//------------------------------------------------------------------------------------------------------------------------------------
            doubleListComponent = new DoubleListComponent();
            changeBookPanel.add(doubleListComponent);
//------------------------------------------------------------------------------------------------------------------------------------

        JPanel changeReaderPanel = new JPanel();
        tabs.addTab("Изменить читателя", changeReaderPanel);
            changeReaderCaption = new LabelComponent(30, "Изменить читателя", 18, 1);
            changeReaderPanel.add(changeReaderCaption);
            changeReaderInfo = new LabelComponent(30, "Данные о изменении читателя", 14, 0);
            changeReaderPanel.add(changeReaderInfo);
            changeReaderReadersList = new ListReadersComponent(350, 100);
            changeReaderPanel.add(changeReaderReadersList);
            changeReaderName = new EditComponent(350, "Имя", 30);
            changeReaderPanel.add(changeReaderName);
            changeReaderSurname = new EditComponent(350, "Фамилия", 30);
            changeReaderPanel.add(changeReaderSurname);
            changeReaderPhone = new EditComponent(350, "Телефон", 30);
            changeReaderPanel.add(changeReaderPhone);
            changeReaderButton = new ButtonComponent("Обновить данные", 30, 150);
            changeReaderPanel.add(changeReaderButton);
            changeReaderReadersList.getList().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    Reader reader = (Reader) changeReaderReadersList.getList().getSelectedValue();
                    changeReaderName.getEditor().setText(reader.getFirstname());
                    changeReaderSurname.getEditor().setText(reader.getSurename());
                    changeReaderPhone.getEditor().setText(reader.getPhoneNumber());
                }
            });
            changeReaderButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Reader reader = (Reader) changeReaderReadersList.getList().getSelectedValue();
                    
                    if (changeReaderName.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите имя читателя", changeReaderInfo, Color.red);
                        return;
                    }
                    if (changeReaderSurname.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию читателя", changeReaderInfo, Color.red);
                        return;
                    }
                    if (changeReaderPhone.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите телефон читателя", changeReaderInfo, Color.red);
                        return;
                    }
                    
                    reader.setFirstname(changeReaderName.getEditor().getText().trim());
                    reader.setSurename(changeReaderSurname.getEditor().getText().trim());
                    reader.setPhoneNumber(changeReaderPhone.getEditor().getText().trim());
                    
                    ReaderFacade readerFacade = new ReaderFacade();
                    try {
                        readerFacade.edit(reader);
                        editLabel("Читатель успешно обновлен", changeReaderInfo, Color.green);
                        changeReaderName.getEditor().setText("");
                        changeReaderSurname.getEditor().setText("");
                        changeReaderPhone.getEditor().setText("");
                    } catch (Exception e) {
                    }
                }
            });
            
        JPanel changeAuthorPanel = new JPanel();
        tabs.addTab("Изменить автора", changeAuthorPanel);
            changeAuthorCaption = new LabelComponent(30, "Изменить автора", 18, 1);
            changeAuthorPanel.add(changeAuthorCaption);
            changeAuthorInfo = new LabelComponent(30, "Данные о изменении автора", 14, 0);
            changeAuthorPanel.add(changeAuthorInfo);
            changeAuthorAuthorsList = new ListAuthorsComponent(350, 100);
            changeAuthorPanel.add(changeAuthorAuthorsList);
            changeAuthorName = new EditComponent(350, "Имя", 30);
            changeAuthorPanel.add(changeAuthorName);
            changeAuthorSurname = new EditComponent(350, "Фамилия", 30);
            changeAuthorPanel.add(changeAuthorSurname);
            changeAuthorBornYear = new EditComponent(350, "Год рождения", 30);
            changeAuthorPanel.add(changeAuthorBornYear);
            changeAuthorButton = new ButtonComponent("Обновить данные", 30, 150);
            changeAuthorPanel.add(changeAuthorButton);
            changeAuthorAuthorsList.getList().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    Author author = (Author) changeAuthorAuthorsList.getList().getSelectedValue();
                    changeAuthorName.getEditor().setText(author.getName());
                    changeAuthorSurname.getEditor().setText(author.getSurename());
                    changeAuthorBornYear.getEditor().setText(Integer.toString(author.getBornYear()));
                }
            });
            changeAuthorButton.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Author author = (Author) changeAuthorAuthorsList.getList().getSelectedValue();
                    
                    if (changeAuthorName.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите имя автора", changeAuthorInfo, Color.red);
                        return;
                    }
                    if (changeAuthorSurname.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите фамилию автора", changeAuthorInfo, Color.red);
                        return;
                    }
                    if (changeAuthorBornYear.getEditor().getText().trim().isEmpty()) {
                        editLabel("Введите год рождения автора", changeAuthorInfo, Color.red);
                        return;
                    }
                    
                    author.setName(changeAuthorName.getEditor().getText().trim());
                    author.setSurename(changeAuthorSurname.getEditor().getText().trim());
                    author.setBornYear(Integer.parseInt(changeAuthorBornYear.getEditor().getText().trim()));
                    
                    AuthorFacade authorFacade = new AuthorFacade();
                    try {
                        authorFacade.edit(author);
                        editLabel("Автор успешно обновлен", changeAuthorInfo, Color.green);
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
                editLabel("Информация о изменении книги", changeBookInfo, Color.black);
                editLabel("Информация о изменении читателя", changeAuthorInfo, Color.black);
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

    private void superAdmin() {
        List<User> users = userFacade.findAll();
        if (!users.isEmpty()) {
            return;
        }
        Reader reader = new Reader();
        reader.setFirstname("Dmitrii");
        reader.setSurename("Kreivald");
        reader.setPhoneNumber("+37256871145");
        readerFacade.create(reader);
        
        User user = new User();
        user.setLogin("Admin");
        user.setPassword("qwerty");
        user.setReader(reader);
        userFacade.create(user);
        
        Role role = new Role();
        role.setRoleName("ADMINISTRATOR");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles();
        userRoles.setUser(user);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
        
        role = new Role();
        role.setRoleName("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
        
        role = new Role();
        role.setRoleName("READER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
    }
}
