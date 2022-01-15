
package gui.panels;

import static gui.GuiApp.WINDOW_HEIGHT;
import static gui.GuiApp.WINDOW_WIDTH;
import gui.components.ButtonComponent;
import gui.components.EditComponent;
import gui.components.LabelComponent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class AutentificationPanel extends JPanel{
    
    private LabelComponent autentificationCaption;
    private LabelComponent аutentificationInfo;
    private EditComponent loginField;
    private EditComponent passwordField;
    private ButtonComponent loginButton;
    private ButtonComponent logoutButton;
    
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
    }
    
    public void update(){
        this.removeAll();
        initComponents();
    }
    
}
