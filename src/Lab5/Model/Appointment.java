package Lab5.Model;
public abstract class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String date;
    private String time;
    private boolean isComplite;
    public void setComplite(boolean complite) {
        isComplite = complite;
    }
    public boolean getComplite(){ return isComplite;}
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;

    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
}
