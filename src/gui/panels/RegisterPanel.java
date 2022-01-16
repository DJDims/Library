
package gui.panels;

import entitys.Reader;
import entitys.Role;
import entitys.User;
import entitys.UserRoles;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class RegisterPanel extends JPanel{

    private LabelComponent registerCaption;
    private LabelComponent registerInfo;
    private EditComponent login;
    private EditComponent password;
    private EditComponent firstname;
    private EditComponent secondname;
    private EditComponent phone;
    private ButtonComponent registerButton;
    
    ReaderFacade readerFacade = new ReaderFacade();
    UserRolesFacade userRolesFacade = new UserRolesFacade();
    RoleFacade roleFacade = new RoleFacade();
    List<Role> roleArray = roleFacade.findAll();
    UserFacade userFacade = new UserFacade();
    List<User> usersArray = userFacade.findAll();
    
    public RegisterPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        registerCaption = new LabelComponent(30, "Регистрация", 18, 1);
        this.add(registerCaption);
        registerInfo = new LabelComponent(30, "Информация о регистрации", 14, 0);
        this.add(registerInfo);
        login = new EditComponent(250, "Логин", 30);
        this.add(login);
        password = new EditComponent(250, "Пароль", 30);
        this.add(password);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        firstname = new EditComponent(250, "Имя", 30);
        this.add(firstname);
        secondname = new EditComponent(250, "Фамилия", 30);
        this.add(secondname);
        phone = new EditComponent(250, "Телефон", 30);
        this.add(phone);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        registerButton = new ButtonComponent("Зарегистрироваться", 30, 180);
        this.add(registerButton);
        registerButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                User user = new User();
                Reader reader = new Reader();
                Role role = roleArray.get(2);
                UserRoles userRoles = new UserRoles();
                
                for (int i = 0; i < usersArray.size(); i++) {
                    if (login.getText().equals(usersArray.get(i).getLogin())) {
                        editLabel("Пользователь с таким логином уже существует", registerInfo, Color.red);
                        return;
                    }
                }
                if (login.getText().isEmpty()) {
                    editLabel("Введите логин", registerInfo, Color.red);
                    return;
                }
                if (password.getText().isEmpty()) {
                    editLabel("Введите пароль", registerInfo, Color.red);
                    return;
                }
                if (firstname.getText().isEmpty()) {
                    editLabel("Введите имя читателя", registerInfo, Color.red);
                    return;
                }
                if (secondname.getText().isEmpty()) {
                    editLabel("Введите фамилию читателя", registerInfo, Color.red);
                    return;
                }
                if (phone.getText().isEmpty()) {
                    editLabel("Введите телефон читателя", registerInfo, Color.red);
                    return;
                }
                
                user.setLogin(login.getText());
                user.setPassword(password.getText());
                user.setReader(reader);
                
                reader.setFirstname(firstname.getText());
                reader.setSurename(secondname.getText());
                reader.setPhoneNumber(phone.getText());
                
                userRoles.setUser(user);
                userRoles.setRole(role);
                
                try {
                    readerFacade.create(reader);
                    userFacade.create(user);
                    userRolesFacade.create(userRoles);
                    
                    login.clear();
                    password.clear();
                    firstname.clear();
                    secondname.clear();
                    phone.clear();
                    
                    editLabel("Пользователь успешно добавлен", registerInfo, Color.green);
                } catch (Exception e) {
                    editLabel("Что-то пошло не так", registerInfo, Color.red);
                }
                
            }
        });
        
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }
    
}
