import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NavigationPage extends JPanel {
    // declaring parameters
    private JLabel label;
    private JLabel directions;
    private JLabel conclusion;
    private JButton returnHome;

    // constructor
    public NavigationPage(GUI mainGUI) {
        // declaring parameters
        label = new JLabel("How to Use: jelli!");

        // necessary layout
        setLayout(null);

        // setting the values
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBounds(125, 15, 400, 30); 

        // adding the label/directions text
        directions = new JLabel("<html>1. write events, due dates, and other details! <br><br> 2. rank the tasks you want to finish first. . . <br><br> 3. as you finish tasks, mark them complete. </html>");
        directions.setFont(new Font("Arial", Font.BOLD, 15));
        directions.setBounds(50, 100, 400, 120);

        conclusion = new JLabel("<html>jelli! will prioritize what you don't. . . <br><br> jelli! will remind you what you don't remember. </html>");
        conclusion.setFont(new Font("Arial", Font.BOLD, 15));
        conclusion.setBounds(50, 200, 400, 200);

        // adding button functionality 
        returnHome = new JButton("Home");
        returnHome.setBounds(150, 350, 100, 30); 
        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.switchToPanel("homePage");
            }
        });

        // adding components to panel
        add(label);
        add(directions);
        add(conclusion);
        add(returnHome);
    }
}
