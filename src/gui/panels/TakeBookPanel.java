
package gui.panels;

import entitys.History;
import facade.BookFacade;
import facade.HistoryFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListBooksComponent;
import gui.components.Lists.ListReadersComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TakeBookPanel extends JPanel{
    
    private LabelComponent takeBookCaption;
    private LabelComponent takeBookInfo;
    private ButtonComponent takeBookButton;
    private ListBooksComponent takeBookBooksList;
    private ListReadersComponent takeBookReadersList;
    
    public TakeBookPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        takeBookCaption = new LabelComponent(30, "Взять книгу", 18, 1);
        this.add(takeBookCaption);
        takeBookInfo = new LabelComponent(30, "Информация о взятой книги", 14, 0);
        this.add(takeBookInfo);
        takeBookBooksList = new ListBooksComponent(300, 200);
        this.add(takeBookBooksList);
        takeBookReadersList = new ListReadersComponent(300, 200);
        this.add(takeBookReadersList);
        takeBookButton = new ButtonComponent("Взять книгу", 30, 150);
        this.add(takeBookButton);
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
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
    private Date localdateToDate(LocalDate dateToConvert){
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public void update(){
        this.removeAll();
        initComponents();
    }
        
}
