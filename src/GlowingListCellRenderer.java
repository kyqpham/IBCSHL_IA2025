import java.awt.*;
import javax.swing.*;

public class GlowingListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        // getting the renderer component
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // applying highlights to only the first component
        if (index == 0) { 
            label.setForeground(Color.BLUE); 
            label.setFont(label.getFont().deriveFont(Font.BOLD)); 
            label.setBackground(new Color(173, 216, 230));
            label.setOpaque(true);
        } else {
            label.setForeground(Color.BLACK);
            label.setFont(label.getFont().deriveFont(Font.PLAIN));
            label.setOpaque(false); 
        }
        return label;
    }
}
