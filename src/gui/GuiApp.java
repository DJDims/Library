
package gui;

import gui.components.CaptionComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import gui.components.ListAuthorsComponent;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class GuiApp extends JFrame{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent editComponent;
    private ListAuthorsComponent listAuthorsComponent;
    
    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(600, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        captionComponent = new CaptionComponent(this.getWidth(), 50, "Добавление книги");
        this.add(captionComponent);
        
        infoComponent = new InfoComponent(this.getWidth(), 30, "Информация о добавлении книги");
        this.add(infoComponent);
        
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        
        editComponent = new EditComponent(this.getWidth(), 30, 250, "Название книги");
        this.add(editComponent);
        
        listAuthorsComponent = new ListAuthorsComponent(this.getWidth(), 120, 250, "Список авторов:");
        this.add(listAuthorsComponent);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GuiApp().setVisible(true);
            }
        });
    }
}
