package Lab6.Repository;

import Lab6.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class DataBaseWorker {

    private static Connection conn;
    public DataBaseWorker(){
        String url = "jdbc:postgresql://localhost:5432/dbClinic";
        String user = "postgres";
        String password = "database";

        Properties auth = new Properties();
        auth.setProperty("user", user);
        auth.setProperty("password", password);

        try {
            conn = DriverManager.getConnection(url, auth);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Doctor> selectDoctors(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet doctorsTable = statement.executeQuery("select * from doctors");
            while (doctorsTable.next()){
                if(doctorsTable.getBoolean("is_clinician")){
                    Clinician clinician = new Clinician();
                    clinician.setId(doctorsTable.getInt("id"));
                    clinician.setFullName(doctorsTable.getString("fullname"));
                    clinician.setPassword(doctorsTable.getString("password"));
                    clinician.setProfession(doctorsTable.getString("profession"));
                    clinician.setCanExamine(new ArrayList<>(Arrays.asList(doctorsTable.getString("can_work_with").split("&"))));
                    doctors.add(clinician);
                }
                else{
                    Diagnostican diagnostican = new Diagnostican();
                    diagnostican.setId(doctorsTable.getInt("id"));
                    diagnostican.setFullName(doctorsTable.getString("fullname"));
                    diagnostican.setPassword(doctorsTable.getString("password"));
                    diagnostican.setProfession(doctorsTable.getString("profession"));
                    diagnostican.setTypeResearch(doctorsTable.getString("type_research"));
                    diagnostican.setCanResearch(new ArrayList<>(Arrays.asList(doctorsTable.getString("can_work_with").split("&"))));
                    doctors.add(diagnostican);
                }
            }
            doctorsTable.close();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return doctors;
    }
    public static ArrayList<Patient> selectPatients(){
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet patientsTable = statement.executeQuery("select * from patients");
            while (patientsTable.next()){
                Patient patient = new Patient();
                patient.setId(patientsTable.getInt("id"));
                patient.setFullName(patientsTable.getString("fullname"));
                patient.setPassword(patientsTable.getString("password"));
                patient.setYearOfBirth(patientsTable.getInt("year_of_birth"));
                patients.add(patient);
                //System.out.print("\n"+patient.getId()+"\n"+patient.getFullName()+"\n"+patient.getPassword()+"\n"+patient.getYearOfBirth()+"\n");
            }
            patientsTable.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
    public static ArrayList<Appointment> selectAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet appointmentsTable = statement.executeQuery("select * from appointments");
            while (appointmentsTable.next()) {
                if (appointmentsTable.getBoolean("is_clinician_appoint")) {
                    AppointmentOfClinician appointmentOfClinician = new AppointmentOfClinician();
                    appointmentOfClinician.setId(appointmentsTable.getInt("id"));
                    int idd = appointmentsTable.getInt("id_doctor");
                    for (Doctor x : Repository.getDoctors()) {
                        if (x.getId() == idd) {
                            appointmentOfClinician.setDoctor(x);
                            break;
                        }
                    }
                    int idp = appointmentsTable.getInt("id_patient");
                    for (Patient x : Repository.getPatients()) {
                        if (x.getId() == idp) {
                            appointmentOfClinician.setPatient(x);
                            break;
                        }
                    }
                    appointmentOfClinician.setDate(appointmentsTable.getString("date"));
                    appointmentOfClinician.setTime(appointmentsTable.getString("time"));
                    appointmentOfClinician.setComplite(appointmentsTable.getBoolean("is_complite"));
                    if (appointmentOfClinician.getComplite()) {
                        appointmentOfClinician.setComplaints(appointmentsTable.getString("complaints"));
                        appointmentOfClinician.setExamines(appointmentsTable.getString("examines"));
                        appointmentOfClinician.setDiagnosis(appointmentsTable.getString("diagnosis"));
                        appointmentOfClinician.setTreatment(appointmentsTable.getString("treatment"));
                    }
                    appointments.add(appointmentOfClinician);
                } else {
                    AppointmentOfDiagnostican appointmentOfDiagnostican = new AppointmentOfDiagnostican();
                    appointmentOfDiagnostican.setId(appointmentsTable.getInt("id"));
                    int idd = appointmentsTable.getInt("id_doctor");
                    for (Doctor x : Repository.getDoctors()) {
                        if (x.getId() == idd) {
                            appointmentOfDiagnostican.setDoctor(x);
                            break;
                        }
                    }
                    int idp = appointmentsTable.getInt("id_patient");
                    for (Patient x : Repository.getPatients()) {
                        if (x.getId() == idp) {
                            appointmentOfDiagnostican.setPatient(x);
                            break;
                        }
                    }
                    appointmentOfDiagnostican.setDate(appointmentsTable.getString("date"));
                    appointmentOfDiagnostican.setTime(appointmentsTable.getString("time"));
                    appointmentOfDiagnostican.setComplite(appointmentsTable.getBoolean("is_complite"));
                    if (appointmentOfDiagnostican.getComplite()) {
                        appointmentOfDiagnostican.setResearch(appointmentsTable.getString("examines"));
                        appointmentOfDiagnostican.setConclusion(appointmentsTable.getString("diagnosis"));
                    }
                    appointments.add(appointmentOfDiagnostican);
                }
            }
            appointmentsTable.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public static void insertAppointment(Appointment appointment){
        try {
            PreparedStatement statement = conn.prepareStatement("insert into appointments" +
                    "(id_doctor, id_patient, date, time, is_complite, is_clinician_appoint) values (?,?,?,?,?,?)");
            statement.setInt(1,appointment.getDoctor().getId());
            statement.setInt(2,appointment.getPatient().getId());
            statement.setString(3,appointment.getDate());
            statement.setString(4,appointment.getTime());
            statement.setBoolean(5,appointment.getComplite());
            statement.setBoolean(6,appointment instanceof AppointmentOfClinician);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointent(int appoinmentId){
        try {
            Statement statement = conn.createStatement();
            statement.execute("delete from appointments where id = "+appoinmentId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAppointment(Appointment appointment){
        try {
            PreparedStatement statement = conn.prepareStatement("update appointments set " +
                    "is_clinician_appoint=? , complaints=?, examines=?, diagnosis=?, treatment=?, is_complite=? where id = "+appointment.getId());
            statement.setBoolean(1,appointment instanceof AppointmentOfClinician);
            statement.setBoolean(6, true);
            if (appointment instanceof AppointmentOfClinician){
                AppointmentOfClinician appointmentC = (AppointmentOfClinician)appointment;
                statement.setString(2,(appointmentC.getComplaints()));
                statement.setString(3,(appointmentC.getExamines()));
                statement.setString(4,(appointmentC.getDiagnosis()));
                statement.setString(5,(appointmentC.getTreatment()));
            }
            else {
                AppointmentOfDiagnostican appointmentD = (AppointmentOfDiagnostican) appointment;
                statement.setNull(2, Types.VARCHAR);
                statement.setString(3,(appointmentD.getResearch()));
                statement.setString(4,(appointmentD.getConclusion()));
                statement.setNull(5, Types.VARCHAR);
                System.out.println(statement.toString());
            }
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delConn(){
        try {
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
