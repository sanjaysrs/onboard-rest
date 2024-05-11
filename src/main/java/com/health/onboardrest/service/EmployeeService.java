package com.health.onboardrest.service;

import com.health.onboardrest.entity.Employee;
import com.health.onboardrest.exception.EmployeeNotFoundException;
import com.health.onboardrest.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id %s not found", id)));
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        if (!employeeRepository.existsById(id))
            throw new EmployeeNotFoundException(String.format("Employee with id %s not found", id));
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id))
            throw new EmployeeNotFoundException(String.format("Employee with id %s not found", id));
        employeeRepository.deleteById(id);
    }

    @Transactional
    public Employee patchEmployee(Employee employee, Long id) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id %s not found", id)));

        if (employee.getFirstName()!=null)
            existingEmployee.setFirstName(employee.getFirstName());

        if (employee.getLastName()!=null)
            existingEmployee.setLastName(employee.getLastName());

        return employeeRepository.save(existingEmployee);
    }
}
