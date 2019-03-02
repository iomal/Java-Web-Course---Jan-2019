package exam.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Job extends BaseEntity {

    private Sector sector;
    private String profession;
    private BigDecimal Salary;
    private String description;

    public Job() {
    }

    @Enumerated(value = EnumType.STRING)
    @NotNull
    public Sector getSector() {
        return this.sector;
    }
    @NotNull
    public String getProfession() {
        return this.profession;
    }

    @Column(columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    public BigDecimal getSalary() {
        return this.Salary;
    }

    @NotNull
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
