
package gui;

import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import gui.components.ListAuthorsComponent;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GuiApp extends JFrame{
    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 700;
    
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent title;
    private EditComponent count;
    private EditComponent pubslishingYear;
    private ListAuthorsComponent listAuthors;
    private ButtonComponent addButton;
    
    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        tabs.setMinimumSize(tabs.getPreferredSize());
        tabs.setMaximumSize(tabs.getPreferredSize());
        this.add(tabs);
        
        JPanel addReaderPanel = new JPanel();
        tabs.addTab("Добавить читателя", addReaderPanel);
            //code...
        
        JPanel addBookPanel = new JPanel();
        tabs.addTab("Добавить книгу", addBookPanel);
            captionComponent = new CaptionComponent(this.getWidth(), 50, "Добавление книги");
            addBookPanel.add(captionComponent);

            infoComponent = new InfoComponent(this.getWidth(), 30, "Информация о добавлении книги");
            addBookPanel.add(infoComponent);

            title = new EditComponent(250, "Название книги", this.getWidth(), 30);
            addBookPanel.add(title);

            listAuthors = new ListAuthorsComponent(250, "Список авторов", this.getWidth(), 120);
            addBookPanel.add(listAuthors);

            pubslishingYear = new EditComponent(250, "Год публикации", this.getWidth(), 30);
            addBookPanel.add(pubslishingYear);

            count = new EditComponent(250, "Количество книг", this.getWidth(), 30);
            addBookPanel.add(count);

            addButton = new ButtonComponent("Добавить книгу", WINDOW_WIDTH, 50, 200, 150);
            addBookPanel.add(addButton);
            
        JPanel addAuthorPanel = new JPanel();
        tabs.addTab("Добавить автора", addAuthorPanel);
            //code...
                
        JPanel takeBookPanel = new JPanel();
        tabs.addTab("Взять книгу", takeBookPanel);
            //code...
            
        JPanel extendBookPanel = new JPanel();
        tabs.addTab("Продлить книгу", extendBookPanel);
            //code...
            
        JPanel returnBookPanel = new JPanel();
        tabs.addTab("Вернуть книгу", returnBookPanel);
            //code...
            
        JPanel changeBookPanel = new JPanel();
        tabs.addTab("Изменить книгу", changeBookPanel);
            //code...
            
        JPanel changeReaderPanel = new JPanel();
        tabs.addTab("Изменить читателя", changeReaderPanel);
            //code...
            
        JPanel changeAuthorPanel = new JPanel();
        tabs.addTab("Изменить автора", changeAuthorPanel);
            //code...
            
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GuiApp().setVisible(true);
            }
        });
    }
}
