package Lab6.Model;
import java.util.ArrayList;
import java.util.Arrays;

public class Clinic {
    private  String clinicName;

    public Clinic(String clinicName) {
        setClinicName(clinicName);
    }
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void setClinicName(String clinicName){ this.clinicName = clinicName; }

    public String getClinicName(){ return clinicName; }
    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
    public void addPatient(Patient patient){
        patients.add(patient);
    }
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }
    public void delDoctor(Doctor doctor){
        doctors.remove(doctor);
    }
    public void delPatient(Patient patient){
        patients.remove(patient);
    }
    public void delAppointment(Appointment appointment){
        appointments.remove(appointment);
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
   }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    
    public void setDoctors(ArrayList<Doctor> doctors){
        this.doctors = doctors;
    }
    public void setPatients(ArrayList<Patient> patients){
        this.patients = patients;
    }

    public void setAppointments(ArrayList<Appointment> appointments){
        this.appointments = appointments;
    }
}
