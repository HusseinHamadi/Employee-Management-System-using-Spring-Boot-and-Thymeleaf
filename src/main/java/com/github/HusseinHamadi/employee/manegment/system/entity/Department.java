package com.github.HusseinHamadi.employee.manegment.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
//used getter and setter instead of @data to disable the use of toString, since there is a stackoverflow error using it
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")

public class Department {

    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    @NotEmpty
    @NotBlank
    @NotNull
    private String name;


    @Column(name = "location")
    @NotEmpty
    @NotBlank
    @NotNull
    private String location;


    @Column(name = "description")
    private String description;


    @Column(name = "budget")
    private Long budget;




    @UpdateTimestamp
    @Column(name = "update_date",nullable = false)
    private Date updateDate;


    @OneToMany(mappedBy = "department")
    private List<Employee> employees; // One department can have many employees



    //@JsonManagedReference used with @JsonBackReference on the get methods to prevent infinite calling each other class
    //@JsonIgnore used to hide a response field from the client/user such as postman
    @JsonManagedReference
    @JsonIgnore
    public List<Employee> getEmployees() {
        return employees;
    }

    //department has one manager
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "employee_id"
    )
    private Employee manager;

    //to get the manager id in the response
    public Long getManagerId(){
        return manager.getEmployeeId();
    }

    //to prevent infinite loop of calling the response of each entity
    @JsonBackReference
//    @JsonIgnore
    public Employee getManager() {
        return manager;
    }

}
