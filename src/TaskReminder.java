import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskReminder {
    // declaring special parameters + initial brightness for dialogue
    private List<Task> dailyTasks;
    private int brightnessLevel = 50; 
    private final Timer reminderTimer;

    public TaskReminder(List<Task> dailyTasks) {
        this.dailyTasks = dailyTasks;

        //set up a timer every 15 minutes (900,000 ms)
        reminderTimer = new Timer(900_000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dailyTasks.isEmpty()) {
                    showReminder();
                }
            }
        });

        reminderTimer.start(); 
    }

    private void showReminder() {
        Task firstTask = dailyTasks.get(0); 
        JDialog reminder = new JDialog();
        reminder.setTitle("Task Reminder");
        reminder.setSize(300, 150);
        reminder.setLayout(new BorderLayout());
    
        // Reset brightness level to initial value
        brightnessLevel = 50; // Reset to initial brightness
        Color textColor = new Color(brightnessLevel, brightnessLevel, brightnessLevel);
    
        // Displaying the reminder
        JLabel taskLabel = new JLabel("<html><center>Reminder: " + firstTask.getName() + "<br>Complete it soon!</center></html>");
        taskLabel.setForeground(textColor);
        taskLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        JButton dismissButton = new JButton("Got it!");
        dismissButton.addActionListener(e -> reminder.dispose());
    
        reminder.add(taskLabel, BorderLayout.CENTER);
        reminder.add(dismissButton, BorderLayout.SOUTH);
        reminder.setLocationRelativeTo(null);
        reminder.setVisible(true);
    
        // Start a timer to gradually increase the brightness
        Timer brightnessTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brightnessLevel = Math.min(brightnessLevel + 30, 255); // Increase brightness
                taskLabel.setForeground(new Color(brightnessLevel, brightnessLevel, brightnessLevel));
                if (brightnessLevel >= 255) {
                    ((Timer)e.getSource()).stop(); // Stop the timer when max brightness is reached
                }
            }
        });
        brightnessTimer.start();
    }
}
