package com.github.HusseinHamadi.employee.manegment.system.repository;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Modifying
    @Query(value = "CALL addProject(:inName, :inDescription, :inCost, :inStartDate, :inDepartmentId)", nativeQuery = true)
    void saveProject(
            @Param("inName") String name,
            @Param("inDescription") String description,
            @Param("inCost") Double cost,
            @Param("inStartDate") Date startDate,
            @Param("inDepartmentId") Long id
    );

    @Modifying
    @Query(value = "Call AddEmployeeToProject(:inEmpId, :inProjId)", nativeQuery = true)
    void addEmployeeToProject(
            @Param("inEmpId") Long empId,
            @Param("inProjId") Long projId
    );

    @Modifying
    @Query(value = "Call deleteProject(:inProjId)", nativeQuery = true)
    void deleteById(@Param("inProjId") Long id);

    @Modifying
    @Query(value = "CALL updateProject(:inId, :inName, :inDescription, :inCost, :inStartDate, :inDepartmentId)", nativeQuery = true)
    void updateProject(
            @Param("inId") Long projId,
            @Param("inName") String name,
            @Param("inDescription") String description,
            @Param("inCost") Double cost,
            @Param("inStartDate") Date startDate,
            @Param("inDepartmentId") Long depId
    );

    @Modifying
    @Query(value = "Call removeEmployee(:inId, :inProjId)", nativeQuery = true)
    void removeEmployee(
            @Param("inId") Long id,
            @Param("inProjId") Long projId
        );
}
