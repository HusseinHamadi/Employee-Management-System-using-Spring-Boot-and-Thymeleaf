package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.DepartmentNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Department> listOfDepartments();

    Department getDepartmentById(Long id) throws DepartmentNotFoundException;

    Department createDepartment(Department department);

    Department updateDepartment(Long id, Department department) throws DepartmentNotFoundException, EmployeeNotFoundException;

    void deleteDepartment(Long id) throws DepartmentNotFoundException;
}
