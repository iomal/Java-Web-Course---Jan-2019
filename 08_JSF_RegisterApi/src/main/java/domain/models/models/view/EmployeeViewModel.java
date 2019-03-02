package domain.models.models.view;

public class EmployeeViewModel {
    private String firstName;
    private String lastName;
    private String position;
    private Double salary;
    private Integer age;

    public EmployeeViewModel() {
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
}
