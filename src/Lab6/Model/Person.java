package Lab6.Model;

public abstract class Person {
    private String fullName;
    private String password;
    private int id;
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setPassword(String password) { this.password = password;}
    public String getPassword() {return password; }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
