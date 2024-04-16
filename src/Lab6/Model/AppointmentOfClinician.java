package Lab6.Model;
public class AppointmentOfClinician extends Appointment {
    public AppointmentOfClinician(Doctor doctor, Patient patient, String date, String time){
        this.setDoctor(doctor);
        this.setPatient(patient);
        this.setDate(date);
        this.setTime(time);
        this.setComplite(false);
    }
    public AppointmentOfClinician(){

    }
    private String complaints;
    private String examines;
    private String diagnosis;
    private String treatment;

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }
    public void setExamines(String examines) {
        this.examines = examines;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    public String getComplaints() {
        return complaints;
    }
    public String getExamines() {
        return examines;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public String getTreatment() {
        return treatment;
    }
}
