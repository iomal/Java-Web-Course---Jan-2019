package domain.models.enitities;

import java.io.Serializable;
import java.util.Objects;


public class Employee implements Serializable {

    private String firstName;
    private String lastName;
    private String position;
    private Double salary;
    private Integer age;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String position, Double salary, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPosition() {
        return this.position;
    }

    public Double getSalary() {
        return this.salary;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFirstName(), employee.getFirstName()) &&
                Objects.equals(getLastName(), employee.getLastName()) &&
                Objects.equals(getPosition(), employee.getPosition()) &&
                Objects.equals(getSalary(), employee.getSalary()) &&
                Objects.equals(getAge(), employee.getAge());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getPosition(), getSalary(), getAge());
    }
}
