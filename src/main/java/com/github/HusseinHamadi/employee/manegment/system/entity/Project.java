package com.github.HusseinHamadi.employee.manegment.system.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
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
    @Column(name = "description")
    private String description;

    @CreationTimestamp
    private Date startDate = new Date();

    private Double cost;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    // Many projects belong to a department
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToMany(mappedBy = "projects")
    @JsonManagedReference
    private List<Employee> employees;

    // Getter without @JsonIgnore
    @JsonManagedReference
    public List<Employee> getEmployees() {
        return employees;
    }
}
