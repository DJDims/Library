
package gui.panels;

import entitys.Reader;
import facade.ReaderFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import gui.components.Lists.ListReadersComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChangeReaderPanel extends JPanel{
    
    private LabelComponent changeReaderCaption;
    private LabelComponent changeReaderInfo;
    private EditComponent changeReaderName;
    private EditComponent changeReaderSurname;
    private EditComponent changeReaderPhone;
    private ButtonComponent changeReaderButton;
    private ListReadersComponent changeReaderReadersList;
    
    public ChangeReaderPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        changeReaderCaption = new LabelComponent(30, "Изменить читателя", 18, 1);
        this.add(changeReaderCaption);
        changeReaderInfo = new LabelComponent(30, "Данные о изменении читателя", 14, 0);
        this.add(changeReaderInfo);
        changeReaderReadersList = new ListReadersComponent(350, 100);
        this.add(changeReaderReadersList);
        changeReaderName = new EditComponent(350, "Имя", 30);
        this.add(changeReaderName);
        changeReaderSurname = new EditComponent(350, "Фамилия", 30);
        this.add(changeReaderSurname);
        changeReaderPhone = new EditComponent(350, "Телефон", 30);
        this.add(changeReaderPhone);
        changeReaderButton = new ButtonComponent("Обновить данные", 30, 150);
        this.add(changeReaderButton);
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
