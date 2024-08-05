package com.ideas2it.ems.employee.dao;

import java.util.List;

import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.HibernateException;

import com.ideas2it.ems.helper.HibernateConnection;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * This class handle the employee storing operation related based on user request
 * @author Gokul
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee createEmployee(Employee employee) throws MyException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in Employee adding in DataBase ", e);
        }
        return employee;
    }
  
    @Override
    public boolean deleteEmployee(int id) throws MyException {
        boolean flag = false;
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (!employee.getIsDeleted()) {
                employee.setIsDeleted(true);
                flag = true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in department id in present or not" + id, e);
        }
        return flag;
    }

    @Override
    public Employee retrieveEmployeeId(int id) throws MyException {
        Employee employee = null;
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction(); 
            employee = session.get(Employee.class, id);
            if (employee == null || employee.getIsDeleted()) {
            employee = null;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in department id in db present or not" + id, e);
        }
        return employee;
    }

    @Override
    public List<Employee> retrieveEmployees() throws MyException  {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            return session.createQuery("FROM Employee WHERE employee_is_deleted = false", Employee.class).list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in View All Department in DataBase ", e);
        }
    }

    @Override
    public List<Employee> retrieveEmployeeByDepartment(int departmentId) throws MyException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            return session.createQuery("FROM Employee WHERE department_id  = " + departmentId, Employee.class).list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in view employees form departmentwise" + departmentId, e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws MyException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.connection()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("Error in update employee in DataBase " + employee.getName() + employee.getId(), e);
        }        
    }
}