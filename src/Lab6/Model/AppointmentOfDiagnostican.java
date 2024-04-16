
package Lab6.Model;
public class AppointmentOfDiagnostican extends Appointment {
    private String research;
    private String conclusion;
    public AppointmentOfDiagnostican(Doctor doctor, Patient patient, String date, String time){
        this.setDoctor(doctor);
        this.setPatient(patient);
        this.setDate(date);
        this.setTime(time);
        this.setComplite(false);
    }

    public AppointmentOfDiagnostican(){

    }
    public void setResearch(String research) {
        this.research = research;
    }
    public String getResearch() {
        return research;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public String getConclusion() {
        return conclusion;
    }
}
