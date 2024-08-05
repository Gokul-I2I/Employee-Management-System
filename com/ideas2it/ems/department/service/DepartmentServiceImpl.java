package com.ideas2it.ems.department.service;

import java.util.List;
import com.ideas2it.ems.department.dao.DepartmentDao;
import com.ideas2it.ems.department.dao.DepartmentDaoImpl;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * This class handle the all operation related to Department CRUD based on user request
 * @author Gokul
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public Department createDepartment(String departmentName) throws MyException {
        return departmentDao.createDepartment(new Department(departmentName));
    } 

    @Override
    public boolean isDepartment() throws MyException {
        return departmentDao.isDepartment();
    }

    @Override
    public boolean isDepartmentIdPresent(int departmentId) throws MyException {
        return departmentDao.isDepartmentIdPresent(departmentId);    
    }
    
    @Override
    public Department retrieveDepartment(int departmentId) throws MyException {
        return departmentDao.retrieveDepartment(departmentId);   
    }

    @Override
    public boolean deleteDepartment(int id) throws MyException {
        return departmentDao.deleteDepartment(id);
    }  

    @Override
    public List<Department> retrieveDepartments() throws MyException {
        return departmentDao.retrieveDepartments();
    }

    @Override
    public void updateDepartmentName(String departmentName, int departmentId) throws MyException {
        departmentDao.updateDepartmentName(departmentName, departmentId);
    }
}