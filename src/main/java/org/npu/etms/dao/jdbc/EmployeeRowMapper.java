package org.npu.etms.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.npu.etms.domain.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("employeeRowMapper")
public class EmployeeRowMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet resultSet, int row) throws SQLException {

		Employee employee = new Employee();
		employee.setEmployeeId(resultSet.getInt("employeeid"));
		employee.setFirstName(resultSet.getString("firstname"));
		employee.setLastName(resultSet.getString("lastname"));
		employee.setMiddleName(resultSet.getString("middlename"));
		employee.setEmail(resultSet.getString("email"));
		employee.setBirthDate(resultSet.getDate("birthdate"));
		employee.setJoiningDate(resultSet.getDate("JoiningDate"));
		employee.setContactNo(resultSet.getString("ContactNo"));
		employee.setCurrentAddress(resultSet.getString("CurrentAddress"));
		employee.setDepartment(resultSet.getString("department"));
		employee.setDesignation(resultSet.getString("designation"));
		employee.setCreatedDate(resultSet.getDate("createddate"));
		employee.setModifiedDate(resultSet.getDate("modifieddate"));
		return employee;
	}

}
