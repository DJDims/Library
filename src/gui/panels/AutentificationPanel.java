
package gui.panels;

import entitys.User;
import facade.UserFacade;
import facade.UserRolesFacade;
import gui.GuiApp;
import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AutentificationPanel extends JPanel{
    
    private LabelComponent autentificationCaption;
    private LabelComponent аutentificationInfo;
    private EditComponent loginField;
    private EditComponent passwordField;
    private ButtonComponent loginButton;
    private ButtonComponent logoutButton;
    
    private UserFacade userFacade = new UserFacade();
    private UserRolesFacade userRolesFacade = new UserRolesFacade();
    
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
                
                if (loginField.getText().isEmpty()) {
                    editLabel("Введите имя пользователя", аutentificationInfo, Color.red);
                    return;
                }
                if (passwordField.getText().isEmpty()) {
                    editLabel("Введите пароль", аutentificationInfo, Color.red);
                    return;
                }
                if (userFacade.findByLogin(loginField.getText()) == null) {
                    editLabel("Пользователь с таким именем не зарегистрирован", аutentificationInfo, Color.red);
                    return;
                }
                user = userFacade.findByLogin(loginField.getText());
                
                if (!user.getPassword().equals(passwordField.getText())) {
                    editLabel("Неверный пароль", аutentificationInfo, Color.red);
                    return;
                }
                
                editLabel("Вы авторизовались", аutentificationInfo, Color.green);
                loginField.clear();
                passwordField.clear();
                loginButton.getButton().setEnabled(false);
                logoutButton.getButton().setEnabled(true);
                

                
                switch (userRolesFacade.getTopRole(user)) {
                    case "ADMINISTRATOR":
                        GuiApp.administratorTabs();
                        break;
                    case "MANAGER":
                        GuiApp.managerTabs();
                        break;
                    case "READER":
                        GuiApp.readerTabs();
                        break;
                }
            }
        });
        
        logoutButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.getButton().setEnabled(true);
                logoutButton.getButton().setEnabled(false);
                editLabel("Вы вышли из аккаунта", аutentificationInfo, Color.green);
                GuiApp.resetTabs();
            }
        });
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
}
