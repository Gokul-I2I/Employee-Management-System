package com.ideas2it.ems.project.dao;

import java.util.List;
import java.util.Set;

import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * This class handle the all operation related to Project database
 * @author Gokul
 */
public interface ProjectDao {

    /**
     * Add the project details to the database
     *
     * @param details of the project
     * @return project : details of the project 
     * @throws MyException
     */
    public Project createProject(Project project) throws MyException;

    /**
     * Delete the project by its id
     *
     * @param id : id of the project
     *
     * @return boolean project is removed or not
     * @throws MyException
     */
    public boolean removeProject(int projectId) throws MyException;

    /**
     * Get the all Projects details
     *
     * @return List<Project> : list of the all projects details
     * @throws MyException
     */
    public List<Project> retrieveProjects() throws MyException;

    /**
     * Get the project id is present or not
     *
     * @param id : Id of the project
     *
     * @return boolean : project id is contain true or false
     * @throws MyException
     */
    public boolean isValidProjectId(int projectId) throws MyException;

    /**
     * Get all employees by the project id
     *
     * @param  id of the project
     *     
     * @return Set<Employee> : list of the all employees details in set
     * @throws MyException
     */
    public Set<Employee> retrieveProject(int projectId) throws MyException;

    /**
     * Add employee to the project
     *
     * @param employee details
     * @param project details
     *
     * @return project details
     * @throws MyException
     */
    public Project addEmployee(Employee employee, int projectId) throws MyException;

    /**
     * Update the project name by its id
     *
     * @param proejct id : id of the project
     * @param projectName : new name of the project
     * @throws MyException
     */
    public void updateProjectName(String projectName, int projectId) throws MyException;
}