package fdmc.domain.enitites;

import java.io.Serializable;

public class Cat implements Serializable {
    static final long serialVersionUID = 42L;
    private String name;
    private String breed;
    private String colour;
    private Integer age;

    public Cat() {
    }

    public Cat(String name, String breed, String colour, Integer age) {
        this.name = name;
        this.breed = breed;
        this.colour = colour;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
