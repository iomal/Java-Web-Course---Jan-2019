package exam.domain.models.binding;

import exam.domain.entities.Sector;

import java.io.Serializable;
import java.math.BigDecimal;

public class JobBindingModel implements Serializable {
    private String id;
    private String sector;
    private String profession;
    private BigDecimal Salary;
    private String description;

    public JobBindingModel() {
    }

    public String getId() {
        return this.id;
    }

    public String getSector() {
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

    public void setSector(String sector) {
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

    public void setId(String id) {
        this.id = id;
    }
}
