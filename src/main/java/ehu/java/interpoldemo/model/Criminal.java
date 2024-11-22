package ehu.java.interpoldemo.model;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Criminal {


    //todo
    public Criminal() {
    }

    public Criminal(int id, String name, String surname, LocalDate dateOfBirth, String citizenship, String description, double reward, boolean isArrested) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.description = description;
        this.reward = reward;
        this.isArrested = isArrested;
    }

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

    //todo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criminal criminal = (Criminal) o;
        return id == criminal.id && Double.compare(reward, criminal.reward) == 0 && isArrested == criminal.isArrested && Objects.equals(name, criminal.name) && Objects.equals(surname, criminal.surname) && Objects.equals(dateOfBirth, criminal.dateOfBirth) && Objects.equals(citizenship, criminal.citizenship) && Objects.equals(description, criminal.description);
    }

    //todo
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, dateOfBirth, citizenship, description, reward, isArrested);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Criminal.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("citizenship='" + citizenship + "'")
                .add("description='" + description + "'")
                .add("reward=" + reward)
                .add("isArrested=" + isArrested)
                .toString();
    }
}
