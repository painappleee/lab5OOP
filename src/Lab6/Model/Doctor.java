package Lab6.Model;

import java.util.ArrayList;

public abstract class Doctor extends Person{
    private String profession;
    public abstract void admitPatient(Appointment appointment, ArrayList<String> writeToAppointment);
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getProfession() {
        return profession;
    }
}
