package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.DepartmentNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@SpringBootTest
class DepartmentServiceTest {

    //to test dep serv, need depServ instance
    @Autowired
    private DepartmentService departmentService;

    //to mock dep rep, need a mocked depRep instance
    @MockBean
    private DepartmentRepository departmentRepository;

    //beforeEach means for every test called, we call it
    //beforeAll means for all tests, we call it once
    //more @ to study...
    @BeforeEach
    void setUp() {

         Department department=
                 Department.builder()
                         .id(1l)
                         .name("IT")
                         .description("Information Technology")
                         .budget(20000l)
                         .location("Beirut")
                         .build();
         Department department1=
                 Department.builder()
                         .id(2l)
                         .name("HR")
                         .description("Human Resources")
                         .budget(15000l)
                         .location("Tripoly")
                         .build();
        Employee manager=
                Employee.builder()
                        .firstName("John")
                        .lastName("Smith")
                        .employeeId(1l)
                        .departmentManagement(department)
                        .build();

        List<Department> departmentList=new ArrayList<>();

        departmentList.add(department);
        departmentList.add(department1);

         Mockito.when(departmentRepository.findAll())
                         .thenReturn(departmentList);

         Mockito.when(departmentRepository.findById(1l))
                .thenReturn(Optional.ofNullable(department));

         Mockito.when(departmentRepository.save(department1))
                 .thenReturn(department1);

    }

    @Test
    public void whenValidDepartmentId_thenDepartmentShouldFound() throws DepartmentNotFoundException {
        Long departmentId=1l;
        Department found =
                departmentService.getDepartmentById(departmentId);
        assertEquals(departmentId,found.getId());
    }

    @Test
    public void whenCalled_departmentListReturned(){
        List<Department> foundList=
                departmentService.listOfDepartments();
        assertEquals(2,foundList.size());
    }

    @Test
    public void whenValidDepartmentId_thenUpdateDepartment() throws DepartmentNotFoundException, EmployeeNotFoundException {

        Employee manager=
                Employee.builder()
                        .firstName("John")
                        .lastName("Smith")
                        .employeeId(1l)
                        .build();

        Department dep=
                Department.builder()
                        .manager(manager)
                        .build();
        Department savedDep=
                departmentService.updateDepartment(1l,dep);

        assertEquals(2l,savedDep.getManager().getDepartment().getId());
    }

    @Test
    public void whenCalled_thenSaveDepartment(){

        Department dep=
                Department.builder()
                        .id(1l)
                        .name("ND")
                        .description("Network Department")
                        .build();
        Employee manager=
                Employee.builder()
                        .firstName("John")
                        .lastName("Smith")
//                        .employeeId(1l)
                        .departmentManagement(dep)
                        .build();

        Department saved=
                departmentService.createDepartment(dep);

        assertEquals(manager, dep.getManager());
    }

    @Test
    public void whenValidDepartmentId_thenDeleteDepartment() throws DepartmentNotFoundException {

        departmentService.deleteDepartment(1l);

        verify(departmentRepository).findById(1l);
    }
}