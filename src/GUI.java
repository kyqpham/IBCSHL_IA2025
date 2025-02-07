import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    //the entire GUI assembled from the constituent classes
    //declaring parameters

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private HomePage homePanel;
    private NavigationPage navigationPanel;
    private TasksPage dailyTasksPanel;
    private PastTasksPage pastTasksPanel;

    public GUI () {
        //cardlayout allows for easier switching of creens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        //instantiates initial components
        homePanel = new HomePage(this);
        navigationPanel = new NavigationPage(this);
        pastTasksPanel = new PastTasksPage(this);
        dailyTasksPanel = new TasksPage(this, pastTasksPanel);

        // adding the panels to the card layout
        mainPanel.add(homePanel, "homePage");
        mainPanel.add(navigationPanel, "navigationPage");
        mainPanel.add(dailyTasksPanel, "dailyTasksPage");
        mainPanel.add(pastTasksPanel, "pastTasksPage");

        frame = new JFrame("jelli!");
        frame.setSize(450,450);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    // switching screen method for card layout
    public void switchToPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void quickSort(int [] array, int start, int end) {
        //base case for recursion
        if (end <= start) {
            return;
        }
    
        int pivot = partition(array, start, end);
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }
    
    public int partition(int [] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;
    
        for (int j = start; j <= end -1; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++;
    
        return i;
    }
}

