package Lab5.Model;
import java.util.ArrayList;

public class Diagnostican extends Doctor{
    private String typeResearch;
    private ArrayList<String> canResearch;
    public Diagnostican(String fullName, String profession, String typeResearch, ArrayList<String> canResearch, String password){
        super(fullName,password);
        this.setProfession(profession);
        this.setTypeResearch(typeResearch);
        this.setCanResearch(canResearch);
    }
    public void admitPatient(Appointment appointment,  ArrayList<String> writeToAppointment) {
        appointment.setComplite(true);
        AppointmentOfDiagnostican appointmentOfDiagnostican = (AppointmentOfDiagnostican)appointment;
        makeResearch(appointmentOfDiagnostican,writeToAppointment.get(0));
        makeConclusion(appointmentOfDiagnostican, writeToAppointment.get(1));

    }
    private void makeResearch(AppointmentOfDiagnostican appointment, String research){
        appointment.setResearch(typeResearch+" "+research);
    }
    private void makeConclusion(AppointmentOfDiagnostican appointment, String conclusion){
        appointment.setConclusion(conclusion);
    }
    public void setTypeResearch(String typeResearch) {
        this.typeResearch = typeResearch;
    }
    public String getTypeResearch() {
        return typeResearch;
    }
    public void setCanResearch(ArrayList<String> canResearch) {
        this.canResearch = canResearch;
    }
    public ArrayList<String> getCanResearch() {
        return canResearch;
    }
}
