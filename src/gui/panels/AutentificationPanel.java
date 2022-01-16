
package gui.panels;

import entitys.User;
import facade.UserFacade;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;

public class AutentificationPanel extends JPanel{
    
    private LabelComponent autentificationCaption;
    private LabelComponent аutentificationInfo;
    private EditComponent loginField;
    private EditComponent passwordField;
    private ButtonComponent loginButton;
    private ButtonComponent logoutButton;
    
    private UserFacade userFacade = new UserFacade();
    
    public AutentificationPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        autentificationCaption = new LabelComponent(30, "Авторизация", 18, 1);
        this.add(autentificationCaption);
        аutentificationInfo = new LabelComponent(30, "Информация о авторизации", 14, 0);
        this.add(аutentificationInfo);
        loginField = new EditComponent(250, "Логин", 30);
        this.add(loginField);
        passwordField = new EditComponent(250, "Пароль", 30);
        this.add(passwordField);
        loginButton = new ButtonComponent("Войти в аккаунт", 30, 150);
        this.add(loginButton);
        logoutButton = new ButtonComponent("Выйти из аккаунта", 30, 150);
        logoutButton.getButton().setEnabled(false);
        this.add(logoutButton);
        loginButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                User user = new User();
                List<User> usersArray = userFacade.findAll();
                
                if (loginField.getText().isEmpty()) {
                    editLabel("Введите имя пользователя", аutentificationInfo, Color.red);
                    return;
                }
                if (passwordField.getText().isEmpty()) {
                    editLabel("Введите пароль", аutentificationInfo, Color.red);
                    return;
                }
                for (int i = 0; i < usersArray.size(); i++) {
                    if (usersArray.get(i).getLogin().equals(loginField.getText())) {
                        user = usersArray.get(i);
                        break;
                    }
                }
                if (user.getLogin() == null) {
                    editLabel("Пользователь с таким именем не зарегистрирован", аutentificationInfo, Color.red);
                    return;
                }
                if (!user.getPassword().equals(passwordField.getText())) {
                    editLabel("Неверный пароль", аutentificationInfo, Color.red);
                    return;
                }
                
            }
        });
    }
    
    public void update(){
        this.removeAll();
        initComponents();
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
}
