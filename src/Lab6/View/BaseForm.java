package Lab6.View;

import javax.swing.*;
import java.awt.*;

public abstract class BaseForm extends JFrame {
    public BaseForm(Dimension dimension, String title){
        super(title);
        setSize(dimension);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/Lab6/images/icon.png");
        setIconImage(icon.getImage());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }
}
