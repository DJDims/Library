
package gui.panels;

import entitys.Author;
import facade.AuthorFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import gui.components.lists.ListAuthorsComponent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChangeAuthorPanel extends JPanel{
    
    private LabelComponent changeAuthorCaption;
    private LabelComponent changeAuthorInfo;
    private EditComponent changeAuthorName;
    private EditComponent changeAuthorSurname;
    private EditComponent changeAuthorBornYear;
    private ButtonComponent changeAuthorButton;
    private ListAuthorsComponent changeAuthorAuthorsList;
    
    public ChangeAuthorPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        changeAuthorCaption = new LabelComponent(30, "Изменить автора", 18, 1);
        this.add(changeAuthorCaption);
        changeAuthorInfo = new LabelComponent(30, "Данные о изменении автора", 14, 0);
        this.add(changeAuthorInfo);
        changeAuthorAuthorsList = new ListAuthorsComponent(350, 100);
        this.add(changeAuthorAuthorsList);
        changeAuthorName = new EditComponent(350, "Имя", 30);
        this.add(changeAuthorName);
        changeAuthorSurname = new EditComponent(350, "Фамилия", 30);
        this.add(changeAuthorSurname);
        changeAuthorBornYear = new EditComponent(350, "Год рождения", 30);
        this.add(changeAuthorBornYear);
        changeAuthorButton = new ButtonComponent("Обновить данные", 30, 150);
        this.add(changeAuthorButton);
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

                if (changeAuthorName.getText().isEmpty()) {
                    editLabel("Введите имя автора", changeAuthorInfo, Color.red);
                    return;
                }
                if (changeAuthorSurname.getText().isEmpty()) {
                    editLabel("Введите фамилию автора", changeAuthorInfo, Color.red);
                    return;
                }
                if (changeAuthorBornYear.getText().isEmpty()) {
                    editLabel("Введите год рождения автора", changeAuthorInfo, Color.red);
                    return;
                }

                author.setName(changeAuthorName.getText().trim());
                author.setSurename(changeAuthorSurname.getText().trim());
                author.setBornYear(Integer.parseInt(changeAuthorBornYear.getText().trim()));

                AuthorFacade authorFacade = new AuthorFacade();
                try {
                    authorFacade.edit(author);
                    editLabel("Автор успешно обновлен", changeAuthorInfo, Color.green);
                    changeAuthorName.clear();
                    changeAuthorSurname.clear();
                    changeAuthorBornYear.clear();
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
