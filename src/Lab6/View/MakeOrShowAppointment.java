package Lab6.View;

import Lab6.Repository.Repository;
import Lab6.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MakeOrShowAppointment extends BaseForm {
    private JPanel appointsPanel;
    private JLabel  appoints;
    private JPanel radioPanel;
    private ButtonGroup radioButtongroup;
    private JRadioButton clinicianRadioButton;
    private JRadioButton diagnosticianRadioButton;
    private JPanel doctorProfComboBoxPanel;
    private JPanel buttonsPanel;
    private JPanel buttons;
    private JComboBox<String> doctorProfComboBox;
    public MakeOrShowAppointment(JFrame lastFrame, Person person, boolean isMake){
        super(new Dimension(600, 600),isMake ? "Запись на приём" : "Просмотр приёмов");
        createComponents(isMake);
        setSizes(isMake);
        setLayouts();
        setFonts();
        setAlignments();
        setComponents();
        addListeners(lastFrame,person,isMake);
    }
    private void createComponents(boolean isMake){
        appointsPanel =            new JPanel();
        appoints =                 new JLabel(isMake ? "Запись на приём" : "Просмотр приёмов");
        radioPanel =               new JPanel();
        radioButtongroup =         new ButtonGroup();
        clinicianRadioButton =     new JRadioButton("клиенцист");
        diagnosticianRadioButton = new JRadioButton("диагност");
        doctorProfComboBoxPanel =  new JPanel();
        buttonsPanel =             new JPanel();
        buttons =                  new JPanel();
        doctorProfComboBox =                 new JComboBox<>(Repository.getDoctorProf(clinicianRadioButton.isSelected()));
    }
    private void setSizes(boolean isMake) {
        appointsPanel.setMaximumSize(new Dimension(500,45));
        radioPanel.setMaximumSize(new Dimension(600,35));
        if (isMake)
            doctorProfComboBox.setPreferredSize(new Dimension(320,30));
        else
            doctorProfComboBox.setPreferredSize(new Dimension(420,30));
        doctorProfComboBoxPanel.setMaximumSize(new Dimension(500, 60));
    }
    private void setLayouts(){
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.setLayout(new GridLayout(0, 1,0,10));
    }
    private void setAlignments(){
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        doctorProfComboBox.setAlignmentX(LEFT_ALIGNMENT);
    }
    private void setFonts() {
        appoints.setFont(new Font("Arial", Font.PLAIN, 35));
        clinicianRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        diagnosticianRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        doctorProfComboBox.setFont(new Font("Arial", Font.PLAIN, 18));

    }
    public void setComponents(){
        add(Box.createVerticalStrut(9));

        appointsPanel.add(appoints);
        add(appointsPanel);

        add(Box.createVerticalStrut(7));

        radioButtongroup.add(clinicianRadioButton);
        radioButtongroup.add(diagnosticianRadioButton);
        radioButtongroup.setSelected(clinicianRadioButton.getModel(), true);

        radioPanel.add(clinicianRadioButton);
        radioPanel.add(Box.createHorizontalStrut(70));
        radioPanel.add(diagnosticianRadioButton);
        add(radioPanel);

        add(Box.createVerticalStrut(7));

        doctorProfComboBox.setSelectedIndex(0);
        doctorProfComboBoxPanel.add(doctorProfComboBox);
        add(doctorProfComboBoxPanel);

        add(buttonsPanel);
    }
    public void addListeners(JFrame lastFrame,Person person, boolean isMake){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lastFrame.setVisible(true);
            }
        });
        doctorProfComboBox.addItemListener(e -> {
            buttons.removeAll();
            if (isMake){
                createMakeButtons(person);
            }
            else {
                createShowButtons(person);
            }
            buttonsPanel.add(buttons);
            revalidate();
        });
        clinicianRadioButton.addChangeListener(e -> {
            doctorProfComboBox.removeAllItems();
            for (String x : Repository.getDoctorProf(clinicianRadioButton.isSelected()))
                doctorProfComboBox.addItem(x);
        });
    }
    private void createMakeButtons(Person person){
        for (Doctor x : Repository.getDoctors()) {
            if (doctorProfComboBox.getSelectedItem() == x.getProfession()) {
                JButton doctorButton = new JButton(x.getFullName());
                doctorButton.setPreferredSize(new Dimension(320, 50));
                doctorButton.setFont(new Font("Arial", Font.PLAIN, 18));
                doctorButton.addActionListener(e2 ->{
                    this.setEnabled(false);
                    new CreateAppoint(this,(Patient)person,x);
                });
                buttons.add(doctorButton);
            }
        }
    }
    private void createShowButtons(Person person) {
        for (Appointment x : Repository.getAppointments()) {
            if (x.getPatient().getFullName().equals(person.getFullName()) && x.getComplite()) {
                if (doctorProfComboBox.getSelectedItem() == x.getDoctor().getProfession()) {
                    JButton appButton = new JButton(x.getDoctor().getFullName() + ", " + x.getDate());
                    appButton.setPreferredSize(new Dimension(420, 50));
                    appButton.setFont(new Font("Arial", Font.PLAIN, 17));
                    appButton.addActionListener(e1 -> {
                        this.setVisible(false);
                        new AppointmentForm(this, x, false);
                    });
                    buttons.add(appButton);
                }
            }
        }
    }
}
