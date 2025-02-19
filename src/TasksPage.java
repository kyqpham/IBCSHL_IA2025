import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// Extends JPanel so the tasks page is an actual panel
public class TasksPage extends JPanel {
    // declaring parameters for the panel itself
    protected JLabel title;
    private JButton returnHome;

    protected JList<String> taskList;
    public ArrayList<Task> dailyTasks;
    protected DefaultListModel<String> taskListModel;

    // adding the elements for a popup for user selection of editing the task list
    private JPanel editPanel;
    private JPopupMenu editOptions;
    private JButton deleteTaskButton;
    private JButton editTaskButton;
    private JButton addTaskButton;

    // these should not be inherited by the child class; remove them from child class
    protected JButton createNewListButton;
    protected JButton editListButton;

    // adding a new task
    private TaskEntryPage addTaskPage;

    // scroll pane for ui
    protected JScrollPane scrollPane;

    // necessary access data to transfer to past task list
    private PastTasksPage currentData;

    // timer parameter
    private TaskReminder taskReminder;

    public TasksPage(GUI mainGUI, PastTasksPage pastTasksPage) {
        // setting the layout for the panel
        setLayout(null);

        // instantiating components
        title = new JLabel("Daily Task List");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(125, 15, 400, 30); 

        returnHome = new JButton("Home");
        returnHome.setBounds(150, 325, 100, 30); 

        dailyTasks = new ArrayList<>();
    
        // create list mdoel and add tasks from araraylist
        taskListModel = new DefaultListModel<>();
        for (Task task : dailyTasks) {
            taskListModel.addElement(task.getName());
        }

        // jlist assumes the list model + is housed in a scrollpane
        taskList = new JList<>(taskListModel);
        scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(75, 60, 250, 200); 

        // declaring the buttons + setting bounds
        editListButton = new JButton ("Edit Task List");
        createNewListButton = new JButton("Create New");

        editListButton.setBounds(75, 270, 120, 30);
        createNewListButton.setBounds(200, 270, 120, 30);
        
        // adding the custom renderer for the highlighting
        GlowingListCellRenderer renderer = new GlowingListCellRenderer();
        taskList.setCellRenderer(renderer);

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

        // declaring necessary values for data for transfer to past tasks
        currentData = pastTasksPage;

        editPanel.add(deleteTaskButton);
        editPanel.add(editTaskButton);
        editPanel.add(addTaskButton);

        editOptions.add(editPanel);

        // adding + instantiating the timer 
        taskReminder = new TaskReminder(dailyTasks);

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

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskEntryPage taskEntryDialog = new TaskEntryPage(mainGUI.getFrame(), getCurrentData());
                taskEntryDialog.setVisible(true);
            }
        });

        createNewListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentData.refreshTasklist(currentData.getPastTasks(), currentData.pastListModel, dailyTasks);

                dailyTasks.clear();
                refreshTasklist(dailyTasks, taskListModel, dailyTasks);
        
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) { 
                    dailyTasks.remove(selectedIndex);
                    taskListModel.remove(selectedIndex); 
                } 
            }
        });

        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) { 
                    Task selectedTask = dailyTasks.get(selectedIndex);
                    showEditTaskDialog(selectedTask, selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a task to edit.");
                }
            }
        });

    }

    // returning the access to data
    public TasksPage getCurrentData () {
        return this;
    }

    public ArrayList<Task> getDailyTasks () {
        return dailyTasks;
    }

    // refreshing the page to show any changes
    public void refreshTasklist (ArrayList<Task> updatedTasks, DefaultListModel<String> x, ArrayList<Task> y) {
        x.clear();    
        for (Task tasks : y) {
            x.addElement(tasks.getName());
        }
    }

    public void quickSort(ArrayList<Task> tasks, int start, int end) {
        // base case for recursion
        if (end <= start) {
            return;
        }
    
        int pivot = partition(tasks, start, end);
        quickSort(tasks, start, pivot - 1);
        quickSort(tasks, pivot + 1, end);
    }
    
    public int partition(ArrayList<Task> tasks, int start, int end) {
        int pivot = tasks.get(end).getPriority();
        int i = start - 1;
    
        for (int j = start; j <= end -1; j++) {
            if (tasks.get(j).getPriority() < pivot) {
                i++;
                Task temp = tasks.get(i);
                tasks.set(i, tasks.get(j));
                tasks.set(j, temp);
            }
        }
        i++;
        Task temp = tasks.get(i);
        tasks.set(i, tasks.get(end));
        tasks.set(end, temp);
    
        return i;
    }

    private void showEditTaskDialog(Task task, int index) {
        // creating jdialog to view details + edit
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Task", true);
        dialog.setSize(350, 300);
        dialog.setLayout(new GridLayout(6, 2, 5, 5));
        dialog.setTitle("Edit Tasks");
    
        // displaying the fields/data
        JLabel nameLabel = new JLabel("Task Name:");
        JTextField nameField = new JTextField(task.getName());
    
        JLabel priority = new JLabel("Priority:");
        JTextField priorityInput = new JTextField(String.valueOf(task.getPriority()));
    
        JLabel description = new JLabel("Description:");
        JTextField descriptionInput = new JTextField(task.getDescription());
    
        JLabel deadline = new JLabel("Deadline:");
        JTextField deadlineInput = new JTextField(String.valueOf(task.getDeadline()));
    
        JLabel duration = new JLabel("Duration:");
        JTextField durationInput = new JTextField(String.valueOf(task.getDuration()));
    
        // save button + updating to display changes
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskListModel.set(index, nameField.getText());
                dailyTasks.set(index, new Task(
                    nameField.getText(),
                    Integer.parseInt(priorityInput.getText()),
                    descriptionInput.getText(),
                    Double.parseDouble(deadlineInput.getText()),
                    Integer.parseInt(durationInput.getText())
                ));
    
                dialog.dispose(); 
            }
        });
    
        // adding components to dialog
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(priority);
        dialog.add(priorityInput);
        dialog.add(description);
        dialog.add(descriptionInput);
        dialog.add(deadline);
        dialog.add(deadlineInput);
        dialog.add(duration);
        dialog.add(durationInput);
        dialog.add(new JLabel()); 
        dialog.add(saveButton);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
}
