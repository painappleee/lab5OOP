package Lab5.Model;

public abstract class Person {
    private String fullName;
    private String login;
    private String password;
    public Person(String fullName, String password){
        setFullName(fullName);
        setPassword(password);

    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setPassword(String password) { this.password = password;}
    public String getPassword() {return password; }
}
