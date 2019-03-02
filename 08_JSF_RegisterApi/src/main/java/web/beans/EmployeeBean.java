package web.beans;

import domain.models.models.binding.EmployeeBindingModel;
import domain.models.models.service.EmployeeServiceModel;
import domain.models.models.view.EmployeeViewModel;
import org.modelmapper.ModelMapper;
import services.EmployeeService;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named(value = "employees")
public class EmployeeBean implements Serializable {
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    private EmployeeBindingModel employeeBindingModel;
    private List<EmployeeViewModel> employees;
    private List<EmployeeServiceModel> employeeServiceModels;

    @Inject
    public EmployeeBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employeeBindingModel = new EmployeeBindingModel();
        this.employees = Arrays.asList(modelMapper.map(this.employeeService.getAll(), EmployeeViewModel[].class));
        this.employeeServiceModels = this.employeeService.getAll();
    }

    public void register()throws IOException {
        this.employeeService.register(modelMapper.map(employeeBindingModel, EmployeeServiceModel.class));
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }

    public void delete(EmployeeViewModel employeeViewModel) throws IOException {
        this.employeeService.delete(modelMapper.map(employeeViewModel, EmployeeServiceModel.class));
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }

    public Double getsumTotalSalaries() {
        if (employeeServiceModels.size() != 0) {
            return employeeServiceModels.stream().mapToDouble(EmployeeServiceModel::getSalary).sum();
        } else {
            return 0D;
        }
    }

    public Double getAvarageSalary() {
        if (employeeServiceModels.size() != 0) {
            return employeeServiceModels.stream().mapToDouble(EmployeeServiceModel::getSalary).average()
                    .getAsDouble();
        } else {
            return 0D;
        }
    }

    public EmployeeBindingModel getEmployeeBindingModel() {
        return this.employeeBindingModel;
    }

    public void setEmployeeBindingModel(EmployeeBindingModel employeeBindingModel) {
        this.employeeBindingModel = employeeBindingModel;
    }

    public List<EmployeeViewModel> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<EmployeeViewModel> employees) {
        this.employees = employees;
    }

    public List<EmployeeServiceModel> getEmployeeServiceModels() {
        return this.employeeServiceModels;
    }

    public void setEmployeeServiceModels(List<EmployeeServiceModel> employeeServiceModels) {
        this.employeeServiceModels = employeeServiceModels;
    }
}
