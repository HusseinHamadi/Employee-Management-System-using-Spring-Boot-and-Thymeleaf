package com.github.HusseinHamadi.employee.manegment.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )
    private Long id;


    @NotEmpty
    @NotBlank
    @NotNull
    private String name;


    @NotEmpty
    @NotBlank
    @NotNull
    private String description;


    @CreationTimestamp
    private Date startDate;

    private Double cost;


    @UpdateTimestamp
    @Column(name = "update_date",nullable = false)
    private Date updateDate;

    //many projects belong to department
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id"
    )
    private Department department;



    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "employee_project_map",
            joinColumns = @JoinColumn(
                    name = "project_id",
                    referencedColumnName = "id"
            ),

            inverseJoinColumns = @JoinColumn(
                    name = "employee_id",
                    referencedColumnName = "employee_id"
            )
    )
    private List<Employee> employees;

}
