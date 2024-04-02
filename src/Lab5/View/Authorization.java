package Lab5.View;
import Lab5.Controller.Repository;
import Lab5.Model.Person;

import java.awt.*;
import javax.swing.*;
public class Authorization extends BaseForm {
    private JPanel authorizationPanel;
    private JLabel authorization;
    private JPanel radioPanel;
    private ButtonGroup radioButtongroup;
    private JRadioButton doctorRadioButton;
    private JRadioButton patientRadioButton;
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JTextField login;
    private JPanel passwordPanel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JPanel warningPanel;
    private JLabel warning;
    private JPanel enterPanel;
    private JButton enter;
    public Authorization() {
        super(new Dimension(600,600),"Авторизация");
        createComponents();
        setFonts();
        setAlignments();
        setComponents();
        setListeners();
        revalidate();
    }
    private void createComponents(){
        authorizationPanel = new JPanel();
        authorization =      new JLabel(Repository.getClinicName());
        radioPanel =         new JPanel();
        radioButtongroup =   new ButtonGroup();
        doctorRadioButton =  new JRadioButton("доктор");
        patientRadioButton = new JRadioButton("пациент");
        loginPanel =         new JPanel();
        loginLabel =         new JLabel("Логин: ");
        login =              new JTextField(20);
        passwordPanel =      new JPanel();
        passwordLabel =      new JLabel("Пароль: ");
        password =           new JPasswordField(19 );
        enterPanel =         new JPanel();
        enter =              new JButton("Войти");
        warningPanel =       new JPanel();
        warning =            new JLabel("     ");
    }
    private void setFonts(){
        authorization.setFont(new Font("Arial", Font.PLAIN, 38));
        doctorRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        patientRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        login.setFont(new Font("Arial", Font.PLAIN, 18));
        password.setFont(new Font("Arial", Font.PLAIN, 18));
        warning.setFont(new Font("Arial", Font.BOLD, 18));
        warning.setForeground(Color.red);
        enter.setFont(new Font("Arial", Font.PLAIN, 25));
    }
    private void setAlignments(){
        authorization.setAlignmentX(CENTER_ALIGNMENT);
        warningPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        enter.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void setComponents(){
        add(Box.createHorizontalStrut(7));

        authorizationPanel.add(authorization);
        add(authorizationPanel);

        radioButtongroup.add(doctorRadioButton);
        radioButtongroup.add(patientRadioButton);
        radioButtongroup.setSelected(patientRadioButton.getModel(), true);

        radioPanel.add(doctorRadioButton);
        radioPanel.add(Box.createHorizontalStrut(50));
        radioPanel.add(patientRadioButton);
        add(radioPanel);

        add(Box.createVerticalStrut(50));

        loginPanel.add(loginLabel);
        loginPanel.add(login);
        add(loginPanel);

        passwordPanel.add(passwordLabel);
        password.setEchoChar('?');
        passwordPanel.add(password);
        add(passwordPanel);

        warningPanel.add(warning);
        add(warningPanel);

        enterPanel.add(enter);
        add(enterPanel);

        add(Box.createVerticalStrut(60));
    }
    private void setListeners(){
        enter.addActionListener(e -> {
            warning.setText("    ");
            int type;
            if (doctorRadioButton.isSelected())
                type = 1;
            else
                type=2;
            Person currPerson = Repository.getPerson(type,login.getText(),String.copyValueOf(password.getPassword()));

            if (currPerson!=null){
                this.dispose();
                login.setText("");
                password.setText("");
                new PersonalArea(this, currPerson);
            }
            else
                warning.setText("Неверный логин или пароль");

        });
    }
}