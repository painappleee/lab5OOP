package Lab5.View;

import Lab5.Controller.Repository;
import Lab5.Model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteAppoint extends BaseForm {
    private JPanel appointsPanel ;
    private JLabel appoints;
    private JPanel infoPanel;
    private JLabel doctorFio;
    private JLabel doctorProf;
    private JLabel date;
    private JLabel time;
    private JPanel delPanel;
    private JButton del;
    public DeleteAppoint(JFrame lastForm, Appointment currAppoint){
        super(new Dimension(300, 220),"Удаление записей" );
        createComponents(currAppoint);
        setSizes();
        setLayouts();
        setAlignments();
        setFonts();
        setComponents();
        addListeners(lastForm,currAppoint);
    }
    private void createComponents(Appointment currAppoint){
        appointsPanel = new JPanel();
        appoints =      new JLabel("Удаление записи");
        infoPanel =     new JPanel();
        doctorFio =     new JLabel("Врач:  " + currAppoint.getDoctor().getFullName());
        doctorProf =    new JLabel("Профессия врача:  " + currAppoint.getDoctor().getProfession());
        date =          new JLabel("Дата:  " + currAppoint.getDate());
        time =          new JLabel("Время:  " + currAppoint.getTime());
        delPanel =      new JPanel();
        del =           new JButton("Удалить");
    }
    private void setSizes(){
        appointsPanel.setMaximumSize(new Dimension(300, 40));
        infoPanel.setMaximumSize(new Dimension(300,150));
    }
    private void setLayouts(){
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    }
    private void setAlignments(){
        appoints.setAlignmentX(CENTER_ALIGNMENT);
        del.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void setFonts(){
        appoints.setFont(new Font("Arial", Font.PLAIN, 23));
        doctorFio.setFont(new Font("Arial", Font.PLAIN, 16));
        doctorProf.setFont(new Font("Arial", Font.PLAIN, 16));
        date.setFont(new Font("Arial", Font.PLAIN, 16));
        time.setFont(new Font("Arial", Font.PLAIN, 16));
        del.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    private void setComponents(){
        add(Box.createVerticalStrut(5));

        appointsPanel.add(appoints);
        add(appointsPanel);

        infoPanel.add(doctorFio);
        infoPanel.add(doctorProf);
        infoPanel.add(date);
        infoPanel.add(time);
        add(infoPanel);

        delPanel.add(del);
        add(del);

        add(Box.createVerticalStrut(10));
    }
    private void addListeners(JFrame lastForm, Appointment currAppoint){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lastForm.dispose();
                lastForm.setEnabled(true);
            }
        });
        del.addActionListener(e -> {
            Repository.getAppointments().removeIf(x -> x.getPatient().equals(currAppoint.getPatient()) && x.getDate().equals(currAppoint.getDate()) && x.getTime().equals(currAppoint.getTime()));
            this.dispose();
            lastForm.setEnabled(true);
        });
    }

}
