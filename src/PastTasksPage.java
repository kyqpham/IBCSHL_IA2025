import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class PastTasksPage extends TasksPage {
    public DefaultListModel<String> pastListModel;
    public ArrayList<String> pastTasks;
    public JList<String> pastTaskList;


    public PastTasksPage(GUI mainGUI) {
        // adding necessary components
        super(mainGUI, null);
        title.setText("Past Tasks List");

        // removing unnecessary components
        remove(createNewListButton);
        remove(editListButton);
        remove(taskList);
        remove(scrollPane);

        pastTasks = new ArrayList<String>();
        pastTasks.add("example");

        pastListModel = new DefaultListModel<>();
        for (String x:pastTasks) {
            pastListModel.addElement(x);
        }

        pastTaskList = new JList<>(pastListModel);
        JScrollPane scrollPane = new JScrollPane(pastTaskList);
        scrollPane.setBounds(75, 60, 250, 200); 

   
        // readding all the necessary components
        add(scrollPane);
    }

    public ArrayList<String> getPastTasks() {
        return pastTasks;
    }

    
}


