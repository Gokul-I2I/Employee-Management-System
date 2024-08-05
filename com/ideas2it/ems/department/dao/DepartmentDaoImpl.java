package com.ideas2it.ems.department.dao;
	
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.query.Query;
import org.hibernate.HibernateException; 

import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.department.dao.DepartmentDao;
import com.ideas2it.ems.helper.HibernateConnection;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * This class handle the all operation related to Department database
 * @author Gokul
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public Department createDepartment(Department department) throws MyException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction(); 
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in department adding in DataBase " + department.getDepartmentId() + " " + department.getDepartmentName(), e);
        }
        return department;
    }

    @Override
    public boolean isDepartment() throws MyException {
        return true;
    }

    @Override
    public boolean isDepartmentIdPresent(int departmentId) throws MyException {
        boolean flag = false;
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            if (!department.getIsDeleted()) {
                flag = true;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in department id in present or not" + departmentId, e);
        }
        return flag;
    }

    @Override
    public Department retrieveDepartment(int departmentId) throws MyException {
        Department department = null;
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction(); 
            department = session.get(Department.class, departmentId);
            if (department.getIsDeleted()) {
            department = null;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in get department details in database by its Id : " + departmentId, e);
        }
        return department;
    }   

    @Override
    public boolean deleteDepartment(int id) throws MyException {
        Transaction transaction = null;
        boolean flag = false;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction(); 
            Department department = session.get(Department.class, id);
            if (!department.getIsDeleted()) {
                department.setIsDeleted(true);
                flag = true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in removing department in DataBase " + id, e);
        }
        return flag;
    } 
                                              
    /**
     * View all departments
     *
     * @return List<Department> : all departments details
     */
    @Override
    public List<Department> retrieveDepartments() throws MyException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            return session.createQuery("FROM Department", Department.class).list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in View All Department in DataBase ", e);
        }
    }

    @Override
    public void updateDepartmentName(String departmentName, int departmentId) throws MyException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            department.setDepartmentName(departmentName);
            session.update(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in update Department Name in DataBase " + departmentName + departmentId, e);
        }
    }
}