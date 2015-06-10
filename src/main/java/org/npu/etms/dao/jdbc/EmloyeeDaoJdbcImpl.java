package org.npu.etms.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.npu.etms.dao.EmployeeDAO;
import org.npu.etms.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("employeeDaoJdbc")
public class EmloyeeDaoJdbcImpl implements EmployeeDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@Autowired
	@Qualifier("employeeRowMapper")
	private EmployeeRowMapper empRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("employee")
				.usingGeneratedKeyColumns("employeeid")
				.usingColumns("firstname", "lastname", "middlename", "email",
						"birthdate", "joiningdate", "contactno",
						"currentaddress", "department", "designation",
						"createddate");
	}

	@Override
	public int getEmployeeCount() {
		String sql = "select count(*) FROM Employee";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<Employee> findAllEmployees() {
		String sql = "SELECT * FROM Employee";
		List<Employee> employeeList = jdbcTemplate.query(sql, empRowMapper);
		return employeeList;
	}

	@Override
	public List<Employee> findEmployeeById(int employeeId) {
		String sql = "SELECT * FROM employee "
				+ "WHERE employeeId = :employeeId";
		MapSqlParameterSource params = new MapSqlParameterSource("employeeId",
				employeeId);
		List<Employee> employee = namedTemplate
				.query(sql, params, empRowMapper);
		return employee;
	}

	@Override
	public int insertEmployee(Employee newEmployee) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(
				newEmployee);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		int employeeId = newId.intValue();
		newEmployee.setEmployeeId(employeeId);
		return employeeId;
	}

	@Override
	public int updateEmployeeById(Employee employee) {
		String queryStr = "UPDATE employee set firstname = :firstname,lastname = :lastname ,middlename = :middlename ,email = :email,birthdate = :birthdate,joiningdate = :joiningdate,"
				+ "contactno = :contactno ,currentaddress = :currentaddress,department = :department,designation = :designation, modifieddate = :modifieddate where employeeId = :employeeId";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("employeeId",
				employee.getEmployeeId());
		params.addValue("firstname", employee.getFirstName());
		params.addValue("lastname", employee.getLastName());
		params.addValue("middlename", employee.getMiddleName());
		params.addValue("email", employee.getEmail());
		params.addValue("birthdate", employee.getBirthDate());
		params.addValue("joiningdate", employee.getJoiningDate());
		params.addValue("contactno", employee.getContactNo());
		params.addValue("currentaddress", employee.getCurrentAddress());
		params.addValue("department", employee.getDepartment());
		params.addValue("designation", employee.getDesignation());
		params.addValue("modifieddate", employee.getModifiedDate());
		rowsAffected = namedTemplate.update(queryStr, params);
		return rowsAffected;
	}

	@Override
	public int removeEmployeeById(int employeeId) {
		String queryStr = "DELETE FROM employee WHERE employeeId = :employeeId";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("employeeId",
				employeeId);
		rowsAffected = namedTemplate.update(queryStr, params);
		return rowsAffected;
	}

	@Override
	public List<Employee> findEmployeeByName(String firstName, String lastName) {
		String sql = "SELECT * FROM employee WHERE firstName like :firstName or lastname like :lastName";
		firstName = "%" + firstName + "%";
		lastName = "%" + lastName + "%";
		MapSqlParameterSource params = new MapSqlParameterSource("firstName",
				firstName);
		params.addValue("lastName", lastName);
		List<Employee> employeeList = namedTemplate.query(sql, params,
				empRowMapper);
		return employeeList;

	}

	@Override
	public List<Employee> findEmployeeByEmail(String email,
			boolean checkNotForSameEmp, int employeeId) {
		String sql = "";
		MapSqlParameterSource params;
		if (checkNotForSameEmp) {
			sql = "SELECT * FROM employee "
					+ "WHERE email = :email and employeeId <> :employeeId";
			params = new MapSqlParameterSource("email", email);
			params.addValue("employeeId", employeeId);
		} else {
			sql = "SELECT * FROM employee " + "WHERE email = :email";
			params = new MapSqlParameterSource("email", email);
		}
		List<Employee> employee = namedTemplate
				.query(sql, params, empRowMapper);
		return employee;
	}
}