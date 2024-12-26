package ehu.java.interpoldemo.model;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class Criminal extends AbstractModel {


    public Criminal(CriminalBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.dateOfBirth = builder.dateOfBirth;
        this.citizenship = builder.citizenship;
        this.description = builder.description;
        this.reward = builder.reward;
        this.isArrested = builder.isArrested;
        this.image = builder.image;
    }

    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String citizenship;
    private String description;
    private double reward;
    private boolean isArrested;
    private byte[] image;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getCitizenship() {
        return citizenship;
    }
    public String getDescription() {
        return description;
    }
    public double getReward() {
        return reward;
    }
    public boolean isArrested() {
        return isArrested;
    }
    public byte[] getImage() {return image;}


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Criminal criminal = (Criminal) o;
        return id == criminal.id && Double.compare(reward, criminal.reward) == 0 && isArrested == criminal.isArrested && Objects.equals(name, criminal.name) && Objects.equals(surname, criminal.surname) && Objects.equals(dateOfBirth, criminal.dateOfBirth) && Objects.equals(citizenship, criminal.citizenship) && Objects.equals(description, criminal.description) && Objects.deepEquals(image, criminal.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, dateOfBirth, citizenship, description, reward, isArrested, image);
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
                .add("image=" + Arrays.toString(image))
                .toString();
    }

    public static class CriminalBuilder{
        private int id;
        private String name;
        private String surname;
        private LocalDate dateOfBirth;
        private String citizenship;
        private String description;
        private double reward;
        private boolean isArrested;
        private byte[] image;

        public CriminalBuilder(String name, String surname){
            this.name = name;
            this.surname = surname;
        }

        public CriminalBuilder setId(int id){
            this.id = id;
            return this;
        }

        public CriminalBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public CriminalBuilder setCitizenship(String citizenship) {
            this.citizenship = citizenship;
            return this;
        }
        public CriminalBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
        public CriminalBuilder setReward(double reward) {
            this.reward = reward;
            return this;
        }
        public CriminalBuilder setIsArrested(boolean isArrested) {
            this.isArrested = isArrested;
            return this;
        }

        public CriminalBuilder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Criminal build(){
            return new Criminal(this);
        }
    }
}
