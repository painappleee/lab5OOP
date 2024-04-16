package Lab6.Model;
import java.util.ArrayList;

public class Clinician extends Doctor {
    private ArrayList<String> canExamine;
    public void setCanExamine(ArrayList<String> canExamine) {
        this.canExamine = canExamine;
    }
    public ArrayList<String> getCanExamine() {
        return canExamine;
    }
    public void admitPatient(Appointment appointment, ArrayList<String> writeToAppointment) {
        appointment.setComplite(true);
        AppointmentOfClinician appointmentOfClinician = (AppointmentOfClinician)appointment;
        listenComplaints(appointmentOfClinician, writeToAppointment.get(0));
        examinePatient(appointmentOfClinician, writeToAppointment.get(1));
        determineDiagnosis(appointmentOfClinician,writeToAppointment.get(2));
        prescribeTreatment(appointmentOfClinician, writeToAppointment.get(3));
    }
    private void listenComplaints(AppointmentOfClinician appointment, String complaints){
        appointment.setComplaints(complaints);
    }
    private void examinePatient(AppointmentOfClinician appointment, String examines){
        appointment.setExamines(examines);
    }
    private void determineDiagnosis(AppointmentOfClinician appointment, String diagnosis) {
        appointment.setDiagnosis(diagnosis);
    }
    private void prescribeTreatment(AppointmentOfClinician appointment, String treatment) {
       appointment.setTreatment(treatment);
    }
}