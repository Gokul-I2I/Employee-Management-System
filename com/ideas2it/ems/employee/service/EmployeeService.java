package com.ideas2it.ems.employee.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.employee.dao.EmployeeDaoImpl;
import com.ideas2it.ems.employee.service.EmployeeServiceImpl;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Laptop;

/**
 * Interface that has the following methods
 */
public interface EmployeeService {

    /**
     * Get the list of all departments details
     *
     * @return List<Department> : list of all departments deatils 
     * @throws MyException
     */
    public List<Department> retrieveDepartments() throws MyException;

    /**
     * Get the department is present or not by its id
     *
     * @param departmentId : id of the department
     *
     * @return boolean : department is present or not  
     * @throws MyException
     */
    public boolean isDepartmentIdPresent(int departmentId) throws MyException;

    /**
     * Used to create employee with name, dateOfBirth, department id, laptop details
     *
     * @param employee name
     * @param employee dateOfBirth
     * @param employee departmentId 
     * @param employee laptop details
     *
     * @return Employee : details of the employee 
     * @throws MyException
     */
    public Employee createEmployee(String employeeName, LocalDate dateOfBirth, int employeeDepartmentId, Laptop laptop) throws MyException;
    
    /**
     * Delete the employee by its id
     *
     * @param id : delete the employee details by its id
     *
     * @return boolean : employee is removed or not 
     * @throws MyException
     */
    public boolean deleteEmployee(int id) throws MyException;

   /**
     * Get the all employees from database by department id wise
     *
     * @param departmentId : id of the department
     *
     * @return list<Employee> : list of the all employees deatils from department 
     * @throws MyException
     */
    public List<Employee> retrieveEmployeeByDepartment(int departmentId) throws MyException;
    
    /**
     * Get the all employees from database
     *
     * @return List<Employee> : list of the All employees details  
     * @throws MyException
     */
    public List<Employee> retrieveEmployees() throws MyException;

    /**
     * Update employee name, department, age and laptop details
     *
     * @param employee : employee with new changes
     * 
     * @throws MyException
     */
    public void updateEmployee(Employee employee) throws MyException;

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
     * Get the department details by its id
     *
     * @param id : id of the department
     *
     * @return department : details of the department
     * @throws MyException
     */
    public Department retrieveDepartment(int departmentId) throws MyException;
}