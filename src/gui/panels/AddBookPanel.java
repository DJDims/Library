
package gui.panels;

import entitys.Author;
import entitys.Book;
import facade.BookFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListAuthorsComponent;
import gui.components.SpinnerComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddBookPanel extends JPanel{
    
    private LabelComponent addBookCaption;
    private LabelComponent addBookInfo;
    private EditComponent addBookTitle;
    private EditComponent addBookPublishYear;
    private ButtonComponent addBookButton;
    private ListAuthorsComponent addBookAuthorsList;
    private SpinnerComponent addBookCount;
    
    public AddBookPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        addBookCaption = new LabelComponent(30, "Добавление книги", 18, 1);
        this.add(addBookCaption);
        addBookInfo = new LabelComponent(30, "Информация о добавлении книги", 14, 0);
        this.add(addBookInfo);
        addBookTitle = new EditComponent(250, "Название книги", 30);
        this.add(addBookTitle);
        addBookAuthorsList = new ListAuthorsComponent(250, 150);
        this.add(addBookAuthorsList);
        addBookPublishYear = new EditComponent(250, "Год публикации", 30);
        this.add(addBookPublishYear);
        addBookCount = new SpinnerComponent(30, "Количество книг", 250, 50);
        this.add(addBookCount);
        addBookButton = new ButtonComponent("Добавить книгу", 30, 150);
        this.add(addBookButton);
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
