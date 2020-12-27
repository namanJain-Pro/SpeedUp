package DataModel;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String dateOfBirth;
    private int practiceRecord;
    private int testRecord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPracticeRecord() {
        return practiceRecord;
    }

    public void setPracticeRecord(int practiceRecord) {
        this.practiceRecord = practiceRecord;
    }

    public int getTestRecord() {
        return testRecord;
    }

    public void setTestRecord(int testRecord) {
        this.testRecord = testRecord;
    }
}

