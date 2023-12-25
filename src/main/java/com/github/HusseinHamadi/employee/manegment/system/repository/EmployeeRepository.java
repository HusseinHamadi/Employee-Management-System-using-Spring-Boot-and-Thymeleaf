package com.github.HusseinHamadi.employee.manegment.system.repository;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Procedure(name="GetAllEmployees")
    List<Employee> findAll();
    @Procedure(name = "GetEmployeesOfDepartment")
    List<Employee> findByDepartmentId(Long id);


    @Procedure(procedureName = "AddEmployee")
    Employee saves(Employee employee);

    @Procedure(procedureName = "getBySalaryBetween")
    List <Employee> getBySalaryBetween(Double upper, Double lower);

}
