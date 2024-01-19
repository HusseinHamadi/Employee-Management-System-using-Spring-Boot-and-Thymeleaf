package com.github.HusseinHamadi.employee.manegment.system.repository;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {



    @Modifying
    @Query(value = "CALL addDepartmentWithManager(:inName, :inLocation, :inDescription, :inBudget, :inFirstName, :inLastName, :inAddress, :inPhoneNumber, :inSalary, :inStartDate)", nativeQuery = true)
    void addDepartmentWithManager(
            @Param("inName") String name,
            @Param("inLocation") String location,
            @Param("inDescription") String description,
            @Param("inBudget") Double budget,
            @Param("inFirstName") String inFirstName,
            @Param("inLastName") String inLastName,
            @Param("inAddress") String inAddress,
            @Param("inPhoneNumber") Long inPhoneNumber,
            @Param("inSalary") Double inSalary,
            @Param("inStartDate") Date inStartDate
    );


    @Modifying
    @Query(value = "CALL updateDepartment(:inDepartmentId, :inName, :inLocation, :inDescription, :inBudget, " +
            ":inManagerId, :inFirstName, :inLastName, :inAddress, :inPhoneNumber, :inSalary)", nativeQuery = true)
    void updateDepartment(
            @Param("inDepartmentId") Long departmentId,
            @Param("inName") String name,
            @Param("inLocation") String location,
            @Param("inDescription") String description,
            @Param("inBudget") Double budget,
            @Param("inManagerId") Long managerId,
            @Param("inFirstName") String firstName,
            @Param("inLastName") String lastName,
            @Param("inAddress") String address,
            @Param("inPhoneNumber") Long phoneNumber,
            @Param("inSalary") Double salary
    );

    @Modifying
    @Query(value = "CALL deleteDepartment(:departmentId)", nativeQuery = true)
    void deleteDepartmentById(@Param("departmentId") Long departmentId);

}
