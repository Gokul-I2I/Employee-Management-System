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
 * This class model of the Department details 
 * @author Gokul
 */
@Entity
@Table (name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="department_id")
    private int departmentId;

    @Column(name = "department_name", unique = true)
    private String departmentName;

    @Column(name = "department_is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    Set<Employee> employees;
 
    public Department(){
    }
 
    /**
     * Department constructor 
     * @param departmentName  
     */
    public Department(String departmentName) { 
        this.departmentId = departmentId;  
        this.departmentName = departmentName.toUpperCase();
        this.employees = new HashSet<Employee>(); 
        this.isDeleted = false;  
    }   

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int id) {
        this.departmentId = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
 
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }   
 
    public Set<Employee> getEmployees() {
        return employees;
    } 

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}