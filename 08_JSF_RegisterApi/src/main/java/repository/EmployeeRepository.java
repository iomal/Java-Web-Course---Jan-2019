package repository;

import domain.models.enitities.Employee;

import java.util.List;

public interface EmployeeRepository {
     void add(Employee e);
     void delete(Employee e);
     List<Employee> getAll();
}
