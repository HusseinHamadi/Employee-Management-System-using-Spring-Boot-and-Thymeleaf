package com.github.HusseinHamadi.employee.manegment.system.repository;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Procedure(name="GetAllEmployees")
    List<Employee> findAll();


    @Procedure(procedureName = "GetEmployeesOfDepartment")
    List<Employee> findByDepartmentId(Long id);



    @Modifying
    @Query(value = "CALL EmployeeAdd(:inFirstName, :inLastName, :inAddress, :inPhoneNumber, :inSalary, :inStartDate, :inDepartmentId)", nativeQuery = true)
    void addEmployeeStoredProcedure(
            @Param("inFirstName") String inFirstName,
            @Param("inLastName") String inLastName,
            @Param("inAddress") String inAddress,
            @Param("inPhoneNumber") String inPhoneNumber,
            @Param("inSalary") BigDecimal inSalary,
            @Param("inStartDate") Date inStartDate,
            @Param("inDepartmentId") Long inDepartmentId
    );

    @Modifying
    @Query(value = "CALL updateEmployee(:inId, :inFirstName, :inLastName, :inAddress, :inPhoneNumber, :inSalary, :inStartDate, :inDepartmentId)", nativeQuery = true)
    void updateEmployeeStoredProcedure(
            @Param("inId") long inId,
            @Param("inFirstName") String inFirstName,
            @Param("inLastName") String inLastName,
            @Param("inAddress") String inAddress,
            @Param("inPhoneNumber") long inPhoneNumber,
            @Param("inSalary") double inSalary,
            @Param("inStartDate") Date inStartDate,
            @Param("inDepartmentId") long inDepartmentId
    );

    @Modifying
    @Query(value = "CALL AddEmployee(:inFirstName, :inLastName, :inAddress, :inPhoneNumber, :inSalary, :inStartDate, :inDepartmentId)", nativeQuery = true)
    void saveEmployeeStoredProcedure(
            @Param("inFirstName") String inFirstName,
            @Param("inLastName") String inLastName,
            @Param("inAddress") String inAddress,
            @Param("inPhoneNumber") long inPhoneNumber,
            @Param("inSalary") double inSalary,
            @Param("inStartDate") Date inStartDate,
            @Param("inDepartmentId") Long inDepartmentId
    );

    @Modifying
    @Query(value = "Call deleteEmployee(:id)", nativeQuery = true)
    void deleteById(
            @Param("id") Long id
    );

    @Procedure(procedureName = "getBySalaryBetween")
    List <Employee> getBySalaryBetween(Double upper, Double lower);

    @Modifying
    @Query(value = "Call getEmployeesOfProject(:id)", nativeQuery = true)
    List<Employee> getEmployeesOfProject(@Param("id") Long id);


}
