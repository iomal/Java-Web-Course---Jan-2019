package exam.domain.models.binding;

import exam.domain.entities.Sector;

import java.math.BigDecimal;

public class AddJobBindingModel {
    private Sector sector;
    private String profession;
    private BigDecimal Salary;
    private String description;

    public AddJobBindingModel() {
    }

    public Sector getSector() {
        return this.sector;
    }

    public String getProfession() {
        return this.profession;
    }

    public BigDecimal getSalary() {
        return this.Salary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setSalary(BigDecimal salary) {
        Salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
