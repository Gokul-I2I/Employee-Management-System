package com.ideas2it.ems.department.dao;
	
import java.util.List;

import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * This class handle the all operation related to Department database
 * @author Gokul
 */
public interface DepartmentDao {

   /**
     * Create the new department by name
     *
     * @param departmentName : name of the new department
     *
     * @return department details
     * @throws MyException
     */
    public Department createDepartment(Department department) throws MyException;

    /**
     *  Get the Database have a department 
     *  
     * @return boolean : department is present or not 
     * @throws MyException
     */
    public boolean isDepartment() throws MyException;

    /**
     * Get the department by its id present or not
     *
     * @param departmentId : id of the department
     *     
     * @return boolean : department is present or not
     * @throws MyException
     */
    public boolean isDepartmentIdPresent(int departmentId) throws MyException;

    /**
     * Get the department by its id
     *
     * @param departmentId : id of the department
     *     
     * @return Department : department details
     * @throws MyException
     */
    public Department retrieveDepartment(int departmentId) throws MyException;  
 
    /**
     * Delete the department by its id from database
     *
     * @param id : id of the department 
     *     
     * @return Boolean :  department is removed or not
     * @throws MyException
     */
    public boolean deleteDepartment(int id) throws MyException;

    /**
     * Get all departments from database
     *
     * @return List<Department> : List of all departments details
     * @throws MyException
     */
    public List<Department> retrieveDepartments() throws MyException;

    /**
     * Update the department name by its id
     *
     * @param departmentId : id of the department 
     * @param departmentName : new name of the department
     * @throws MyException
     */
    public void updateDepartmentName(String departmentName, int departmentId) throws MyException;  
}