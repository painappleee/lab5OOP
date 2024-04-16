package Lab6.View;

import Lab6.Repository.Repository;
import Lab6.Model.Appointment;
import Lab6.Model.Doctor;
import Lab6.Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CurrAppoints extends BaseForm{
    private JPanel appointsPanel;
    private JLabel appoints;
    private JPanel buttonsPanel;
    private JPanel buttons;
    public CurrAppoints(JFrame lastForm, Person person) {
        super(new Dimension(600, 600), "Текущие записи");
        createComponents();
        setSizes();
        setLayouts();
        setAlignments();
        setFonts();
        setComponents(person);
        addListeners(lastForm);
    }
    private void createComponents(){
        appointsPanel = new JPanel();
        appoints =      new JLabel("Текущие записи");
        buttonsPanel =  new JPanel();
        buttons =       new JPanel();
    }
    private void setSizes(){
        appointsPanel.setMaximumSize(new Dimension(500, 45));
    }
    private void setLayouts(){
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.setLayout(new GridLayout(0, 1, 0, 10));
    }
    private void setAlignments(){appoints.setAlignmentX(CENTER_ALIGNMENT);}
    private void setFonts(){ appoints.setFont(new Font("Arial", Font.PLAIN, 35));}
    private void setComponents(Person person){
        add(Box.createVerticalStrut(9));

        appointsPanel.add(appoints);
        add(appointsPanel);

        add(Box.createVerticalStrut(7));

        createAppButtons(person);
        buttonsPanel.add(buttons);
        revalidate();
        add(buttonsPanel);
    }
    private void addListeners(JFrame lastForm){addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {lastForm.setVisible(true);}
        });}
    private void createAppButtons(Person person){
        for (Appointment x : Repository.getAppointments()) {
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
                        new DeleteAppoint(this, x);
                    });
                    buttons.add(appButton);
                }
            }
        }
    }
}
