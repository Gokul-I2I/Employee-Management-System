package com.ideas2it.ems.project.controller;

import java.util.InputMismatchException; 
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.project.service.ProjectServiceImpl;

/**
 * This class handle the all operation related to Project based on user request
 * @author Gokul
 */
public class ProjectController {
    private static  Logger logger = LogManager.getLogger(ProjectController.class);
    Scanner scanner = new Scanner(System.in);
    ProjectServiceImpl projectService = new ProjectServiceImpl();

    /**
     * Create project based on user request
     * @throws MyException
     */
    public void createProject() throws MyException {
        System.out.print("Enter Project Name : ");
        String projectName = scanner.nextLine();
        Project project = projectService.createProject(projectName);
        logger.info("Project Added");
        System.out.println("--------------------------------");
        System.out.printf(" %-5s | %-10s %n", project.getProjectId(), project.getProjectName());
    }

    /**
     * Soft delete project by its id 
     * @throws MyException
     */
    public void deleteProject() throws MyException {
        System.out.print("Enter Project ID : ");
        int projectId = 0;
        try {
            projectId = scanner.nextInt();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        if (projectService.removeProject(projectId)) {
            logger.info("Project Removed");
        } else {
            logger.info("Project Not Found");
        }
    }

    /**
     * Get the List of all projects details and Employee Details
     * @throws MyException
     */
    public void viewProject() throws MyException {
        System.out.println(" (1) View All Project" + '\n'
                         + " (2) List Employees by Project" + '\n'
                         + " Enter Option : ");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        if (option == 1) {
            List<Project> projects = projectService.retrieveProjects();
            if (!projects.isEmpty()) {
                for (Project project : projects) {
                    if (!project.getIsDeleted()) {
                        System.out.println("--------------------------------");
                        System.out.printf(" %-5s | %-10s %n", project.getProjectId(), project.getProjectName());
                    }
                }
            } else {
                logger.info("No project Found");
            }
        } else if (option == 2) {
            System.out.print("Enter Project Id : ");
            int projectId = scanner.nextInt();
            if (projectService.isValidProjectId(projectId)) {
                Set<Employee> employees = projectService.retrieveProject(projectId);
                if (employees.isEmpty() && employees == null) {
                    logger.info("employee not found");
                } else {
                    for (Employee employee : employees) {
                        if (!employee.getIsDeleted()) {
                            System.out.println("-----------------------------------------");
                            System.out.printf(" %-5s | %-10s | %-10s%n", employee.getId(), employee.getName(), employee.getDepartment().getDepartmentName());
                        }
                    }
                }
            } else {
                logger.info("Project not found");
            }
        } else {
            logger.info("Choose 1 or 2");
        }
    }

    /**
     * Update the project name and add the employees to project
     * @throws MyException
     */
    public void updateProject() throws MyException {
        System.out.println(" (1) Change Project Name" + '\n'
                         + " (2) Add Employee" + '\n'
                         + " Enter Option : ");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        if (option == 1) {
            System.out.print("Enter ProjectId : ");
            int projectId = 0;
            try {
                projectId = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                throw new MyException("Invalid Input : ", e);
            }
            boolean isValidId = projectService.isValidProjectId(projectId);
            if (isValidId) {
                System.out.print("Enter new Project Name : ");
                String projectName = scanner.nextLine();
                projectService.updateProjectName(projectName, projectId);
                logger.info("Project Name Changed");
            } else {
                logger.info("Project Id not found");
            }
        } else if (option == 2) {
            System.out.print("Enter Employee Id : ");
            int employeeId = 0;
            try {
                 employeeId = scanner.nextInt();
            } catch (Exception e) {
                 throw new MyException("Invalid Input : ", e);
            }
            Employee employee = projectService.isValidEmployeeId(employeeId);
            List<Project> projects = projectService.retrieveProjects();
            for (Project project : projects) {
                if (!project.getIsDeleted()) {
                    System.out.println("--------------------------------");
                    System.out.printf(" %-5s | %-10s %n", project.getProjectId(), project.getProjectName());
                }
            }
            if (employee != null) {
                System.out.print("Enter Project ID : ");
                int projectId = 0;
                try {
                    projectId = scanner.nextInt();
                } catch (Exception e) {
                    throw new MyException("Invalid Input : ", e);
                }
                boolean isValidId = projectService.isValidProjectId(projectId);
                if (isValidId) {
                    projectService.addEmployee(employee, projectId);
                    System.out.println("Employee Project Added in Data Base");
                } else {
                    logger.info("Project Id not Found");
                }
            } else {
                logger.info("Employee Not Found");
            }
        } else {
            logger.info("Choose 1 or 2");
        }
        
    }

    /**
     * Show Menu for Project CRUD Operation. 
     */
    public void projectManagementMenu() {
        ProjectController projectController = new ProjectController();
	boolean flag = false;
	while (!flag) {
	    System.out.println(" (1) Add project " + '\n' 
		               + " (2) Remove project " + '\n' 
			       + " (3) View project " + '\n'
                               + " (4) Edit project " + '\n'
			       + " (5) Back " + '\n'
	                       + "************************************"); 
            System.out.print("Enter the Number : "); 
            int option = 0;
	    try {
                option = scanner.nextInt();
	        if (option == 1) {
	            projectController.createProject();
	            flag = false;
	        } else if (option == 2) {
	            projectController.deleteProject();	
	            flag = false;
	        } else if (option == 3) {
	            projectController.viewProject();
	            flag = false;
	        } else if (option == 4) {
                    projectController.updateProject();
		    flag = false;
	        } else if (option == 5) {
		    flag = true;
	        } else {
	            System.out.println(" Choose 1 - 5 Only "); 
	            flag = false;
	        }
	    } catch (InputMismatchException e) {
	        logger.warn("Invalid Input Choose Number");
                projectController.projectManagementMenu();
            } catch (MyException e) {
                logger.error(e.getMessage());
            }
	}
    }  
}