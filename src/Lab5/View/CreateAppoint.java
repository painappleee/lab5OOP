package Lab5.View;

import Lab5.Controller.Controller;
import Lab5.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateAppoint extends JFrame {
    public CreateAppoint(JFrame personalArea, Patient patient, Doctor doctor){
        super("Запись на приём");
        setSize(300, 220);
        Controller.baseForm(this);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                personalArea.setVisible(true);
            }
        });


        add(Box.createVerticalStrut(5));

        JPanel appointsPanel = new JPanel();
        JLabel appoints = new JLabel("Запись на приём");
        appointsPanel.setMaximumSize(new Dimension(300, 40));
        appoints.setFont(new Font("Arial", Font.PLAIN, 23));
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        appointsPanel.add(appoints);
        add(appointsPanel);

        JPanel datePanel = new JPanel();
        JLabel dateLabel = new JLabel("Дата: ");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        datePanel.add(dateLabel);
        JTextField date = new JTextField(10);
        date.setFont(new Font("Arial", Font.PLAIN, 18));
        datePanel.add(date);
        add(datePanel);

        JPanel timePanel = new JPanel();
        JLabel timeLabel = new JLabel("Время: ");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timePanel.add(timeLabel);
        JTextField time = new JTextField(10);
        time.setFont(new Font("Arial", Font.PLAIN, 18));
        timePanel.add(time);
        add(timePanel);


        JPanel createPanel = new JPanel();
        JButton create = new JButton("Запиcаться");
        create.setAlignmentX(CENTER_ALIGNMENT);
        create.setFont(new Font("Arial", Font.PLAIN, 20));
        createPanel.add(create);
        add(create);

        add(Box.createVerticalStrut(10));

        create.addActionListener(e -> {
            Appointment newApp;
            if (doctor instanceof Clinician)
                newApp = new AppointmentOfClinician(doctor,patient, date.getText(),time.getText());
            else
                newApp = new AppointmentOfDiagnostican(doctor,patient, date.getText(),time.getText());
            Controller.clinic.getAppointments().add(newApp);
            this.dispose();
        });
    }
}
