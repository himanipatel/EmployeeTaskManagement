package org.npu.etms.services;

import java.util.List;

import org.npu.etms.domain.Employee;

public interface EmployeeService {
	public Employee findEmployeeById(int employeeId);

	public int insertEmployee(Employee newEmployee);

	public int removeEmployeeById(int employeeId);

	public int updateEmployeeById(Employee employee);
	
	public List<Employee> findEmployeeByName(String firstName,String lastName);

	public List<Employee> findAllEmployees();
}
