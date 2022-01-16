
package gui.panels;

import entitys.Author;
import facade.AuthorFacade;
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

public class AddAuthorPanel extends JPanel{
    
    private LabelComponent addAuthorCaption;
    private LabelComponent addAuthorInfo;
    private EditComponent addAuthorName;
    private EditComponent addAuthorSurename;
    private EditComponent addAuthorBornYear;
    private ButtonComponent addAuthorButton;
    
    public AddAuthorPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        addAuthorCaption = new LabelComponent(30, "Добавление автора", 18, 1);
        this.add(addAuthorCaption);
        addAuthorInfo = new LabelComponent(30, "Информация о добавлении автора", 14, 0);
        this.add(addAuthorInfo);
        addAuthorName = new EditComponent(250, "Имя автора", 30);
        this.add(addAuthorName);
        addAuthorSurename = new EditComponent(250, "Фамилия автора", 30);
        this.add(addAuthorSurename);
        addAuthorBornYear = new EditComponent(250, "Год рождения", 30);
        this.add(addAuthorBornYear);
        addAuthorButton = new ButtonComponent("Добавить автора", 30, 150);
        this.add(addAuthorButton);
        addAuthorButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Author author = new Author();

                if (addAuthorName.getText().isEmpty()) {
                    editLabel("Введите имя автора", addAuthorInfo, Color.red);
                    return;
                }
                if (addAuthorSurename.getText().isEmpty()) {
                    editLabel("Введите фамилию автора", addAuthorInfo, Color.red);
                    return;
                }
                author.setName(addAuthorName.getText());
                author.setSurename(addAuthorSurename.getText());

                try {
                    author.setBornYear(Integer.parseInt(addAuthorBornYear.getText()));
                } catch (Exception e) {
                    editLabel("Введите год рождения автора", addAuthorInfo, Color.red);
                    return;
                }

                AuthorFacade authorFacade = new AuthorFacade();
                try {
                    authorFacade.create(author);
                    editLabel("Автор успешно добавлен", addAuthorInfo, Color.green);
                    addAuthorName.clear();
                    addAuthorSurename.clear();
                    addAuthorBornYear.clear();
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
