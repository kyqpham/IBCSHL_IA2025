import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TaskEntryPage extends JDialog {
    private JPanel panel;

    // prompt labels parameters
    private JLabel taskNamePrompt;
    private JLabel taskPriorityPrompt;
    private JLabel taskDurationPrompt;
    private JLabel taskDeadlinePrompt;
    private JLabel taskDescriptionPrompt;

    // user input fields parameters
    private JTextField taskNameInput;
    private JTextField taskPriorityInput;
    private JTextField taskDurationInput;
    private JTextField taskDeadlineInput;
    private JTextField taskDescriptionInput;

    // accessing task list
    private TasksPage currentData;

    // finish button
    private JButton finishTask;

    public TaskEntryPage(JFrame parent, TasksPage x) {
        super(parent, "Enter Task Details", true); 

        // grabbing the data from the task page for necessary use
        currentData = x;

        setLayout(new BorderLayout());

        // setting the dialogue
        setSize(400, 300); 
        setLocationRelativeTo(parent); 

        // create panel with GridBagLayout for more precise formatting
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 350));

        // initializing prompts and inputs
        taskNamePrompt = new JLabel("Task name:");
        taskPriorityPrompt = new JLabel("Task priority:");
        taskDurationPrompt = new JLabel("Task duration:");
        taskDeadlinePrompt = new JLabel("Task deadline:");
        taskDescriptionPrompt = new JLabel("Task description:");

        taskNameInput = new JTextField(15);
        taskPriorityInput = new JTextField(15);
        taskDurationInput = new JTextField(15);
        taskDeadlineInput = new JTextField(15);
        taskDescriptionInput = new JTextField(15);

        finishTask = new JButton("Finish");

        // gridlayout setting
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(taskNamePrompt, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(taskNameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(taskPriorityPrompt, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(taskPriorityInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(taskDurationPrompt, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(taskDurationInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(taskDeadlinePrompt, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(taskDeadlineInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(taskDescriptionPrompt, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(taskDescriptionInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(finishTask, gbc);

        add(panel, BorderLayout.CENTER);

        // finish task functioanlity
        finishTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskNameInput.getText();
                String taskPriority = taskPriorityInput.getText();
                String taskDuration = taskDurationInput.getText();
                String taskDeadline = taskDeadlineInput.getText();
                String taskDescription = taskDescriptionInput.getText();

                // adding the tasks to the local data list, returned to the task page list
                // tasks page should be refreshed to reflect the change
                currentData.getDailyTasks().add(taskName);
                currentData.refreshTasklist(currentData.getDailyTasks());

                dispose(); 
            }
        });

    }
}
