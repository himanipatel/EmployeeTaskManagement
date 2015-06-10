package org.npu.etms.services;

import java.util.List;

import org.npu.etms.dao.EmployeeDAO;
import org.npu.etms.domain.Employee;
import org.npu.etms.exceptions.EmployeeUniqueEmailException;
import org.npu.etms.exceptions.UnknownEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier("employeeDaoJdbc")
	private EmployeeDAO employeeDao;

	@Transactional(readOnly = false)
	@Override
	public Employee findEmployeeById(int employeeId) {
		List<Employee> emp = employeeDao.findEmployeeById(employeeId);
		if (emp != null && emp.size() > 0) {
			return emp.get(0);
		} else {
			throw new UnknownEmployeeException(
					"No Employee found with employee id" + employeeId);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public int insertEmployee(Employee newEmployee) {
		List<Employee> emp = employeeDao.findEmployeeByEmail(
				newEmployee.getEmail(), false, 0);
		if (emp != null && emp.size() > 0) {
			throw new EmployeeUniqueEmailException("Email id already exist.");
		} else {
			int employeeId = employeeDao.insertEmployee(newEmployee);
			return employeeId;
		}
	}

	@Transactional
	@Override
	public int removeEmployeeById(int employeeId) {
		int empRemoved = 0;
		empRemoved = employeeDao.removeEmployeeById(employeeId);
		return empRemoved;
	}

	@Transactional
	@Override
	public int updateEmployeeById(Employee employee) {
		int empUpdated = 0;
		List<Employee> emp = employeeDao.findEmployeeByEmail(
				employee.getEmail(), true, employee.getEmployeeId());
		if (emp != null && emp.size() > 0) {
			throw new EmployeeUniqueEmailException("Email id already exist.");
		} else {
			empUpdated = employeeDao.updateEmployeeById(employee);
			return empUpdated;
		}
	}

	@Transactional(readOnly = false)
	@Override
	public List<Employee> findEmployeeByName(String firstName, String lastName) {
		List<Employee> emp = employeeDao
				.findEmployeeByName(firstName, lastName);
		return emp;
	}

	@Transactional(readOnly = false)
	@Override
	public List<Employee> findAllEmployees() {
		List<Employee> emp = employeeDao
				.findAllEmployees();
		return emp;

	}
}