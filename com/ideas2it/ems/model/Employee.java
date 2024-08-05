package com.ideas2it.ems.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.lang.StringBuilder;

import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.utils.Validator;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

/**
 * This class model of the Employee details
 * @author Gokul
 */
@Entity
@Table (name = "employee")
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "Date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "employee_is_deleted")
    private boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_project", 
	joinColumns = @JoinColumn(name = "employee_id"), 
	inverseJoinColumns = @JoinColumn(name = "project_id")) 
    private Set<Project> projects;
  
    public Employee() {
    }

    /**
     * Employee constructor 
     * @param name : employee Name  
     * @param dateOfBirth : employee date of birth 
     * @param department : employee department details 
     * @param laptop : employee laptop details
     */
    public Employee(String name, LocalDate dateOfBirth, Department department, Laptop laptop) {
	this.name = name.toUpperCase();
	this.dateOfBirth = dateOfBirth;
	this.isDeleted = false;
        this.department = department;
        this.laptop = laptop;
        this.projects = new HashSet<Project>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this. id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name.toUpperCase();
    }

    public LocalDate getAge() {
        return dateOfBirth;
    }

    public void setAge(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
	return department; 
    }

    public void setDepartment(Department department) {
	this.department = department;
    }

    public Laptop getLaptop() {
	return laptop; 
    }

    public void setLaptop(Laptop laptop) {
	this.laptop = laptop;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }
    
    public Set<Project> getProjects() {
        return projects;
    } 

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String displayProjects() {
        StringBuilder stringBuilder = new StringBuilder();
        if (projects.isEmpty()) {
            return "-";
        }
        for(Project project : projects) {
            if (!project.getIsDeleted()) {
                stringBuilder.append(project.getProjectName() + " ");
            }
        }
        return stringBuilder.toString();
    }
}