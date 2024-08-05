package com.ideas2it.ems.employee.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException; 
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.employee.service.EmployeeServiceImpl;
import com.ideas2it.ems.utils.Validator;

/**
 * This class handle the all operation related to Employee based on user request
 * @author Gokul
 */
public class EmployeeController {
    private static  Logger logger = LogManager.getLogger(EmployeeController.class);
    Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * Get employee details from user and add to database
     * @throws MyException
     */
    public void createEmployee() throws MyException {   
        System.out.print("Enter Name : ");
        String employeeName = scanner.nextLine();
        boolean validName = Validator.isValidName(employeeName);
            while (!validName) {
                System.out.print("Enter a Valid Name : ");
                employeeName = scanner.nextLine();
                validName = Validator.isValidName(employeeName);
            }
        boolean validAge = false;
        String employeeDateOfBirth = "";
        while (!validAge) {
            try {
                System.out.print("Enter Date of Birth (dd/mm/yyyy) : ");
                employeeDateOfBirth = scanner.nextLine();
                Period age = Validator.calculateDateOfBirth(employeeDateOfBirth);
                System.out.println("AGE : " + age);
                if (18 <= age.getYears() && age.getYears() <= 60) {
                    validAge = true;
                    String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
                } else {
                    System.out.println("AGE INVALID");
                    employeeDateOfBirth = scanner.nextLine();
                    age = Validator.calculateDateOfBirth(employeeDateOfBirth);
                }
	    } catch (Exception e) {
	        logger.error("Invalid Type");
            }     
         }
         List<Department> departments = employeeService.retrieveDepartments();
         if (departments == null || departments.isEmpty()) {
                logger.info("No Department Found Create one ");

         } else {                    
             System.out.printf("%-5s | %-20s %n",
                           " ID ", " Name ");
            for (Department department : departments)  {
                if (!department.getIsDeleted()) {
                    System.out.println("--------------------------------");
                    System.out.printf(" %-5s | %-20s %n", department.getDepartmentId(), department.getDepartmentName());
                }
            }
             System.out.print("Enter Department ID : ");        
             int employeeDepartmentId = scanner.nextInt();
             scanner.nextLine();
             LocalDate dateOfBirth = Validator.dateConverter(employeeDateOfBirth);
             System.out.print("Enter Laptop Model ID : ");
             String laptopModel = scanner.nextLine();
             Laptop laptop = new Laptop(laptopModel);
             Employee employee = employeeService.createEmployee(employeeName, dateOfBirth, employeeDepartmentId, laptop);
             Period age = Validator.calculateDateOfBirth(employee.getAge());
             String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
             logger.info("Employee Added");
             System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s |%n" , " ID ", " NAME ", " AGE ", " DEPARTMENT ", "LAPTOP MODEL");
             System.out.println("----------------------------------------------------------------------------------");
             System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s |%n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.getLaptop().getModel());    
         }        
    }

    /**
     * Delete the employee by its id
     * @throws MyException
     */
    public void deleteEmployee() throws MyException {
        System.out.print("Enter ID :");
        int id = 0;
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
             throw new MyException("Invalid Input : ", e);
        }
        if (employeeService.deleteEmployee(id)) {
            logger.info("Employee Removed");
        } else {
            logger.info("Employee Not Found");
        }
    }

    /**
     * Get the all employee details by its id, departmentwise 
     * @throws MyException
     */
    public void viewEmployee() throws MyException {
        System.out.println(" (1) Search ID " + '\n' 
                         + " (2) View All " + '\n'
                         + " (3) Department List");
        System.out.print("Choose number : ");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            throw new MyException("Invalid Input : ", e);
        }
        if (option == 1) {
            System.out.print("Enter Employee ID : ");
            int id = 0;
            try {
                id = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                throw new MyException("Invalid Input : ", e);
            }
            Employee employee = employeeService.retrieveEmployeeId(id);
            if (employee != null) {
                Period age = Validator.calculateDateOfBirth(employee.getAge());
                String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
                System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n" , " ID ", " NAME ", " AGE ", "DEPARTMENT", "PROJECT", "LAPTOP MODEL");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects(), employee.getLaptop().getModel());
            } else {
                logger.info("Employee Id not Found");
            }
        } else if (option == 2) {
            List<Employee> employees = employeeService.retrieveEmployees();
            if (employees.isEmpty()) {
                logger.info("NO DATA AVAILABLE");
            } else { 
                System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n" , " ID ", " NAME ", " AGE ", "DEPARTMENT", "PROJECT", "LAPTOP MODEL");
                for (Employee employee : employees) {
                    if (!employee.getIsDeleted()) {
                        Period age = Validator.calculateDateOfBirth(employee.getAge());
                        String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects(), employee.getLaptop().getModel());
                    }
                }
            }
        } else if (option == 3) {  
            System.out.print("Enter Department ID : ");
            int departmentId = 0;
            try {
                departmentId = scanner.nextInt();
            } catch (Exception e) {
                throw new MyException("Invalid Input : ", e);
            }
            List<Employee> employees = employeeService.retrieveEmployeeByDepartment(departmentId);
            if (employees.isEmpty()) {
                logger.info("No Employees Found");
            }else {
                System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n", " ID ", " NAME ", " AGE ", "DEPARTMENT", "PROJECT", "LAPTOP MODEL");
                for (Employee employee : employees) {
                    if (!employee.getIsDeleted()) {
                        Period age = Validator.calculateDateOfBirth(employee.getAge());
                        String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects(), employee.getLaptop().getModel());
                    }
                }
            }
        } else {
            logger.info("Choose 1 or 2 or 3");
        }
    } 

    /**
     * Update employee details by id
     * @throws MyException
     */
    public void updateEmployee() throws MyException {
        boolean isValidId = false;
        int id = 0;
        while (!isValidId) {
        System.out.print("Enter Employee ID NO : ");
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                throw new MyException("Invalid Input : ", e);
            }
            Employee employee = employeeService.retrieveEmployeeId(id);
            Period age = Validator.calculateDateOfBirth(employee.getAge());
            String currentAge = age.getYears() + "Y" + age.getMonths() + "M";
            System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n", " ID ", " NAME ", " AGE ", "DEPARTMENT", "PROJECT", "LAPTOP MODEL");
            System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects(), employee.getLaptop().getModel());
            if (employee == null && !employee.getIsDeleted()) {
                logger.info("employee not Available at : " + id);
                isValidId = true;
            } else {
                System.out.println(" What you need to change" + '\n'
    	                        + " (1) Change Name" + '\n'
    	                        + " (2) Change Date Of Birth" + '\n'
    	                        + " (3) Change Department" + '\n'
    	                        + " (4) Change Laptop" + '\n'
    	                        + " (5) Back" + '\n'
    	                        + "_______________________________________");
    	        System.out.print("Enter the Number : ");                
    	        int option = 0;
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
    	        } catch (InputMismatchException e) {
       	            throw new MyException("Invalid Input : ", e);
                }
                if (option == 1 ) {
                    System.out.print("Enter Name : ");
                    String employeeName = scanner.nextLine();
                    boolean validName = Validator.isValidName(employeeName);
                    while (!validName) {
                        System.out.print("Enter a Valid Name : ");
                        employeeName = scanner.nextLine();
                        validName = Validator.isValidName(employeeName);
                    }
                    employee.setName(employeeName);
                    employeeService.updateEmployee(employee);
                    logger.info("Employee name Updated");
                    System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects());
                } else if (option == 2) {
                    boolean validAge = false;
                    System.out.print("Enter Date of Birth (dd/mm/yyyy) : ");
                    String employeeDateOfBirth = scanner.nextLine();
                    while (!validAge) {
                        try {
                            age = Validator.calculateDateOfBirth(employeeDateOfBirth);
                            System.out.println("AGE : " + age);
                            if (18 <= age.getYears() && age.getYears() <= 60) {
                                validAge = true;
                                currentAge = age.getYears() + "Y " + age.getMonths() + "M";
                            } else {
                                System.out.println("AGE INVALID");
                                employeeDateOfBirth = scanner.nextLine();
                                age = Validator.calculateDateOfBirth(employeeDateOfBirth);
                            }
	                } catch (Exception e) {
	                    System.out.println(e.getMessage());
                        }     
                    }
                    LocalDate dateOfBirth = Validator.dateConverter(employeeDateOfBirth);
                    System.out.println();
                    employee.setAge(dateOfBirth);
                    employeeService.updateEmployee(employee);
                    logger.info("Employee Age Updated");
                    System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects());
                } else if (option == 3) {
                    List<Department> departments = employeeService.retrieveDepartments();
                    System.out.printf("%-5s | %-20s %n", " Department ID ", " Department Name ");
                    for (Department department : departments)  {
                        if (!department.getIsDeleted()) {
                            System.out.println("--------------------------------");
                            System.out.printf(" %-5s | %-20s %n", department.getDepartmentId(), department.getDepartmentName()); 
                        }
                    }   
                    System.out.print("Enter Department ID : "); 
                    int departmentId = 0;
                    try {   
                        departmentId = scanner.nextInt();
                    } catch (Exception e) {
                        throw new MyException("Invalid Input : ", e);
                    }
                    Department department = employeeService.retrieveDepartment(departmentId);
                    if (department != null && !department.getIsDeleted()) {
                        employee.setDepartment(department);
                        employeeService.updateEmployee(employee);
                        logger.info("Department changed");
                        System.out.printf("| %-5s | %-20s | %-8s | %-20s | %-20s %n", employee.getId(), employee.getName(), currentAge, employee.getDepartment().getDepartmentName(), employee.displayProjects());
                    } else {
                        logger.info(" Department Not Found " + '\n' + " Enter Correct ID ");
                    }
                } else if (option == 4) {
                    System.out.print("Enter new Laptop Model : ");
                    String laptopModel = scanner.nextLine();
                    Laptop laptop = new Laptop(laptopModel);
                    employee.setLaptop(laptop);
                    employeeService.updateEmployee(employee);
                    logger.info("Employee Laptop Model Changed");
                } else if (option == 5) {
                    isValidId = true;
                }           
            isValidId = true;
            }
        }    
    }

    /**
     * Show Menu for Employee CRUD Operation. 
     */
    public void employeeManagementMenu() {
        EmployeeController employeeController = new EmployeeController();
	boolean flag = false;
	while (!flag) {
	    System.out.println(" (1) Add Employee Details" + '\n' 
		               + " (2) Remove Employee Details" + '\n' 
			       + " (3) View Employee Details" + '\n'
			       + " (4) Edit Employee Deatils" + '\n'
			       + " (5) Back" + '\n'
			       + "************************************"); 
            System.out.print("Enter the Number : "); 
            int option = 0;
	    try {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    employeeController.createEmployee();
                    flag = false;
	        } else if (option == 2) {
	            employeeController.deleteEmployee();	
	            flag = false;
	        } else if (option == 3) {
	            employeeController.viewEmployee();
	            flag = false;
	        } else if (option == 4) {
	            employeeController.updateEmployee();
	            flag = false;
	        } else if (option == 5) {
	            flag = true;
                    break;
                } else {
	            System.out.println(" Choose 1 - 5 Only "); 
	            flag = false;
                }
	    } catch (InputMismatchException e) {
		logger.warn("Invalid Input Choose Number");
                employeeController.employeeManagementMenu();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
	}
    } 
}