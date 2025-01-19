import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class PastTasksPage extends TasksPage {
    public PastTasksPage(GUI mainGUI) {
        super(mainGUI);
        title.setText("Past Tasks List");

        // removing unnecessary components
        remove(createNewListButton);
        remove(editListButton);

    }
    
}
