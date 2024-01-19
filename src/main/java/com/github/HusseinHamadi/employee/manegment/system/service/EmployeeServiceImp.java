package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.repository.DepartmentRepository;
import com.github.HusseinHamadi.employee.manegment.system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> listOfEmployees(){

        return employeeRepository.findAll();
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        // Assuming you have the necessary logic for creating a new Employee entity
        // ...

        // Call the stored procedure to save the employee
        employeeRepository.saveEmployeeStoredProcedure(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getSalary(),
                employee.getStartingDate(),
                employee.getDepartment().getId()
        );
    }

    @Override
    @Transactional
    public void updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException{
        Optional<Employee> empOptional=employeeRepository.findById(id);
        if(empOptional.isPresent()){
            Employee emp = empOptional.get();
            if(Objects.nonNull(employee.getAddress()) && !"".equalsIgnoreCase(employee.getAddress())){
                emp.setAddress(employee.getAddress());
            }
            if(Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())){
                emp.setFirstName(employee.getFirstName());
            }
            if(Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())){
                emp.setLastName(employee.getLastName());
            }
            if(Objects.nonNull(employee.getPhoneNumber())){
                emp.setPhoneNumber(employee.getPhoneNumber());
            }
            if(Objects.nonNull(employee.getSalary())){
                emp.setSalary(employee.getSalary());
            }
            emp.setDepartment(employee.getDepartment());
            employeeRepository.updateEmployeeStoredProcedure(
                    emp.getEmployeeId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getAddress(),
                    emp.getPhoneNumber(),
                    emp.getSalary(),
                    emp.getStartingDate(),
                    emp.getDepartment().getId()
            );
        }
        else
            throw new EmployeeNotFoundException("Employee Id doesn't exist");
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) throws EmployeeNotFoundException{
        Optional<Employee> employeeToDelete = employeeRepository.findById(id);

        if(employeeToDelete.isPresent()){
            employeeToDelete.get().getProjects().clear();
            employeeRepository.deleteById(id);
            }
        else
            throw new EmployeeNotFoundException("Employee Id doesn't exist");
    }

    @Override
    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(!employee.isPresent()){
            throw new EmployeeNotFoundException("Employee Not Present");
        }
        return employee.get();
    }


    @Override
    @Transactional
    public List<Employee> getEmployeeByDepartmentId(Long id) {
        return employeeRepository.findByDepartmentId(id);
    }

    @Transactional
    @Override
    public List<Employee> getEmployeeBySalary(Double up, Double low) {
        return employeeRepository.getBySalaryBetween(up,low);
    }
}
