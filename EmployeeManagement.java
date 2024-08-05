import java.util.ArrayList;
import java.util.InputMismatchException; 
import java.util.Scanner;

import com.ideas2it.ems.department.controller.DepartmentController;
import com.ideas2it.ems.employee.controller.EmployeeController;
import com.ideas2it.ems.project.controller.ProjectController;

/**
 * This class handle the all operation related to Employee Management based on user request
 * @author Gokul
 */
public class EmployeeManagement { 
    EmployeeController employeeController = new EmployeeController();
    DepartmentController departmentController = new DepartmentController();
    ProjectController projectController = new ProjectController();
    Scanner scanner = new Scanner(System.in);
    
    /**
     * This main method is used to call the controllers which is used to handle all operation  
     * related to employee and department and project based on user request.
     */
    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
	employeeManagement.showMenu();	
    }

    /**
     * This method is used to call all controller which is used to handle all operation  
     */
    public void showMenu() {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        boolean flag = false;
        while (!flag) {
            System.out.print(" (1) Employee Operation" + '\n' 
                           + " (2) Department Operation " + '\n'
                           + " (3) Project Operation"+ '\n'
                           + " (4) Exit " + '\n'
                           + "Choose Number : ");
            int option = 0;
            try {
                option = scanner.nextInt(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input : ");
 	        employeeManagement.showMenu();     
            }
            if (option == 1) {
                employeeController.employeeManagementMenu();                 
            } else if (option == 2) {
                departmentController.departmentManagementMenu();
            } else if (option == 3) {
                projectController.projectManagementMenu();
            } else if (option == 4){
                System.out.println("CLOSED");
                flag = true;
            } else {
                System.out.println("Choose 1 - 4 only");
            }
        }
    }
}           
