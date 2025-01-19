import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// Extends JPanel so the tasks page is an actual panel
public class TasksPage extends JPanel {
    // declaring parameters for the panel itself
    protected JLabel title;
    private JButton returnHome;
    private JList<String> taskList;
    private ArrayList<String> dailyTasks;
    private DefaultListModel<String> taskListModel;

    // adding the elements for a popup for user selection of editing the task list
    private JPanel editPanel;
    private JPopupMenu editOptions;
    private JButton deleteTaskButton;
    private JButton editTaskButton;
    private JButton addTaskButton;

    // these should not be inherited by the child class; remove them from child class
    protected JButton createNewListButton;
    protected JButton editListButton;

    public TasksPage(GUI mainGUI) {
        // setting the layout for the panel
        setLayout(null);

        // instantiating components
        title = new JLabel("Daily Task List");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(125, 15, 400, 30); 

        returnHome = new JButton("Home");
        returnHome.setBounds(150, 325, 100, 30); 

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

        // declaring the buttons + setting bounds
        editListButton = new JButton ("Edit Task List");
        createNewListButton = new JButton("Create New");

        editListButton.setBounds(75, 270, 120, 30);
        createNewListButton.setBounds(200, 270, 120, 30);
        
        // declaring + instantiating the popup menu and its options
        //necessary panel for positioning of the editing buttons
        editPanel = new JPanel();

        editOptions = new JPopupMenu("Edit Options");
        deleteTaskButton = new JButton("Delete Task");
        editTaskButton = new JButton("Edit Task");
        addTaskButton = new JButton("Add Task");

        editPanel.setLayout(null);
        editPanel.setPreferredSize(new Dimension(280, 180));

        deleteTaskButton.setBounds(75, 20, 130, 30); 
        editTaskButton.setBounds(75, 70, 130, 30);
        addTaskButton.setBounds(75, 120, 130, 30);

        editPanel.add(deleteTaskButton);
        editPanel.add(editTaskButton);
        editPanel.add(addTaskButton);

        editOptions.add(editPanel);

        // adding components to the panel
        add(title);
        add(scrollPane);
        add(returnHome);
        add(editListButton);
        add(createNewListButton);
        add(editOptions);


        // adding functionality to the return home button
        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.switchToPanel("homePage");
            }
        });

        editListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOptions.show(editListButton, editListButton.getWidth() / 2, -125);
            }
        });
    }
}
