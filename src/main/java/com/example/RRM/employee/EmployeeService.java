package com.example.RRM.employee;

import com.example.RRM.employee.exception.BadRequestException;
import com.example.RRM.employee.exception.EmployeeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        Boolean existsEmail = employeeRepository.selectExistsEmail(employee.getEmail());
        if (existsEmail) {
            throw new BadRequestException("Email " + employee.getEmail() + " is taken.");
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee with the ID " + employeeId + " does not exist.");
        }
        employeeRepository.deleteById(employeeId);
    }
}
