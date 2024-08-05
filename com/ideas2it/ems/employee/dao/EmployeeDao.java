package com.ideas2it.ems.employee.dao;

import java.util.List;

import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * This class handle the employee storing operation related based on user request
 * @author Gokul
 */
public interface EmployeeDao {

    /**
     * Add the employee in database
     *
     * @param employee : details of the employee 
     *
     * @return Employee : details of the employee 
     * @throws MyException
     */
    public Employee createEmployee(Employee employee) throws MyException;
  
    /**
     * Delete the employee by its id
     *
     * @param id : id of the employee
     *
     * @return boolean : employee removed or not
     * @throws MyException
     */
    public boolean deleteEmployee(int id) throws MyException;

    /**
     * Get the employee details by its id
     *
     * @param id : id of the employee
     *
     * @return Employee : details of the employee
     * @throws MyException 
     */
    public Employee retrieveEmployeeId(int id) throws MyException;

    /**
     * Get the all employee details
     *
     * @return List<Employee> : list of the all employee details
     * @throws MyException
     */
    public List<Employee> retrieveEmployees() throws MyException;

    /**
     * Get the all employees by department id
     *
     * @param departmentId : id of the department 
     *
     * @return List<Employee> : list of all employees details
     * @throws MyException
     */
    public List<Employee> retrieveEmployeeByDepartment(int departmentId) throws MyException;
 
    /**
     * Update the employee name, department, age and laptop details  by its id
     *
     * @param employee : employee with new changes
     * @throws MyException
     */
    public void updateEmployee(Employee employee) throws MyException;
}