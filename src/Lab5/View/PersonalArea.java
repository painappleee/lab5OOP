package Lab5.View;

import Lab5.Model.Patient;
import Lab5.Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonalArea extends BaseForm {
    private JPanel peoplePanel;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JPanel fioPanel;
    private JLabel surname;
    private JLabel name;
    private JLabel secondName;
    private JPanel buttons;
    private JButton showAppButton;
    private JButton makeAppButton;
    private JButton showLastAppButton;
    private JButton exitButton;
    private JPanel buttonsPanel;
    public PersonalArea(JFrame lastFrame, Person currPerson){
        super(new Dimension(600,600), "Личный кабинет");
        createComponents(currPerson);
        setLayouts();
        setAlignments();
        setFonts();
        setComponents(currPerson);
        addListeners(lastFrame,currPerson);
    }
    private void createComponents(Person currPerson){
        peoplePanel =         new JPanel();
        imagePanel =          new JPanel();
        ImageIcon imageIcon = new ImageIcon("src/Lab5/images/image2.png");
        imageLabel =          new JLabel(imageIcon);
        String[] fio =        currPerson.getFullName().split(" ");
        fioPanel =            new JPanel();
        surname =             new JLabel(fio[0]);
        name =                new JLabel(fio[1]);
        secondName =          new JLabel(fio[2]);
        buttons =             new JPanel();
        showAppButton =       new JButton("Посмотреть записи");
        makeAppButton =       new JButton("Записаться на приём");
        showLastAppButton =   new JButton("Посмотреть приёмы");
        exitButton =          new JButton("Выйти");
        buttonsPanel =        new JPanel();
    }
    private void setLayouts(){
        fioPanel.setLayout(new BoxLayout(fioPanel,BoxLayout.Y_AXIS));
        buttons.setLayout(new GridLayout(0, 1,0,20));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    private void setAlignments(){
        surname.setAlignmentX(LEFT_ALIGNMENT);
        name.setAlignmentX(LEFT_ALIGNMENT);
        secondName.setAlignmentX(LEFT_ALIGNMENT);
    }
    private void setFonts(){
        surname.setFont(new Font("Arial", Font.PLAIN, 30));
        name.setFont(new Font("Arial", Font.PLAIN, 30));
        secondName.setFont(new Font("Arial", Font.PLAIN, 30));
        showAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 25));
        makeAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
        showLastAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
    }
    private void setComponents(Person currPerson){
        imagePanel.add(imageLabel);

        fioPanel.add(surname);
        fioPanel.add(Box.createVerticalStrut(7));
        fioPanel.add(name);
        fioPanel.add(Box.createVerticalStrut(7));
        fioPanel.add(secondName);

        peoplePanel.add(imagePanel);
        peoplePanel.add(Box.createHorizontalStrut(50));
        peoplePanel.add(fioPanel);
        add(peoplePanel);

        if (currPerson instanceof Patient){
            buttons.add(makeAppButton);
            buttons.add(showLastAppButton);
        }

        buttons.add(showAppButton);
        buttons.add(exitButton);
        buttonsPanel.add(buttons);
        add(buttonsPanel);
    }
    private void addListeners(JFrame lastFrame, Person currPerson){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lastFrame.setVisible(true);
            }
        });
        if (currPerson instanceof Patient){
            makeAppButton.addActionListener(e ->{
                this.setVisible(false);
                new MakeOrShowAppointment(this, currPerson, true);
            });
            showLastAppButton.addActionListener(e -> {
                this.setVisible(false);
                new MakeOrShowAppointment(this,currPerson, false);
            });
        }
        exitButton.addActionListener(e -> {
            this.dispose();
            lastFrame.setVisible(true);

        });
        showAppButton.addActionListener(e -> {
            this.setVisible(false);
            new CurrAppoints(this,currPerson);
        });
    }

}
