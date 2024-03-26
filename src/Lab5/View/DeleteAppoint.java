package Lab5.View;

import Lab5.Controller.Controller;
import Lab5.Model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteAppoint extends JFrame {

    public DeleteAppoint(JFrame personalArea, Appointment currAppoint){
        super("Удаление записей");
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
        JLabel appoints = new JLabel("Удаление записи");
        appointsPanel.setMaximumSize(new Dimension(300, 40));
        appoints.setFont(new Font("Arial", Font.PLAIN, 23));
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        appointsPanel.add(appoints);
        add(appointsPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setMaximumSize(new Dimension(300,150));
        JLabel doctorFio = new JLabel("Врач:  " + currAppoint.getDoctor().getFullName());
        doctorFio.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel doctorProf = new JLabel("Профессия врача:  " + currAppoint.getDoctor().getProfession());
        doctorProf.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel date = new JLabel("Дата:  " + currAppoint.getDate());
        date.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel time = new JLabel("Время:  " + currAppoint.getTime());
        time.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(doctorFio);
        infoPanel.add(doctorProf);
        infoPanel.add(date);
        infoPanel.add(time);

        add(infoPanel);

        JPanel delPanel = new JPanel();
        JButton del = new JButton("Удалить");
        del.setAlignmentX(CENTER_ALIGNMENT);
        del.setFont(new Font("Arial", Font.PLAIN, 20));
        delPanel.add(del);
        add(del);

        add(Box.createVerticalStrut(10));

        del.addActionListener(e -> {
            Controller.clinic.getAppointments().removeIf(x -> x.getPatient().equals(currAppoint.getPatient()) && x.getDate().equals(currAppoint.getDate()) && x.getTime().equals(currAppoint.getTime()));
            this.dispose();
        });
    }
}
