package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> listOfEmployees();
    Employee saveEmployee(Employee employee, Long depId);
    Employee updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException;

    void deleteEmployee(Long id) throws EmployeeNotFoundException;
    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;
}
