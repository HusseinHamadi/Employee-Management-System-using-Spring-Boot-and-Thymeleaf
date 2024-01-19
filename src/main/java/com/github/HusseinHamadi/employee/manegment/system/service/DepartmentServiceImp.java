package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.DepartmentNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.repository.DepartmentRepository;
import com.github.HusseinHamadi.employee.manegment.system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImp implements DepartmentService{


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Department> listOfDepartments() {

        return departmentRepository.findAll();

    }

    @Override
    public Department getDepartmentById(Long id) throws DepartmentNotFoundException{
        Optional<Department> department=departmentRepository.findById(id);
        if(department.isPresent()){
            return department.get();
        }
        else
            throw new DepartmentNotFoundException("Department Id doesn't exist");
    }

    @Override
    @Transactional
    public void createDepartmentWithManager(Department department, Employee manager) {

        if(department.getManager()!=null)
            //setting the managers department id to the department associated to him
            department.getManager().setDepartment(department);

//        manager.setDepartment(department);
//        employeeService.saveEmployee(manager);

        departmentRepository.addDepartmentWithManager(
                department.getName(),
                department.getLocation(),
                department.getDescription(),
                department.getBudget(),
                manager.getFirstName(),
                manager.getLastName(),
                manager.getAddress(),
                manager.getPhoneNumber(),
                manager.getSalary(),
                manager.getStartingDate()
        );
    }

    @Override
    @Transactional
    public void updateDepartment(Long id, Department department, Long mgrId, Employee employee) throws DepartmentNotFoundException, EmployeeNotFoundException {
        Optional<Department> depOptional=departmentRepository.findById(id);
        Employee emp=employeeRepository.findById(mgrId).get();

        if(depOptional.isPresent()) {
            Department dep=depOptional.get();
            if (Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
                dep.setName(department.getName());
            }
            if (Objects.nonNull(department.getLocation()) && !"".equalsIgnoreCase(department.getLocation())) {
                dep.setLocation(department.getLocation());
            }
            if (Objects.nonNull(department.getDescription()) && !"".equalsIgnoreCase(department.getDescription())) {
                dep.setDescription(department.getDescription());
            }
            if (Objects.nonNull(department.getBudget())) {
                dep.setBudget(department.getBudget());
            }

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


            //if we pass a manager
            departmentRepository.updateDepartment(
                    dep.getId(),
                    dep.getName(),
                    dep.getLocation(),
                    dep.getDescription(),
                    dep.getBudget(),
                    emp.getEmployeeId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getAddress(),
                    emp.getPhoneNumber(),
                    emp.getSalary()

            );
        }
        else
            throw new DepartmentNotFoundException("Department Id doesn't exist");
    }


    @Override
    @Transactional
    public void deleteDepartment(Long id) throws DepartmentNotFoundException{

            departmentRepository.deleteDepartmentById(id);


    }
}
