
package gui;

import entitys.Reader;
import entitys.Role;
import entitys.User;
import entitys.UserRoles;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
import gui.components.LabelComponent;
import gui.panels.AddAuthorPanel;
import gui.panels.AddBookPanel;
import gui.panels.AddReaderPanel;
import gui.panels.AutentificationPanel;
import gui.panels.ChangeAuthorPanel;
import gui.panels.ChangeBookPanel;
import gui.panels.ChangeReaderPanel;
import gui.panels.ExtendBookPanel;
import gui.panels.PermissionsPanel;
import gui.panels.RegisterPanel;
import gui.panels.ReturnBookPanel;
import gui.panels.ShowAuthors;
import gui.panels.ShowBooks;
import gui.panels.ShowReaders;
import gui.panels.TakeBookPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuiApp extends JFrame{
    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 700;
    
    private static JTabbedPane tabs;
    
    private static AutentificationPanel autentificationPanel;
    private static RegisterPanel registerPanel;
    private static PermissionsPanel permissionsPanel;
    private static AddAuthorPanel addAuthorPanel;
    private static AddBookPanel addBookPanel;
    private static AddReaderPanel addReaderPanel;
    private static ChangeAuthorPanel changeAuthorPanel;
    private static ChangeBookPanel changeBookPanel;
    private static ChangeReaderPanel changeReaderPanel;
    private static ExtendBookPanel extendBookPanel;
    private static ReturnBookPanel returnBookPanel;
    private static TakeBookPanel takeBookPanel;
    private static ShowAuthors showAuthors;
    private static ShowBooks showBooks;
    private static ShowReaders showReaders;
    
    private UserFacade userFacade = new UserFacade();
    private RoleFacade roleFacade = new RoleFacade();
    private UserRolesFacade userRolesFacade = new UserRolesFacade();
    private ReaderFacade readerFacade = new ReaderFacade();

    public GuiApp() {
        superAdmin();
        this.setTitle("Library");
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        tabs.setMinimumSize(tabs.getPreferredSize());
        tabs.setMaximumSize(tabs.getPreferredSize());
        this.add(tabs);
        
        helloTabs();
        
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
//                addBookPanel.update();
//                takeBookPanel.update();
//                extendBookPanel.update();
//                returnBookPanel.update();
//                changeBookPanel.update();
//                changeReaderPanel.update();
//                changeAuthorPanel.update();
            }
        });
    }
    
    public static void helloTabs(){
        autentificationPanel = new AutentificationPanel();
        tabs.addTab("???????? ?? ??????????????", autentificationPanel);
        registerPanel = new RegisterPanel();
        tabs.addTab("??????????????????????", registerPanel);
        showBooks = new ShowBooks();
        tabs.addTab("???????????????? ????????", showBooks);
        showAuthors = new ShowAuthors();
        tabs.addTab("???????????????? ??????????????", showAuthors);
    }
    
    public static void administratorTabs(){
        tabs.remove(3);
        tabs.remove(2);
        tabs.remove(1);
        
        permissionsPanel = new PermissionsPanel();
        tabs.addTab("???????????????????? ???????? ??????????????????????????", permissionsPanel);
        
    }
    
    public static void managerTabs(){
        tabs.remove(3);
        tabs.remove(2);
        tabs.remove(1);
        
        addBookPanel = new AddBookPanel();
        tabs.addTab("???????????????? ??????????", addBookPanel);
        addAuthorPanel = new AddAuthorPanel();
        tabs.addTab("???????????????? ????????????", addAuthorPanel);
        takeBookPanel = new TakeBookPanel();
        tabs.addTab("?????????? ??????????", takeBookPanel);
        extendBookPanel = new ExtendBookPanel();
        tabs.addTab("???????????????? ??????????", extendBookPanel);
        returnBookPanel = new ReturnBookPanel();
        tabs.addTab("?????????????? ??????????", returnBookPanel);
        changeBookPanel = new ChangeBookPanel();
        tabs.addTab("???????????????? ??????????", changeBookPanel);
        changeAuthorPanel = new ChangeAuthorPanel();
        tabs.addTab("???????????????? ????????????", changeAuthorPanel);
    }
    
    public static void readerTabs(){
        tabs.remove(3);
        tabs.remove(2);
        tabs.remove(1);
        
        takeBookPanel = new TakeBookPanel();
        tabs.addTab("?????????? ??????????", takeBookPanel);
        changeReaderPanel = new ChangeReaderPanel();
        tabs.addTab("???????????????? ????????????????", changeReaderPanel);
    }
    
    public static void resetTabs(){
        tabs.removeAll();
        helloTabs();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GuiApp().setVisible(true);
            }
        });
    }
    
    private void editLabel(String text, LabelComponent label, Color color){
        label.getLabel().setForeground(color);
        label.getLabel().setText(text);
    }

    private void superAdmin() {
        List<User> users = userFacade.findAll();
        if (!users.isEmpty()) {
            return;
        }
        Reader reader = new Reader();
        reader.setFirstname("Dmitrii");
        reader.setSurename("Kreivald");
        reader.setPhoneNumber("+37256871145");
        readerFacade.create(reader);
        
        User user = new User();
        user.setLogin("Admin");
        user.setPassword("qwerty");
        user.setReader(reader);
        userFacade.create(user);
        
        Role role = new Role();
        role.setRoleName("ADMINISTRATOR");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles();
        userRoles.setUser(user);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
        
        role = new Role();
        role.setRoleName("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
        
        role = new Role();
        role.setRoleName("READER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
    }
}
