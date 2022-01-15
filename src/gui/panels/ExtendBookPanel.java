
package gui.panels;

import entitys.History;
import facade.HistoryFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListHistorysComponent;
import gui.components.SpinnerComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ExtendBookPanel extends JPanel{
    
    private LabelComponent extendBookCaption;
    private LabelComponent extendBookInfo;
    private ButtonComponent extendBookButton;
    private ListHistorysComponent extendBookBooksList;
    private SpinnerComponent extendBookWeeks;
    
    public ExtendBookPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        extendBookCaption = new LabelComponent(30, "Продлить срок даты сдачи книги", 18, 1);
        this.add(extendBookCaption);
        extendBookInfo = new LabelComponent(30, "Информация о продлении книги", 14, 0);
        this.add(extendBookInfo);
        extendBookBooksList = new ListHistorysComponent(350, 150);
        this.add(extendBookBooksList);

        extendBookWeeks = new SpinnerComponent(30, "Количество недель", 350, 4);
        this.add(extendBookWeeks);

        extendBookButton = new ButtonComponent("Продлить книгу", 30, 200);
        this.add(extendBookButton);
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
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
    private Date localdateToDate(LocalDate dateToConvert){
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    private LocalDate dateToLocaldate(Date dateToConvert){
	return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public void update(){
        this.removeAll();
        initComponents();
    }
    
}
