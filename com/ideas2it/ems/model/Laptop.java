package com.ideas2it.ems.model;

import com.ideas2it.ems.model.Employee;
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

/**
 * This class model of the Employee details
 * @author Gokul
 */
@Entity
@Table (name = "Laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private int id;

    @Column(name = "model", unique = true)
    private String model;

    @OneToOne(mappedBy = "laptop",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    public Laptop() {
    }

    /**
     * Laptop constructor 
     * @param model : laptop model 
     */
    public Laptop(String model) {
        this.id = id;
        this.model = model;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this. id = id;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public Employee getEmployee() {
	return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }
}