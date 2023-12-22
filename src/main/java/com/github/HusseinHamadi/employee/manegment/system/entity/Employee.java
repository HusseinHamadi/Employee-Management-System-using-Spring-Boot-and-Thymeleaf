package com.github.HusseinHamadi.employee.manegment.system.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name = "employees")
public class Employee {


    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;


    @Column(name = "first_name")
    @NotEmpty
    @NotBlank
    @NotNull
    private String firstName;


    @Column(name = "last_name")
    @NotEmpty
    @NotBlank
    @NotNull
    private String lastName;


    @CreationTimestamp
    @Column(name = "start_date", nullable = false, updatable = false)
    private Date startingDate;


    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;


    @Column(name = "address")
    private String address;


    @Column(name = "phone_number")
    private Long phoneNumber;


    @Column(name = "salary")
    private Double salary;


    //one manager per department
    @OneToOne(
            mappedBy = "manager"
    )
    private Department departmentManagement;

    //@JsonInclude(JsonInclude.Include.NON_NULL) is used to ignore null values
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(value = {"id", "updateDate", "location", "managerId", "description"})
    public Department getDepartmentManagement() {
        return departmentManagement;
    }


    //many employees belong to department
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id",
            nullable = false
    )
    @NotNull
    private Department department;



    @JsonBackReference
    public Department getDepartment() {
        return department;
    }


    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "employee_project_map",
            joinColumns = @JoinColumn(
                    name = "employee_id",
                    referencedColumnName = "employee_id"
            ),

            inverseJoinColumns = @JoinColumn(
                    name = "project_id",
                    referencedColumnName = "id"
            )
    )
    private List<Project> projects;

}
