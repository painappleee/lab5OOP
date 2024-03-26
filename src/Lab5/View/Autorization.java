package Lab5.View;
import Lab5.Controller.Controller;
import Lab5.Model.Clinic;
import Lab5.Model.Doctor;
import Lab5.Model.Patient;
import Lab5.Model.Person;

import java.awt.*;
import javax.swing.*;
public class Autorization extends JFrame {
    public Autorization() {

        super("Авторизация");

        setSize(600, 600);

        Controller.baseForm(this);
        add(Box.createHorizontalStrut(7));

        JPanel autorizationPanel = new JPanel();
        JLabel autorization = new JLabel(Controller.clinic.getClinicName());
        autorization.setFont(new Font("Arial", Font.PLAIN, 38));
        autorization.setAlignmentX(CENTER_ALIGNMENT);
        autorizationPanel.add(autorization);
        add(autorizationPanel);


        JPanel radioPanel = new JPanel();
        ButtonGroup radioButtongroup = new ButtonGroup();
        JRadioButton doctorRadioButton = new JRadioButton("доктор");
        doctorRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        JRadioButton patientRadioButton = new JRadioButton("пациент");
        patientRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        radioButtongroup.add(doctorRadioButton);
        radioButtongroup.add(patientRadioButton);
        radioButtongroup.setSelected(patientRadioButton.getModel(), true);
        radioPanel.add(doctorRadioButton);
        radioPanel.add(Box.createHorizontalStrut(50));
        radioPanel.add(patientRadioButton);
        add(radioPanel);


        JPanel loginPanel = new JPanel();
        JLabel loginLabel = new JLabel("Логин: ");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(loginLabel);
        JTextField login = new JTextField(20);
        login.setFont(new Font("Arial", Font.PLAIN, 18));
        loginPanel.add(login);
        add(loginPanel);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Пароль: ");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordPanel.add(passwordLabel);
        JPasswordField password = new JPasswordField(19 );
        password.setEchoChar('?');
        password.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordPanel.add(password);
        add(passwordPanel);

        JPanel enterPanel = new JPanel();
        JButton enter = new JButton("Войти");
        enter.setAlignmentX(CENTER_ALIGNMENT);
        enter.setFont(new Font("Arial", Font.PLAIN, 25));
        enterPanel.add(enter);
        add(enterPanel);

        enter.addActionListener(e -> {
            int type;
            if (doctorRadioButton.isSelected())
                type = 1;
            else
                type=2;
            Person currPerson = Controller.autorization(type,login.getText(),String.copyValueOf(password.getPassword()));

            if (currPerson!=null){
                this.dispose();
                new PersonalArea(currPerson);
            }

        });

        revalidate();
    }
}