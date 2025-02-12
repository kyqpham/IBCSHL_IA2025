import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class PastTasksPage extends TasksPage {
    public DefaultListModel<String> pastListModel;
    public ArrayList<Task> pastTasks;
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

        if (pastTasks == null) {
            pastTasks = new ArrayList<Task>();
        }

        pastListModel = new DefaultListModel<>();
        for (Task x:pastTasks) {
            pastListModel.addElement(x.getName());
        }

        pastTaskList = new JList<>(pastListModel);
        JScrollPane scrollPane = new JScrollPane(pastTaskList);
        scrollPane.setBounds(75, 60, 250, 200); 

   
        // readding all the necessary components
        add(scrollPane);
    }

    public ArrayList<Task> getPastTasks() {
        return pastTasks;
    }

    
}


