package repository;

import domain.models.enitities.Employee;
import org.apache.commons.lang.SerializationUtils;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named (value = "em")
@SessionScoped
public class EmplyeeRepositoryImpl implements EmployeeRepository, Serializable {
    private static List<Employee> employees;
    private Employee employee;

    static {
        employees = new ArrayList<>(Arrays
                .asList(new Employee("Mark", "Otto", "Computer Specialist", 5500D, 29),
                        new Employee("Donald", "Fitzgerald", "Marketing Manager", 2500D, 34),
                        new Employee("Casio", "Florenco", "Graphic Designer", 4000D, 27),
                        new Employee("Isacc", "Netero", "Tech Support", 4900D, 30)));
    }

    public EmplyeeRepositoryImpl() {
        this.employee = new Employee("Isacc", "Netero", "Tech Support", 4900D, 30);
    }

    @Override
    public void add(Employee e) {
        Employee eNew = (Employee) SerializationUtils.clone(e);
        employees.add(eNew);
    }

    @Override
    public void delete(Employee e) {
        employees.remove(e);
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static void setEmployees(List<Employee> employees) {
        EmplyeeRepositoryImpl.employees = employees;
    }

    public Employee getEmployee() {
        return this.employee;
    }
//
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
