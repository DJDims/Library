
package gui.panels;

import entitys.Book;
import entitys.History;
import facade.BookFacade;
import facade.HistoryFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListHistorysComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReturnBookPanel extends JPanel{
    
    private LabelComponent returnBookCaption;
    private LabelComponent returnBookInfo;
    private ButtonComponent returnBookButton;
    private ListHistorysComponent extendBookHistorysList;
    
    public ReturnBookPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        returnBookCaption = new LabelComponent(30, "Вернуть книгу", 18, 1);
        this.add(returnBookCaption);
        returnBookInfo = new LabelComponent(30, "Информация о возвращении книги", 14, 0);
        this.add(returnBookInfo);
        extendBookHistorysList = new ListHistorysComponent(300, 200);
        this.add(extendBookHistorysList);
        returnBookButton = new ButtonComponent("Вернуть книгу", 30, 150);
        this.add(returnBookButton);
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
                    update();
                    extendBookHistorysList.getList().clearSelection();
                    editLabel("Книга успешно возвращена", returnBookInfo, Color.green);
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
