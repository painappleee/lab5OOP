package Lab5.View;

import Lab5.Repository.Repository;
import Lab5.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateAppoint extends BaseForm{
    private JPanel appointsPanel;
    private JLabel appoints;
    private JPanel datePanel;
    private JLabel dateLabel;
    private JTextField date;
    private JPanel timePanel;
    private JLabel timeLabel;
    private JTextField time;
    private JPanel warningPanel;
    private JLabel warning;
    private JPanel createPanel;
    private JButton create;

    public CreateAppoint(JFrame lastForm, Patient patient, Doctor doctor){
        super(new Dimension(300,230), "Запись на приём");
        createComponents();
        setSizes();
        setAlignments();
        setFonts();
        setComponents();
        addListeners(lastForm,patient,doctor);
    }
    private void createComponents(){
        appointsPanel = new JPanel();
        appoints =      new JLabel("Запись на приём");
        datePanel =     new JPanel();
        dateLabel =     new JLabel("Дата: ");
        date =          new JTextField(10);
        timePanel =     new JPanel();
        timeLabel =     new JLabel("Время: ");
        time =          new JTextField(10);
        createPanel =   new JPanel();
        create =        new JButton("Запиcаться");
        warningPanel =  new JPanel();
        warning =       new JLabel("   ");
    }
    private void setSizes(){
        appointsPanel.setMaximumSize(new Dimension(300, 40));
    }
    private void setAlignments(){
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        create.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void setFonts(){
        appoints.setFont(new Font("Arial", Font.PLAIN, 23));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        date.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        time.setFont(new Font("Arial", Font.PLAIN, 18));
        create.setFont(new Font("Arial", Font.PLAIN, 20));
        warning.setFont(new Font("Arial", Font.BOLD, 15));
        warning.setForeground(Color.red);
    }
    private void setComponents(){
        add(Box.createVerticalStrut(5));

        appointsPanel.add(appoints);
        add(appointsPanel);

        datePanel.add(dateLabel);
        datePanel.add(date);
        add(datePanel);

        timePanel.add(timeLabel);
        timePanel.add(time);
        add(timePanel);

        warningPanel.add(warning);
        add(warningPanel);

        createPanel.add(create);
        add(create);

        add(Box.createVerticalStrut(10));
    }
    private void addListeners(JFrame lastForm, Patient patient, Doctor doctor){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lastForm.setVisible(true);
                lastForm.setEnabled(true);
            }
        });
        create.addActionListener(e -> {
            if (!(date.getText().isEmpty()) && !(time.getText().isEmpty())){
                Appointment newApp;
                if (doctor instanceof Clinician)
                    newApp = new AppointmentOfClinician(doctor,patient, date.getText(),time.getText());
                else
                    newApp = new AppointmentOfDiagnostican(doctor,patient, date.getText(),time.getText());
                Repository.getAppointments().add(newApp);

                this.dispose();
                lastForm.setEnabled(true);
            }
            else {
                warning.setText("Не все поля заполнены!");
            }

        });
    }
}
