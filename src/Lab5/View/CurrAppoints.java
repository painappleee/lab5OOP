package Lab5.View;

import Lab5.Controller.Controller;
import Lab5.Model.Appointment;
import Lab5.Model.Doctor;
import Lab5.Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CurrAppoints extends JFrame{

    public CurrAppoints(JFrame personalArea, Person person) {
        super("Текущие записи");
        setSize(600, 600);
        Controller.baseForm(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                personalArea.setVisible(true);
            }
        });


        add(Box.createVerticalStrut(9));

        JPanel appointsPanel = new JPanel();
        JLabel appoints = new JLabel("Текущие записи");
        appointsPanel.setMaximumSize(new Dimension(500, 45));
        appoints.setFont(new Font("Arial", Font.PLAIN, 35));
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        appointsPanel.add(appoints);
        add(appointsPanel);

        add(Box.createVerticalStrut(7));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttons = new JPanel(new GridLayout(0, 1, 0, 10));
        for (Appointment x : Controller.clinic.getAppointments()) {
            if(person instanceof Doctor) {
                if (!x.getComplite() && x.getDoctor().getFullName().equals(person.getFullName())) {
                    JButton appButton = new JButton(x.getPatient().getFullName() + ", " + x.getDate()+","+x.getTime());
                    appButton.setPreferredSize(new Dimension(420, 50));
                    appButton.setFont(new Font("Arial", Font.PLAIN, 17));
                    appButton.addActionListener(e1 -> {
                        this.setVisible(false);
                        new AppointmentForm(this, x, true);
                    });
                    buttons.add(appButton);
                }
            }
            else {
                if (!x.getComplite() && x.getPatient().getFullName().equals(person.getFullName())) {
                    JButton appButton = new JButton(x.getDoctor().getFullName() + ", " + x.getDate()+","+x.getTime());
                    appButton.setPreferredSize(new Dimension(420, 50));
                    appButton.setFont(new Font("Arial", Font.PLAIN, 17));
                    appButton.addActionListener(e1 -> {
                        this.setEnabled(false);
                        new DeleteAppoint(personalArea,x);
                    });
                    buttons.add(appButton);
                }
                }


        }
        buttonsPanel.add(buttons);
        revalidate();
        add(buttonsPanel);


    }
}
