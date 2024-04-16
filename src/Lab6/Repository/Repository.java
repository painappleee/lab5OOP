package Lab6.Repository;

import Lab6.Model.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Repository {
    private static Clinic clinic;
    public Repository(String clinicName) {
        clinic = new Clinic(clinicName);
        clinic.setClinicName(clinicName);
        new DataBaseWorker();
        clinic.setDoctors(DataBaseWorker.selectDoctors());
        clinic.setPatients(DataBaseWorker.selectPatients());
        clinic.setAppointments(DataBaseWorker.selectAppointments());
    }
    public static Person getPerson(int type, String login, String password) {
        Person currentPerson = null;
        if (type == 1) {
            for (Doctor x : Repository.clinic.getDoctors()) {
                if (x.getFullName().equals(login) && (x.getPassword().equals(password))) {
                    currentPerson = x;
                    break;
                }
            }
        }
        else if (type == 2) {
            for (Patient x : Repository.clinic.getPatients()) {
                if (x.getFullName().equals(login) && (x.getPassword().equals(password))) {
                    currentPerson = x;
                    break;
                }
            }
        }
        return currentPerson;
    }
    public static String[] getDoctorProf(boolean isClinician){
        HashSet<String> doctorProf = new HashSet<>();
        for (Doctor doctor : Repository.clinic.getDoctors()){
            if (isClinician){
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
    public static String getClinicName() {
        return clinic.getClinicName();
    }
    public static ArrayList<Appointment> getAppointments(){return clinic.getAppointments();}
    public static ArrayList<Doctor> getDoctors(){return clinic.getDoctors();}
    public static ArrayList<Patient> getPatients(){return clinic.getPatients();}
    public static void insertAppointment(Appointment appointment){
        getAppointments().add(appointment);
        DataBaseWorker.insertAppointment(appointment);
    }
    public static void deleteAppointment(Appointment appointment){
        getAppointments().remove(appointment);
        DataBaseWorker.deleteAppointent(appointment.getId());
    }
    public static void updateAppointment(Appointment appointment){
        DataBaseWorker.updateAppointment(appointment);
    }
    public static void closeConn(){
        DataBaseWorker.delConn();
    }


}



