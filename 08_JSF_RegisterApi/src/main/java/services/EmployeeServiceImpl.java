package services;

import domain.models.enitities.Employee;
import domain.models.models.service.EmployeeServiceModel;
import org.modelmapper.ModelMapper;
import repository.EmployeeRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;
@Named (value = "emServ")
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void register(EmployeeServiceModel employeeServiceModel) {
        this.employeeRepository.add(modelMapper.map(employeeServiceModel, Employee.class));
    }

    @Override
    public void delete(EmployeeServiceModel employeeServiceModel){
        this.employeeRepository.delete(modelMapper.map(employeeServiceModel, Employee.class));
    }

    @Override
    public List<EmployeeServiceModel> getAll() {
        return this.employeeRepository.getAll().stream().map(e-> this.modelMapper.map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    }
