package com.github.HusseinHamadi.employee.manegment.system.repository;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    List<Employee> findByDepartmentId(Long id);
    List <Employee> findBySalaryBetween(Double upper, Double lower);

}
