package com.ideas2it.ems.department.controller;

import java.time.Period;
import java.util.InputMismatchException;
import java.util.List; 
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.utils.Validator;

/**
 * This class handle the all operation related to Department based on user request
 * @author Gokul
 */
public class DepartmentController {
    private static  Logger logger = LogManager.getLogger(DepartmentController.class);
    Scanner scanner = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentServiceImpl();

    /**
     * Create the department based on the user request
     * @throws MyException
     */
    public void createDepartment() throws MyException {
        System.out.print("Enter Department Name : ");
        String departmentName = "";
        boolean validName = false;
        while (!validName) {
            departmentName = scanner.nextLine();
            validName = Validator.isValidName(departmentName);
        }
        Department department = departmentService.createDepartment(departmentName);
        logger.info("Department added");
        System.out.println("--------------------------------");
        System.out.printf(" %-5s | %-10s %n", department.getDepartmentId(), department.getDepartmentName());    
    }

    /**
     * Delete the department by its Id
     * @throws MyException
     */
    public void deleteDepartment() throws MyException {
        System.out.print("Enter Department ID : ");
        int id = 0;
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        Boolean flag = departmentService.deleteDepartment(id);
        if (flag) {
            logger.info("Department Removed");
        } else {
            logger.info("Department Not Found");
        }
    }

    /**
     * Get the all departments and the list of employees
     * @throws MyException
     */
    public void viewDepartment() throws MyException {
        System.out.println( " (1) View All  " + '\n'
                          + " (2) List of Employees by Department");
        System.out.print("Choose number : ");
        int option = 0;
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        if (option == 1) {
            List<Department> departments = departmentService.retrieveDepartments();
            if (departments == null) {
                logger.info("NO departments Found");
            } else {
                System.out.printf("%-5s | %-10s %n", " ID ", " Name ");
                for (Department department : departments)  {
                    if (!department.getIsDeleted()) {
                        System.out.println("--------------------------------");
                        System.out.printf(" %-5s | %-10s %n", department.getDepartmentId(), department.getDepartmentName());
                    }
                } 
            }
        } else if (option == 2) {
            System.out.print("Enter Department Id :");
            int departmentId = 0;
            try {
                 departmentId = scanner.nextInt();
                 scanner.nextLine();
            } catch (Exception e) {
                throw new MyException("Invalid Input : ", e);
            }
            boolean isDepartment = departmentService.isDepartmentIdPresent(departmentId);
            if (isDepartment) {
                Department department = departmentService.retrieveDepartment(departmentId);
                if (department == null) {
                    logger.info("No Employee Found");
                } else {
                    System.out.printf("| %-5s | %-10s | %-5s %n" , " ID ", " NAME ", " AGE ");
                    System.out.println("-----------------------------------------------------------------------------");
                    for (Employee employee : department.getEmployees()) {
                        if (!employee.getIsDeleted()) {
                            Period age = Validator.calculateDateOfBirth(employee.getAge());
                            String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
                            System.out.printf("| %-5s | %-10s | %-5s %n", employee.getId(), employee.getName(), currentAge);
                        }
                    }
                }
            } else {
                logger.info("Department Not Found");
            }    
        } else {
            logger.warn("Choose 1 or 2");
        }  
    }

    /**
     * Update the department Name by its ID
     * @throws MyException
     */
    public void updateDepartment() throws MyException {
        System.out.print("Enter Department Id : ");
        int departmentId = 0;
        try {
            departmentId = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        boolean isValidId = departmentService.isDepartmentIdPresent(departmentId);
        while (isValidId) { 
            if (isValidId) {
                System.out.print("Enter New Department Name : ");
                String departmentName = scanner.nextLine();
                departmentService.updateDepartmentName(departmentName, departmentId);
                logger.info("Department Name changed");
            } else {
                logger.info("Department Not Found");
            }
            isValidId = false;
        }
    }

    /**
     * Show Menu for Department CRUD Operation. 
     */
    public void departmentManagementMenu() {
        DepartmentController departmentController = new DepartmentController();
	boolean flag = false;
	while (!flag) {
	    System.out.println(" (1) Add Department " + '\n' 
		               + " (2) Remove Department " + '\n' 
			       + " (3) View Department " + '\n'
                               + " (4) Edit Department " + '\n'
			       + " (5) Back " + '\n'
	                       + "************************************"); 
            System.out.print("Enter the Number : "); 
            int option = 0;
	    try {
                option = scanner.nextInt();
	        switch (option) {
                    case 1 : departmentController.createDepartment();
                             break;
           
                    case 2 : departmentController.deleteDepartment();
                             break;

                    case 3 : departmentController.viewDepartment();
                             break;

                    case 4 : departmentController.updateDepartment();
                             break;
 
                    case 5 : flag = true;
                             break;

                    default : logger.info("Choose 1 to 5");
                              break;
                } 
	    } catch (InputMismatchException e) {
		logger.warn("Invalid Input Choose Number 1 to 5");
                departmentController.departmentManagementMenu();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
	}
    }  
}