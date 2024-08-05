package com.ideas2it.ems.model;

import java.util.HashSet;
import java.util.Set;

import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

/**
 * This class model of the Project details
 * @author Gokul
 */
@Entity
@Table (name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "project_name" )
    private String projectName;

    @Column(name = "Project_is_deleted")
    private boolean isDeleted;
  
    @ManyToMany(fetch = FetchType.EAGER)  
    @JoinTable(name = "employee_project",
	 joinColumns = @JoinColumn(name = "project_id"), 
	 inverseJoinColumns = @JoinColumn(name = "employee_id")) 
    private Set<Employee> employees;

    public Project() {
    }

    /**
     * Project constructor 
     * @param projectName 
     */ 
    public Project(String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.employees = new HashSet<Employee>();
        this.isDeleted = false;
    }

    public int getProjectId() {
        return projectId;
    }
  
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }   
}