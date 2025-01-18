import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Extends JPanel so the tasks page is an actual panel
public class TasksPage extends JPanel {
    // Declaring parameters
    protected JLabel title;
    private JButton returnHome;
    protected JList<String> taskList;
    protected ArrayList<String> dailyTasks;
    protected DefaultListModel<String> taskListModel;

    public TasksPage(GUI mainGUI) {
        // setting the layout for the panel
        setLayout(null);

        // instantiating components
        title = new JLabel("Daily Task List");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(125, 15, 400, 30); 

        returnHome = new JButton("Home");
        returnHome.setBounds(150, 300, 100, 30); 

        dailyTasks = new ArrayList<>();

        // adding tasks to arraylist
        dailyTasks.add("Example Task 1");
        dailyTasks.add("Example Task 2");
        dailyTasks.add("Example Task 3");

        // create list mdoel and add tasks from araraylist
        taskListModel = new DefaultListModel<>();
        for (String task : dailyTasks) {
            taskListModel.addElement(task);
        }

        // jlist assumes the list model + is housed in a scrollpane
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(75, 60, 250, 200); 

        // adding components to the panel
        add(title);
        add(scrollPane);
        add(returnHome);

        // adding functionality to the return home button
        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.switchToPanel("homePage");
            }
        });
    }
}
