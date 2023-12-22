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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @ManyToMany(mappedBy = "projects")
    private List<Employee>  employees = new ArrayList<>();

}
