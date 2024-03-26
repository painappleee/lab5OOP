package Lab5.Controller;

import Lab5.Model.*;
import Lab5.View.Autorization;

import javax.swing.*;
import java.util.HashSet;

public class Controller {
    public static Clinic clinic = new Clinic("Поликлиника №11");

    public Controller() {
        clinic.iniDoctors();
        clinic.iniPatients();
        clinic.iniAppointments();

        new Autorization();
    }

    public static Person autorization(int type, String login, String password) {
        Person currentPerson = null;
        if (type == 1) {
            for (Doctor x : Controller.clinic.getDoctors()) {
                if (x.getFullName().equals(login) && (x.getPassword().equals(password))) {
                    currentPerson = x;
                    break;
                }
            }
        }
        else if (type == 2) {
            for (Patient x : Controller.clinic.getPatients()) {
                if (x.getFullName().equals(login) && (x.getPassword().equals(password))) {
                    currentPerson = x;
                    break;
                }
            }
        }
        return currentPerson;
    }

    public static String[] getComboboxItem(boolean isClinincan){
        HashSet<String> doctorProf = new HashSet<>();
        for (Doctor doctor : Controller.clinic.getDoctors()){
            if (isClinincan){
                if (doctor instanceof Clinician)
                    doctorProf.add(doctor.getProfession());
            }
            else {
                if (doctor instanceof Diagnostican)
                    doctorProf.add(doctor.getProfession());
            }
        }


        return doctorProf.toArray(new String[]{});
    }

    public static void baseForm(JFrame frame){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/Lab5/images/icon.png");
        frame.setIconImage(icon.getImage());
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }
}



