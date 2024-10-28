package ehu.java.interpoldemo.model;
import java.time.LocalDate;

public class Criminal {
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String citizenship;
    private String description;
    private double reward;
    private boolean isArrested;

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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getCitizenship() {
        return citizenship;
    }
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getReward() {
        return reward;
    }
    public void setReward(double reward) {
        this.reward = reward;
    }
    public boolean isArrested() {
        return isArrested;
    }
    public void setArrested(boolean isArrested) {
    }
}
