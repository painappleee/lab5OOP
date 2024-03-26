package Lab5.Model;
public class Patient extends Person{
    private int yearOfBirth;

    public Patient(String fullname,Integer yearOfBirth, String password){
        super(fullname, password);
        this.yearOfBirth = yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public Integer getYearOfBirth() {
        return yearOfBirth;
    }
}
