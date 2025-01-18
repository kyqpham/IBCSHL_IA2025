import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//extends jpanel so that homepage is an actual panel
//necessary for card layout
public class HomePage extends JPanel {
    //declaring parameters/elements for panel
    private JLabel title;
    private JButton taskPageButton, pastTaskPageButton, directionsButton;

    // constructor for the home page
    public HomePage(GUI mainGUI) {
        // inidividual layout for the panel
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(450, 450));

        // instantiating elements
        title = new JLabel("jelli!");
        taskPageButton = new JButton("Daily Tasks");
        pastTaskPageButton = new JButton("Previous Tasks");
        directionsButton = new JButton("How to Use");

        // setting the size for individual components + other attributes
        title.setFont(new Font("Arial", Font.BOLD, 20));
        taskPageButton.setPreferredSize(new Dimension(200, 75));
        pastTaskPageButton.setPreferredSize(new Dimension(200, 75));
        directionsButton.setPreferredSize(new Dimension(200, 75));

        // necessary gridbag constraints for the layout
        // will be used for the spacing/positioning of elements
        GridBagConstraints gbc = new GridBagConstraints();

        // title spacing
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(35, 10, 10, 10);
        add(title, gbc);

        // task button spacing
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(taskPageButton, gbc);

        // past tasks button spacing
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(pastTaskPageButton, gbc);

        // directions button spacing
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(directionsButton, gbc);

        // adding functionality to the buttons via actionlisteners
        directionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.switchToPanel("navigationPage");
            }
        });

        taskPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.switchToPanel("dailyTasksPage");
            }
        });

        pastTaskPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.switchToPanel("pastTasksPage");
            }
        });
    }
}
