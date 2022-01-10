
package gui.components.Lists;

import entitys.History;
import facade.HistoryFacade;
import gui.GuiApp;
import gui.components.renderers.ListHistorysCellRenderer;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class ListHistorysComponent extends JPanel{
    private JLabel title;
    private JList<History> list;
    
    public ListHistorysComponent(int widthList, int heightPanel) {
        initComponents(widthList, heightPanel);
    }

    private void initComponents(int widthList, int heightPanel) {
        this.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        title = new JLabel("Список историй");
        title.setPreferredSize(new Dimension(GuiApp.WINDOW_WIDTH/3, 25));
        title.setMinimumSize(title.getPreferredSize());
        title.setMaximumSize(title.getPreferredSize());
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setAlignmentY(TOP_ALIGNMENT);
        title.setFont(new Font("Tahoma", 0, 12));
        this.add(title);
        
        this.add(Box.createRigidArea(new Dimension(10,0)));
        
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(new ListHistorysCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthList, heightPanel));
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    private ListModel<History> getListModel() {
        HistoryFacade historyFacade = new HistoryFacade();
        List<History> historysArray = historyFacade.findAll();
        DefaultListModel<History> defaultListModel = new DefaultListModel<>();
        for (History historysArray1 : historysArray) {
            if (historysArray1.getReturnedDate() == null) {
                defaultListModel.addElement(historysArray1);
            }
        }
        return defaultListModel;
    }
    
    public JList<History> getList() {
        return list;
    }
}
