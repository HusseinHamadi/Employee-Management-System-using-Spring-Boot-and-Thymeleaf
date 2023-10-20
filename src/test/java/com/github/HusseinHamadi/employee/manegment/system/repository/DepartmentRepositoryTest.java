package com.github.HusseinHamadi.employee.manegment.system.repository;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private long id;

    @BeforeEach
    void setUp() {

        Employee hussein=
                Employee.builder()
                        .firstName("Hussein")
                        .lastName("Hamadi")
                        .address("Beirut")
                        .salary(2000.0)
                        .build();
        Department department=
                Department.builder()
                        .name("ME")
                        .description("Mechanical Engineering")
                        .location("Beirut")
                        .manager(hussein)
                        .budget(30000l)
                        .build();

        testEntityManager.persist(department);
        id=department.getId();
    }

    @Test
    public void whenFindById_thenReturnData(){
        Department department=
                departmentRepository.findById(id).get();
        Assertions.assertEquals("ME", department.getName());
    }
}