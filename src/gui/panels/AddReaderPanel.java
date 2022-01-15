
package gui.panels;

import entitys.Reader;
import facade.ReaderFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddReaderPanel extends JPanel{
    
    private LabelComponent addReaderCaption;
    private LabelComponent addReaderInfo;
    private EditComponent addReaderName;
    private EditComponent addReaderSurename;
    private EditComponent addReaderPhone;
    private ButtonComponent addReaderButton;
    
    public AddReaderPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        addReaderCaption = new LabelComponent(30, "Добавление читателя", 18, 1);
        this.add(addReaderCaption);
        addReaderInfo = new LabelComponent(30, "Информация о добавлении читателя", 14, 0);
        this.add(addReaderInfo);
        addReaderName = new EditComponent(200, "Имя читателя", 30);
        this.add(addReaderName);
        addReaderSurename = new EditComponent(200, "Фамилия читателя", 30);
        this.add(addReaderSurename);
        addReaderPhone = new EditComponent(200, "Телефон читателя", 30);
        this.add(addReaderPhone);
        addReaderButton = new ButtonComponent("Добавить читателя", 30, 150);
        this.add(addReaderButton);
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
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
}
