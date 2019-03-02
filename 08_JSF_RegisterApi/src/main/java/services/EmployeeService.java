package services;

import domain.models.models.service.EmployeeServiceModel;

import java.util.List;
public interface EmployeeService {
    void register(EmployeeServiceModel employeeServiceModel);
    void delete(EmployeeServiceModel employeeServiceModel);
    List<EmployeeServiceModel> getAll();
}
