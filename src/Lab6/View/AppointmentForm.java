package Lab6.View;
import Lab6.Model.*;
import Lab6.Repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AppointmentForm extends BaseForm{
    private JPanel appointsPanel;
    private JLabel appoints;
    private JPanel headPanel;
    private JPanel fioPanel;
    private JLabel doctorFio;
    private JLabel patientFio;
    private JPanel infoPanel;
    private JLabel doctorProf;
    private JLabel patientAge;
    private JPanel allAppPanel;
    private JPanel complainsPanel;
    private JLabel complainsLabel;
    private Doctor currDoctor;
    private JTextField complainsTextField;
    private JTextField diagnosisTextField;
    private JTextField treatmentTextField;
    private JTextField conclusionTextField;
    private ArrayList<JTextField> exams;
    private JComboBox<Object> researchComboBox;
    private JLabel complainsIsLabel;
    private JPanel diagnosisPanel;
    private JLabel diagnosisLabel;
    private JLabel diagnosisIsLabel;
    private  JPanel treatmentPanel;
    private JLabel treatmentLabel;
    private JLabel treatmentIsLabel;
    private JPanel researchPanel;
    private JLabel researchLabel;
    private JLabel researchIsLabel;
    private JPanel conclusionPanel;
    private JLabel conclusionLabel;
    private JLabel conclusionIsLabel;
    private JPanel warningPanel;
    private JLabel warning;
    private JPanel savePanel;
    private JButton save;
    public AppointmentForm(JFrame lastForm, Appointment currAppoint, boolean isDoctor) {
        super(new Dimension(600, 600), isDoctor ? "Текущий приём" : "Прошедший приём");
        currDoctor=currAppoint.getDoctor();
        createComponents(currAppoint,isDoctor);
        setSizes(currAppoint,isDoctor);
        setLayouts();
        setAlignments();
        setFonts();
        setComponents(currAppoint,isDoctor);
        addListeners(lastForm,currAppoint);
    }
    private void createComponents(Appointment currAppoint, boolean isDoctor){
        appointsPanel =       new JPanel();
        appoints =            new JLabel(isDoctor ? "Текущий приём" : "Прошедший приём");
        headPanel =           new JPanel();
        fioPanel =            new JPanel();
        doctorFio =           new JLabel("Врач:  " + currAppoint.getDoctor().getFullName());
        patientFio =          new JLabel("Пациент:  " + currAppoint.getPatient().getFullName());
        infoPanel =           new JPanel();
        doctorProf =          new JLabel("Профессия:  " + currAppoint.getDoctor().getProfession());
        patientAge =          new JLabel("Год рождения:  " + currAppoint.getPatient().getYearOfBirth());
        allAppPanel =         new JPanel();
        complainsPanel =      new JPanel();
        exams =               new ArrayList<>();
        complainsLabel =      new JLabel("Жалобы: ");
        complainsTextField=   new JTextField(30);
        diagnosisTextField =  new JTextField(30);
        treatmentTextField =  new JTextField(30);
        conclusionTextField = new JTextField(28);
        researchComboBox =    new JComboBox<>();
        diagnosisPanel =      new JPanel();
        diagnosisLabel =      new JLabel("Диагноз: ");
        treatmentPanel =      new JPanel();
        treatmentLabel =      new JLabel("Лечение: ");
        researchPanel =       new JPanel();
        if(currDoctor instanceof Diagnostican){
            researchComboBox =    new JComboBox<>(((Diagnostican)currDoctor).getCanResearch().toArray());
            researchIsLabel =     new JLabel(((AppointmentOfDiagnostican) currAppoint).getResearch());
            conclusionIsLabel =   new JLabel(((AppointmentOfDiagnostican) currAppoint).getConclusion());
            researchLabel =       new JLabel(((Diagnostican) currDoctor).getTypeResearch() + ": ");
        }
        else{
            complainsIsLabel =    new JLabel(((AppointmentOfClinician) currAppoint).getComplaints());
            diagnosisIsLabel =    new JLabel(((AppointmentOfClinician) currAppoint).getDiagnosis());
            treatmentIsLabel =    new JLabel(((AppointmentOfClinician) currAppoint).getTreatment());
        }
        conclusionPanel =     new JPanel();
        conclusionLabel =     new JLabel("Заключение: ");
        warningPanel =        new JPanel();
        warning =             new JLabel();
        savePanel =           new JPanel();
        save =                new JButton("Сохранить");

    }
    private void setSizes(Appointment currAppoint,boolean isDoctor){
        appointsPanel.setMaximumSize(new Dimension(600,50));
        headPanel.setMaximumSize(new Dimension(600,70));
        if (currAppoint instanceof AppointmentOfClinician)
            allAppPanel.setMaximumSize(new Dimension(600, 250));
        else{
            allAppPanel.setMaximumSize(new Dimension(600,100));
            researchComboBox.setPreferredSize(new Dimension(200, 30));
        }


        if (isDoctor){
            warningPanel.setMaximumSize(new Dimension(600,50));
            savePanel.setMaximumSize(new Dimension(600,100));
        }
    }
    private void setLayouts(){
        appointsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headPanel.setLayout(new FlowLayout());
        fioPanel.setLayout(new BoxLayout(fioPanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        allAppPanel.setLayout(new BoxLayout(allAppPanel, BoxLayout.Y_AXIS));
        complainsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        diagnosisPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        treatmentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        researchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        conclusionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        warningPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    private void setAlignments(){
        researchComboBox.setAlignmentX(LEFT_ALIGNMENT);
        save.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void setFonts(){
        appoints.setFont(new Font("Arial", Font.PLAIN, 35));
        doctorFio.setFont(new Font("Arial", Font.PLAIN, 17));
        patientFio.setFont(new Font("Arial", Font.PLAIN, 17));
        doctorProf.setFont(new Font("Arial", Font.PLAIN, 17));
        patientAge.setFont(new Font("Arial", Font.PLAIN, 17));
        if (currDoctor instanceof Clinician){
            complainsLabel.setFont(new Font("Arial", Font.BOLD, 18));
            complainsTextField.setFont(new Font("Arial", Font.PLAIN, 18));
            complainsIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            diagnosisLabel.setFont(new Font("Arial", Font.BOLD, 18));
            diagnosisTextField.setFont(new Font("Arial", Font.PLAIN, 18));
            diagnosisIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            treatmentLabel.setFont(new Font("Arial", Font.BOLD, 18));
            treatmentTextField.setFont(new Font("Arial", Font.PLAIN, 18));
            treatmentIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        }
        else{
            researchLabel.setFont(new Font("Arial", Font.BOLD, 18));
            researchComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
            researchIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            conclusionLabel.setFont(new Font("Arial", Font.BOLD, 18));
            conclusionTextField.setFont(new Font("Arial", Font.PLAIN, 18));
            conclusionIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        }
        warning.setFont(new Font("Arial", Font.BOLD, 18));
        warning.setForeground(Color.red);
        save.setFont(new Font("Arial", Font.PLAIN, 25));
    }
    private void setComponents(Appointment currAppoint, boolean isDoctor) {
        appointsPanel.add(appoints);
        add(appointsPanel);

        add(Box.createVerticalStrut(10));

        fioPanel.add(doctorFio);
        fioPanel.add(patientFio);

        infoPanel.add(doctorProf);
        infoPanel.add(patientAge);

        headPanel.add(fioPanel);
        headPanel.add(Box.createHorizontalStrut(7));
        headPanel.add(infoPanel);
        add(headPanel);

        if (currDoctor instanceof Clinician) {
            //добавление жалоб
            complainsPanel.add(Box.createHorizontalStrut(10));
            complainsPanel.add(complainsLabel);
            if (isDoctor)
                complainsPanel.add(complainsTextField);
            else
                complainsPanel.add(complainsIsLabel);
            allAppPanel.add(complainsPanel);

            //динамическое добавление осмотра
            createExamines(currAppoint, isDoctor);
            revalidate();

            //добавление диагноза
            diagnosisPanel.add(Box.createHorizontalStrut(10));
            diagnosisPanel.add(diagnosisLabel);
            if (isDoctor)
                diagnosisPanel.add(diagnosisTextField);
            else
                diagnosisPanel.add(diagnosisIsLabel);
            allAppPanel.add(diagnosisPanel);

            //добавление лечения
            treatmentPanel.add(Box.createHorizontalStrut(10));
            treatmentPanel.add(treatmentLabel);
            if (isDoctor)
                treatmentPanel.add(treatmentTextField);
            else
                treatmentPanel.add(treatmentIsLabel);
            allAppPanel.add(treatmentPanel);

        }
        else {
            //добавление обследуемого
            researchPanel.add(Box.createHorizontalStrut(10));
            researchPanel.add(researchLabel);
            if (isDoctor) {
                researchComboBox.setSelectedIndex(0);
                researchPanel.add(researchComboBox);
            }
            else {
                researchPanel.add(researchIsLabel);
            }
            allAppPanel.add(researchPanel);

            //добавление заключения
            conclusionPanel.add(Box.createHorizontalStrut(10));
            conclusionPanel.add(conclusionLabel);
            if (isDoctor)
                conclusionPanel.add(conclusionTextField);
            else
                conclusionPanel.add(conclusionIsLabel);
            allAppPanel.add(conclusionPanel);
        }

        add(allAppPanel);
        revalidate();

        if (isDoctor) {

            add(Box.createVerticalStrut(10));

            warningPanel.add(warning);
            add(warningPanel);

            savePanel.add(save);
            add(savePanel);


        }
    }
    private void addListeners(JFrame lastForm, Appointment currAppoint){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lastForm.dispose();
            }
        });
        save.addActionListener(e ->{
            boolean isAllSet = isAllSet();
            if (isAllSet) {
                currAppoint.setComplite(true);
                if (currDoctor instanceof Clinician) {
                    AppointmentOfClinician currApp = (AppointmentOfClinician) currAppoint;
                    currApp.setComplaints(complainsTextField.getText());
                    StringBuilder ex = new StringBuilder();
                    for (int i = 0; i < exams.size(); i++) {
                        ex.append(exams.get(i).getText()).append("\n");
                    }
                    currApp.setExamines(ex.toString());

                    currApp.setDiagnosis(diagnosisTextField.getText());
                    currApp.setTreatment(treatmentTextField.getText());
                }
                else {
                    AppointmentOfDiagnostican currApp = (AppointmentOfDiagnostican) currAppoint;
                    currApp.setResearch(researchComboBox.getSelectedItem().toString());
                    currApp.setConclusion(conclusionTextField.getText());
                }
                Repository.updateAppointment(currAppoint);
                this.dispose();
            }
            else{
                warning.setText("Не все поля заполнены!");
            }
        });
    }
    private void createExamines(Appointment currAppoint, boolean isDoctor){
        String[] exam = {};
        if (currAppoint instanceof AppointmentOfClinician && currAppoint.getComplite())
            exam = ((AppointmentOfClinician) currAppoint).getExamines().split("\n");
        int i = 0;
        for (String x : ((Clinician) currDoctor).getCanExamine()) {
            JPanel examinePanel = new JPanel();
            examinePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            examinePanel.add(Box.createHorizontalStrut(10));
            JLabel examineLabel = new JLabel(x + ": ");
            examineLabel.setFont(new Font("Arial", Font.BOLD, 18));
            examinePanel.add(examineLabel);
            if (isDoctor) {
                JTextField examineTextField = new JTextField(30);
                examineTextField.setFont(new Font("Arial", Font.PLAIN, 18));
                examinePanel.add(examineTextField);
                exams.add(examineTextField);
            }
            else {
                JLabel examineIsLabel = new JLabel(exam[i]);
                i++;
                examineIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                examinePanel.add(examineIsLabel);
            }
            allAppPanel.add(examinePanel);
        }
    }
    private boolean isAllSet() {
        boolean isAllSet = false;
        if (currDoctor instanceof Clinician){
            boolean isAllExSet =true;
            for (JTextField x: exams){
                if (x.getText().isEmpty()){
                    isAllExSet=false;
                    break;
                }
            }
            if (isAllExSet && !(complainsTextField.getText().isEmpty()) && !(treatmentTextField.getText().isEmpty()) && !(diagnosisTextField.getText().isEmpty()))
                isAllSet=true;
        }
        else {
            if(!(conclusionTextField.getText().isEmpty()))
                isAllSet=true;
        }
        return isAllSet;
    }
}


