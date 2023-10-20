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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImp implements DepartmentService{


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


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
    public Department createDepartment(Department department) {

        if(department.getManager()!=null)
            //setting the managers department id to the department associated to him
            department.getManager().setDepartment(department);
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) throws DepartmentNotFoundException, EmployeeNotFoundException {
        Optional<Department> depOptional=departmentRepository.findById(id);
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
            //if we pass a manager
            if (Objects.nonNull(department.getManager())) {
                //check if id is passed
                Long employeeId=department.getManager().getEmployeeId();
                if(employeeId>0){
                    //check if id is present in DB
                    Optional<Employee> employeeOptional=employeeRepository.findById(department.getManager().getEmployeeId());
                    if(employeeOptional.isPresent()){
                        //getting the manager
                        Employee employee=employeeOptional.get();
                        //assigning the manager the department
                        employee.setDepartment(dep);
                        //assign dep manager to the existing one
                        dep.setManager(employee);
                    }
                    else{
                        throw new EmployeeNotFoundException("Employee Id doesn't exist");
                    }
                }
                else{
                    //if id is not passed set manager to department
                    dep.setManager(department.getManager());
                    //set managers department to the present department he is managing
                    department.getManager().setDepartment(dep);
                }

            }
            return departmentRepository.save(dep);
        }
        else
            throw new DepartmentNotFoundException("Department Id doesn't exist");
    }

    @Override
    public void deleteDepartment(Long id) throws DepartmentNotFoundException{
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()){
            departmentRepository.deleteById(id);
        }
        else{
            throw new DepartmentNotFoundException("Department Id doesn't exist");
        }
    }
}
