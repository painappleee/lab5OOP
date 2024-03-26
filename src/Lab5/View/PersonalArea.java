package Lab5.View;

import Lab5.Controller.Controller;
import Lab5.Model.Patient;
import Lab5.Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonalArea extends JFrame {
    public PersonalArea(Person currPerson){
        super("Личный кабинет");
        setSize(600,600);
        Controller.baseForm(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                new Autorization();
            }
        });


        JPanel peoplePanel = new JPanel();

        JPanel imagePanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("src/Lab5/images/image2.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel);

        String[] fio = currPerson.getFullName().split(" ");
        JPanel fioPanel = new JPanel();
        fioPanel.setLayout(new BoxLayout(fioPanel,BoxLayout.Y_AXIS));
        JLabel surname = new JLabel(fio[0]);
        surname.setFont(new Font("Arial", Font.PLAIN, 30));
        JLabel name = new JLabel(fio[1]);
        name.setFont(new Font("Arial", Font.PLAIN, 30));
        JLabel secondName = new JLabel(fio[2]);
        secondName.setFont(new Font("Arial", Font.PLAIN, 30));
        fioPanel.add(surname);
        surname.setAlignmentX(LEFT_ALIGNMENT);
        fioPanel.add(Box.createVerticalStrut(7));
        fioPanel.add(name);
        name.setAlignmentX(LEFT_ALIGNMENT);
        fioPanel.add(Box.createVerticalStrut(7));
        fioPanel.add(secondName);
        secondName.setAlignmentX(LEFT_ALIGNMENT);

        peoplePanel.add(imagePanel);
        peoplePanel.add(Box.createHorizontalStrut(50));
        peoplePanel.add(fioPanel);

        add(peoplePanel);

        JPanel buttons = new JPanel(new GridLayout(0, 1,0,20));

        JButton showAppButton = new JButton("Посмотреть записи");
        showAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
        buttons.add(showAppButton);


        if (currPerson instanceof Patient){
            JButton makeAppButton = new JButton("Записаться на приём");
            makeAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
            buttons.add(makeAppButton);

            JButton showLastAppButton = new JButton("Посмотреть приёмы");
            showLastAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
            buttons.add(showLastAppButton);

            makeAppButton.addActionListener(e ->{
                this.setVisible(false);
                new MakeOrShowAppointment(this, currPerson, true);
            });

            showLastAppButton.addActionListener(e -> {
                this.setVisible(false);
                new MakeOrShowAppointment(this,currPerson, false);
            });

        }


        JButton exitButton = new JButton("Выйти");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 25));
        buttons.add(exitButton);


        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(buttons);
        add(buttonsPanel);

        exitButton.addActionListener(e -> {
            this.dispose();
            new Autorization();

        });

        showAppButton.addActionListener(e -> {
            this.setVisible(false);
                new CurrAppoints(this,currPerson);
        });
    }
}
