package com.ideas2it.ems.project.service;

import java.util.List;
import java.util.Set;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.employee.service.EmployeeServiceImpl;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.project.dao.ProjectDao;
import com.ideas2it.ems.project.dao.ProjectDaoImpl;
import com.ideas2it.ems.project.service.ProjectService;

/**
 * This class handle the all operation related to Project CURD based on user request
 * @author Gokul
 */
public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao = new ProjectDaoImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public Project createProject(String projectName) throws MyException {
        return projectDao.createProject(new Project(projectName));
    }

    @Override
    public boolean removeProject(int projectId) throws MyException {
        return projectDao.removeProject(projectId);
    }

    @Override
    public List<Project> retrieveProjects() throws MyException {
        return projectDao.retrieveProjects();
    }

    @Override
    public boolean isValidProjectId(int projectId) throws MyException {
        return projectDao.isValidProjectId(projectId);
    }

    @Override
    public Set<Employee> retrieveProject(int projectId) throws MyException {
        return projectDao.retrieveProject(projectId);
    }

    @Override
    public void updateProjectName(String projectName, int projectId) throws MyException {
        projectDao.updateProjectName(projectName, projectId);
    }

    @Override
    public Employee isValidEmployeeId(int employeeId) throws MyException {
        return employeeService.retrieveEmployeeId(employeeId);
    }
   
    @Override
    public void addEmployee(Employee employee, int projectId) throws MyException {
        Project project = projectDao.addEmployee(employee, projectId);
    }
}