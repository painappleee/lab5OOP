package Lab5.View;

import Lab5.Controller.Controller;
import Lab5.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

public class MakeOrShowAppointment extends JFrame {
    public MakeOrShowAppointment(JFrame personalArea, Person person, boolean isMake){

        super(isMake ? "Запись на приём" : "Просмотр приёмов");
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
        JLabel  appoints = new JLabel(isMake ? "Запись на приём" : "Просмотр приёмов");
        appointsPanel.setMaximumSize(new Dimension(500,45));
        appoints.setFont(new Font("Arial", Font.PLAIN, 35));
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        appointsPanel.add(appoints);
        add(appointsPanel);

        add(Box.createVerticalStrut(7));

        JPanel radioPanel = new JPanel();
        ButtonGroup radioButtongroup = new ButtonGroup();
        radioPanel.setMaximumSize(new Dimension(600,35));
        JRadioButton clinicianRadioButton = new JRadioButton("клиенцист");
        clinicianRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        JRadioButton diagnosticanRadioButton = new JRadioButton("диагност");

        diagnosticanRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        radioButtongroup.add(clinicianRadioButton);
        radioButtongroup.add(diagnosticanRadioButton);
        radioButtongroup.setSelected(clinicianRadioButton.getModel(), true);
        radioPanel.add(clinicianRadioButton);
        radioPanel.add(Box.createHorizontalStrut(70));
        radioPanel.add(diagnosticanRadioButton);
        add(radioPanel);

        add(Box.createVerticalStrut(7));


        JPanel comboBoxPanel = new JPanel();
        JComboBox comboBox = new JComboBox(Controller.getComboboxItem(clinicianRadioButton.isSelected()));
        if (isMake)
            comboBox.setPreferredSize(new Dimension(320,30));
        else
            comboBox.setPreferredSize(new Dimension(420,30));
        comboBoxPanel.setMaximumSize(new Dimension(500, 60));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        comboBox.setSelectedIndex(0);
        comboBoxPanel.add(comboBox);
        add(comboBoxPanel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttons = new JPanel(new GridLayout(0, 1,0,10));
        comboBox.addItemListener(e -> {
            buttons.removeAll();
            if (isMake){
                for (Doctor x : Controller.clinic.getDoctors()) {
                    if (comboBox.getSelectedItem() == x.getProfession()) {
                        JButton doctorButton = new JButton(x.getFullName());
                        doctorButton.setPreferredSize(new Dimension(320, 50));
                        doctorButton.setFont(new Font("Arial", Font.PLAIN, 18));
                        doctorButton.addActionListener(e2 ->{
                            this.setEnabled(false);
                            new CreateAppoint(personalArea,(Patient)person,x);
                        });
                        buttons.add(doctorButton);

                    }
                }

            }
            else{
                for (Appointment x : Controller.clinic.getAppointments()) {
                    if (x.getPatient().getFullName().equals(person.getFullName()) && x.getComplite()){
                        if (comboBox.getSelectedItem() == x.getDoctor().getProfession()) {
                            JButton appButton = new JButton(x.getDoctor().getFullName()+", "+x.getDate());
                            appButton.setPreferredSize(new Dimension(420, 50));
                            appButton.setFont(new Font("Arial", Font.PLAIN, 17));
                            appButton.addActionListener(e1 -> {
                                this.setVisible(false);
                                new AppointmentForm(this, x,false);
                            });
                            buttons.add(appButton);
                        }
                    }

                }
            }
            buttonsPanel.add(buttons);
            revalidate();
        });

        add(buttonsPanel);

        clinicianRadioButton.addChangeListener(e -> {
            comboBox.removeAllItems();
            for (String x : Controller.getComboboxItem(clinicianRadioButton.isSelected()))
                comboBox.addItem(x);
        });
    }
}
