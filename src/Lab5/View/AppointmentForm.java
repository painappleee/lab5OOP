package Lab5.View;
import Lab5.Controller.Controller;
import Lab5.Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AppointmentForm extends JFrame {
    public AppointmentForm(JFrame lastForm, Appointment currAppoint, boolean isDoctor) {

        super(isDoctor ? "Текущий приём" : "Прошедший приём");
        setSize(600, 600);
        Controller.baseForm(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lastForm.dispose();
            }
        });

        //Заголовок
        JPanel appointsPanel = new JPanel();
        appointsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        appointsPanel.setMaximumSize(new Dimension(600,50));
        JLabel appoints = new JLabel(isDoctor ? "Текущий приём" : "Прошедший приём");
        appoints.setFont(new Font("Arial", Font.PLAIN, 35));
        appointsPanel.add(appoints);
        add(appointsPanel);

        add(Box.createVerticalStrut(10));

        //Информация о приёме
        JPanel headPanel = new JPanel();
        headPanel.setLayout(new FlowLayout());
        headPanel.setMaximumSize(new Dimension(600,70));

        JPanel fioPanel = new JPanel();
        fioPanel.setLayout(new BoxLayout(fioPanel, BoxLayout.Y_AXIS));
        JLabel doctorFio = new JLabel("Врач:  " + currAppoint.getDoctor().getFullName());
        doctorFio.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabel patientFio = new JLabel("Пациент:  " + currAppoint.getPatient().getFullName());
        patientFio.setFont(new Font("Arial", Font.PLAIN, 17));

        fioPanel.add(doctorFio);
        fioPanel.add(patientFio);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel doctorProf = new JLabel("Профессия:  " + currAppoint.getDoctor().getProfession());
        doctorProf.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabel patientAge = new JLabel("Год рождения:  " + currAppoint.getPatient().getYearOfBirth());
        patientAge.setFont(new Font("Arial", Font.PLAIN, 17));
        infoPanel.add(doctorProf);
        infoPanel.add(patientAge);

        headPanel.add(fioPanel);
        headPanel.add(Box.createHorizontalStrut(7));
        headPanel.add(infoPanel);

        add(headPanel);

        //информация о пациенте
        JPanel allAppPanel = new JPanel();
        allAppPanel.setLayout(new BoxLayout(allAppPanel, BoxLayout.Y_AXIS));
        allAppPanel.setMaximumSize(new Dimension(600, 300));

        JPanel complainsPanel = new JPanel();
        JLabel complainsLabel = new JLabel("Жалобы: ");
        complainsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        complainsPanel.add(Box.createHorizontalStrut(10));
        complainsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        complainsPanel.add(complainsLabel);

        Doctor currDoctor = currAppoint.getDoctor();
        String[] exam = {};
        ArrayList<JTextField> exams = new ArrayList<>();
        if (currAppoint instanceof AppointmentOfClinician && currAppoint.getComplite())
            exam = ((AppointmentOfClinician) currAppoint).getExamines().split("\n");

        //поля ввода
        JTextField complainsTextField= new JTextField(30);
        JTextField diagnosisTextField = new JTextField(30);
        JTextField treatmentTextField = new JTextField(30);
        JTextField conclusionTextField = new JTextField(28);
        JComboBox comboBox = new JComboBox();

        if (currDoctor instanceof Clinician) {

            if (isDoctor) {
                complainsTextField.setFont(new Font("Arial", Font.PLAIN, 18));
                complainsPanel.add(complainsTextField);
            } else {
                JLabel complainsIsLabel = new JLabel(((AppointmentOfClinician) currAppoint).getComplaints());
                complainsIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                complainsPanel.add(complainsIsLabel);
            }
            allAppPanel.add(complainsPanel);

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
                } else {
                    JLabel examineIsLabel = new JLabel(exam[i]);
                    i++;
                    examineIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    examinePanel.add(examineIsLabel);

                }
                allAppPanel.add(examinePanel);
                revalidate();
            }

            JPanel diagnosisPanel = new JPanel();
            diagnosisPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            diagnosisPanel.add(Box.createHorizontalStrut(10));
            JLabel diagnosisLabel = new JLabel("Диагноз: ");
            diagnosisLabel.setFont(new Font("Arial", Font.BOLD, 18));
            diagnosisPanel.add(diagnosisLabel);

            if (isDoctor) {
                diagnosisTextField.setFont(new Font("Arial", Font.PLAIN, 18));
                diagnosisPanel.add(diagnosisTextField);
            } else {
                JLabel diagnosisIsLabel = new JLabel(((AppointmentOfClinician) currAppoint).getDiagnosis());
                diagnosisIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                diagnosisPanel.add(diagnosisIsLabel);
            }

            allAppPanel.add(diagnosisPanel);

            JPanel treatmentPanel = new JPanel();
            treatmentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            treatmentPanel.add(Box.createHorizontalStrut(10));
            JLabel treatmentLabel = new JLabel("Лечение: ");
            treatmentLabel.setFont(new Font("Arial", Font.BOLD, 18));
            treatmentPanel.add(treatmentLabel);

            if (isDoctor) {
                treatmentTextField.setFont(new Font("Arial", Font.PLAIN, 18));
                treatmentPanel.add(treatmentTextField);
            } else {
                JLabel treatmentIsLabel = new JLabel(((AppointmentOfClinician) currAppoint).getTreatment());
                treatmentIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                treatmentPanel.add(treatmentIsLabel);
            }

            allAppPanel.add(treatmentPanel);

        } else {
            allAppPanel.setMaximumSize(new Dimension(600,150));
            JPanel researchPanel = new JPanel();
            researchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            researchPanel.add(Box.createHorizontalStrut(10));
            JLabel researchLabel = new JLabel(((Diagnostican) currDoctor).getTypeResearch() + ": ");
            researchLabel.setFont(new Font("Arial", Font.BOLD, 18));
            researchPanel.add(researchLabel);

            if (isDoctor) {
                comboBox = new JComboBox(((Diagnostican)currDoctor).getCanResearch().toArray());
                comboBox.setPreferredSize(new Dimension(200, 30));
                comboBox.setAlignmentX(LEFT_ALIGNMENT);
                comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                comboBox.setSelectedIndex(0);
                researchPanel.add(comboBox);
            } else {
                JLabel researchIsLabel = new JLabel(((AppointmentOfDiagnostican) currAppoint).getResearch());
                researchIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                researchPanel.add(researchIsLabel);
            }
            allAppPanel.add(researchPanel);

            JPanel conclusionPanel = new JPanel();
            conclusionPanel.add(Box.createHorizontalStrut(10));
            conclusionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel conclusionLabel = new JLabel("Заключение: ");
            conclusionLabel.setFont(new Font("Arial", Font.BOLD, 18));
            conclusionPanel.add(conclusionLabel);

            if (isDoctor) {
                conclusionTextField.setFont(new Font("Arial", Font.PLAIN, 18));
                conclusionPanel.add(conclusionTextField);

            } else {
                JLabel conclusionIsLabel = new JLabel(((AppointmentOfDiagnostican) currAppoint).getConclusion());
                conclusionIsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                conclusionPanel.add(conclusionIsLabel);
            }
            allAppPanel.add(conclusionPanel);

        }

        add(allAppPanel);
        revalidate();

        if(isDoctor){
            JPanel savePanel = new JPanel();
            savePanel.setMaximumSize(new Dimension(600,100));
            JButton save = new JButton("Сохранить");
            save.setAlignmentX(CENTER_ALIGNMENT);
            save.setFont(new Font("Arial", Font.PLAIN, 25));
            savePanel.add(save);
            add(savePanel);

            JComboBox finalComboBox = comboBox;
            save.addActionListener(e ->{
                currAppoint.setComplite(true);
                if (currDoctor instanceof Clinician){
                    AppointmentOfClinician currApp = (AppointmentOfClinician)currAppoint;
                    currApp.setComplaints(complainsTextField.getText());
                    StringBuilder ex= new StringBuilder();
                    for(int i=0; i< exams.size();i++){
                        ex.append(exams.get(i).getText()).append("\n");
                    }
                    currApp.setExamines(ex.toString());

                    currApp.setDiagnosis(diagnosisTextField.getText());
                    currApp.setTreatment(treatmentTextField.getText());
                }
                else {
                    AppointmentOfDiagnostican currApp = (AppointmentOfDiagnostican) currAppoint;
                    currApp.setResearch(finalComboBox.getSelectedItem().toString());
                    currApp.setConclusion(conclusionTextField.getText());
                }
                this.dispose();


            });
        }
    }
}


