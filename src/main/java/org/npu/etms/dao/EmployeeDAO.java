package org.npu.etms.dao;

import java.util.List;

import org.npu.etms.domain.Employee;

public interface EmployeeDAO {

	public List<Employee> findAllEmployees();

	public List<Employee> findEmployeeById(int employeeId);

	public int insertEmployee(Employee newEmployee);

	public int updateEmployeeById(Employee employee);

	public int removeEmployeeById(int employeeId);

	public List<Employee> findEmployeeByName(String firstName,String lastName);

	public int getEmployeeCount();

	public List<Employee> findEmployeeByEmail(String email,
			boolean checkNotForSameEmp, int employeeId);
}
